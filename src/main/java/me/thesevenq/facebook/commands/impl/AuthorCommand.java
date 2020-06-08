package me.thesevenq.facebook.commands.impl;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.utils.string.Color;
import org.bukkit.entity.Player;

public class AuthorCommand extends BaseCommand {

    public AuthorCommand() {
        super("facebook", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        player.sendMessage(Color.translate("&7&m--------------------------------------------"));
        player.sendMessage(Color.translate("&eThis plugin has been created by &bthesevenq&e."));
        player.sendMessage(Color.translate("&eVersion&7: &b1.7 x 1.8 "));
        player.sendMessage(Color.translate("&ePlugin version&7: &b1.0"));
        player.sendMessage(Color.translate("&7&m--------------------------------------------"));

    }
}
