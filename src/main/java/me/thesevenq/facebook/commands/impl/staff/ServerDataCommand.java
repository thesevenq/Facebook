package me.thesevenq.facebook.commands.impl.staff;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.server.menus.ServerDataMenu;
import me.thesevenq.facebook.server.objects.Server;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.string.MessageUtils;
import me.thesevenq.facebook.utils.time.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

import java.util.Collections;

public class ServerDataCommand extends BaseCommand {

    public ServerDataCommand() {
        super("server", Collections.singletonList("data"), "facebook.op", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 0 || args.length == 2) {
            player.sendMessage(usage);
            return;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("list")) {
                new ServerDataMenu().openMenu(player);
                return;
            }
            Server data = core.getServerManager().getByName(args[0]);

            if (!data.isOnline()) {
                player.sendMessage(Color.translate("&cThere is no data for &l" + args[0] + " &cserver."));
                return;
            }
            player.sendMessage(Color.translate("\n " + data.getStatusColorBold() + data.getName() + ""));
            player.sendMessage(Color.translate("  &7" + MessageUtils.CIRCLE + " &eStatus: " + data.getStatus()));
            player.sendMessage(Color.translate("  &7" + MessageUtils.CIRCLE + " &eType: &6" + data.getType().getName()));
            player.sendMessage(Color.translate("  &7" + MessageUtils.CIRCLE + " &ePlayers: &6" + data.getOnlinePlayers() + "/" + data.getMaxPlayers()));
            player.sendMessage(Color.translate("  &7" + MessageUtils.CIRCLE + " &eTPS (1m, 5m, 15m): &6" + data.getTps1() + ", " + data.getTps2() + ", " + data.getTps3()));
            player.sendMessage(Color.translate("  &7" + MessageUtils.CIRCLE + " &eUptime: &6" + TimeUtil.millisToRoundedTime(data.getUpTime()) + "\n "));
            return;
        }

        if (args[0].equalsIgnoreCase("runcmd")) {
            String text = StringUtils.join(args, " ", 2, args.length);

            if (args[1].equalsIgnoreCase("all")) {
                //apple.getRedisManager().publish(RedisUtil.SERVERS, "command;all;" + text);
                player.sendMessage(Color.translate("&aCommand '&l" + text + "&a' has been executed on all servers."));
                return;
            }
            Server data = core.getServerManager().getByName(args[1]);

            if (!data.isOnline()) {
                player.sendMessage(Color.translate("&cThere is no data for &l" + args[1] + " &cserver."));
                return;
            }
            //apple.getRedisManager().publish(RedisUtil.SERVERS,  "command;" + data.getName() +';' + text);
            player.sendMessage(Color.translate("&aCommand '&l" + text + "&a' has been executed on &l" + data.getName() + " &aserver."));
        } else {
            player.sendMessage(usage);
        }
    }

    private final String usage = Color.translate(
            "&cServer Commands - Help"
                    + "\n/server list"
                    + "\n/server <name>"
                    + "\n/server runcmd <name|all> <command>");
}
