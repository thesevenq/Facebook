package me.thesevenq.facebook.player.info.menus;

import com.sun.org.apache.regexp.internal.RE;
import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.menu.ButtonSound;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.ItemBuilder;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.Menu;
import me.thesevenq.facebook.utils.menu.buttons.CloseButton;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class UserViewMenu extends Menu {

    {
        setAutoUpdate(true);
    }

    private final PlayerData data;

    @Override
    public String getTitle(Player player) {
        return CC.PRIMARY + "User Menu -> " + data.getName();
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(26, new CloseButton());

        surroundButtons(true, buttons, new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").durability(7).build());

        buttons.put(4, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.NETHER_STAR);

                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(CC.SECONDARY + "Player&7: " + CC.PRIMARY + data.getName());
                lore.add("");

                return item.name(CC.PRIMARY + "Information").lore(Color.translate(lore)).build();
            }
        });

        buttons.put(10, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.QUARTZ);

                List<String> lore = new ArrayList<>();

                lore.add("");
                lore.add(CC.SECONDARY + "Current rank is " + data.getRank().getColor() + data.getRank().getName());
                lore.add("");
                lore.add(CC.PRIMARY + "Information&7: ");
                lore.add(CC.SECONDARY + " Added by&7: " + CC.PRIMARY + data.getGrant().getAddedBy());
                lore.add(CC.SECONDARY + " Reason&7: " + CC.PRIMARY + data.getGrant().getReason());
                lore.add("");
                lore.add(CC.GRAY + "Click here to view more info.");

                return item.name(CC.PRIMARY + "Rank Info").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.performCommand("grants " + data.getName());
                playSound(player, ButtonSound.CLICK);
            }
        });

        buttons.put(11, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.WOOL).durability(14);

                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(CC.SECONDARY + "Active&7: " + CC.GREEN + "No");
                lore.add(CC.SECONDARY + "Number of punishments&7: " + CC.PRIMARY + "5");
                lore.add(CC.SECONDARY + "Last punishment&7: " + CC.PRIMARY + "Permanent Mute");
                lore.add("");
                lore.add(CC.GRAY + "Click here to view more info.");

                return item.name(CC.PRIMARY + "Punishment Info").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                //todo
            }
        });

        buttons.put(12, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.BLAZE_POWDER);

                List<String> lore = new ArrayList<>();

                lore.add("");
                lore.add(CC.SECONDARY + "Prefix&7: " + CC.PRIMARY + data.getPrefix().getName());
                lore.add(CC.SECONDARY + "Color&7: " + data.getColor().getColor() + data.getColor().getName());
                lore.add(CC.SECONDARY + "Booster&7: " + CC.PRIMARY + "None");
                lore.add(CC.SECONDARY + "Death Animation&7: " + CC.PRIMARY + data.getKillEffectType().getName());
                lore.add("");
                lore.add(CC.GRAY + "Click here to view more info.");

                return item.name(CC.PRIMARY + "Cosmetics Info").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
            }
        });

        buttons.put(13, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.EMERALD);

                List<String> lore = new ArrayList<>();

                lore.add("");
                lore.add(CC.SECONDARY + "Balance&7: " + CC.PRIMARY + data.getCoins());
                lore.add("");
                lore.add(CC.GRAY + "Click here to view more info.");

                return item.name(CC.PRIMARY + "Gems Info").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
            }
        });


        return buttons;
    }


    @Override
    public int getSize() {
        return 9 * 3;
    }
}
