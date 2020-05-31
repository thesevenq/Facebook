package me.thesevenq.facebook.commands.impl.staff;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.FacebookAPI;
import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HealCommand extends BaseCommand {

    public HealCommand() {
        super("heal", "facebook.staff", true);
    }

    private FacebookAPI api = FacebookAPI.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);

        switch (args.length) {
            case 0:
                if (!args[0].equalsIgnoreCase("all")) {
                    if (target == null) player.sendMessage(MessageUtils.playerOffline());

                    heal(target);
                    player.sendMessage(CC.SECONDARY + "You have healed " + api.getColoredName(target) + CC.SECONDARY + ".");
                    target.sendMessage(CC.SECONDARY + "You have been healed by " + api.getColoredName(player) + CC.SECONDARY + ".");
                } else if (args[0].equalsIgnoreCase("all")) {
                    Facebook.getInstance().getServer().getOnlinePlayers().forEach((players) -> {
                        heal(players);
                        players.sendMessage(CC.SECONDARY + "All online players have been healed by " + api.getColoredName(player) + CC.SECONDARY + ".");

                    });
                }
        }

    }

    private void heal(Player player) {
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(10.0F);
        player.setFireTicks(0);
    }
}
