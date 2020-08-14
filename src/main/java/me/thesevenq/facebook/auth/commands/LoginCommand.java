package me.thesevenq.facebook.auth.commands;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.auth.discord.DiscordWebhook;
import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.PlayerUtil;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.string.Msg;
import me.thesevenq.facebook.utils.time.TimeUtil;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class LoginCommand extends BaseCommand {

    public LoginCommand() {
        super("login", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        String code;
        PlayerData playerData = PlayerData.getByName(player.getName());

        if (!playerData.isRegistered()) {
            player.sendMessage(Color.translate("&cIt seems that you are not registered."));
            player.sendMessage(Color.translate(Facebook.getInstance().getConfig().getString("AUTHENTICATION.REGISTER_MESSAGE")));
            return;
        }

        if (playerData.isAuthenticated()) {
            player.sendMessage(Color.translate(Facebook.getInstance().getConfig().getString("AUTHENTICATION.ALREADY_AUTHENTICATED")));
            return;
        }

        if (!args[0].equalsIgnoreCase(playerData.getPassword())) {
            player.sendMessage(Color.translate(Facebook.getInstance().getConfig().getString("AUTHENTICATION.WRONG_PASSWORD")));
            return;
        }

        playerData.setAuthenticated(true);
        player.sendMessage(Color.translate(Facebook.getInstance().getConfig().getString("AUTHENTICATION.SUCCESSFULLY_AUTHENTICATED")));

        player.sendMessage("");
        player.sendMessage(Color.translate(Facebook.getInstance().getConfig().getString("AUTHENTICATION.NEED_TO_AUTH")));
        player.sendMessage(Color.translate(Facebook.getInstance().getConfig().getString("AUTHENTICATION.AUTH_CODE") + Facebook.getInstance().getConfig().getString("AUTHENTICATION.DISCORD")));
        player.sendMessage("");


        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 15, 10);

        code = PlayerUtil.getNewCode();
        playerData.setCode(code);

        try {
            DiscordWebhook webhook = new DiscordWebhook("https://discordapp.com/api/webhooks/741324484379672768/N9Fkpyo8SPDDdi-BMr7MTzcTgWbFHh_stSUCWyrXfCORdipsUDU2yhTc644mji-R_Mbn");
            webhook.setUsername("Unique Auth");
            webhook.setTts(false);
            webhook.addEmbed(
                    new DiscordWebhook.EmbedObject()
                            .setTitle("New Auth Code!")
                            .setDescription("Auth code for **" + player.getName() + "**")
                            .addField("**Code:**", code, true)
                            .addField("**Usage:**", "/auth <code> ingame", true)
                            .setFooter("Generated at: " + TimeUtil.formatDate(System.currentTimeMillis()), webhook.getAvatarUrl())
                            .setColor(java.awt.Color.GREEN));
            webhook.execute();
        } catch (Exception e) {
            Msg.logConsole("&c[Facebook] &cDiscord failed to announce auth code!");
        }
    }
}

