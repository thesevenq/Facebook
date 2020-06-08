package me.thesevenq.facebook.cosmetics.prefix;

import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.cosmetics.prefix.submenus.NormalPrefixesMenu;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.ranks.Rank;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.ItemBuilder;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class PrefixMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return CC.PRIMARY + "Cosmetics -> Prefixes";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        PlayerData data = PlayerData.getByName(player.getName());
        buttons.put(13, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.NAME_TAG);

                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "-----------------------------------");
                if (data.getRank() != Rank.DEFAULT) {
                    lore.add(CC.GRAY + "Here you can select");
                    lore.add(CC.GRAY + "normal chat prefix to");
                    lore.add(CC.GRAY + "to outstand from other players.");
                    lore.add("");
                    lore.add(CC.GREEN + "Click here to browse normal prefixes.");
                } else {
                    lore.add(CC.RED + "You don't own any prefixes!");
                    lore.add("");
                    lore.add(CC.GRAY + "Buy rank on our store");
                    lore.add(CC.GRAY + "to gain access to prefixes.");
                    lore.add("");
                    lore.add(CC.B_PRIMARY + "Store&7:");
                    lore.add(CC.SECONDARY + " " + Facebook.getInstance().getConfig().getString("SERVER_STORE"));
                }

                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "-----------------------------------");


                return item.name(CC.B_PRIMARY + "Normal Prefixes").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                if (data.getRank() != Rank.DEFAULT) {
                    new NormalPrefixesMenu(data).openMenu(player);
                } else {
                    player.sendMessage(CC.RED + "You don't own any normal prefixes. Buy them on our store " + CC.B_RED + Facebook.getInstance().getConfig().getString("SERVER_STORE"));
                    player.closeInventory();
                }
            }
        });

        buttons.put(14, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.NAME_TAG);

                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "-----------------------------------");
                if (data.getRank() != Rank.DEFAULT) {
                    lore.add(CC.GRAY + "Here you can select");
                    lore.add(CC.GRAY + "special chat prefix to");
                    lore.add(CC.GRAY + "to outstand from other players.");
                    lore.add("");
                    lore.add(CC.GREEN + "Click here to browse special prefixes.");
                } else {
                    lore.add(CC.RED + "You don't own any prefixes!");
                    lore.add("");
                    lore.add(CC.GRAY + "Buy rank on our store");
                    lore.add(CC.GRAY + "to gain access to prefixes.");
                    lore.add("");
                    lore.add(CC.B_PRIMARY + "Store&7:");
                    lore.add(CC.SECONDARY + " " + Facebook.getInstance().getConfig().getString("SERVER_STORE"));
                }

                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "-----------------------------------");


                return item.name(CC.B_PRIMARY + "Special Prefixes").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {

                    player.sendMessage(CC.RED + "You don't own any special prefixes. Buy them on our store " + CC.B_RED + Facebook.getInstance().getConfig().getString("SERVER_STORE"));
                    player.closeInventory();

            }
        });

        return buttons;
    }

    @Override
    public int getSize() {
        return 9*4;
    }
}
