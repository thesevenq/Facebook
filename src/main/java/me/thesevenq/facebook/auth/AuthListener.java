package me.thesevenq.facebook.auth;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.string.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class AuthListener implements Listener {

    public AuthListener() {
        Bukkit.getPluginManager().registerEvents(this, Facebook.getInstance());
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getByName(player.getName());

        if (Facebook.getInstance().getConfig().getBoolean("AUTHENTICATION.ENABLED") && !playerData.isAuthenticated()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getByName(player.getName());

        if (Facebook.getInstance().getConfig().getBoolean("AUTHENTICATION.ENABLED")) {

            if (!playerData.isRegistered()) {
                player.sendMessage(Color.translate(Facebook.getInstance().getConfig().getString("REGISTER_MESSAGE")));
            } else if (!playerData.isAuthenticated()) {
                player.sendMessage(Color.translate(Facebook.getInstance().getConfig().getString("LOGIN_MESSAGE")));
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getByName(player.getName());

        if (Facebook.getInstance().getConfig().getBoolean("AUTHENTICATION.ENABLED") && !playerData.isAuthenticated()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getByName(player.getName());

        if (Facebook.getInstance().getConfig().getBoolean("AUTHENTICATION.ENABLED") && !playerData.isAuthenticated()) {
            event.setCancelled(true);
        }
    }
}