package me.thesevenq.facebook.auth.commands;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.string.Color;
import org.bukkit.entity.Player;

public class AuthCommand extends BaseCommand {

    public AuthCommand() {
        super("auth", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        PlayerData playerData = PlayerData.getByName(player.getName());

        if(args.length == 0) {
            player.sendMessage(Color.translate("&cUsage: /auth <code>"));
            return;
        }

        if(!args[0].equalsIgnoreCase(playerData.getCode())) {
            player.sendMessage(Color.translate(Facebook.getInstance().getConfig().getString("WRONG_CODE")));
            return;
        }

        playerData.setCodeVerified(true);
        player.sendMessage(Color.translate(Facebook.getInstance().getConfig().getString("AUTHENTICATION.CODE_AUTHENTICATED")).replace("<code>", playerData.getCode()));
    }
}
