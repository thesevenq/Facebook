package me.thesevenq.facebook.ranks.commands;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.player.grant.Grant;
import me.thesevenq.facebook.ranks.Rank;
import me.thesevenq.facebook.utils.Color;
import me.thesevenq.facebook.utils.Tasks;
import me.thesevenq.facebook.utils.TimeFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetRankCommand extends BaseCommand {
    public SetRankCommand() {
        super("setrank", false);
    }

    public void execute(CommandSender sender, String[] args) {
        Tasks.runAsync(() -> {
            Grant grant;
            if (args.length < 4) {
                sender.sendMessage(Color.translate("&cInvalid usage: /setrank <player> <rank> <duration> <reason>"));
                return;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(Color.translate("&cThat player is offline."));
                return;
            }
            PlayerData data = PlayerData.getByName(target.getName());
            Rank rank = Rank.getRankByName(args[1]);
            if (rank == null) {
                sender.sendMessage(Color.translate("&cThat rank doesn't exist."));
                return;
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 3; i < args.length; i++)
                builder.append(args[i]).append(" ");
            String reason = builder.toString().trim();
            long duration = TimeFormatUtils.parseTime(args[2]);
            if (duration == -1L) {
                grant = new Grant(rank, -1L, System.currentTimeMillis(), sender.getName(), reason);
            } else {
                grant = new Grant(rank, System.currentTimeMillis() + duration, System.currentTimeMillis(), sender.getName(), reason);
            }
            data.setGrant(grant);
            data.getGrants().add(grant);
            sender.sendMessage(Color.translate("&aYou have set &e" + target.getName() + " &arank to " + rank.getColor() + rank.getName()));
            target.sendMessage(Color.translate("&aYour rank has been set to " + rank.getColor() + rank.getName()));
        });
    }
}
