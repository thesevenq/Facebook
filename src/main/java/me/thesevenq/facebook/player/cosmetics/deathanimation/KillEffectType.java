package me.thesevenq.facebook.player.cosmetics.deathanimation;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.utils.trail.ParticleEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public enum KillEffectType {
    ANGRY("Angry", (location) -> {
        ParticleEffect.VILLAGER_ANGRY.display(0.5F, 0.5F, 0.5F, 0.01F, 25, location, 20.0D);
    }, 5000),
    BLOOD("Blood", (location) -> {
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.REDSTONE, (byte) 0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.BLOCK_DUST.display(new ParticleEffect.BlockData(Material.REDSTONE_BLOCK, (byte) 0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.REDSTONE, (byte) 0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.BLOCK_DUST.display(new ParticleEffect.BlockData(Material.REDSTONE_BLOCK, (byte) 0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.REDSTONE, (byte) 0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.BLOCK_DUST.display(new ParticleEffect.BlockData(Material.REDSTONE_BLOCK, (byte) 0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.REDSTONE, (byte) 0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.BLOCK_DUST.display(new ParticleEffect.BlockData(Material.REDSTONE_BLOCK, (byte) 0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.REDSTONE, (byte) 0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.BLOCK_DUST.display(new ParticleEffect.BlockData(Material.REDSTONE_BLOCK, (byte) 0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.REDSTONE, (byte) 0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.BLOCK_DUST.display(new ParticleEffect.BlockData(Material.REDSTONE_BLOCK, (byte) 0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.REDSTONE, (byte) 0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.BLOCK_DUST.display(new ParticleEffect.BlockData(Material.REDSTONE_BLOCK, (byte) 0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
    }, 5000),
    CHESS("Chess", (location) -> {
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.QUARTZ_BLOCK, (byte) 0), 0.3F, 0.3F, 0.3F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.COAL_BLOCK, (byte) 0), 0.3F, 0.3F, 0.3F, 0.1F, 5, location, 20.0D);
    }, 5000),
    CLOUD("Cloud", (location) -> {
        ParticleEffect.CLOUD.display(0.0F, 0.0F, 0.0F, 0.1F, 100, location, 20.0D);
    }, 5000),
    COAL("Coal", (location) -> {
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.COAL, (byte) 0), 0.3F, 0.3F, 0.3F, 0.1F, 20, location, 20.0D);
        ParticleEffect.SMOKE_LARGE.display(0.3F, 0.3F, 0.3F, 0.1F, 3, location, 20.0D);
    }, 5000),
    COOKIE("Cookie", (location) -> {
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.COOKIE, (byte) 0), 0.7F, 0.7F, 0.7F, 0.1F, 35, location, 20.0D);
    }, 5000),
    DIAMOND("Diamond", (location) -> {
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.DIAMOND, (byte) 0), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.DIAMOND, (byte) 0), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.DIAMOND, (byte) 0), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.DIAMOND, (byte) 0), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.DIAMOND, (byte) 0), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.DIAMOND, (byte) 0), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.DIAMOND, (byte) 0), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.FIREWORKS_SPARK.display(0.3F, 0.3F, 0.3F, 0.1F, 3, location, 20.0D);
    }, 5000),
    EMERALD("Emerald", (location) -> {
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.EMERALD, (byte) 0), 0.3F, 0.3F, 0.3F, 0.1F, 20, location, 20.0D);
        ParticleEffect.FIREWORKS_SPARK.display(0.3F, 0.3F, 0.3F, 0.1F, 3, location, 20.0D);
    }, 5000),
    TNT("TNT", (location) -> {
        ParticleEffect.EXPLOSION_LARGE.display(0.5F, 0.5F, 0.5F, 1.0F, 12, location, 20.0D);
    }, 5000),
    FIREWORK("Firework", (location) -> {
        location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        location.getWorld().spawnEntity(location, EntityType.FIREWORK);
    }, 5000),
    FLAME("Flame", (location) -> {
        ParticleEffect.FLAME.display(0.2F, 0.2F, 0.2F, 0.1F, 10, location, 20.0D);
    }, 5000),
    GOLD("Gold", (location) -> {
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.GOLD_INGOT, (byte) 0), 0.3F, 0.3F, 0.3F, 0.1F, 20, location, 20.0D);
        ParticleEffect.FIREWORKS_SPARK.display(0.3F, 0.3F, 0.3F, 0.1F, 3, location, 20.0D);
    }, 5000),
    HALLOWEEN("Halloween", (location) -> {
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.PUMPKIN, (byte) 0), 0.3F, 0.3F, 0.3F, 0.1F, 10, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.DEAD_BUSH, (byte) 0), 0.3F, 0.3F, 0.3F, 0.1F, 10, location, 20.0D);
    }, 5000),
    HAPPY("Happy", (location) -> {
        ParticleEffect.VILLAGER_HAPPY.display(0.5F, 0.5F, 0.5F, 0.01F, 100, location, 20.0D);
    }, 5000),
    HEART("Heart", (location) -> {
        ParticleEffect.HEART.display(0.4F, 0.4F, 0.4F, 0.1F, 10, location, 20.0D);
    }, 5000),
    IRON("Iron", (location) -> {
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.IRON_INGOT, (byte) 0), 0.3F, 0.3F, 0.3F, 0.1F, 20, location, 20.0D);
        ParticleEffect.FIREWORKS_SPARK.display(0.3F, 0.3F, 0.3F, 0.1F, 3, location, 20.0D);
    }, 5000),
    LAVA("Lava", (location) -> {
        ParticleEffect.LAVA.display(0.5F, 0.5F, 0.5F, 0.1F, 12, location, 20.0D);
    }, 5000),
    LIGHTING("Lighting", (location) -> {
        location.getWorld().strikeLightningEffect(location);
    }, 5000),
    NOTE("Note", (location) -> {
        ParticleEffect.NOTE.display(0.5F, 0.5F, 0.5F, 1.0F, 12, location, 20.0D);
    }, 5000),
    THUNDER("Thunder", (location) -> {
        ParticleEffect.CLOUD.display(0.3F, 0.3F, 0.3F, 0.1F, 20, location, 20.0D);
        ParticleEffect.WATER_SPLASH.display(0.3F, 0.4F, 0.3F, 0.1F, 20, location, 20.0D);
        location.getWorld().strikeLightningEffect(location);
    }, 5000),
    NUKE("Nuke", (loc) -> {
        (new BukkitRunnable() {
            private double amount = 0.7853981633974483D;
            private Location location = loc;

            public void run() {
                this.amount += 0.3141592653589793D;

                for (double d1 = 0.0D; d1 <= 6.283185307179586D; d1 += 0.09817477042468103D) {
                    double d2 = amount * Math.cos(d1);
                    double d3 = 2.0D * Math.exp(-0.1D * amount) * Math.sin(amount) + 1.5D;
                    double d4 = amount * Math.sin(d1);
                    this.location.add(d2, d3, d4);
                    ParticleEffect.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.0F, 1, location, 100.0D);
                    location.subtract(d2, d3, d4);
                    d2 = amount * Math.cos(d1 += 0.04908738521234052D);
                    d3 = 2.0D * Math.exp(-0.1D * amount) * Math.sin(amount) + 1.5D;
                    d4 = amount * Math.sin(d1);
                    location.add(d2, d3, d4);
                    ParticleEffect.FLAME.display(0.0F, 0.0F, 0.0F, 0.0F, 1, location, 100.0D);
                    location.subtract(d2, d3, d4);
                }

                if (amount > 10.0D) {
                    cancel();
                }

            }
        }).runTaskTimerAsynchronously(Facebook.getInstance(), 0L, 1L);
    }, 10000);

    private String name;
    private EffectCallable callable;
    private int price;

    public static KillEffectType getByName(String input) {
        return (KillEffectType) Arrays.stream(values()).filter((type) -> {
            return type.name().equalsIgnoreCase(input) || type.getName().equalsIgnoreCase(input);
        }).findFirst().orElse(null);
    }

    public boolean hasPermission(Player player) {
        return player.hasPermission(this.getPermissionForAll()) || player.hasPermission(this.getPermission());
    }

    public String getPermissionForAll() {
        return "cosmetics.killeffect.*";
    }

    public String getPermission() {
        return "cosmetics.killeffect." + this.name().toLowerCase();
    }

    public String getName() {
        return this.name;
    }

    public EffectCallable getCallable() {
        return this.callable;
    }

    public int getPrice() {
        return this.price;
    }

    private KillEffectType(String name, EffectCallable callable, int price) {
        this.name = name;
        this.callable = callable;
        this.price = price;
    }
}
