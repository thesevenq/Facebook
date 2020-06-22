package me.thesevenq.facebook.player.cosmetics;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.player.cosmetics.deathanimation.KillEffectType;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.trail.ParticleEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class CosmeticListeners implements Listener {
   private Map users = new HashMap();

   public CosmeticListeners() {
      (new BukkitRunnable() {
         public void run() {
            if (!CosmeticListeners.this.users.isEmpty()) {
               Iterator iterator = CosmeticListeners.this.users.entrySet().iterator();

               while(iterator.hasNext()) {
                  Entry entry = (Entry)iterator.next();
                  Entity item = (Entity)entry.getKey();
                  if (item == null || item.isOnGround() || item.isDead()) {
                     iterator.remove();
                     return;
                  }

                  ((ParticleEffect)entry.getValue()).display(0.0F, 0.0F, 0.0F, 0.0F, 1, item.getLocation(), 256.0D);
               }

            }
         }
      }).runTaskTimerAsynchronously(Facebook.getInstance(), 0L, 2L);
   }

   /*@EventHandler
   public void onProjectileShoot(ProjectileLaunchEvent event) {
      Projectile entity = event.getEntity();
      if (entity.getType() != EntityType.SPLASH_POTION) {
         if (entity.getShooter() instanceof Player) {
            TrailType trail = PlayerData.getByUuidWithoutLoad(((Player)entity.getShooter()).getUniqueId()).getTrail();
            if (trail != null) {
               this.users.put(entity, trail.getEffect());
            }
         }

      }
   }*/

   @EventHandler
   public void onPlayerDeath(PlayerDeathEvent event) {
      Player player = event.getEntity();
      Player killer = player.getKiller();
      if (killer != null) {
         PlayerData data = PlayerData.getByName(killer.getName());
         KillEffectType effect = data.getKillEffectType();
         if (effect != null && effect.getCallable() != null) {
            effect.getCallable().call(player.getLocation().clone().add(0.0D, 1.0D, 0.0D));
         }
      }

   }
}
