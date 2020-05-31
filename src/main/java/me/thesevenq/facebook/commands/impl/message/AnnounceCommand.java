package me.thesevenq.facebook.commands.impl.message;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.utils.Color;
import me.thesevenq.facebook.utils.MessageUtils;
import org.bukkit.entity.Player;

public class AnnounceCommand extends BaseCommand {

    public AnnounceCommand() {
        super("announce", "facebook.admin", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(Color.translate("&7&m--------------------------"));
            player.sendMessage(Color.translate(" &8" + MessageUtils.CIRCLE + " &6/&eannounce <message...> &7" + MessageUtils.LINE + " &7- Announces a message on this server."));
            player.sendMessage(Color.translate(" &8" + MessageUtils.CIRCLE + " &6/&eannounce &6<server> &e<message...> &7" + MessageUtils.LINE + " &7-  Announces a message on a specified server."));
            player.sendMessage(Color.translate(" &8" + MessageUtils.CIRCLE + " &6/&eannounce &6ALL &e<message...> &7" + MessageUtils.LINE + " &7-  Announces a message on all servers."));
            player.sendMessage(Color.translate("&7&m--------------------------"));
        } else {
            if (args.length > 0) {
                final StringBuilder message = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    message.append(Color.translate(args[i])).append(" ");
                }

                MessageUtils.announce(message.toString());
                player.sendMessage(Color.translate("&fYou have successfully announced a message."));
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
                player.sendMessage(Color.translate("&fYou have successfully announced a message on all servers."));
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
