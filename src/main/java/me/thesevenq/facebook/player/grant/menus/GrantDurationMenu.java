package me.thesevenq.facebook.player.grant.menus;

import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.ranks.procedure.GrantProcedure;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.ItemBuilder;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.Menu;
import me.thesevenq.facebook.utils.menu.buttons.BackButton;
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
        return CC.PRIMARY + "Please select a duration";
    }

    private final GrantProcedure grantProcedure;
    private final PlayerData data;


    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(27, new BackButton(new GrantReasonMenu(grantProcedure, data)));

        surroundButtons(true, buttons, new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").durability(7).build());

        buttons.put(4, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(CC.SECONDARY + "Player&7: " + CC.PRIMARY + grantProcedure.getAddedTo().getName());
                lore.add(CC.SECONDARY + "Rank&7: " + CC.PRIMARY + grantProcedure.getRank().getColor() + grantProcedure.getRank().getName());
                lore.add("");
                lore.add(CC.GRAY + "Click one of the items");
                lore.add(CC.GRAY + "to select grant duration.");
                return new ItemBuilder(Material.NETHER_STAR).lore(Color.translate(lore)).name(CC.PRIMARY + "Grant Info").build();
            }
        });

        buttons.put(10, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + "Click here to set grant");
                lore.add(CC.SECONDARY + "grant duration to " + CC.RED + "permanent" + CC.GRAY + ".");
                return new ItemBuilder(Material.PAPER).lore(Color.translate(lore)).name(CC.B_RED + "Permanent").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.chat("perm");
                new GrantReasonMenu(grantProcedure, data).openMenu(player);
            }
        });

        buttons.put(11, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + "Click here to set grant");
                lore.add(CC.SECONDARY + "grant duration to " + CC.GREEN + "1 month" + CC.GRAY + ".");
                return new ItemBuilder(Material.PAPER).lore(Color.translate(lore)).name(CC.B_GREEN + "1 Month").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.chat("30d");
                new GrantReasonMenu(grantProcedure, data).openMenu(player);

            }
        });

        buttons.put(12, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + "Click here to set grant");
                lore.add(CC.GRAY + "grant duration to " + CC.YELLOW + "1 week" + CC.GRAY + ".");
                return new ItemBuilder(Material.PAPER).lore(Color.translate(lore)).name(CC.B_YELLOW + "1 Week").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.chat("1w");
                new GrantReasonMenu(grantProcedure, data).openMenu(player);
            }
        });

        buttons.put(14, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + "Click here to set grant");
                lore.add(CC.GRAY + "grant duration to " + CC.PINK + "30 minutes" + CC.GRAY + ".");
                return new ItemBuilder(Material.PAPER).lore(Color.translate(lore)).name(CC.BL_PURPLE + "30 minutes.").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.chat("30m");
                new GrantReasonMenu(grantProcedure, data).openMenu(player);
            }
        });

        buttons.put(15, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + "Click here to set grant");
                lore.add(CC.GRAY + "grant duration to " + CC.GOLD + "15 minutes" + CC.GRAY + ".");
                return new ItemBuilder(Material.PAPER).lore(Color.translate(lore)).name(CC.B_GOLD + "15 minutes.").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.chat("15m");
                new GrantReasonMenu(grantProcedure, data).openMenu(player);

            }
        });

        buttons.put(16, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + "Click here to set grant");
                lore.add(CC.GRAY + "grant duration to " + CC.DARK_AQUA + "1 minute" + CC.GRAY + ".");
                return new ItemBuilder(Material.PAPER).lore(Color.translate(lore)).name(CC.BD_AQUA + "1 minute.").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.chat("1m");
                new GrantReasonMenu(grantProcedure, data).openMenu(player);
            }
        });

        buttons.put(22, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + "Click here to set grant");
                lore.add(CC.GRAY + "grant duration to " + CC.AQUA + "custom" + CC.GRAY + ".");
                return new ItemBuilder(Material.BOOK_AND_QUILL).lore(Color.translate(lore)).name(CC.B_AQUA + "Custom").build();
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

        buttons.put(35, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.REDSTONE).name(CC.BD_RED + "Cancel").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.chat("cancel");
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
