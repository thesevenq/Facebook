package me.thesevenq.facebook.server.shutdown;

import lombok.AllArgsConstructor;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.MessageUtils;
import me.thesevenq.facebook.utils.time.TimeUtilties;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

@AllArgsConstructor
public class ShutdownTask extends BukkitRunnable {
    private int shutdownSeconds;

    @Override
    public void run() {
        if (shutdownSeconds == 0) {
            Bukkit.shutdown();
        } else if (shutdownSeconds % 60 == 0 || shutdownSeconds == 30 || shutdownSeconds == 10 || shutdownSeconds <= 5) {
            Bukkit.getServer().broadcastMessage(CC.BD_RED + MessageUtils.WARNING + " " + CC.GRAY + MessageUtils.LINE + CC.RED + " The server is restarting in " + CC.D_RED + TimeUtilties.formatTimeSeconds(shutdownSeconds) + ".");
        }

        shutdownSeconds--;
    }
}
