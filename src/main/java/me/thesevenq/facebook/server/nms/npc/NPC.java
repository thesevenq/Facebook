package me.thesevenq.facebook.server.nms.npc;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import me.thesevenq.facebook.server.nms.helpers.NMSHelper;
import me.thesevenq.facebook.register.FacebookRegister;
import me.thesevenq.facebook.utils.Color;
import me.thesevenq.facebook.utils.Tasks;
import me.thesevenq.facebook.utils.location.LocationUtil;
import net.minecraft.server.v1_7_R4.DataWatcher;
import net.minecraft.server.v1_7_R4.Entity;
import net.minecraft.server.v1_7_R4.EntityCreature;
import net.minecraft.server.v1_7_R4.EntityLiving;
import net.minecraft.server.v1_7_R4.EntityPigZombie;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.EntitySkeleton;
import net.minecraft.server.v1_7_R4.EntityVillager;
import net.minecraft.server.v1_7_R4.EntityZombie;
import net.minecraft.server.v1_7_R4.MathHelper;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityLook;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_7_R4.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_7_R4.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_7_R4.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_7_R4.PlayerInteractManager;
import net.minecraft.util.com.mojang.authlib.GameProfile;
import net.minecraft.util.com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.entity.EntityType;

public class NPC extends NMSHelper {
   private static Map<String, NPC> npcs = new HashMap();
   private UUID uuid;
   private String message;
   private String name;
   private String command;
   private Location location;
   private int entityId;
   private EntityType type;

   public NPC() {
      this.load();
   }

   public NPC(String message, String name, String command, Location location, EntityType type) {
      this.uuid = UUID.randomUUID();
      this.message = message;
      this.name = name;
      this.command = command;
      this.location = location;
      this.type = type;
      this.entityId = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
      npcs.put(name, this);
   }

   private void load() {
      FileConfiguration config = FacebookRegister.getInstance().getNpcs();
      ConfigurationSection section = config.getConfigurationSection("npcs");
      if (section != null) {
         section.getKeys(false).forEach((key) -> {
            key = "npcs." + key + '.';
            (new NPC(config.getString(key + "message"), config.getString(key + "name"), config.getString(key + "command"), LocationUtil.deserializeLocation(config.getString(key + "location")), EntityType.valueOf(config.getString(key + "type")))).spawn();
         });
      }
   }

   public void save() {
      FileConfiguration config = FacebookRegister.getInstance().getNpcs();
      config.set("npcs", null);
      npcs.values().stream().filter(Objects::nonNull).forEach((npc) -> {
         String key = "npcs." + npc.getName() + '.';
         config.set(key + "message", npc.getMessage());
         config.set(key + "name", npc.getName());
         config.set(key + "command", npc.getCommand());
         config.set(key + "location", LocationUtil.serializeLocation(npc.getLocation()));
         config.set(key + "type", npc.getType().name());
      });
      FacebookRegister.getInstance().getNpcs().save();
   }

