package me.thesevenq.facebook.server;

import lombok.Getter;
import lombok.Setter;
import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.FacebookAPI;
import me.thesevenq.facebook.server.database.DatabaseManager;
import me.thesevenq.facebook.utils.string.Color;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class ServerData {

    @Getter @Setter
    public static Map<String, ServerData> servers = new HashMap<>();

    private String name, motd;
    private int onlinePlayers, maxPlayers;
    private long lastUpdate;
    private boolean whitelisted;
    private double tps;

    public ServerData() {
        this.name = FacebookAPI.getServerName();

        Bukkit.getScheduler().runTaskTimerAsynchronously(Facebook.getInstance(), () -> {
            DatabaseManager.getInstance().getPublisher().write(
                    "global",
                    "dataUpdate;"
                            + this.name + ";"
                            + Bukkit.getServer().getMotd() + ";"
                            + Bukkit.getOnlinePlayers().size() + ";"
                            + Bukkit.getMaxPlayers() + ";" + Bukkit.spigot().getTPS()[0] + ";" + Bukkit.hasWhitelist());


        }, 40L, 20L);
    }

    public boolean isOnline() {
        return System.currentTimeMillis() - this.lastUpdate < 20000L;
    }

    public static ServerData getByName(String name) {
        return servers.values().stream().filter(serverData -> serverData.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public String getTranslatedStatus() {
        String status;
        if (isOnline() && !isWhitelisted()) {
            status = Color.translate("&aOnline");
        } else if (isOnline() && isWhitelisted()) {
            status = Color.translate("&eWhitelisted");
        } else if (!isOnline()) {
            status = Color.translate("&cOffline");
        } else {
            status = Color.translate("&cOffline");
        }
        return status;
    }
}
