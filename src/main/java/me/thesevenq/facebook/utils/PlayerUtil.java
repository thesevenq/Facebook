package me.thesevenq.facebook.utils;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.server.objects.Server;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class PlayerUtil {

    public static Collection<?extends Player> getOnlinePlayers() {
        return new HashSet<Player>(Bukkit.getServer().getOnlinePlayers());
    }

    public static Collection<?extends String> getOnlinePlayersNames() {
        Collection names = new HashSet<>();

        for (Player player : getOnlinePlayers())
            names.add(player.getName());

        return names;
    }

    public static int getGlobalOnlinePlayers() {
        int i = 0;

        for (Server data : Facebook.getInstance().getServerManager().getAllServersData()) {
            if (data.isOnline()) {
                i += data.getOnlinePlayers();
            }
        }
        return i;
    }

    public static String getNewCode() {
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            code.append(new Random().nextInt(9) + 1);
        }
        return code.toString();
    }

}