   public void sendSpawnPackets(EntityPlayer player, boolean rebuild) {
      PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport();
      if (!rebuild) {
         teleport.a = this.entityId;
      }

      teleport.b = MathHelper.floor(this.location.getX() * 32.0D);
      teleport.c = MathHelper.floor(this.location.getY() * 32.0D);
      teleport.d = MathHelper.floor(this.location.getZ() * 32.0D);
      teleport.onGround = false;
      Object entity;
      switch(this.type) {
      case ZOMBIE:
         entity = new EntityZombie(((CraftWorld)this.location.getWorld()).getHandle());
         break;
      case VILLAGER:
         entity = new EntityVillager(((CraftWorld)this.location.getWorld()).getHandle());
         break;
      case SKELETON:
         entity = new EntitySkeleton(((CraftWorld)this.location.getWorld()).getHandle());
         break;
      case PIG_ZOMBIE:
         entity = new EntityPigZombie(((CraftWorld)this.location.getWorld()).getHandle());
         break;
      case PLAYER:
         GameProfile gameProfile = new GameProfile(this.uuid, this.getNiceMessage());
         Property property = player.getProfile().getProperties().get("textures").iterator().next();
         gameProfile.getProperties().put("textures", new Property("textures", property.getValue(), property.getSignature()));
         NPCEntity npc = new NPCEntity(((CraftWorld)this.location.getWorld()).getHandle().getMinecraftServer(), ((CraftWorld)this.location.getWorld()).getHandle(), gameProfile, new PlayerInteractManager(((CraftWorld)this.location.getWorld()).getHandle()));
         if (!rebuild) {
            npc.d(this.entityId);
         }

         player.playerConnection.sendPacket(PacketPlayOutPlayerInfo.addPlayer(npc));
         PacketPlayOutNamedEntitySpawn packet = new PacketPlayOutNamedEntitySpawn(npc);
         packet.c = MathHelper.floor(this.location.getX() * 32.0D);
         packet.d = MathHelper.floor(this.location.getY() * 32.0D);
         packet.e = MathHelper.floor(this.location.getZ() * 32.0D);
         packet.f = (byte)((int)(this.location.getYaw() * 256.0F / 360.0F));
         packet.g = (byte)((int)(this.location.getPitch() * 256.0F / 360.0F));
         player.playerConnection.sendPacket(packet);
         player.playerConnection.sendPacket(teleport);
         player.playerConnection.sendPacket(new PacketPlayOutEntityLook(this.entityId, (byte)((int)(this.location.getYaw() * 256.0F / 360.0F)), (byte)((int)(this.location.getPitch() * 256.0F / 360.0F)), true));
         PacketPlayOutEntityHeadRotation packetHead = new PacketPlayOutEntityHeadRotation();
         packetHead.a = this.entityId;
         packetHead.b = (byte)((int)(this.location.getYaw() * 256.0F / 360.0F));
         player.playerConnection.sendPacket(packetHead);
         Tasks.runAsyncLater(() -> {
            player.playerConnection.sendPacket(PacketPlayOutPlayerInfo.removePlayer(npc));
         }, 10L);
         return;
      default:
         throw new UnsupportedOperationException("Unsupported yet");
      }

      if (!rebuild) {
         ((EntityCreature)entity).d(this.entityId);
      }

      ((EntityCreature)entity).setCustomName(this.getNiceMessage());
      ((EntityCreature)entity).setCustomNameVisible(!ChatColor.stripColor(this.getNiceMessage()).isEmpty());
      ((EntityCreature)entity).setInvisible(false);
      PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving((EntityLiving)entity);
      packet.c = MathHelper.floor(this.location.getX() * 32.0D);
      packet.d = MathHelper.floor(this.location.getY() * 32.0D);
      packet.e = MathHelper.floor(this.location.getZ() * 32.0D);
      packet.i = (byte)((int)(this.location.getYaw() * 256.0F / 360.0F));
      packet.j = (byte)((int)(this.location.getPitch() * 256.0F / 360.0F));
      packet.k = (byte)((int)(this.location.getYaw() * 256.0F / 360.0F));
      player.playerConnection.sendPacket(packet);
      player.playerConnection.sendPacket(teleport);
   }

   private void sendMessagePacket(EntityPlayer player) {
      DataWatcher watcher = new DataWatcher((Entity)null);
      watcher.a(10, this.getNiceMessage());
      player.playerConnection.sendPacket(new PacketPlayOutEntityMetadata(this.entityId, watcher, true));
   }

   public void sendDestroyPacket(EntityPlayer player) {
      player.playerConnection.sendPacket(new PacketPlayOutEntityDestroy(this.entityId, this.entityId + 1));
   }

   public void spawn() {
      this.getEntityPlayers(this.location).forEach((viewer) -> {
         this.sendSpawnPackets(viewer, false);
      });
   }

   public void delete() {
      this.getEntityPlayers(this.location).forEach(this::sendDestroyPacket);
      npcs.values().remove(this);
   }

   public void setMessage(String input) {
      this.message = Color.translate(input);
      this.getEntityPlayers(this.location).forEach((viewer) -> {
         this.sendSpawnPackets(viewer, true);
         this.sendMessagePacket(viewer);
      });
   }

   public String getNiceMessage() {
      return Color.translate(this.message);
   }

   public static NPC getByName(String input) {
      return npcs.values().stream().filter((npc) -> npc.getName().equalsIgnoreCase(input)).findFirst().orElse(null);
   }

   public static NPC getById(int id) {
      return npcs.values().stream().filter((npc) -> {
         return npc.getEntityId() == id;
      }).findFirst().orElse(null);
   }

   public UUID getUuid() {
      return this.uuid;
   }

   public String getMessage() {
      return this.message;
   }

   public String getName() {
      return this.name;
   }

   public String getCommand() {
      return this.command;
   }

   public Location getLocation() {
      return this.location;
   }

   public int getEntityId() {
      return this.entityId;
   }

   public EntityType getType() {
      return this.type;
   }

   public static Map<String, NPC> getNpcs() {
      return npcs;
   }

   public void setCommand(String command) {
      this.command = command;
   }
}
