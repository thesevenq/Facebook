package me.thesevenq.facebook.nms.bossbar;

import com.google.common.base.Preconditions;
import java.beans.ConstructorProperties;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.utils.PlayerUtils;
import me.thesevenq.facebook.utils.Tasks;
import net.minecraft.server.v1_7_R4.DataWatcher;
import net.minecraft.server.v1_7_R4.Entity;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.MinecraftServer;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_7_R4.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_7_R4.WatchableObject;
import net.minecraft.util.gnu.trove.map.hash.TObjectIntHashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class BossBar {
   private static Map displaying = new HashMap();
   private static Map lastUpdatedPosition = new HashMap();
   private static TObjectIntHashMap classToIdMap = null;

   public static void init() {
      Tasks.runTimer(() -> {
         Iterator var0 = displaying.keySet().iterator();

         while(true) {
            Player player;
            int updateTicks;
            do {
               do {
                  if (!var0.hasNext()) {
                     return;
                  }

                  UUID uuid = (UUID)var0.next();
                  player = Bukkit.getPlayer(uuid);
               } while(player == null);

               updateTicks = ((CraftPlayer)player).getHandle().playerConnection.networkManager.getVersion() != 47 ? 60 : 3;
            } while(lastUpdatedPosition.containsKey(player.getUniqueId()) && MinecraftServer.currentTick - (Integer)lastUpdatedPosition.get(player.getUniqueId()) < updateTicks);

            updatePosition(player);
            lastUpdatedPosition.put(player.getUniqueId(), MinecraftServer.currentTick);
         }
      }, 1L, 1L);
      Bukkit.getPluginManager().registerEvents(new Listener() {
         @EventHandler
         public void onPlayerQuit(PlayerQuitEvent event) {
            BossBar.removeBossBar(event.getPlayer());
         }

         @EventHandler
         public void onPlayerTeleport(PlayerTeleportEvent event) {
            Player player = event.getPlayer();
            if (BossBar.displaying.containsKey(player.getUniqueId())) {
               BossBar.BarData data = (BossBar.BarData)BossBar.displaying.get(player.getUniqueId());
               String message = data.message;
               float health = data.health;
               BossBar.removeBossBar(player);
               BossBar.setBossBar(player, message, health);
            }
         }
      }, Facebook.getInstance());
   }

   public static void setBossBar(Player player, String message, float health) {
      try {
         if (message == null) {
            removeBossBar(player);
            return;
         }

         Preconditions.checkArgument(health >= 0.0F && health <= 1.0F, "Health must be between 0 and 1");
         if (message.length() > 64) {
            message = message.substring(0, 64);
         }

         message = ChatColor.translateAlternateColorCodes('&', message);
         if (!displaying.containsKey(player.getUniqueId())) {
            sendSpawnPacket(player, message, health);
         } else {
            sendUpdatePacket(player, message, health);
         }

         BossBar.BarData data = (BossBar.BarData)displaying.get(player.getUniqueId());
         data.message = message;
         data.health = health;
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   public static void removeBossBar(Player player) {
      if (displaying.containsKey(player.getUniqueId())) {
         int entityId = ((BossBar.BarData)displaying.get(player.getUniqueId())).entityId;
         ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(new int[]{entityId}));
         displaying.remove(player.getUniqueId());
         lastUpdatedPosition.remove(player.getUniqueId());
      }
   }

   private static void sendSpawnPacket(Player bukkitPlayer, String message, float health) {
      EntityPlayer player = ((CraftPlayer)bukkitPlayer).getHandle();
      int version = player.playerConnection.networkManager.getVersion();
      displaying.put(bukkitPlayer.getUniqueId(), new BossBar.BarData(PlayerUtils.getFakeEntityId(), message, health));
      BossBar.BarData stored = (BossBar.BarData)displaying.get(bukkitPlayer.getUniqueId());
      PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving();
      packet.a = stored.entityId;
      DataWatcher watcher = new DataWatcher((Entity)null);
      if (version != 47) {
         packet.b = (byte)EntityType.ENDER_DRAGON.getTypeId();
         packet.c = (int)(player.locX * 32.0D);
         packet.d = -6400;
         packet.e = (int)(player.locZ * 32.0D);
         watcher.a(6, health * 200.0F);
      } else {
         packet.b = (byte)EntityType.WITHER.getTypeId();
         double pitch = Math.toRadians((double)player.pitch);
         double yaw = Math.toRadians((double)player.yaw);
         packet.c = (int)((player.locX - Math.sin(yaw) * Math.cos(pitch) * 32.0D) * 32.0D);
         packet.d = (int)((player.locY - Math.sin(pitch) * 32.0D) * 32.0D);
         packet.e = (int)((player.locZ - Math.sin(yaw) * Math.cos(pitch) * 32.0D) * 32.0D);
         watcher.a(6, health * 300.0F);
         watcher.a(20, 880);
      }

      watcher.a(version != 47 ? 10 : 2, message);
      packet.l = watcher;
      player.playerConnection.sendPacket(packet);
   }

   private static void sendUpdatePacket(Player bukkitPlayer, String message, float health) {
      EntityPlayer player = ((CraftPlayer)bukkitPlayer).getHandle();
      int version = player.playerConnection.networkManager.getVersion();
      BossBar.BarData stored = (BossBar.BarData)displaying.get(bukkitPlayer.getUniqueId());
      PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata();
      packet.a = stored.entityId;
      List objects = new ArrayList();
      if (health != stored.health) {
         if (version != 47) {
            objects.add(createWatchableObject(6, health * 200.0F));
         } else {
            objects.add(createWatchableObject(6, health * 300.0F));
         }
      }

      if (!message.equals(stored.message)) {
         objects.add(createWatchableObject(version != 47 ? 10 : 2, message));
      }

      packet.b = objects;
      player.playerConnection.sendPacket(packet);
   }

   private static WatchableObject createWatchableObject(int id, Object object) {
      return new WatchableObject(classToIdMap.get(object.getClass()), id, object);
   }

   private static void updatePosition(Player bukkitPlayer) {
      if (displaying.containsKey(bukkitPlayer.getUniqueId())) {
         EntityPlayer player = ((CraftPlayer)bukkitPlayer).getHandle();
         int version = player.playerConnection.networkManager.getVersion();
         int x;
         int y;
         int z;
         if (version != 47) {
            x = (int)(player.locX * 32.0D);
            y = -6400;
            z = (int)(player.locZ * 32.0D);
         } else {
            double pitch = Math.toRadians((double)player.pitch);
            double yaw = Math.toRadians((double)player.yaw);
            x = (int)((player.locX - Math.sin(yaw) * Math.cos(pitch) * 32.0D) * 32.0D);
            y = (int)((player.locY - Math.sin(pitch) * 32.0D) * 32.0D);
            z = (int)((player.locZ + Math.cos(yaw) * Math.cos(pitch) * 32.0D) * 32.0D);
         }

         player.playerConnection.sendPacket(new PacketPlayOutEntityTeleport(((BossBar.BarData)displaying.get(bukkitPlayer.getUniqueId())).entityId, x, y, z, (byte)0, (byte)0));
      }
   }

   static {
      try {
         Field dataWatcherClassToIdField = DataWatcher.class.getDeclaredField("classToId");
         dataWatcherClassToIdField.setAccessible(true);
         classToIdMap = (TObjectIntHashMap)dataWatcherClassToIdField.get((Object)null);
      } catch (Exception var1) {
         var1.printStackTrace();
      }

   }

   private static class BarData {
      private int entityId;
      private String message;
      private float health;

      @ConstructorProperties({"entityId", "message", "health"})
      public BarData(int entityId, String message, float health) {
         this.entityId = entityId;
         this.message = message;
         this.health = health;
      }
   }
}
