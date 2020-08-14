package me.thesevenq.facebook.commands.impl.staff;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.lunar.LunarAPI;
import me.thesevenq.facebook.player.lunar.impl.Notification;
import me.thesevenq.facebook.utils.string.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;

public class LunarCommand extends BaseCommand {

    public LunarCommand() {
        super("lunarmenu", "facebook.staff", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        //new LunarSettingsMenu().openMenu(player);

        Bukkit.getOnlinePlayers().forEach(toSend -> {
            try {
                LunarAPI.sendTitle(toSend, false, CC.B_PRIMARY + "Unique Network", 2, 5, 25, 15);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Bukkit.getOnlinePlayers().forEach(toSend -> {
            try {
                LunarAPI.sendNotification(toSend, "Nema", Notification.INFO, 5);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        }
}
