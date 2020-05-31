package me.thesevenq.facebook.ranks.commands;

import me.thesevenq.facebook.commands.BaseCommand;

import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.player.grant.Grant;
import me.thesevenq.facebook.ranks.menus.RankListMenu;
import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.Color;
import net.md_5.bungee.api.chat.TextComponent;
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