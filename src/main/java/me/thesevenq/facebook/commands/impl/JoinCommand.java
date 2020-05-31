package me.thesevenq.facebook.commands.impl;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.server.ServerUtils;
import me.thesevenq.facebook.utils.Color;
import org.bukkit.entity.Player;

public class JoinCommand extends BaseCommand {

    public JoinCommand() {
        super("join", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(Color.translate("&cUsage: /join <serverName>"));
        } else {
            ServerUtils.sendToServer(player, args[0]);
        }
    }
}
