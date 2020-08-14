package me.thesevenq.facebook.server;

import lombok.Getter;
import lombok.Setter;
import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.server.database.DatabaseManager;
import me.thesevenq.facebook.server.database.jedis.JedisSubscriber;
import me.thesevenq.facebook.server.objects.Server;
import me.thesevenq.facebook.server.objects.ServerType;
import me.thesevenq.facebook.utils.PlayerUtil;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter @Setter
public class ServerManager {

    private static Map<String, Server> servers = new HashMap<>();

    private Facebook facebook = Facebook.getInstance();

    private final String serverName = Facebook.getInstance().getConfig().getString("SERVERNAME");
    private final String serverType =  Facebook.getInstance().getConfig().getString("SERVERTYPE");

    private final long enabledAt = System.currentTimeMillis();
    private boolean settingUp = true;

    public ServerManager() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(facebook, () -> DatabaseManager.getInstance().getPublisher().write("global",
                "update;"
                        + facebook.getConfig().getString("SERVERNAME") + ";"
                        + serverType + ";"
                        + Bukkit.getServer().getOnlinePlayers().size() + ";"
                        + facebook.getServer().getMaxPlayers() + ";"
                        + facebook.getServer().hasWhitelist() + ";"
                        + facebook.getServer().spigot().getTPS()[0] + ";"
                        + facebook.getServer().spigot().getTPS()[1] + ";"
                        + facebook.getServer().spigot().getTPS()[2] + ";"
                        + PlayerUtil.getOnlinePlayersNames().toString()
        ),40L, 20L);

        Bukkit.getScheduler().runTaskLater(facebook, () -> {
            facebook.getServerManager().setSettingUp(false);
        } ,20);
    }

    public Server getByName(String name) {
        return getAllServersData().stream().filter(server -> server.getName().equalsIgnoreCase(name)).findFirst().orElse(new Server.DefaultServer());
    }

    public List<Server> getAllServersData() {
        return new ArrayList<>(servers.values());
    }

    public List<Server> getServersByType(ServerType type) {
        return servers.values().stream().filter(server -> server.getType() == type).collect(Collectors.toList());
    }

    public List<String> getAllPlayers() {
        List<String> players = new ArrayList<>();

        getAllServersData().forEach(server -> players.addAll(server.getPlayers()));

        return players;
    }

    public boolean isOnline(String name) {
        return getAllPlayers().stream().anyMatch(playerName -> playerName.toLowerCase().contains(name.toLowerCase()));
    }

    public boolean isType(ServerType serverType) {
        ServerType type = ServerType.getType(this.serverType);

        return type == serverType;
    }

    public void addServer(String name, Server server) {
        servers.put(name, server);
    }

    public void removeServer(String name) {
        servers.remove(getByName(name).getName());
    }

    public void onClose() {
        DatabaseManager.getInstance().getPublisher().write("global", "remove;" + serverName);
    }
}
