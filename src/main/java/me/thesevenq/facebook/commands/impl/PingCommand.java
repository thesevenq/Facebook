package me.thesevenq.facebook.commands.impl;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.utils.Color;
import me.thesevenq.facebook.utils.FacebookUtils;
import me.thesevenq.facebook.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PingCommand extends BaseCommand {

    public PingCommand() {
        super("ping", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 0) {
            int ping = (((CraftPlayer) player).getHandle().ping);
            player.sendMessage(Color.translate("&eYour Ping: " + FacebookUtils.colorPing(ping) + "ms"));
        } else if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                player.sendMessage(MessageUtils.playerOffline());
            }
            int ping = (((CraftPlayer) target).getHandle().ping);
            //player.sendMessage(Color.translate((ping <= 40 ? "&a" : ping <= 70 ? "&e" : ping <= 100 ? "&6" : "&c") + " &eping."));
            player.sendMessage(Color.translate("&6" + target.getName() + "&e's ping: " +  FacebookUtils.colorPing(ping) + "ms"));
        }
    }
}
