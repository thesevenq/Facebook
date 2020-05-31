package me.thesevenq.facebook.ranks.commands;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.grant.menus.QuickGrantMenu;
import org.bukkit.entity.Player;

public class QuickGrantCommand extends BaseCommand {

    public QuickGrantCommand() {
        super("qgrant", "facebook.manager", true);
    }


    public void execute(Player player, String[] args) {
        new QuickGrantMenu().openMenu(player);
    }
}
