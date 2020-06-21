package me.thesevenq.facebook.commands.impl.staff;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.utils.string.CC;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.entity.Player;
import org.spigotmc.SpigotWorldConfig;

import java.lang.management.ManagementFactory;
import java.util.Arrays;

public class LagCommand extends BaseCommand {
    
    public LagCommand() {
        super("lag", Arrays.asList(new String[]{"tps", "checklag"}), "facebook.staff", false);
    }

    @Override
    public void execute(Player player, String[] args) {
        player.sendMessage("");
        double[] tps = Bukkit.spigot().getTPS();
        String[] tpsAvg = new String[3];

        for(int i = 0; i < 3; ++i) {
            tpsAvg[i] = this.format(tps[i]);
        }

        player.sendMessage(CC.SECONDARY + "TPS from last 1m, 5m, 15m: " + StringUtils.join(tpsAvg, ", "));
        player.sendMessage("");
        Runtime runtime = Runtime.getRuntime();
        //player.sendMessage(CC.SECONDARY + "Uptime: " + CC.PRIMARY + TimeUtil.formatDuration(System.currentTimeMillis() - ManagementFactory.getRuntimeMXBean().getStartTime()));
        player.sendMessage(CC.SECONDARY + "Maximum Memory: " + CC.PRIMARY + runtime.maxMemory() / 1024L / 1024L + CC.SECONDARY + " MB");
        player.sendMessage(CC.SECONDARY + "Total Memory: " + CC.PRIMARY + runtime.totalMemory() / 1024L / 1024L + CC.SECONDARY + " MB");
        player.sendMessage(CC.SECONDARY + "Free Memory: " + CC.PRIMARY + runtime.freeMemory() / 1024L / 1024L + CC.SECONDARY + " MB");
        player.sendMessage("");
        Bukkit.getWorlds().forEach((world) -> {
            SpigotWorldConfig config = ((CraftWorld)world).getHandle().spigotConfig;
            player.sendMessage(" " + CC.PRIMARY + world.getName() + ' ' + CC.PRIMARY + '(' + CC.SECONDARY + "Entities: " + world.getEntities().size() + CC.PRIMARY + ") " + '(' + CC.SECONDARY + "Loaded Chunks: " + world.getLoadedChunks().length + CC.PRIMARY + ") " + '(' + CC.SECONDARY + "Player: " + config.playerTrackingRange + CC.PRIMARY + ") " + '(' + CC.SECONDARY + "Animal: " + config.animalTrackingRange + CC.PRIMARY + ") " + '(' + CC.SECONDARY + "Monster: " + config.monsterTrackingRange + CC.PRIMARY + ") " + '(' + CC.SECONDARY + "Misc: " + config.miscTrackingRange + CC.PRIMARY + ") " + '(' + CC.SECONDARY + "Other: " + config.otherTrackingRange + CC.PRIMARY + ") ");
        });
        player.sendMessage("");
    }

    private String format(double tps) {
        return (tps > 18.0D ? ChatColor.GREEN : (tps > 16.0D ? ChatColor.YELLOW : ChatColor.RED)).toString() + (tps > 20.0D ? "*" : "") + Math.min((double)Math.round(tps * 100.0D) / 100.0D, 5.0E8D);
    }

    private ChatColor getFullTickColor(double ms) {
        return ms <= 15.0D ? ChatColor.GREEN : (ms <= 25.0D ? ChatColor.YELLOW : ChatColor.RED);
    }
}

