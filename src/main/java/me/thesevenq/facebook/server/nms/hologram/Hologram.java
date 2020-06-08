package me.thesevenq.facebook.server.nms.hologram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import me.thesevenq.facebook.FacebookAPI;
import me.thesevenq.facebook.server.nms.helpers.NMSHelper;
import me.thesevenq.facebook.utils.register.FacebookRegister;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.location.LocationUtil;
import net.minecraft.server.v1_7_R4.DataWatcher;
import net.minecraft.server.v1_7_R4.Entity;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.MathHelper;
import net.minecraft.server.v1_7_R4.PacketPlayOutAttachEntity;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_7_R4.PacketPlayOutSpawnEntity;
import net.minecraft.server.v1_7_R4.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class Hologram extends NMSHelper {
    private static Map<String, Hologram> holograms = new HashMap();
    private String message;
    private String name;
    private Location location;
    private int entityId;
    private List<Hologram> linesAbove;
    private List<Hologram> linesBelow;

    public Hologram() {
        this.linesAbove = new ArrayList();
        this.linesBelow = new ArrayList();
        this.load();
    }

    public Hologram(String message, Location location) {
        this(message, "hologram-" + ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE), location, true);
    }

    public Hologram(String message, String name, Location location, boolean add) {
        this.linesAbove = new ArrayList();
        this.linesBelow = new ArrayList();
        this.message = message;
        this.name = name;
        this.location = location;
        this.entityId = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
        if (add) {
            holograms.put(name, this);
        }

    }

    private void load() {
        FileConfiguration config = FacebookRegister.getInstance().getHolograms();
        ConfigurationSection section = config.getConfigurationSection("holograms");
        if (section != null) {
            section.getKeys(false).forEach((key) -> {
                key = "holograms." + key + '.';
                Hologram hologram = new Hologram(config.getString(key + "message"), config.getString(key + "name"), LocationUtil.deserializeLocation(config.getString(key + "location")), true);
                ConfigurationSection aboveSection = config.getConfigurationSection(key + "above");
                String root;
                if (aboveSection != null) {
                    Iterator var4 = aboveSection.getKeys(false).iterator();

                    while (var4.hasNext()) {
                        String rootx = (String) var4.next();
                        root = key + "above." + rootx + '.';
                        hologram.getLinesAbove().add(new Hologram(config.getString(root + "message"), config.getString(root + "name"), LocationUtil.deserializeLocation(config.getString(root + "location")), false));
                    }
                }

                ConfigurationSection belowSection = config.getConfigurationSection(key + "below");
                if (belowSection != null) {
                    Iterator var9 = belowSection.getKeys(false).iterator();

                    while (var9.hasNext()) {
                        root = (String) var9.next();
                        String belowKey = key + "below." + root + '.';
                        hologram.getLinesBelow().add(new Hologram(config.getString(belowKey + "message"), config.getString(belowKey + "name"), LocationUtil.deserializeLocation(config.getString(belowKey + "location")), false));
                    }
                }

                hologram.spawn(true);
            });
        }
    }

    public void save() {
        String server = FacebookAPI.getServerName();
        FileConfiguration config = FacebookRegister.getInstance().getHolograms();
        config.set("holograms", null);
        holograms.values().stream().filter(Objects::nonNull).forEach((hologram) -> {

            String key;
            if (server.startsWith("uhc")) {
                key = hologram.getName();
            }

            key = "holograms." + hologram.getName() + '.';
            config.set(key + "message", hologram.getMessage());
            config.set(key + "name", hologram.getName());
            config.set(key + "location", LocationUtil.serializeLocation(hologram.getLocation()));
            Iterator var4;
            Hologram line;
            String belowKey;
            if (hologram.getLinesAbove() != null && !hologram.getLinesAbove().isEmpty()) {
                var4 = hologram.getLinesAbove().iterator();

                while (var4.hasNext()) {
                    line = (Hologram) var4.next();
                    belowKey = key + "above." + line.getName() + '.';
                    config.set(belowKey + "message", line.getMessage());
                    config.set(belowKey + "name", line.getName());
                    config.set(belowKey + "location", LocationUtil.serializeLocation(line.getLocation()));
                }
            }

            if (hologram.getLinesBelow() != null && !hologram.getLinesBelow().isEmpty()) {
                var4 = hologram.getLinesBelow().iterator();

                while (var4.hasNext()) {
                    line = (Hologram) var4.next();
                    belowKey = key + "below." + line.getName() + '.';
                    config.set(belowKey + "message", line.getMessage());
                    config.set(belowKey + "name", line.getName());
                    config.set(belowKey + "location", LocationUtil.serializeLocation(line.getLocation()));
                }
            }

        });
        FacebookRegister.getInstance().getHolograms().save();
    }


    public void sendTeleportPacket(EntityPlayer player) {
        PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport();
        teleport.a = this.entityId;
        teleport.b = MathHelper.floor(this.location.getX() * 32.0D);
        teleport.c = MathHelper.floor(this.location.getY() * 32.0D);
        teleport.d = MathHelper.floor(this.location.getZ() * 32.0D);
        teleport.onGround = true;
        player.playerConnection.sendPacket(teleport);
    }

    public void sendSpawnPackets(EntityPlayer player, boolean rebuild) {
        PacketPlayOutSpawnEntity skull = new PacketPlayOutSpawnEntity();
        if (!rebuild) {
            skull.a = this.entityId + 1;
        }

        skull.b = MathHelper.floor(this.location.getX() * 32.0D);
        skull.c = MathHelper.floor((this.location.getY() + 54.55D) * 32.0D);
        skull.d = MathHelper.floor(this.location.getZ() * 32.0D);
        skull.j = 66;
        PacketPlayOutAttachEntity attach = new PacketPlayOutAttachEntity();
        if (!rebuild) {
            this.setValue(attach, "b", this.entityId);
            this.setValue(attach, "c", this.entityId + 1);
        }

        PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport();
        if (!rebuild) {
            teleport.a = this.entityId;
        }

        teleport.b = MathHelper.floor(this.location.getX() * 32.0D);
        teleport.c = MathHelper.floor(this.location.getY() * 32.0D);
        teleport.d = MathHelper.floor(this.location.getZ() * 32.0D);
        teleport.onGround = true;
        PacketPlayOutSpawnEntityLiving horse;
        DataWatcher watcher;
        if (player.playerConnection.networkManager.getVersion() < 47) {
            horse = new PacketPlayOutSpawnEntityLiving();
            if (!rebuild) {
                horse.a = this.entityId;
            }

            horse.b = 100;
            horse.c = MathHelper.floor(this.location.getX() * 32.0D);
            horse.d = MathHelper.floor((this.location.getY() - 54.55D) * 32.0D);
            horse.e = MathHelper.floor(this.location.getZ() * 32.0D);
            watcher = new DataWatcher((Entity) null);
            watcher.a(0, (byte) 0);
            watcher.a(1, (short) 300);
            watcher.a(10, this.getNiceMessage());
            watcher.a(11, (byte) 1);
            watcher.a(12, -1700000);
            horse.l = watcher;
            player.playerConnection.sendPacket(horse);
            player.playerConnection.sendPacket(skull);
            player.playerConnection.sendPacket(attach);
        } else {
            horse = new PacketPlayOutSpawnEntityLiving();
            if (!rebuild) {
                horse.a = this.entityId;
            }

            horse.b = 30;
            horse.c = teleport.b;
            horse.d = teleport.c;
            horse.e = teleport.d;
            watcher = new DataWatcher((Entity) null);
            watcher.a(0, (byte) 32);
            watcher.a(2, this.getNiceMessage());
            watcher.a(3, (byte) 1);
            watcher.a(10, (byte) 22);
            horse.l = watcher;
            player.playerConnection.sendPacket(horse);
            player.playerConnection.sendPacket(teleport);
        }

    }

    private void sendMessagePacket(EntityPlayer player) {
        DataWatcher watcher = new DataWatcher((Entity) null);
        watcher.a(player.playerConnection.networkManager.getVersion() < 47 ? 10 : 2, this.getNiceMessage());
        player.playerConnection.sendPacket(new PacketPlayOutEntityMetadata(this.entityId, watcher, true));
    }

    public void sendDestroyPacket(EntityPlayer player) {
        player.playerConnection.sendPacket(new PacketPlayOutEntityDestroy(this.entityId, this.entityId + 1));
    }

    public void teleportToNewLocation(Location newLocation) {
        this.location = newLocation;
        this.linesAbove.iterator().forEachRemaining((hologram) -> {
            Location location = hologram.location;
            location.setX(newLocation.getX());
            location.setY(newLocation.getY() + (double) (this.linesAbove.indexOf(hologram) + 1) * 0.3D);
            location.setZ(newLocation.getZ());
            this.getEntityPlayers(location).forEach(hologram::sendTeleportPacket);
        });
        this.getEntityPlayers(newLocation).forEach(this::sendTeleportPacket);
        this.linesBelow.iterator().forEachRemaining((hologram) -> {
            Location location = hologram.location;
            location.setX(newLocation.getX());
            location.setY(newLocation.getY() - (double) (this.linesBelow.indexOf(hologram) + 1) * 0.3D);
            location.setZ(newLocation.getZ());
            this.getEntityPlayers(location).forEach(hologram::sendTeleportPacket);
        });
    }

    public void spawn(boolean all) {
        if (all) {
            this.linesAbove.forEach((hologram) -> {
                this.getEntityPlayers(hologram.getLocation()).forEach((viewer) -> {
                    hologram.sendSpawnPackets(viewer, false);
                });
            });
            this.getEntityPlayers(this.location).forEach((player) -> {
                this.sendSpawnPackets(player, false);
            });
            this.linesBelow.forEach((hologram) -> {
                this.getEntityPlayers(hologram.getLocation()).forEach((viewer) -> {
                    hologram.sendSpawnPackets(viewer, false);
                });
            });
        } else {
            this.getEntityPlayers(this.location).forEach((viewer) -> {
                this.sendSpawnPackets(viewer, false);
            });
        }

    }

    public void spawn(EntityPlayer player, boolean all) {
        if (all) {
            this.linesAbove.iterator().forEachRemaining((hologram) -> {
                hologram.sendSpawnPackets(player, false);
            });
            this.sendSpawnPackets(player, false);
            this.linesBelow.iterator().forEachRemaining((hologram) -> {
                hologram.sendSpawnPackets(player, false);
            });
        } else {
            this.getEntityPlayers(this.location).forEach((viewer) -> {
                this.sendSpawnPackets(viewer, false);
            });
        }

    }

    public void delete(boolean all) {
        if (all) {
            this.linesAbove.forEach((hologram) -> {
                this.getEntityPlayers(hologram.getLocation()).forEach(hologram::sendDestroyPacket);
            });
            this.getEntityPlayers(this.location).forEach(this::sendDestroyPacket);
            this.linesBelow.forEach((hologram) -> {
                this.getEntityPlayers(hologram.getLocation()).forEach(hologram::sendDestroyPacket);
            });
        } else {
            this.getEntityPlayers(this.location).forEach(this::sendDestroyPacket);
        }

        holograms.values().remove(this);
    }

    public List getAllHolograms() {
        List toReturn = new ArrayList(this.linesAbove);
        toReturn.add(this);
        toReturn.addAll(this.linesBelow);
        return toReturn;
    }

    public void addLineAbove(String input) {
        Hologram toSpawn = new Hologram(input, "hologram-" + ThreadLocalRandom.current().nextInt(1000) + 100, (this.linesAbove.size() > 0 ? (Hologram) this.linesAbove.get(this.linesAbove.size() - 1) : this).getLocation().clone().add(0.0D, 0.3D, 0.0D), false);
        toSpawn.spawn(false);
        this.linesAbove.add(toSpawn);
    }

    public void addLineBelow(String input) {
        Hologram toSpawn = new Hologram(input, "hologram-" + ThreadLocalRandom.current().nextInt(1000) + 100, (this.linesBelow.size() > 0 ? (Hologram) this.linesBelow.get(this.linesBelow.size() - 1) : this).getLocation().clone().subtract(0.0D, 0.3D, 0.0D), false);
        toSpawn.spawn(false);
        this.linesBelow.add(toSpawn);
    }

    public void removeLineAbove(String text) {
        Hologram hologram = this.getLinesByMessage(text, this.linesAbove);
        if (hologram != null) {
            hologram.delete(false);
            this.linesAbove.remove(hologram);
        }

    }

    public void removeLineBelow(String text) {
        Hologram hologram = this.getLinesByMessage(text, this.linesBelow);
        if (hologram != null) {
            hologram.delete(false);
            this.linesBelow.remove(hologram);
        }

    }

    public void setMessage(String input) {
        this.message = Color.translate(input);
        this.getEntityPlayers(this.location).forEach(this::sendMessagePacket);
    }

    public Hologram getLinesByMessage(String input, List<Hologram> list) {
        return list.stream().filter((hologram) -> hologram.getNiceMessage().equalsIgnoreCase(Color.translate(input))).findFirst().orElse(null);
    }

    public String getNiceMessage() {
        return Color.translate(this.message);
    }

    public static Hologram getByName(String input) {
        return (Hologram) holograms.values().stream().filter((hologram) -> {
            return hologram.getName().equalsIgnoreCase(input);
        }).findFirst().orElse(null);
    }

    public static Hologram getById(int id) {
        return (Hologram) holograms.values().stream().filter((hologram) -> {
            return hologram.getEntityId() == id;
        }).findFirst().orElse(null);
    }

    public String getMessage() {
        return this.message;
    }

    public String getName() {
        return this.name;
    }

    public Location getLocation() {
        return this.location;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public List getLinesAbove() {
        return this.linesAbove;
    }

    public List getLinesBelow() {
        return this.linesBelow;
    }

    public static Map<String, Hologram> getHolograms() {
        return holograms;
    }
}
