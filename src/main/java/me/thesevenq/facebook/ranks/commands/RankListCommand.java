package me.thesevenq.facebook.ranks.commands;

import me.thesevenq.facebook.commands.BaseCommand;

import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.Color;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;


public class RankListCommand extends BaseCommand {

    public RankListCommand() {
        super("ranklist", "facebook.op", false);
    }

    TextComponent clickable;


    @Override
    public void execute(Player player, String[] args) {
        player.sendMessage(Color.translate("&7&m------------------------------------"));
        player.sendMessage(CC.B_PRIMARY + "Available Ranks:");
        player.sendMessage("");
        player.sendMessage(Color.translate(" &4Owner &7- &7(&ePriority: &b100&7)"));
        player.sendMessage(Color.translate(" &4&oManager &7- &7(&ePriority: &b99&7)"));
        player.sendMessage(Color.translate(" &cAdmin &7- &7(&ePriority: &b98&7)"));
        player.sendMessage(Color.translate(" &3&oSenior-Mod &7- &7(&ePriority: &b97&7)"));
        player.sendMessage(Color.translate(" &3Mod &7- &7(&ePriority: &b96&7)"));
        player.sendMessage(Color.translate(" &e&oTrial-Mod &7- &7(&ePriority: &b95&7)"));
        player.sendMessage(Color.translate(" &6Famous &7- &7(&ePriority: &b94&7)"));
        player.sendMessage(Color.translate(" &5YouTuber &7- &7(&ePriority: &b93&7)"));
        player.sendMessage(Color.translate(" &dMedia &7- &7(&ePriority: &b92&7)"));
        player.sendMessage(Color.translate(" &dRuby &7- &7(&ePriority: &b91&7)"));
        player.sendMessage(Color.translate(" &9Platinum &7- &7(&ePriority: &b90&7)"));
        player.sendMessage(Color.translate(" &6Gold &7- &7(&ePriority: &b89&7)"));
        player.sendMessage(Color.translate(" &7Silver &7- &7(&ePriority: &b88&7)"));
        player.sendMessage(Color.translate(" &8Basic &7- &7(&ePriority: &b87&7)"));
        player.sendMessage(Color.translate(" &aDefault &7- &7(&ePriority: &b50&7)"));
        player.sendMessage(Color.translate("&7&m------------------------------------"));

    }
}