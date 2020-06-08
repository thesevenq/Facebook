package me.thesevenq.facebook.ranks.menus;

import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.player.grant.menus.QuickGrantMenu;
import me.thesevenq.facebook.ranks.Rank;
import me.thesevenq.facebook.utils.*;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.string.StringUtil;
import me.thesevenq.facebook.utils.string.clickable.Clickable;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.buttons.CloseButton;
import me.thesevenq.facebook.utils.menu.pagination.PageButton;
import me.thesevenq.facebook.utils.menu.pagination.PaginatedMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class RankListMenu extends PaginatedMenu {

    private final PlayerData data;

    @Override
    public String getPrePaginatedTitle(Player player) {
        return CC.PRIMARY + "Rank List";
    }

    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(0, new PageButton(-1, this));
        buttons.put(8, new PageButton(1, this));
        buttons.put(35, new CloseButton());

        buttons.put(4, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(CC.SECONDARY + "Available Ranks&7: " + CC.PRIMARY + Rank.values().length);
                lore.add(CC.SECONDARY + "Staff Ranks&7: " + CC.PRIMARY + "6");
                lore.add(CC.SECONDARY + "Media Ranks&7: " + CC.PRIMARY + "3");
                lore.add(CC.SECONDARY + "Donator Ranks&7: " + CC.PRIMARY + "5");
                lore.add(CC.SECONDARY + "Special Ranks&7: " + CC.PRIMARY + "3");
                return new ItemBuilder(Material.NETHER_STAR).lore(Color.translate(lore)).name(CC.PRIMARY + "Information").build();
            }
        });

        buttons.put(27, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(CC.GRAY + "Click here to change");
                lore.add(CC.GRAY + "make a grant.");
                return new ItemBuilder(Material.PAPER).lore(Color.translate(lore)).name(CC.PRIMARY + "Make Grant").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                new QuickGrantMenu().openMenu(player);
            }
        });

        surroundButtons(false, buttons, new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").durability(7).build());

        return buttons;
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        Stream.of(Rank.values()).sorted(Comparator.comparingInt(Rank::ordinal).reversed()).forEach(rank -> {
            buttons.put(buttons.size(), new Button() {
                @Override
                public ItemStack getButtonItem(Player player) {
                    ItemBuilder item = new ItemBuilder(Material.WOOL).durability(StringUtil.convertChatColorToWoolData(ChatColor.getByChar(rank.getColor().replace(String.valueOf('§'), "").replace("&", "").replace("o", ""))));

                    List<String> lore = new ArrayList<>();
                    lore.add("");
                    lore.add(CC.SECONDARY + "Color&7: " + rank.getColor() + "Color");
                    lore.add(CC.SECONDARY + "Prefix&7: " + rank.getPrefix());
                    lore.add("");
                    lore.add(CC.GRAY + "Click here to see");
                    lore.add(CC.GRAY + "chat preview of this rank.");
                    return item.name(Color.translate(rank.getColor() + rank.getName())).lore(Color.translate(lore)).build();
                }

                @Override
                public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                    Clickable clickable = new Clickable();
                    player.closeInventory();
                    player.sendMessage(CC.GRAY + CC.STRIKE_THROUGH + "-------------------------------");
                    player.sendMessage(CC.B_PRIMARY + "Rank Preview" + CC.GRAY +":");
                    player.sendMessage(Color.translate(CC.SECONDARY + " With Tag&7: " + "&7[&6&lG&e&lG&7] " + rank.getPrefix() + rank.getColor() + rank.getName() + CC.GRAY + ": " + CC.WHITE + "Hello there!"));
                    player.sendMessage(Color.translate(CC.SECONDARY + " Without Tag&7: " + rank.getPrefix() + rank.getColor() + rank.getName() + CC.GRAY + ": " + CC.WHITE + "Hello there!"));
                    player.sendMessage("");
                    player.sendMessage(CC.SECONDARY + "Showed preview for " + rank.getColor() + rank.getName() + CC.SECONDARY + " rank.");

                    clickable.add("&a&lClick to reopen!", "&aClick to open rank list menu!", "/ranklist");
                    clickable.sendToPlayer(player);
                    player.sendMessage(CC.GRAY + CC.STRIKE_THROUGH + "-------------------------------");
                }
            });
        });

        return buttons;
    }

    @Override
    public int getSize() {
        return 9 * 4;
    }

}
