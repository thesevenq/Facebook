package me.thesevenq.facebook.server.database.jedis;

import com.google.gson.JsonParser;
import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.FacebookAPI;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.server.objects.Server;
import me.thesevenq.facebook.server.objects.ServerType;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.string.ConsoleUtils;
import me.thesevenq.facebook.utils.string.MessageUtils;
import me.thesevenq.facebook.utils.player.Permission;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.ServerOperator;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.ArrayList;
import java.util.List;

public class JedisSubscriber {

    private Jedis jedis;

    private JedisPubSub jedisPubSub;
    private static final JsonParser JSON_PARSER = new JsonParser();

    public JedisSubscriber() {
        this.jedis = new Jedis();
        //this.jedis.auth("EL3DZp3EwMzx4gywmwbEt4DedKwKa5Au");
        subscribe();
    }

    private void subscribe() {
        this.jedisPubSub = get();
        (new Thread(() -> this.jedis.subscribe(this.jedisPubSub, new String[]{Facebook.getInstance().getConfig().getString("SERVERNAME"), "global"}))).start();
    }

    private JedisPubSub get() {
        return new JedisPubSub() {
            public void onMessage(String channel, String message) {

                String[] args = message.split(";");
                String command = args[0];
                if (channel.equalsIgnoreCase(Facebook.getInstance().getConfig().getString("SERVERNAME").toLowerCase())) {
                    switch (command) {
                        case "runcmd":
                            ConsoleUtils.log("&fPerforming &c/" + args[1] + "&f, requested by &c" + args[2] + "&f.");
                            Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(), args[1]);
                            break;
                        case "announce":
                            Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(Color.translate("&4Announcement &7" + MessageUtils.LINE + " &r" + args[1])));
                            break;
                    }
                } else {
                    TextComponent msg;
                    TextComponent sender;
                    switch (channel) {
                        case "global":
                            switch (command) {
                                case "serverstart":
                                    Bukkit.getOnlinePlayers().stream().filter(ServerOperator::isOp).forEach(player -> player.sendMessage(Color.translate("&5[S] &dServer &d&l" + Facebook.getInstance().getConfig().getString("SERVERNAME") +" &dis now online.")));
                                    ConsoleUtils.log("&5[S] &dServer &d&l" + Facebook.getInstance().getConfig().getString("SERVERNAME") + " &dis now online.");
                                    break;
                                case "serverstop":
                                    Bukkit.getOnlinePlayers().stream().filter(ServerOperator::isOp).forEach(player -> player.sendMessage(Color.translate("&5[S] &dServer &d&l" + Facebook.getInstance().getConfig().getString("SERVERNAME") +" &dis now offline.")));
                                    ConsoleUtils.log("&5[S] &dServer &d&l" + Facebook.getInstance().getConfig().getString("SERVERNAME") + " &dis now offline.");
                                    break;
                                case "update": {
                                    Server server = Facebook.getInstance().getServerManager().getByName(args[1]);

                                    if (!server.isOnline()) {
                                        server = new Server();
                                        Facebook.getInstance().getServerManager().addServer(args[1], server);
                                    }
                                    List<String> players = new ArrayList<>();

                                    for (String string : args[8].replace("[", "").replace("]", "").split(",")) {
                                        players.add(string);
                                    }

                                    server.setName(args[1]);
                                    server.setType(ServerType.getType(args[2]));
                                    server.setOnlinePlayers(Integer.parseInt(args[3]));
                                    server.setMaxPlayers(Integer.parseInt(args[4]));
                                    server.setWhitelisted(Boolean.parseBoolean(args[5]));
                                    server.setTps1(Double.parseDouble(args[6]));
                                    server.setTps2(Double.parseDouble(args[7]));
                                    server.setTps3(Double.parseDouble(args[8]));
                                    server.setPlayers(players);
                                    server.setLastUpdate(System.currentTimeMillis());
                                    break;
                                }
                                case "remove": {
                                    //ServerData.getServers().remove(args[1]);
                                    Facebook.getInstance().getServerManager().removeServer(args[1]);
                                    break;
                                }
                                case "staffchat":
                                    msg = new TextComponent(Color.translate("&4S &7" + MessageUtils.LINE + " &c"));
                                    sender = new TextComponent(Color.translate("&c" + args[2]));
                                    sender.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(Color.translate("&c" + args[2] + " &7is currently on &c" + args[1]))).create()));
                                    msg.addExtra((BaseComponent) sender);
                                    msg.addExtra(" &8" + MessageUtils.ARROW_RIGHT + " &f" + args[3]);
                                    Bukkit.getOnlinePlayers()
                                            .stream()
                                            .filter(player -> (PlayerData.getByName(player.getName()).isStaffChat() && player.hasPermission(Permission.STAFF)))
                                            .forEach(player -> player.sendMessage(Color.translate("&9[SC] &r" + "&7[" + args[1].toLowerCase() + "&7] " + args[2] + "&7: &b" + args[3])));
                                    break;
                                case "staffjoin":
                                    Bukkit.getOnlinePlayers()
                                            .stream()
                                            .filter(player -> player.hasPermission("facebook.staff"))
                                            .forEach(player -> player.sendMessage(Color.translate("&9[S] &r" + args[1] + " &bhas connected to &3" + Facebook.getInstance().getConfig().getString("SERVERNAME") + "&b.")));
                                    break;
                                case "chatmute":
                                    Bukkit.getOnlinePlayers()
                                            .stream()
                                            .filter(player -> player.hasPermission("facebook.staff"))
                                            .forEach(player -> player.sendMessage(Color.translate("&9[S] &7[" + FacebookAPI.getServerName() + "&7] " + args[1] + " &3has silenced the chat.")));
                                    break;
                                case "chatunmute":
                                    Bukkit.getOnlinePlayers()
                                            .stream()
                                            .filter(player -> player.hasPermission("facebook.staff"))
                                            .forEach(player -> player.sendMessage(Color.translate("&9[S] &7[" + FacebookAPI.getServerName() + "&7] " + args[1] + " &3has un-silenced the chat.")));
                                    break;
                                case "chatclear":
                                    Bukkit.getOnlinePlayers()
                                            .stream()
                                            .filter(player -> player.hasPermission("facebook.staff"))
                                            .forEach(player -> player.sendMessage(Color.translate("&9[S] &7[" + FacebookAPI.getServerName() + "&7] " + args[1] + " &3cleared the chat.")));
                                    break;
                                case "froze":
                                    Bukkit.getOnlinePlayers()
                                            .stream()
                                            .filter(player -> player.hasPermission("facebook.staff"))
                                            .forEach(player -> player.sendMessage(Color.translate("&9[S] &7[" + FacebookAPI.getServerName() + "&7] " + args[1] + " &3froze " + args[2] + "&3.")));
                                    break;
                                case "unfroze":
                                    Bukkit.getOnlinePlayers()
                                            .stream()
                                            .filter(player -> player.hasPermission("facebook.staff"))
                                            .forEach(player -> player.sendMessage(Color.translate("&9[S] &7[" + FacebookAPI.getServerName() + "&7] " + args[1] + " &3un-froze " + args[2] + "&3.")));
                                    break;
                                case "staffconnect":
                                    Bukkit.getOnlinePlayers()
                                            .stream()
                                            .filter(player -> player.hasPermission("facebook.staff"))
                                            .forEach(player -> player.sendMessage(Color.translate("&9[S] &r" + args[1] + " &bhas connected to network.")));
                                    break;
                                case "staffleave":
                                    Bukkit.getOnlinePlayers()
                                            .stream()
                                            .filter(player -> player.hasPermission("facebook.staff"))
                                            .forEach(player -> player.sendMessage(Color.translate("&9[S] &r" + args[1] + " &bhas disconnected from &3" + Facebook.getInstance().getConfig().getString("SERVERNAME") + "&b.")));
                                    break;
                                case "runcmd":
                                    ConsoleUtils.log("&fPerforming &c/" + args[1] + "&f, requested by &c" + args[2] + "&f.");
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), args[1]);
                                    break;
                                case "announce":
                                    Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(Color.translate("&4Announcement &7" + MessageUtils.LINE + " &r" + args[1])));
                                    ConsoleUtils.log("&4Announcement &7" + MessageUtils.LINE + " &r" + args[1]);
                                    break;
                                case "joinme":
                                    Facebook.getInstance().getServer().getOnlinePlayers().forEach((player) -> {
                                        player.sendMessage(Color.translate("&7[" + CC.B_PRIMARY + MessageUtils.LUNAR + "&7] " + args[2] + CC.SECONDARY + " is currently playing on " + CC.PRIMARY + Facebook.getInstance().getConfig().getString("SERVERNAME").toLowerCase() + CC.SECONDARY + "! Join him using " + CC.PRIMARY + "/join " + Facebook.getInstance().getConfig().getString("SERVERNAME").toLowerCase()));
                                    });
                                    break;
                            }
                            break;
                    }
                }
            }
        };
    }
}
