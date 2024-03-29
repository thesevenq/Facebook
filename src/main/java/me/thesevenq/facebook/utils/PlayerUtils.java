package me.thesevenq.facebook.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.stream.Stream;

import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityDestroy;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerUtils {
    private static Map entityIds = new WeakHashMap();
    private static int currentFakeEntityId = -1;

    public static EntityPlayer getNMSPlayer(Player player) {
        return ((CraftPlayer)player).getHandle();
    }

    public static void unsit(Player player) {
        if (entityIds.containsKey(player.getUniqueId())) {
            ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(new int[]{(Integer)entityIds.get(player.getUniqueId())}));
            entityIds.remove(player.getUniqueId());
        }

    }

    public static void clearPlayer(Player player) {
        player.setHealth(20.0D);
        player.setFoodLevel(20);
        player.setSaturation(12.8F);
        player.setMaximumNoDamageTicks(20);
        player.setFireTicks(0);
        player.setFallDistance(0.0F);
        player.setLevel(0);
        player.setExp(0.0F);
        player.setWalkSpeed(0.2F);
        player.setFlySpeed(0.1F);
        player.getInventory().setHeldItemSlot(0);
        player.setAllowFlight(false);
        player.getInventory().clear();
        player.getInventory().setArmorContents((ItemStack[])null);
        player.closeInventory();
        player.setGameMode(GameMode.SURVIVAL);
        player.getActivePotionEffects().stream().map(PotionEffect::getType).forEach(player::removePotionEffect);
        ((CraftPlayer)player).getHandle().getDataWatcher().watch(9, (byte)0);
        player.getOpenInventory().getTopInventory().clear();
        player.updateInventory();
    }

    public static void freeze(Player player, boolean value) {
        if (value) {
            player.setGameMode(GameMode.SURVIVAL);
            player.setWalkSpeed(0.0F);
            player.setFlySpeed(0.0F);
            player.setFoodLevel(0);
            player.setSprinting(false);
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 200));
        } else {
            player.setWalkSpeed(0.2F);
            player.setFlySpeed(0.1F);
            player.setFoodLevel(20);
            player.setSprinting(true);
            player.removePotionEffect(PotionEffectType.JUMP);
        }

    }

    /*public static boolean hasVotedOnNameMC(UUID uuid) {
        try {
            Scanner scanner = (new Scanner((new URL("https://api.namemc.com/server/sicaromc.info/likes?profile=" + uuid.toString())).openStream())).useDelimiter("\\A");
            Throwable var2 = null;

            boolean var3;
            try {
                var3 = Boolean.valueOf(scanner.next());
            } catch (Throwable var13) {
                var2 = var13;
                throw var13;
            } finally {
                if (scanner != null) {
                    if (var2 != null) {
                        try {
                            scanner.close();
                        } catch (Throwable var12) {
                            var2.addSuppressed(var12);
                        }
                    } else {
                        scanner.close();
                    }
                }

            }

            return var3;
        } catch (IOException var15) {
            var15.printStackTrace();
            return false;
        }
    }*/

    public static boolean isFullInventory(Player player) {
        return player.getInventory().firstEmpty() == -1;
    }


    public static int getFakeEntityId() {
        return currentFakeEntityId--;
    }
}
