package me.thesevenq.facebook.player.grant.menus;

import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.FacebookAPI;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.*;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.buttons.BackButton;
import me.thesevenq.facebook.utils.menu.buttons.CloseButton;
import me.thesevenq.facebook.utils.menu.pagination.PageButton;
import me.thesevenq.facebook.utils.menu.pagination.PaginatedMenu;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@RequiredArgsConstructor
public class QuickGrantMenu extends PaginatedMenu {

    private PlayerData data;

    ChatColor color;

    @Override
    public String getPrePaginatedTitle(Player player) {
        return CC.PRIMARY + "Please select a player";
    }

    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(0, new PageButton(-1, this));
        buttons.put(8, new PageButton(1, this));

        buttons.put(27, new BackButton(new GrantSelectMenu(data)));

        buttons.put(35, new CloseButton());

        buttons.put(4, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(CC.SECONDARY + "Online&7: " + CC.PRIMARY + Bukkit.getOnlinePlayers().size());
                lore.add("");
                lore.add(CC.GRAY + "Click one of the players");
                lore.add(CC.GRAY + "to start a grant procedure.");
                return new ItemBuilder(Material.NETHER_STAR).lore(Color.translate(lore)).name(CC.PRIMARY + "Information").build();
            }
        });

        surroundButtons(false, buttons, new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").durability(7).build());

        return buttons;
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        Bukkit.getOnlinePlayers().forEach(toGrant -> {
            buttons.put(buttons.size(), new Button() {
                @Override
                public ItemStack getButtonItem(Player player) {
                    ItemBuilder item = new ItemBuilder(Material.SKULL_ITEM);

                    PlayerData data = PlayerData.getByName(toGrant.getName());

                    List<String> lore = new ArrayList<>();
                    lore.add("");
                    lore.add(FacebookAPI.getColoredName(toGrant) + CC.SECONDARY + "'s current");
                    lore.add(CC.SECONDARY + "rank is " + data.getRank().getColor() + data.getRank().getName());
                    lore.add("");
                    lore.add(CC.GREEN + "Click here to start a procedure.");
                    return item.name(CC.PRIMARY + toGrant.getName()).lore(Color.translate(lore)).build();
                }

                @Override
                public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                    player.performCommand("grant " + toGrant.getName());
                }
            });
        });

        return buttons;
    }

    @Override
    public int getSize() {
        return 9*4;
    }

}
