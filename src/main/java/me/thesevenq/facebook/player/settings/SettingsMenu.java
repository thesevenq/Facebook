package me.thesevenq.facebook.player.settings;

import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.cosmetics.CosmeticsMenu;
import me.thesevenq.facebook.cosmetics.scoreboard.ScoreboardType;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.Color;
import me.thesevenq.facebook.utils.ItemBuilder;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.Menu;
import me.thesevenq.facebook.utils.menu.buttons.CloseButton;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class SettingsMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return CC.PRIMARY + "Settings";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        surroundButtons(true, buttons, new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").durability(7).build());
        buttons.put(26, new CloseButton());

        PlayerData data = PlayerData.getByName(player.getName());
        buttons.put(10, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.ITEM_FRAME);

                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(CC.GRAY + "Change scoreboard");
                lore.add(CC.GRAY + "format to your likings.");
                lore.add("");
                lore.add(CC.SECONDARY + " Current: " + CC.PRIMARY + data.getScoreboard().getName());
                lore.add("");
                lore.add(CC.GRAY + "Click here to change scoreboard.");

                return item.name(CC.B_PRIMARY + "Scoreboard Format").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                if (data.getScoreboard().getName() == "Normal") {
                    data.setScoreboard(ScoreboardType.OLDSCHOOL);
                    player.sendMessage(CC.PRIMARY + "OldSchool " + CC.SECONDARY + "is now selected as your board design.");
                } else {
                    data.setScoreboard(ScoreboardType.NORMAL);
                    player.sendMessage(CC.PRIMARY + "Normal " + CC.SECONDARY + "is now selected as your board design.");
                }
            }
        });

        buttons.put(12, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.PAPER);

                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(CC.GRAY + "Don't want to receive");
                lore.add(CC.GRAY + "private messages?");
                lore.add("");
                lore.add(CC.GRAY + "Click here to toggle messages.");

                return item.name(CC.B_PRIMARY + "Private Messages").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.performCommand("togglepm");
            }
        });

        buttons.put(14, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.GOLD_NUGGET);

                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(CC.GRAY + "Don't want to receive");
                lore.add(CC.GRAY + "server tips?");
                lore.add("");
                lore.add(CC.GRAY + "Click here to toggle tips.");
                return item.name(CC.B_PRIMARY + "Server Tips").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.performCommand("toggletips");
            }
        });


        return buttons;
    }


    @Override
    public int getSize() {
        return 9 * 3;
    }
}
