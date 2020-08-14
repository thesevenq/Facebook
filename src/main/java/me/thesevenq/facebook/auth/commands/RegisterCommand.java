package me.thesevenq.facebook.auth.commands;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.string.Color;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class RegisterCommand extends BaseCommand {

    public RegisterCommand() {
        super("register", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        PlayerData playerData = PlayerData.getByName(player.getName());

        if(args.length == 0) {
            player.sendMessage(Color.translate(Facebook.getInstance().getConfig().getString("REGISTER_MESSAGE")));
            return;
        }

        if(playerData.isRegistered()) {
            player.sendMessage(Color.translate(Facebook.getInstance().getConfig().getString("AUTHENTICATION.ALREADY_REGISTERED")));
            return;
        }

        playerData.setRegistered(true);
        playerData.setPassword(args[0]);
        player.sendMessage(Color.translate(Facebook.getInstance().getConfig().getString("AUTHENTICATION.SUCCESSFULLY_REGISTERED")));

        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 15, 10);
    }
}
