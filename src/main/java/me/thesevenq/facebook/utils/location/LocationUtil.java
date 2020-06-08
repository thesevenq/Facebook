package me.thesevenq.facebook.utils.location;

import java.util.concurrent.ThreadLocalRandom;

import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.Tasks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class LocationUtil {
   private static final String[] FACES = new String[]{"S", "SW", "W", "NW", "N", "NE", "E", "SE"};

   public static Location deserializeLocation(String input) {
      String[] attributes = input.split(":");
      World world = null;
      Double x = null;
      Double y = null;
      Double z = null;
      Float pitch = null;
      Float yaw = null;
      String[] var8 = attributes;
      int var9 = attributes.length;

      for(int var10 = 0; var10 < var9; ++var10) {
         String attribute = var8[var10];
         String[] split = attribute.split(";");
         if (split[0].equalsIgnoreCase("#w")) {
            world = Bukkit.getWorld(split[1]);
         } else if (split[0].equalsIgnoreCase("#x")) {
            x = Double.parseDouble(split[1]);
         } else if (split[0].equalsIgnoreCase("#y")) {
            y = Double.parseDouble(split[1]);
         } else if (split[0].equalsIgnoreCase("#z")) {
            z = Double.parseDouble(split[1]);
         } else if (split[0].equalsIgnoreCase("#p")) {
            pitch = Float.parseFloat(split[1]);
         } else if (split[0].equalsIgnoreCase("#yaw")) {
            yaw = Float.parseFloat(split[1]);
         }
      }

      if (world != null && x != null && y != null && z != null && pitch != null && yaw != null) {
         return new Location(world, x, y, z, yaw, pitch);
      } else {
         return null;
      }
   }

   public static String serializeLocation(Location location) {
      return "#w;" + location.getWorld().getName() + ":#x;" + location.getX() + ":#y;" + location.getY() + ":#z;" + location.getZ() + ":#p;" + location.getPitch() + ":#yaw;" + location.getYaw();
   }

   public static String sexyLocation(Location location) {
      return CC.GRAY + "[" + CC.SECONDARY + location.getWorld().getName() + CC.GRAY + "] " + CC.GRAY + "(" + CC.SECONDARY + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + CC.GRAY + ")";
   }

   public static boolean checkZone(Location location, int input) {
      return Math.abs(location.getBlockX()) <= input && Math.abs(location.getBlockZ()) <= input;
   }

   public static void safeTeleport(Player player, Location location) {
      if (player.getVehicle() != null && player.getVehicle() instanceof Horse) {
         Horse vehicle = (Horse)player.getVehicle();
         vehicle.eject();
         Tasks.runLater(() -> {
            vehicle.teleport(location.add(0.0D, 1.0D, 0.0D));
         }, 1L);
         Tasks.runLater(() -> {
            vehicle.setPassenger(player);
         }, 2L);
      } else {
         player.setFallDistance(0.0F);
         player.teleport(location);
      }

   }

   public static Location getScatterLocation(int border, String worldName) {
      int x = ThreadLocalRandom.current().nextInt(-border + 10, border - 10);
      int z = ThreadLocalRandom.current().nextInt(-border + 10, border - 10);
      World world = Bukkit.getWorld(worldName);
      Block block = world.getHighestBlockAt(x, z);
      Material relative = block.getRelative(BlockFace.DOWN).getType();
      return block.getLocation().getY() >= 40.0D && !relative.name().endsWith("WATER") && !relative.name().endsWith("LAVA") ? block.getLocation().add(0.0D, 0.5D, 0.0D) : getScatterLocation(border, worldName);
   }

   public static void spawnHead(LivingEntity entity) {
      entity.getLocation().getBlock().setType(Material.NETHER_FENCE);
      entity.getWorld().getBlockAt(entity.getLocation().add(0.0D, 1.0D, 0.0D)).setType(Material.SKULL);
      Skull skull = (Skull)entity.getLocation().add(0.0D, 1.0D, 0.0D).getBlock().getState();
      if (entity instanceof Player) {
         Player player = (Player)entity;
         skull.setOwner(player.getName());
      } else {
         skull.setOwner(ChatColor.stripColor(entity.getCustomName()));
      }

      skull.update();
      Block block = entity.getLocation().add(0.0D, 1.0D, 0.0D).getBlock();
      block.setData((byte)1);
   }

   public static String getDirection(Player player) {
      return FACES[Math.round(player.getLocation().getYaw() / 45.0F) & 7];
   }

   public static Location getHighest(Location location) {
      int x = location.getBlockX();
      int y = 256;
      int z = location.getBlockZ();

      Block block;
      do {
         if ((double)y <= location.getY()) {
            return location;
         }

         World var10000 = location.getWorld();
         --y;
         block = var10000.getBlockAt(x, y, z);
      } while(block.isEmpty());

      location.setX((double)location.getBlockX() + 0.5D);
      location.setY((double)(block.getLocation().getBlockY() + 1));
      location.setZ((double)location.getBlockZ() + 0.5D);
      return location;
   }
}
