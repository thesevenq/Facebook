package me.thesevenq.facebook.player.freeze;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.string.CC;
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
        for(String messages : Facebook.getInstance().getConfig().getStringList("FREEZE.FREEZE_MESSAGE")) {
            player.sendMessage(messages.replace("<player>", player.getName()));
        }
    }
}