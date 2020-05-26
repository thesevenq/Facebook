package me.thesevenq.facebook.player.grant.menus;

import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.FacebookAPI;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.ranks.Rank;
import me.thesevenq.facebook.ranks.procedure.GrantProcedure;
import me.thesevenq.facebook.ranks.procedure.GrantProcedureStage;
import me.thesevenq.facebook.utils.*;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.pagination.PaginatedMenu;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class GrantSelectMenu extends PaginatedMenu {

    private final PlayerData data;
    ChatColor color;

    @Override
    public String getPrePaginatedTitle(Player player) {
        return Color.translate(CC.PRIMARY + "[S] " + player.getName());
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
                    lore.add("&7&m------------------------------------------------");
                    lore.add(CC.SECONDARY + "Player&7: " + FacebookAPI.getColoredName(player));
                    lore.add("");
                    lore.add(CC.GRAY + "Click here to grant " + rank.getColor() + rank.getName() + CC.GRAY + "rank.");
                    lore.add("&7&m------------------------------------------------");
                    return item.name(Color.translate(rank.getColor() + rank.getName())).lore(Color.translate(lore)).build();
                }

                @Override
                public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                    if(clickType == ClickType.MIDDLE) {
                        player.closeInventory();
                    } else {
                        GrantProcedure grantProcedure = new GrantProcedure(rank, data, player.getName(), GrantProcedureStage.DURATION);
                        grantProcedure.setRank(rank);
                        new GrantDurationMenu().openMenu(player);
                    }
                }
            });
        });

        return buttons;
    }

}
