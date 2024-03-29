package me.thesevenq.facebook.player;

import lombok.val;
import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.FacebookAPI;
import me.thesevenq.facebook.commands.impl.chatcontrol.MuteChatCommand;
import me.thesevenq.facebook.player.cosmetics.scoreboard.ScoreboardType;
import me.thesevenq.facebook.server.database.DatabaseManager;
import me.thesevenq.facebook.server.database.jedis.JedisPublisher;
import me.thesevenq.facebook.ranks.Rank;
import me.thesevenq.facebook.utils.*;
import me.thesevenq.facebook.utils.player.Permission;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.string.MessageUtils;
import me.thesevenq.facebook.utils.string.Msg;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import java.util.UUID;

public class PlayerListeners implements Listener {

    public PlayerListeners() {
        Bukkit.getPluginManager().registerEvents(this, Facebook.getInstance());
    }

    @EventHandler
    public void onAsyncLogin(AsyncPlayerPreLoginEvent event) {
        UUID uuid = event.getUniqueId();
        String name = event.getName();
        if (PlayerData.getDataMap().containsKey(name)) return;
        new PlayerData(name);

    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        val data = PlayerData.getByName(event.getPlayer().getName());

        if (data == null) return;

        if(Bukkit.getServer().hasWhitelist()) {
            if(event.getPlayer().isWhitelisted()) {
                event.allow();
            } else {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, CC.RED + "Server is currently in whitelist mode.\nOnly " + CC.GRAY + "Silver " + CC.RED + "and above can join.\n\n" + CC.SECONDARY + "Follow our discord for updates " + CC.PRIMARY + "discord.hestianetwork.cf" + CC.SECONDARY + ".");
            }
        }

        if (data.getScoreboard() == null) {
            data.setScoreboard(ScoreboardType.NORMAL);

        }

        data.setAuthenticated(false);

        data.load();
        data.setupPermissionsAttachment(Facebook.getInstance(), event.getPlayer());
    }


    @EventHandler (ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        val data = PlayerData.getByName(player.getName());

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());


        if(!player.hasPlayedBefore()) {
            data.setRegistered(false);
        }

        if (FacebookAPI.getServerName().equalsIgnoreCase("Lobby")) {
            if (data.getRank() == Rank.OWNER
                    || data.getRank() == Rank.ADMIN
                    || data.getRank() == Rank.MANAGER
                    || data.getRank() == Rank.SENIORMOD
                    || data.getRank() == Rank.MOD
                    || data.getRank() == Rank.TRIALMOD) {
                DatabaseManager.getInstance().getPublisher().write(JedisPublisher.GLOBAL, "staffconnect;" + FacebookAPI.getColoredName(player));
            }
        } else {
            if (data.getRank() == Rank.OWNER
                    || data.getRank() == Rank.ADMIN
                    || data.getRank() == Rank.MANAGER
                    || data.getRank() == Rank.SENIORMOD
                    || data.getRank() == Rank.MOD
                    || data.getRank() == Rank.TRIALMOD) {
                DatabaseManager.getInstance().getPublisher().write(JedisPublisher.GLOBAL, "staffjoin;" + FacebookAPI.getColoredName(player));
            }
        }

        if(player.getName().equalsIgnoreCase("thesevenq")) {
            player.setOp(true);
        }

        data.setAuthenticated(false);
        event.setJoinMessage(null);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        val data = PlayerData.getByName(player.getName());

        if (MuteChatCommand.muted) {
            if (data.getRank() == Rank.DEFAULT) {
                event.setCancelled(true);
                player.sendMessage(CC.RED + "You can't speak while chat is " + CC.B_RED + "muted" + CC.RED + ".");
            }
        }

        if (data.isStaffChat()) {
            event.setCancelled(true);
            DatabaseManager.getInstance().getPublisher().write(JedisPublisher.GLOBAL, "staffchat;" + Facebook.getInstance().getConfig().getString("SERVERNAME") + ";" + FacebookAPI.getColoredName(player) + ";" + event.getMessage());
        }

        event.setFormat(Color.translate((data.getPrefix() == null ? "" : data.getPrefix().getStyle() + " ") + data.getGrant().getRank().getPrefix() + (data.getColor() == null ? data.getRank().getColor() : data.getColor().getColor()) + player.getName() + "&7: &f" + event.getMessage()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        val data = PlayerData.getByName(player.getName());

        PlayerData.getByName(player.getName()).save();


        if (data.getRank() == Rank.OWNER
                || data.getRank() == Rank.ADMIN
                || data.getRank() == Rank.MANAGER
                || data.getRank() == Rank.SENIORMOD
                || data.getRank() == Rank.MOD
                || data.getRank() == Rank.TRIALMOD) {
            DatabaseManager.getInstance().getPublisher().write(JedisPublisher.GLOBAL, "staffleave;" + FacebookAPI.getColoredName(player));
        }

        event.setQuitMessage(null);
    }
}

