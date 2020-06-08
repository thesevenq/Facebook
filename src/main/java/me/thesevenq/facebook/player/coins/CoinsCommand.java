package me.thesevenq.facebook.player.coins;

import me.thesevenq.facebook.FacebookAPI;
import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.string.MessageUtils;
import me.thesevenq.facebook.utils.player.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.text.NumberFormat;

public class CoinsCommand extends BaseCommand {

    public CoinsCommand() {
        super("coins", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        NumberFormat format = NumberFormat.getInstance();
        FacebookAPI api = FacebookAPI.getInstance();

        if (args.length == 0) {
            player.sendMessage(CC.SECONDARY + "You have " + CC.GREEN + api.getCoins(player) + CC.SECONDARY + " coins.");
        }

        if (args.length == 1 || (args.length == 2 && !player.hasPermission(Permission.OP))) {
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                player.sendMessage(MessageUtils.playerOffline());
            }

            player.sendMessage(CC.PRIMARY + target.getName() + CC.SECONDARY + " has " + CC.GREEN + api.getCoins(target) + CC.SECONDARY + " coins.");
        }

        if (args.length == 3) {
            if (!player.hasPermission(Permission.OP)) {
                player.sendMessage(MessageUtils.noPermission());
            }

            Player target = Bukkit.getPlayer(args[1]);

            if (target == null) {
                player.sendMessage(MessageUtils.playerOffline());
            }

            int amount = Integer.parseInt(args[2]);
            switch (args[0].toLowerCase()) {
                case "add":
                    PlayerData data = PlayerData.getByName(target.getName());

                    api.addCoins(target, amount);

                    player.sendMessage(Color.translate("&eYou have added &a" + format.format(amount).replace(".", ",") + " &ecoins to &6" + target.getName() + "&e's account."));
                    target.sendMessage(Color.translate("&eYou received &a" + format.format(amount).replace(".", ",") + " &ecoins!"));

                case "remove":
                    api.removeCoins(target, amount);

                    player.sendMessage(Color.translate("&eYou have removed &a" + format.format(amount).replace(".", ",") + " &ecoins from &6" + target.getName() + "&e's account."));
                    target.sendMessage(Color.translate("&eYou lost &a" + format.format(amount).replace(".", ",") + " &ecoins!"));

                case "set":
                    api.setCoins(target, amount);

                    player.sendMessage(Color.translate("&eYou have set &6" + target.getName() + "&e's coins to &a" + format.format(amount).replace(".", ",") + "&e."));
                    target.sendMessage(Color.translate("&eYour coins amount is set to &a" + format.format(amount).replace(".", ",") + "&e."));
            }
        }
    }

}
