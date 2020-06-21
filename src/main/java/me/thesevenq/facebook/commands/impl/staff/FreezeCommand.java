package me.thesevenq.facebook.commands.impl.staff;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.string.MessageUtils;
import me.thesevenq.facebook.utils.string.Msg;
import me.thesevenq.facebook.utils.player.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class FreezeCommand extends BaseCommand {

    public FreezeCommand() {
        super("freeze", Arrays.asList(new String[]{"ss"}), "facebook.staff", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(Color.translate("&cUsage: /freeze <all|player>"));
        } else {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("all") || args[0].equalsIgnoreCase("server")) {
                    if (!player.hasPermission(Permission.OP)) {
                        player.sendMessage(MessageUtils.noPermission());
                        return;
                    }

                    //Facebook.getInstance().getFreezeManager().handleFreezeServer(player);
                } else {
                    Player target = Bukkit.getPlayer(args[0]);

                    if (Msg.checkOffline(player, args[0])) return;

                    if (target.isOp() && !player.isOp()) {
                        player.sendMessage(MessageUtils.noPermission());
                        return;
                    }

                    //Facebook.getInstance().getFreezeManager().handleFreeze(player, target);
                }
            }
        }
    }
}
