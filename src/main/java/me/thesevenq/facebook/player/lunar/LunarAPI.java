package me.thesevenq.facebook.player.lunar;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.player.lunar.impl.Notification;
import me.thesevenq.facebook.player.lunar.impl.ServerRule;
import me.thesevenq.facebook.player.lunar.impl.StaffModule;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class LunarAPI {
   private static Map users = new HashMap();

   public static void sendCooldown(Player player, String name, Material material, int seconds) throws IOException {
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      os.write(3);
      os.write(BufferUtils.writeString(name));
      os.write(BufferUtils.writeLong(TimeUnit.SECONDS.toMillis((long)seconds)));
      os.write(BufferUtils.writeInt(material.getId()));
      os.close();
      player.sendPluginMessage(Facebook.getInstance(), "Lunar-Client", os.toByteArray());
   }

   public static void sendCooldown(Player player, String name, Material material, long mill) throws IOException {
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      os.write(3);
      os.write(BufferUtils.writeString(name));
      os.write(BufferUtils.writeLong(mill));
      os.write(BufferUtils.writeInt(material.getId()));
      os.close();
      player.sendPluginMessage(Facebook.getInstance(), "Lunar-Client", os.toByteArray());
   }

   public static void sendTitle(Player player, boolean subTitle, String message, float size, int duration, int fadeIn, int fadeOut) throws IOException {
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      os.write(14);
      os.write(BufferUtils.writeString(subTitle ? "subtitle" : "normal"));
      os.write(BufferUtils.writeString(message));
      os.write(BufferUtils.writeFloat(size));
      os.write(BufferUtils.writeLong(TimeUnit.SECONDS.toMillis((long)duration)));
      os.write(BufferUtils.writeLong(TimeUnit.SECONDS.toMillis((long)fadeIn)));
      os.write(BufferUtils.writeLong(TimeUnit.SECONDS.toMillis((long)fadeOut)));
      os.close();
      player.sendPluginMessage(Facebook.getInstance(), "Lunar-Client", os.toByteArray());
   }

   public static void performEmote(Player player, int type, boolean everyone, int nearby) throws IOException {
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      os.write(26);
      os.write(BufferUtils.getBytesFromUUID(player.getUniqueId()));
      os.write(BufferUtils.writeInt(type));
      os.close();
      if (everyone) {
         Iterator var5 = player.getNearbyEntities((double)nearby, (double)nearby, (double)nearby).iterator();

         while(var5.hasNext()) {
            Entity entity = (Entity)var5.next();
            if (entity instanceof Player) {
               Player eplayer = (Player)entity;
               if (eplayer.isLunarClient()) {
                  eplayer.sendPluginMessage(Facebook.getInstance(), "Lunar-Client", os.toByteArray());
               }
            }
         }
      } else {
         player.sendPluginMessage(Facebook.getInstance(), "Lunar-Client", os.toByteArray());
      }

   }

   public static void sendNotification(Player player, String message, Notification level, int delay) throws IOException {
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      os.write(9);
      os.write(BufferUtils.writeString(message));
      os.write(BufferUtils.writeLong(TimeUnit.SECONDS.toMillis((long)delay)));
      os.write(BufferUtils.writeString(level.name()));
      os.close();
      player.sendPluginMessage(Facebook.getInstance(), "Lunar-Client", os.toByteArray());
   }

   public static void toggleStaffModule(Player player, StaffModule module, boolean enabled) throws IOException {
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      os.write(12);
      os.write(BufferUtils.writeString(module.name()));
      os.write(BufferUtils.writeBoolean(enabled));
      os.close();
      player.sendPluginMessage(Facebook.getInstance(), "Lunar-Client", os.toByteArray());
   }

   public static void updateServerRule(Player player, ServerRule rule, boolean b, int i, float f, String s) throws IOException {
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      os.write(10);
      os.write(BufferUtils.writeString(rule.getName()));
      os.write(BufferUtils.writeBoolean(b));
      os.write(BufferUtils.writeInt(i));
      os.write(BufferUtils.writeFloat(f));
      os.write(BufferUtils.writeString(s));
      os.close();
      player.sendPluginMessage(Facebook.getInstance(), "Lunar-Client", os.toByteArray());
   }

   public static void updateNameTag(Player player, Player target, String... tags) throws IOException {
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      os.write(7);
      os.write(BufferUtils.getBytesFromUUID(target.getUniqueId()));
      os.write(BufferUtils.writeBoolean(true));
      os.write(BufferUtils.writeVarInt(tags.length));
      String[] var4 = tags;
      int var5 = tags.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         String tag = var4[var6];
         os.write(BufferUtils.writeString(tag));
      }

      os.close();
      player.sendPluginMessage(Facebook.getInstance(), "Lunar-Client", os.toByteArray());
   }

   public static void sendTeamMate(Player player, Player... targets) throws IOException {
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      os.write(13);
      os.write(BufferUtils.writeBoolean(true));
      os.write(BufferUtils.getBytesFromUUID(player.getUniqueId()));
      os.write(BufferUtils.writeLong(10L));
      Map playerMap = new HashMap();
      Player[] var4 = targets;
      int var5 = targets.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         Player member = var4[var6];
         Map posMap = new HashMap();
         posMap.put("x", member.getLocation().getX());
         posMap.put("y", member.getLocation().getY());
         posMap.put("z", member.getLocation().getZ());
         playerMap.put(member.getUniqueId(), posMap);
      }

      Map posMap = new HashMap();
      posMap.put("x", player.getLocation().getX());
      posMap.put("y", player.getLocation().getY());
      posMap.put("z", player.getLocation().getZ());
      playerMap.put(player.getUniqueId(), posMap);
      os.write(BufferUtils.writeVarInt(playerMap.size()));
      Iterator var10 = playerMap.entrySet().iterator();

      while(var10.hasNext()) {
         Entry entry = (Entry)var10.next();
         os.write(BufferUtils.getBytesFromUUID((UUID)entry.getKey()));
         os.write(BufferUtils.writeVarInt(((Map)entry.getValue()).size()));
         Iterator var12 = ((Map)entry.getValue()).entrySet().iterator();

         while(var12.hasNext()) {
            Entry posEntry = (Entry)var12.next();
            os.write(BufferUtils.writeString((String)posEntry.getKey()));
            os.write(BufferUtils.writeDouble((Double)posEntry.getValue()));
         }
      }

      os.close();
      player.sendPluginMessage(Facebook.getInstance(), "Lunar-Client", os.toByteArray());
   }

   public static Map getUsers() {
      return users;
   }

   public static class StaffData {
      private boolean xray;
      private boolean bunnyHop;
      private boolean nameTags;
      private boolean noClip;

      public boolean isXray() {
         return this.xray;
      }

      public boolean isBunnyHop() {
         return this.bunnyHop;
      }

      public boolean isNameTags() {
         return this.nameTags;
      }

      public boolean isNoClip() {
         return this.noClip;
      }

      public void setXray(boolean xray) {
         this.xray = xray;
      }

      public void setBunnyHop(boolean bunnyHop) {
         this.bunnyHop = bunnyHop;
      }

      public void setNameTags(boolean nameTags) {
         this.nameTags = nameTags;
      }

      public void setNoClip(boolean noClip) {
         this.noClip = noClip;
      }
   }
}
