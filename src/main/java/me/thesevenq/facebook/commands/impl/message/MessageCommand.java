package me.thesevenq.facebook.commands.impl.message;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.events.PlayerMessageEvent;
import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.Color;
import me.thesevenq.facebook.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessageCommand extends BaseCommand {


    public MessageCommand() {
        super("msg", true);
    }

    public void execute(Player player, String[] args) {
        if(args.length < 2) {
            player.sendMessage(CC.RED + "Usage: /msg <player> <message>");
        } else {
            Player target = Bukkit.getPlayer(args[0]);

            if(MessageUtils.checkOffline(player, args[0])) return;

            if(target == player) {
                player.sendMessage(Color.translate("&cYou can't message your self."));
                return;
            }

            String message = StringUtils.join(args, ' ', 1, args.length);
            PlayerMessageEvent playerMessageEvent = new PlayerMessageEvent(player, target, message, false);

            Bukkit.getPluginManager().callEvent(playerMessageEvent);

            if(!playerMessageEvent.isCancelled()) {
                playerMessageEvent.send();
            }
        }
    }
}


