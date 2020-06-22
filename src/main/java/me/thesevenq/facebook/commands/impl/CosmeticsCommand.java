package me.thesevenq.facebook.commands.impl;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.cosmetics.CosmeticsMenu;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CosmeticsCommand extends BaseCommand {

    public CosmeticsCommand() {
        super("cosmetics", Arrays.asList(new String[]{"prefix", "deathanimation", "color", "tag", "tags", "armors"}), true);
    }


    public void execute(Player player, String[] args) {
        new CosmeticsMenu().openMenu(player);
    }
}
