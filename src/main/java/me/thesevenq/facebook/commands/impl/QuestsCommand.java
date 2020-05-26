package me.thesevenq.facebook.commands.impl;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.cosmetics.CosmeticsMenu;
import me.thesevenq.facebook.player.quests.QuestsMenu;
import org.bukkit.entity.Player;

public class QuestsCommand extends BaseCommand {

    public QuestsCommand() {
        super("quests", true);
    }


    public void execute(Player player, String[] args) {
        new QuestsMenu().openMenu(player);
    }
}
