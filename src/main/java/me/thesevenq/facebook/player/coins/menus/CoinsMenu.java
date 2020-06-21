package me.thesevenq.facebook.player.coins.menus;

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
public class CoinsMenu extends Menu {

    {
        setAutoUpdate(true);
    }

    private final PlayerData data;

    @Override
    public String getTitle(Player player) {
        return CC.PRIMARY + "Coins Menu";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(44, new CloseButton());

        surroundButtons(true, buttons, new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").durability(7).build());

        buttons.put(4, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.NETHER_STAR);

                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(CC.SECONDARY + "Player&7: " + CC.PRIMARY + data.getName());
                lore.add(CC.SECONDARY + "Current balance&7: " + CC.PRIMARY + data.getCoins());
                lore.add("");
                lore.add(CC.GRAY + "Click one of the items");
                lore.add(CC.GRAY + "to manage player's coins.");

                return item.name(CC.PRIMARY + "Information").lore(Color.translate(lore)).build();
            }
        });

        buttons.put(20, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.GOLD_NUGGET);

                List<String> lore = new ArrayList<>();

                lore.add("");
                lore.add(CC.SECONDARY + "Manage with user's coins.");
                lore.add(CC.SECONDARY + "Add limit is 1,000,000");
                lore.add("");
                lore.add(CC.GRAY + "Click here to open submenu.");

                return item.name(CC.GREEN + "Add Coins").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                new CoinsAddMenu(data).openMenu(player);
            }
        });

        buttons.put(22, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.QUARTZ);

                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(CC.SECONDARY + "Pressing this item will");
                lore.add(CC.SECONDARY + "reset coins balance of");
                lore.add(CC.SECONDARY + "user named " + data.getName());
                lore.add("");
                lore.add(CC.SECONDARY + "Current balance&7: " + CC.PRIMARY + data.getCoins());
                lore.add("");
                lore.add(CC.GRAY + "Click here to reset.");

                return item.name(CC.YELLOW + "Reset Coins").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                data.setCoins(0);
                playSound(player, ButtonSound.SUCCESS);
                player.sendMessage(CC.SECONDARY + "Sucessfully rested balance of " + CC.PRIMARY + data.getName() + CC.SECONDARY + ".");
            }
        });

        buttons.put(24, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.REDSTONE);

                List<String> lore = new ArrayList<>();

                lore.add("");
                lore.add(CC.SECONDARY + "Manage with user's coins.");
                lore.add(CC.SECONDARY + "Remove limit is 1,000,000");
                lore.add("");
                lore.add(CC.GRAY + "Click here to open submenu.");

                return item.name(CC.RED + "Remove Coins").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.closeInventory();
            }
        });


        return buttons;
    }


    @Override
    public int getSize() {
        return 9 * 5;
    }
}
