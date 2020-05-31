package me.thesevenq.facebook.player.freeze;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.FacebookAPI;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.ranks.Rank;
import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.Color;
import me.thesevenq.facebook.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

import java.util.stream.Stream;

public class FreezeListener implements Listener {

    private Facebook plugin = Facebook.getInstance();

    public FreezeListener() {
        Bukkit.getPluginManager().registerEvents(this, Facebook.getInstance());
    }

    private String[] commands = {
            "/hub", "/lobby", "/join",
            "/play", "/f home", "/fac home",
            "/faction home", "/f leave", "/fac leave",
            "/faction leave"
    };

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        if (!PlayerData.getByName(event.getPlayer().getName()).isFrozen()) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (PlayerData.getByName(event.getPlayer().getName()).isFrozen()) event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player victim = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();

            if (plugin.getFreezeManager().isFrozen()) {
                event.setCancelled(true);
                return;
            }

            if (PlayerData.getByName(damager.getName()).isFrozen()) {
                event.setCancelled(true);
                damager.sendMessage(Color.translate("&cYou can not damage other players while frozen."));
            }

            if (PlayerData.getByName(victim.getName()).isFrozen()) {
                event.setCancelled(true);
                damager.sendMessage(Color.translate("&c&l" + victim.getName() + " &cis frozen. You can not damage him."));
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!PlayerData.getByName(event.getPlayer().getName()).isFrozen()) return;
        if (event.getItem() == null || event.getItem().getType() != Material.ENDER_PEARL) return;

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            event.setUseItemInHand(Result.DENY);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!PlayerData.getByName(((Player) event.getEntity()).getName()).isFrozen()) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (!PlayerData.getByName(event.getPlayer().getName()).isFrozen()) return;

        if (Stream.of(commands).noneMatch(event.getMessage().toLowerCase()::startsWith)) return;

        event.setCancelled(true);
        event.getPlayer().sendMessage(Color.translate("&cYou cannot use this command while frozen."));
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!PlayerData.getByName(event.getPlayer().getName()).isFrozen()) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!PlayerData.getByName(event.getPlayer().getName()).isFrozen()) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        if (!PlayerData.getByName(event.getWhoClicked().getName()).isFrozen()) return;

        event.setCancelled(true);
    }


    @EventHandler
    public void onPlayerItemDrop(PlayerDropItemEvent event) {
        if (!PlayerData.getByName(event.getPlayer().getName()).isFrozen()) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        if (!PlayerData.getByName(event.getPlayer().getName()).isFrozen()) return;

        event.setCancelled(true);
    }
}