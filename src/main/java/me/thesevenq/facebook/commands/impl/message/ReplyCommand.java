package me.thesevenq.facebook.commands.impl.message;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.cosmetics.CosmeticsMenu;
import me.thesevenq.facebook.player.events.PlayerMessageEvent;
import me.thesevenq.facebook.utils.Color;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class ReplyCommand extends BaseCommand {

    public ReplyCommand() {
        super("reply", true);
    }


    public void execute(Player player, String[] args) {
        new BukkitRunnable() {
            public void run() {
                UUID lastReplied = Facebook.getInstance().getPlayerMessageEvent().getLastReplied().get(player.getUniqueId());

                Player target = lastReplied == null ? null : Bukkit.getPlayer(lastReplied);

                if(args.length < 1) {
                    if(lastReplied != null) {
                        if(target == null) {
                            player.sendMessage(Color.translate("&cThere is no player to reply to."));
                        } else {
                            player.sendMessage(Color.translate("&eYou are in a conversation with " + target.getDisplayName() + "&e."));
                        }
                    } else {
                        player.sendMessage(Color.translate("&cUsage: /reply <meessage>"));
                    }

                    return;
                }

                if(target == null) {
                    player.sendMessage(Color.translate("&cThere is no player to reply to."));
                    return;
                }

                String message = StringUtils.join(args, ' ', 0, args.length);
                PlayerMessageEvent playerMessageEvent = new PlayerMessageEvent(player, target, message, false);

                Bukkit.getPluginManager().callEvent(playerMessageEvent);

                if(!playerMessageEvent.isCancelled()) {
                    playerMessageEvent.send();
                }

            }
        }.runTaskAsynchronously(Facebook.getInstance());
    }
}

