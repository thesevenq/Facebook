package me.thesevenq.facebook.utils.trail;

import me.thesevenq.facebook.Facebook;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

public class ProjectileTrail {
   public static void play(final Entity item, final ParticleEffect effect) {
      (new BukkitRunnable() {
         public void run() {
            if (item != null && !item.isOnGround() && !item.isDead()) {
               effect.display(0.0F, 0.0F, 0.0F, 0.0F, 1, item.getLocation(), 256.0D);
            } else {
               this.cancel();
            }
         }
      }).runTaskTimer(Facebook.getInstance(), 0L, 1L);
   }
}
