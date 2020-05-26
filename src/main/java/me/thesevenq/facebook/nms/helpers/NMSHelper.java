package me.thesevenq.facebook.nms.helpers;

import me.thesevenq.facebook.nms.hologram.Hologram;
import me.thesevenq.facebook.nms.npc.NPC;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.stream.Stream;

public class NMSHelper {

    public static void respawnHologramsAndNpcs(Player player) {
        Hologram.getHolograms().values().stream().filter((holo) -> holo != null && holo.getLocation().getWorld().getName().equalsIgnoreCase(player.getWorld().getName())).forEach((holo) -> {
            holo.spawn(((CraftPlayer)player).getHandle().playerConnection.player, true);
        });
        NPC.getNpcs().values().stream().filter((npc) -> npc != null && npc.getLocation().getWorld().getName().equalsIgnoreCase(player.getWorld().getName())).forEach((npc) -> {
            npc.sendSpawnPackets(((CraftPlayer)player).getHandle().playerConnection.player, false);
        });
    }

    public static void setValueStatic(Object object, String name, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public static Object getValueStatic(Object object, String name) {
        try {
            Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    protected void setValue(Object object, String name, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    protected Object getValue(Object object, String name) {
        try {
            Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    protected Stream<EntityPlayer> getEntityPlayers(Location location) {
        return location.getWorld().getPlayers().stream().map((player) -> ((CraftPlayer)player).getHandle());
    }
}

