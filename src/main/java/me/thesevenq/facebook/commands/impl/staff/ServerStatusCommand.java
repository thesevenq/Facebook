package me.thesevenq.facebook.commands.impl.staff;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.server.ServerData;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.string.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ServerStatusCommand extends BaseCommand {

    public ServerStatusCommand() {
        super("serverstatus", Arrays.asList(new String[]{"serverdata", "data"}),"facebook.staff", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        if(args.length == 0) {
            player.sendMessage(CC.RED + "Usage: /serverstatus <server" + MessageUtils.LINE + "all>");
            return;
        }
        Bukkit.getScheduler().runTaskAsynchronously(Facebook.getInstance(), () -> {
            ServerData data = ServerData.getByName(args[0]);

            if(data == null) {
                player.sendMessage(CC.RED + "That server does cannot be found in our database.");
                return;
            }

            player.sendMessage(Color.translate("&7&m------------------------------"));
            player.sendMessage(CC.B_PRIMARY + "Server Status");
            player.sendMessage("");
            player.sendMessage(Color.translate(" &eName&7: &b" + data.getName()));
            player.sendMessage(Color.translate(" &eOnline&7: &b" + data.getOnlinePlayers() + "/" + data.getMaxPlayers()));
            player.sendMessage(Color.translate(" &eMotd&7: &b" + data.getMotd()));
            player.sendMessage(Color.translate(" &eStatus&7: &b" + data.getTranslatedStatus()));
            player.sendMessage(Color.translate(" &eWhitelisted&7: " + (data.isWhitelisted() ? CC.YELLOW + "Whitelisted" : CC.GREEN + "No (Joinable)")));
            player.sendMessage(Color.translate(" &eTPS&7: &b" + Math.round((data.getTps() > 20 ? 20 : data.getTps()))));
            player.sendMessage(Color.translate("&7&m------------------------------"));

        });
    }
}
