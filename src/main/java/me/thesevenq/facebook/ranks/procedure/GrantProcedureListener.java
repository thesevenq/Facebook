package me.thesevenq.facebook.ranks.procedure;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.player.grant.menus.GrantConfirmMenu;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.time.TimeFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class GrantProcedureListener implements Listener {

    private PlayerData data;

    public GrantProcedureListener() {
        Bukkit.getPluginManager().registerEvents(this, Facebook.getInstance());
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        GrantProcedure grantProcedure = GrantProcedure.getByPlayer(player.getName());

        if (grantProcedure != null) {
            if (grantProcedure.getStage() == GrantProcedureStage.DURATION) {
                event.setCancelled(true);
                if (event.getMessage().equalsIgnoreCase("cancel")) {
                    GrantProcedure.getProcedures().remove(grantProcedure);
                    player.sendMessage(CC.RED + "You have cancelled grant procedure.");
                    return;
                }
                if (event.getMessage().equalsIgnoreCase("perm")) {
                    grantProcedure.setDuration(-1L);
                    grantProcedure.setStage(GrantProcedureStage.REASON);
                    player.sendMessage(CC.SECONDARY + "You have entered " + CC.PRIMARY + "perm" + CC.SECONDARY + " as your duration. Please enter your grant " + CC.PRIMARY + "reason" + CC.SECONDARY + ".");
                    return;
                }
                grantProcedure.setDuration(System.currentTimeMillis() + TimeFormatUtils.parseTime(event.getMessage()));
                grantProcedure.setStage(GrantProcedureStage.REASON);
                player.sendMessage(CC.SECONDARY + "You have entered " + CC.PRIMARY + (System.currentTimeMillis() + TimeFormatUtils.parseTime(event.getMessage()) + CC.SECONDARY + " as your duration. Please enter your grant " + CC.PRIMARY + "reason" + CC.SECONDARY + "."));
            } else if (grantProcedure.getStage() == GrantProcedureStage.REASON) {
                event.setCancelled(true);

                if (event.getMessage().equalsIgnoreCase("cancel")) {
                    GrantProcedure.getProcedures().remove(grantProcedure);
                    player.sendMessage(CC.RED + "You have cancelled grant procedure.");
                    return;
                }
                grantProcedure.setStage(GrantProcedureStage.CONFIRMATION);
                grantProcedure.setReason(event.getMessage());
                player.sendMessage(CC.SECONDARY + "You have entered " + CC.PRIMARY + event.getMessage() + CC.SECONDARY + " as a reason.");
                new GrantConfirmMenu(grantProcedure).openMenu(player);

            }


        }
    }
}

