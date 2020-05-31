package me.thesevenq.facebook.server.shutdown;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.FacebookAPI;
import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.server.events.ServerShutdownCancelEvent;
import me.thesevenq.facebook.server.events.ServerShutdownScheduleEvent;
import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.TimeUtil;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class ShutdownCommand extends BaseCommand {

    public ShutdownCommand() {
        super("shutdown", "facebook.op", false);
        setUsage(CC.RED + "Usage: /shutdown <time|cancel>");
    }

    private BukkitTask shutdownTask;

    @Override
    public void execute(Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage(usageMessage);
            return;
        }

        String arg = args[0];

        if (arg.equalsIgnoreCase("cancel")) {
            if (shutdownTask == null) {
                player.sendMessage(CC.RED + "There is no shutdown in progress.");
            } else {
                Facebook.getInstance().getServer().getPluginManager().callEvent(new ServerShutdownCancelEvent());

                shutdownTask.cancel();
                shutdownTask = null;
                Facebook.getInstance().getServer().broadcastMessage(CC.GREEN + "The shutdown in-progress has been cancelled by " + FacebookAPI.getColoredName(player) + CC.GREEN + ".");
            }
            return;
        }

        if (shutdownTask != null) {
            player.sendMessage(CC.RED + "There is already a shutdown in progress.");
            return;
        }

        int seconds = TimeUtil.parseTime(arg);

        if (seconds >= 5 && seconds <= 300) {
            Facebook.getInstance().getServer().getPluginManager().callEvent(new ServerShutdownScheduleEvent());

            shutdownTask = new ShutdownTask(seconds).runTaskTimer(Facebook.getInstance(), 0L, 20L);
            Facebook.getInstance().getServer().broadcastMessage(CC.SECONDARY + "A server shutdown was initiated by " + FacebookAPI.getColoredName(player) + CC.SECONDARY + " which will occur in " + CC.PRIMARY + arg + ".");
        } else {
            player.sendMessage(CC.RED + "Please enter a time between 5 seconds and 5 minutes.");
        }
    }
}

