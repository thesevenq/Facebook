package me.thesevenq.facebook.commands.impl.staff;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GodCommand extends BaseCommand {

    public GodCommand() {
        super("god", "facebook.seniorstaff", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        Player target;
        if (args.length == 0) {
            if (player instanceof Player) {
                target = (Player) player;
                target.setInvulnerable(!target.isInvulnerable());
                target.sendMessage(CC.SECONDARY + "You are " + CC.PRIMARY + (target.isInvulnerable() ? "now" : "no longer") + CC.SECONDARY + " in god mode.");
            } else {
                player.sendMessage(CC.RED + "Usage: /god <player>");
            }
        } else {
            target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(StringUtil.NO_PLAYER_FOUND.replace("<player>", args[0]));
            } else {
                target.setInvulnerable(!target.isInvulnerable());
                target.sendMessage(CC.SECONDARY + "You are " + CC.PRIMARY + (target.isInvulnerable() ? "now" : "no longer") + CC.SECONDARY + " in god mode.");
                player.sendMessage(CC.SECONDARY + "You've " + CC.PRIMARY + (target.isInvulnerable() ? "enabled" : "disabled") + CC.SECONDARY + " god mode of " + target.getDisplayName() + CC.SECONDARY + '.');
            }
        }
    }
}
