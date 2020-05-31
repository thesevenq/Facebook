package me.thesevenq.facebook.ranks.commands;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.player.grant.menus.GrantSelectMenu;
import me.thesevenq.facebook.utils.Color;
import me.thesevenq.facebook.utils.MessageUtils;
import me.thesevenq.facebook.utils.player.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GrantCommand extends BaseCommand {

    public GrantCommand() {
        super("grant", "facebook.manager", true);
    }


    public void execute(Player player, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);
        if (player.hasPermission(Permission.OP)) {
            if (target == null) {
                player.sendMessage(Color.translate("&cThat player is offline."));
            } else {
                player.sendMessage(MessageUtils.noPermission());
            }
        }
        PlayerData data = PlayerData.getByName(target.getName());
        new GrantSelectMenu(data).openMenu(player);
    }
}
