package me.thesevenq.facebook.player.grant.menus;

import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.ranks.procedure.GrantProcedure;
import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.Color;
import me.thesevenq.facebook.utils.ItemBuilder;
import me.thesevenq.facebook.utils.TimeFormatUtils;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.Menu;
import me.thesevenq.facebook.utils.menu.buttons.BackButton;
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
public class GrantReasonMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return CC.PRIMARY + "Please select a reason";
    }

    private final GrantProcedure grantProcedure;
    private final PlayerData data;


    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(27, new BackButton(new GrantSelectMenu(data)));

        surroundButtons(true, buttons, new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").durability(7).build());

        buttons.put(4, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(CC.SECONDARY + "Player&7: " + CC.PRIMARY + grantProcedure.getAddedTo().getName());
                lore.add(CC.SECONDARY + "Rank&7: " + CC.PRIMARY + grantProcedure.getRank().getColor() + grantProcedure.getRank().getName());
                lore.add(CC.SECONDARY + "Duration&7: " + CC.PRIMARY + grantProcedure.getDuration());
                lore.add("");
                lore.add(CC.GRAY + "Click one of the items");
                lore.add(CC.GRAY + "to select grant reason.");
                return new ItemBuilder(Material.NETHER_STAR).lore(Color.translate(lore)).name(CC.PRIMARY + "Grant Info").build();
            }
        });

        buttons.put(10, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + "Click here to set grant");
                lore.add(CC.SECONDARY + "grant  to " + CC.RED + "permanent" + CC.GRAY + ".");
                return new ItemBuilder(Material.PAPER).lore(Color.translate(lore)).name(CC.B_RED + "New Staff").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.chat("New Staff");
            }
        });

        buttons.put(11, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                return new ItemBuilder(Material.PAPER).lore(Color.translate(lore)).name(CC.B_GREEN + "Promote").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.chat("Promote");
            }
        });

        buttons.put(12, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                return new ItemBuilder(Material.PAPER).lore(Color.translate(lore)).name(CC.B_YELLOW + "Demote").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.chat("Demote");
            }
        });

        buttons.put(14, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                return new ItemBuilder(Material.PAPER).lore(Color.translate(lore)).name(CC.BL_PURPLE + "Store Issues").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.chat("Store Issues");
            }
        });

        buttons.put(15, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                return new ItemBuilder(Material.PAPER).lore(Color.translate(lore)).name(CC.B_GOLD + "Giveaway").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.chat("Giveaway");
            }
        });

        buttons.put(16, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                return new ItemBuilder(Material.PAPER).lore(Color.translate(lore)).name(CC.BD_AQUA + "Other").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.chat("Other");
            }
        });

        buttons.put(22, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + "Click here to set grant");
                lore.add(CC.GRAY + "grant reason to " + CC.AQUA + "custom" + CC.GRAY + ".");
                return new ItemBuilder(Material.BOOK_AND_QUILL).lore(Color.translate(lore)).name(CC.B_AQUA + "Custom").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.closeInventory();
                player.sendMessage(Color.translate("&7&m-------------------------------------------------"));
                player.sendMessage(CC.SECONDARY + "Please type " + CC.PRIMARY + "reason " + CC.SECONDARY + "for this rank.");
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
