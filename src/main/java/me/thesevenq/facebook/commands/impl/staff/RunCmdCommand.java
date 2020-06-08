package me.thesevenq.facebook.commands.impl.staff;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.database.DatabaseManager;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.string.MessageUtils;
import org.bukkit.entity.Player;

public class RunCmdCommand extends BaseCommand {

    public RunCmdCommand() {
        super("runcmd", "facebook.admin", false);
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(Color.translate("&cUsage: /runcmd <server|all> <command...>"));
        } else {
            if (args.length > 0) {
                final StringBuilder command = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    command.append(args[i]).append(" ");
                }

                DatabaseManager.getInstance().getPublisher().write("global", "runcmd;" + command.toString());
                player.sendMessage(Color.translate(CC.SECONDARY + "You have successfully announced a message."));
            } else if (args.length > 1 && args[0].equalsIgnoreCase("ALL")) {
                if (args.length == 1) {
                    player.sendMessage(Color.translate("&cPlease enter a message to announce."));
                    return;
                }

                final StringBuilder message = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    message.append(Color.translate(args[i])).append(" ");
                }

                MessageUtils.announceGlobally(message.toString());
                player.sendMessage(Color.translate(CC.SECONDARY + "You have successfully announced a message on all servers."));
            } else if (args.length > 1) {
                if (args.length == 1) {
                    player.sendMessage(Color.translate("&cPlease enter a message to announce."));
                    return;
                }

                final StringBuilder message = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    message.append(Color.translate(args[i])).append(" ");
                }

                MessageUtils.announce(args[0].toLowerCase());
                player.sendMessage(Color.translate("&fYou have successfully announced a message to &c" + args[0].toLowerCase() + "&f."));
            }

        }
    }
}
