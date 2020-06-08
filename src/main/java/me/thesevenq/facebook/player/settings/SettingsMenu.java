package me.thesevenq.facebook.player.settings;

import lombok.RequiredArgsConstructor;
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

        buttons.put(10, new Button() {
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

        buttons.put(12, new Button() {
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
