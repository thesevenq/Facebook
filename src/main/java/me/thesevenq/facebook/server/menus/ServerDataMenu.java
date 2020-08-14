package me.thesevenq.facebook.server.menus;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.server.objects.Server;
import me.thesevenq.facebook.utils.ItemBuilder;
import me.thesevenq.facebook.utils.WoolUtil;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.buttons.CloseButton;
import me.thesevenq.facebook.utils.menu.pagination.PaginatedMenu;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.string.MessageUtils;
import me.thesevenq.facebook.utils.time.TimeUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ServerDataMenu extends PaginatedMenu {

    public ServerDataMenu() {
        setAutoUpdate(true);
    }

    @Override
    public String getPrePaginatedTitle(Player player) {
        return "&8Server Data";
    }

    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        surroundButtons(true, buttons, new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").durability(7).build());
        buttons.put(getSize() - 1, new CloseButton());

        buttons.put(4, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.NETHER_STAR)
                        .name("&6Information")
                        .lore(Color.translate(Arrays.asList(
                                "",
                                "&eAvailable servers: &6" + Facebook.getInstance().getServerManager().getAllServersData().size(),
                                "",
                                "&7Click here to see the",
                                "&7server list in the chat."
                        )))
                        .build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.sendMessage(Color.translate(" &6&lServer List\n "));
                Facebook.getInstance().getServerManager().getAllServersData().stream().filter(Objects::nonNull)
                        .forEach(data -> player.sendMessage(Color.translate("  " + data.getStatusColorBold() + data.getName()
                                + "\n   &7" + MessageUtils.CIRCLE + " &eStatus: " + data.getStatus()
                                + "\n   &7" + MessageUtils.CIRCLE + " &eType: &6" + data.getType().getName()
                                + "\n   &7" + MessageUtils.CIRCLE + " &ePlayers: &6" + data.getOnlinePlayers() + "/" + data.getMaxPlayers()
                                + "\n   &7" + MessageUtils.CIRCLE + " &eTPS (1m, 5m, 15m): &6" + data.getTps1() + ", " + data.getTps2() + ", " + data.getTps3()
                                + "\n   &7" + MessageUtils.CIRCLE + " &eUptime: &6" + TimeUtil.millisToRoundedTime(data.getUpTime()) + "\n ")));
            }
        });
        return buttons;
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        int slot = 10;
        for (Object server : Facebook.getInstance().getServerManager().getAllServersData().stream().filter(Objects::nonNull).toArray()) {
            Server data = (Server) server;

            buttons.put(slot, new Button() {
                @Override
                public ItemStack getButtonItem(Player player) {

                    return new ItemBuilder(Material.WOOL)
                            .durability(WoolUtil.getWoolData(data.isJoinable() ? ChatColor.GREEN : ChatColor.RED))
                            .name(data.getStatusColorBold() + data.getName())
                            .lore(Arrays.asList(
                                    "",
                                    "&eStatus: " + data.getStatus(),
                                    "&eType: &6" + data.getType().getName(),
                                    "&ePlayers: &6" + data.getOnlinePlayers() + "/" + data.getMaxPlayers(),
                                    "&eTPS (1m, 5m, 15m): &6" + data.getTps1() + ", " + data.getTps2() + ", " + data.getTps3(),
                                    "&eUptime: &6" + TimeUtil.millisToRoundedTime(data.getUpTime())))
                            .build();
                }
            });
            slot++;
        }
        return buttons;
    }

    @Override
    public int getSize() {
        return 9*4;
    }
}
