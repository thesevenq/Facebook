package me.thesevenq.facebook.commands.impl;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.cosmetics.CosmeticsMenu;
import org.bukkit.entity.Player;

public class CosmeticsCommand extends BaseCommand {

    public CosmeticsCommand() {
        super("cosmetics", true);
    }


    public void execute(Player player, String[] args) {
        new CosmeticsMenu().openMenu(player);
    }
}
