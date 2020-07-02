package me.thesevenq.facebook.ranks.commands;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.player.grant.menus.GrantSelectMenu;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.string.MessageUtils;
import me.thesevenq.facebook.utils.player.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GrantCommand extends BaseCommand {

    public GrantCommand() {
        super("grant", "facebook.manager", true);
    }


    public void execute(Player player, String[] args) {

        if(args.length == 0) {
            player.sendMessage(CC.RED + "Usage: /grant <player>");
            return;

        }
        Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(Color.translate("&cThat player is offline."));
                return;
            }
        PlayerData data = PlayerData.getByName(target.getName());
        new GrantSelectMenu(data).openMenu(player);
    }
}
