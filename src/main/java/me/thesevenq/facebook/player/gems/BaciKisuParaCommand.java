package me.thesevenq.facebook.player.gems;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.player.gems.menus.CoinsMenu;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.string.MessageUtils;
import me.thesevenq.facebook.utils.player.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BaciKisuParaCommand extends BaseCommand {

    public BaciKisuParaCommand() {
        super("coins", "facebook.op", true);
    }


    public void execute(Player player, String[] args) {

        if(args.length == 0) {
            player.sendMessage(CC.RED + "Usage: /coins <player>");
            return;

        }
        Player target = Bukkit.getPlayer(args[0]);
        if (player.hasPermission(Permission.OP)) {
            if (target == null) {
                player.sendMessage(Color.translate("&cThat player is offline."));
            } else {
                player.sendMessage(MessageUtils.noPermission());
            }
        }
        PlayerData data = PlayerData.getByName(target.getName());
        new CoinsMenu(data).openMenu(player);
    }
}
