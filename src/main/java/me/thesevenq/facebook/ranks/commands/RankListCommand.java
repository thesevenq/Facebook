package me.thesevenq.facebook.ranks.commands;

import me.thesevenq.facebook.commands.BaseCommand;

import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.ranks.menus.RankListMenu;
import org.bukkit.entity.Player;


public class RankListCommand extends BaseCommand {

    private PlayerData data;

    public RankListCommand() {
        super("ranklist", "facebook.op", false);
    }

    @Override
    public void execute(Player player, String[] args) {
        new RankListMenu(data).openMenu(player);
    }
}