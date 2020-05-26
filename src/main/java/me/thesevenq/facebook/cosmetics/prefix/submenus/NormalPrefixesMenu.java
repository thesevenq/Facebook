package me.thesevenq.facebook.cosmetics.prefix.submenus;

import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.cosmetics.CosmeticsMenu;
import me.thesevenq.facebook.cosmetics.prefix.types.NormalPrefixType;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.ranks.Rank;
import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.Color;
import me.thesevenq.facebook.utils.ItemBuilder;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.ButtonSound;
import me.thesevenq.facebook.utils.menu.pagination.PaginatedMenu;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class NormalPrefixesMenu extends PaginatedMenu {

    private final PlayerData data;
    private NormalPrefixType prefix;

    @Override
    public String getPrePaginatedTitle(Player player) {
        return CC.PRIMARY + "Cosmetics -> Prefixes";
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        Stream.of(NormalPrefixType.values()).forEach(prefixType -> {
            buttons.put(buttons.size(), new Button() {
                @Override
                public ItemStack getButtonItem(Player player) {
                    ItemStack dye = new ItemStack(Material.INK_SACK);
                    ItemBuilder item = new ItemBuilder(dye).durability(10);
                    List<String> lore = new ArrayList<>();
                    lore.add(CC.GRAY + CC.STRIKE_THROUGH + "-----------------------------------");
                    lore.add(CC.B_PRIMARY + "Chat Preview&7:");
                    lore.add(prefixType.getStyle() + data.getRank().getPrefix() + data.getRank().getColor() + player.getName());
                    lore.add("");
                    lore.add(CC.GREEN + "Click here to equip this prefix.");
                    lore.add(CC.GRAY + CC.STRIKE_THROUGH + "-----------------------------------");

                    return item.name(CC.B_PRIMARY + prefixType.getName()).lore(Color.translate(lore)).build();
                }

                @Override
                public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                    if(data.getRank() != Rank.DEFAULT) {
                        data.setPrefix(prefixType);
                        player.sendMessage(CC.PRIMARY + data.getPrefix().getName() + CC.SECONDARY + " is now set as your prefix.");
                        playSound(player, ButtonSound.SUCCESS);
                        player.closeInventory();
                    } else {
                        playSound(player, ButtonSound.FAIL);
                        player.sendMessage(CC.RED + "You don't own any prefix. Buy it at our store " + CC.B_RED + Facebook.getInstance().getConfig().getString("SERVER_STORE"));
                    }
                }
            });
        });

        buttons.put(16, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add("&7&m------------------------------------------------");
                lore.add(CC.RED + "Click here to clear your prefix.");
                lore.add("&7&m------------------------------------------------");
                return new ItemBuilder(Material.LEVER).lore(Color.translate(lore)).name(CC.D_RED + "Clear").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                data.setPrefix(null);
                player.sendMessage(CC.SECONDARY + "You prefix has been removed.");
                World world = player.getWorld();
                world.playSound(player.getLocation(), Sound.ANVIL_LAND, 10, 1);
                player.closeInventory();
            }
        });

        buttons.put(17, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                ItemStack dye = new ItemStack(Material.INK_SACK);
                ItemBuilder item = new ItemBuilder(dye).durability(10);
                lore.add("&7&m------------------------------------------------");
                lore.add(CC.RED + "Click here to go to previous menu.");
                lore.add("&7&m------------------------------------------------");
                return new ItemBuilder(Material.ARROW).lore(Color.translate(lore)).name(CC.D_RED + "Back").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                World world = player.getWorld();
                world.playSound(player.getLocation(), Sound.WOOD_CLICK, 10, 1);

                new CosmeticsMenu().openMenu(player);
            }
        });

        return buttons;
    }
}
