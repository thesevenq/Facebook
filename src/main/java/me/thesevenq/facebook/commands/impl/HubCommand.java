package me.thesevenq.facebook.commands.impl;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.server.ServerUtils;
import me.thesevenq.facebook.utils.string.Color;
import org.bukkit.entity.Player;

public class HubCommand extends BaseCommand {

    public HubCommand() {
        super("hub", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        ServerUtils.sendToServer(player, "Lobby");
        player.sendMessage(Color.translate("&cSending you to Hub..."));
    }
}

