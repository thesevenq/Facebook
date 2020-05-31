package me.thesevenq.facebook.server.tips;

import lombok.Getter;
import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.FacebookUtils;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@Getter
public class TipsManager {

    @Getter
    private static TipsManager instance;
    private Tips currentTip;

    public TipsManager() {
        instance = this;
        currentTip = Tips.STORE;
        start();
    }

    private void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : FacebookUtils.getOnlinePlayers()) {
                    for (String string : currentTip.getText()) {
                        if (PlayerData.getByName(player.getName()).isTips()) {
                            FacebookUtils.centerText(player, string);
                        }
                    }
                }
                setNextTip();
            }
        }.runTaskTimerAsynchronously(Facebook.getInstance(), 2400L, 2400L);
    }

    public void setNextTip() {
        if (currentTip == Tips.STORE) {
            currentTip = Tips.TEAMSPEAK;
        } else if (currentTip == Tips.TEAMSPEAK) {
            currentTip = Tips.DISCORD;
        } else if (currentTip == Tips.DISCORD) {
            currentTip = Tips.STORE;
        }
    }
}
