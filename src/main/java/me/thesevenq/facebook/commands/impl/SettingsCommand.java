package me.thesevenq.facebook.commands.impl;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.settings.SettingsMenu;
import org.bukkit.entity.Player;

public class SettingsCommand extends BaseCommand {

    public SettingsCommand() {
        super("settings", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        new SettingsMenu().openMenu(player);
    }
}
