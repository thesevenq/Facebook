package me.thesevenq.facebook.player.grant.menus;

import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.Color;
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
public class GrantDurationMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return CC.PRIMARY + "[D] " + player.getName();
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        buttons.put(0, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add("&7&m------------------------------------------------");
                lore.add(CC.SECONDARY + "Click here to set grant");
                lore.add(CC.SECONDARY + "grant duration to " + CC.RED + "permanent" + CC.SECONDARY + ".");
                lore.add("&7&m------------------------------------------------");
                return new ItemBuilder(Material.INK_SACK).durability(1).lore(Color.translate(lore)).name(CC.B_RED + "Permanent").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.chat("perm");
                player.closeInventory();
            }
        });

        buttons.put(1, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add("&7&m------------------------------------------------");
                lore.add(CC.SECONDARY + "Click here to set grant");
                lore.add(CC.SECONDARY + "grant duration to " + CC.GREEN + "1 month" + CC.SECONDARY + ".");
                lore.add("&7&m------------------------------------------------");
                return new ItemBuilder(Material.INK_SACK).durability(10).lore(Color.translate(lore)).name(CC.B_GREEN + "1 Month").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.chat("30d");
                player.closeInventory();

            }
        });

        buttons.put(2, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add("&7&m------------------------------------------------");
                lore.add(CC.SECONDARY + "Click here to set grant");
                lore.add(CC.SECONDARY + "grant duration to " + CC.YELLOW + "1 week" + CC.SECONDARY + ".");
                lore.add("&7&m------------------------------------------------");
                return new ItemBuilder(Material.INK_SACK).durability(11).lore(Color.translate(lore)).name(CC.B_YELLOW + "1 Week").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.chat("1w");
                player.closeInventory();
            }
        });

        buttons.put(3, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add("&7&m------------------------------------------------");
                lore.add(CC.SECONDARY + "Click here to set grant");
                lore.add(CC.SECONDARY + "grant duration to " + CC.PINK + "30 minutes" + CC.SECONDARY + ".");
                lore.add("&7&m------------------------------------------------");
                return new ItemBuilder(Material.INK_SACK).durability(5).lore(Color.translate(lore)).name(CC.BL_PURPLE + "30 minutes.").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.chat("30m");
                player.closeInventory();
            }
        });

        buttons.put(7, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add("&7&m------------------------------------------------");
                lore.add(CC.SECONDARY + "Click here to set grant");
                lore.add(CC.SECONDARY + "grant duration to " + CC.AQUA + "custom" + CC.SECONDARY + ".");
                lore.add("&7&m------------------------------------------------");
                return new ItemBuilder(Material.BOOK_AND_QUILL).lore(Color.translate(lore)).name(CC.AQUA + "Custom").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.closeInventory();
                player.sendMessage(Color.translate("&7&m-------------------------------------------------"));
                player.sendMessage(CC.SECONDARY + "Please type " + CC.PRIMARY + "duration " + CC.SECONDARY + "for this rank.");
                player.sendMessage(CC.SECONDARY + "Example: " + CC.GRAY + "(1y, 1m, 1w, 1d, 1h, 1m, 1s)");
                player.sendMessage(Color.translate("&7&m-------------------------------------------------"));
            }
        });

        buttons.put(8, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add("&7&m------------------------------------------------");
                lore.add(CC.SECONDARY + "Click here to cancel this grant,");
                lore.add("&7&m------------------------------------------------");
                return new ItemBuilder(Material.REDSTONE).lore(Color.translate(lore)).name(CC.BD_RED + "Cancel").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.chat("cancel");
                player.closeInventory();
            }
        });

        return buttons;
    }
}
