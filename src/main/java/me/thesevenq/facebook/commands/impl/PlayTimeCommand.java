package me.thesevenq.facebook.commands.impl;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.string.Msg;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class PlayTimeCommand extends BaseCommand {

    public PlayTimeCommand() {
        super("playtime", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 0) {
            long l = player.getStatistic(Statistic.PLAY_ONE_TICK);

            player.sendMessage(Color.translate("&eYour playtime is &6" + DurationFormatUtils.formatDurationWords(l * 50L, true, true) + " &eon this server."));
        } else {
            Player target = Bukkit.getPlayer(args[0]);

            if (Msg.checkOffline(player, args[0])) return;

            long l = target.getStatistic(Statistic.PLAY_ONE_TICK);

            player.sendMessage(Color.translate("&6" + target.getName() + "'s &eplaytime is &6" + DurationFormatUtils.formatDurationWords(l * 50L, true, true) + " &eon this server."));
        }

    }
}
