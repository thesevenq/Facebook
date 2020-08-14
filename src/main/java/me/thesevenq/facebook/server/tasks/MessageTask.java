package me.thesevenq.facebook.server.tasks;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.utils.PlayerUtil;
import me.thesevenq.facebook.utils.string.Color;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class MessageTask extends BukkitRunnable {

    private final List<String> messages = Facebook.getInstance().getConfig().getStringList("MESSAGES");

    private boolean started;
    private int index = 0;

    public MessageTask() {
        runTaskTimer(Facebook.getInstance(), 0, 20*60*3);
    }

    @Override
    public void run() {
        if (messages.isEmpty()) return;

        PlayerUtil.getOnlinePlayers().forEach(player -> player.sendMessage(Color.translate("&6[TIP] &r" + messages.get(getIndex()))));

        if (!started) started = true;
    }

    private int getIndex() {
        int index = new Random().nextInt(messages.size());

        if (!started || index != this.index || messages.size() == 1) {
            this.index = index;
            return index;
        }
        return getIndex();
    }
}
