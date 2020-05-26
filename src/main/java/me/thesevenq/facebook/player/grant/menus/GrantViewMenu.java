package me.thesevenq.facebook.player.grant.menus;

import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.player.grant.Grant;
import me.thesevenq.facebook.ranks.Rank;
import me.thesevenq.facebook.utils.*;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.pagination.PaginatedMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@RequiredArgsConstructor
public class GrantViewMenu extends PaginatedMenu {

    private final PlayerData data;

    @Override
    public String getPrePaginatedTitle(Player player) {
        return Color.translate(CC.SECONDARY + "Grants of " + CC.PRIMARY + player.getName());
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        data.getGrants().stream().filter(grant -> grant.getRank() != Rank.DEFAULT).sorted(Comparator.comparingLong(Grant::getAddedAt).reversed()).forEach(grant -> {
            buttons.put(buttons.size(), new Button() {
                @Override
                public ItemStack getButtonItem(Player player) {
                    List<String> lore = new ArrayList<>();
                    ItemBuilder item = new ItemBuilder(Material.WOOL).durability(StringUtil.convertChatColorToWoolData(ChatColor.getByChar(grant.getRank().getColor().replace(String.valueOf('§'), "").replace("&", "").replace("o", ""))));
                    lore.add("&7&m------------------------------------------------");
                    lore.add(CC.SECONDARY + "Expires in&7: " + CC.PRIMARY + grant.getNiceDuration());
                    lore.add(CC.SECONDARY + "Added by&7: " + CC.PRIMARY + grant.getAddedBy());
                    lore.add(CC.SECONDARY + "Added at&7: " + CC.PRIMARY + new Date(grant.getAddedAt()));
                    lore.add("");
                    lore.add(CC.B_PRIMARY + "Added Reason&7: " + CC.SECONDARY + grant.getReason());
                    lore.add("");
                    lore.add(CC.RED + "Click here to remove this grant.");
                    lore.add("&7&m------------------------------------------------");
                    return item.name(CC.B_GREEN + "Added Rank &7" + MessageUtils.LINE + " &l" + grant.getRank().getColor() + grant.getRank().getName()).lore(Color.translate(lore)).build();
                }
            });
        });
        return buttons;
    }
}
