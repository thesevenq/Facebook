package me.thesevenq.facebook.commands.impl.message;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.FacebookAPI;
import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.database.DatabaseManager;
import me.thesevenq.facebook.database.jedis.JedisPublisher;
import org.bukkit.entity.Player;

public class JoinMeCommand extends BaseCommand {

    public JoinMeCommand() {
        super("joinme", "facebook.donor");
    }

    @Override
    public void execute(Player player, String[] args) {
        DatabaseManager.getInstance().getPublisher().write(JedisPublisher.GLOBAL, "joinme;" + ";" + FacebookAPI.getColoredName(player) + ";" + Facebook.getInstance().getConfig().getString("SERVERNAME"));
    }
}
