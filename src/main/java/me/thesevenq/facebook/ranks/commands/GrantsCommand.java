package me.thesevenq.facebook.ranks.commands;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.player.grant.menus.GrantViewMenu;
import me.thesevenq.facebook.ranks.procedure.GrantProcedure;
import me.thesevenq.facebook.utils.Color;
import me.thesevenq.facebook.utils.Tasks;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GrantsCommand extends BaseCommand {

    public GrantsCommand() {
        super("grants", "facebook.manager" ,true);
    }

    public void execute(Player player, String[] args) {
        Tasks.runAsync(() -> {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(Color.translate("&cThat player is offline."));
                return;
            }
            PlayerData data = PlayerData.getByName(target.getName());
            if (data.getGrants().isEmpty()) {
                player.sendMessage(Color.translate("&cThat player has &l0 &cgrants."));
                return;
            }
            new GrantViewMenu(data).openMenu(player);
        });

    }
}
