package me.thesevenq.facebook.player.freeze;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class FreezeTask extends BukkitRunnable {

    public FreezeTask() {
        this.runTaskTimerAsynchronously(Facebook.getInstance(), 200L, 200L);
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            PlayerData data = PlayerData.getByName(player.getName());

            if(data.isFrozen()) sendMessage(player);
        });
    }

    public void sendMessage(Player player) {
        player.sendMessage("");
        player.sendMessage(CC.SECONDARY + "You have " + CC.PRIMARY + "3" + CC.SECONDARY + " minutes to join our discord!");
        player.sendMessage(CC.PRIMARY + " discord.sicaro.club");
        player.sendMessage("");
        player.sendMessage(CC.SECONDARY + "Any modification of files before screenshare");
        player.sendMessage(CC.SECONDARY + "will result to a " + CC.RED + "permanent " + CC.SECONDARY + "punishment.");
        player.sendMessage("");
    }
}