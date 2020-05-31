package me.thesevenq.facebook.commands.impl.staff;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.utils.Color;
import me.thesevenq.facebook.utils.player.Permission;
import org.bukkit.entity.Player;

public class SetSpawnCommand extends BaseCommand {

    public SetSpawnCommand() {
        super("setspawn", "facebook.op", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        if (player.hasPermission(Permission.OP) || player.isOp()) {
            Facebook.getInstance().getServer().getWorld(player.getWorld().getName()).setSpawnLocation(
                    player.getLocation().getBlockX(),
                    player.getLocation().getBlockY(),
                    player.getLocation().getBlockZ());
            player.sendMessage(Color.translate("&aSuccessfully set spawn location in '" + player.getWorld().getName() + "' world."));
        }

    }
}
