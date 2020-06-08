package me.thesevenq.facebook.commands.impl;

import com.google.common.collect.ImmutableList;
import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.ranks.Rank;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.string.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ListCommand extends BaseCommand {

    public ListCommand() {
        super("list", true);
    }

    public void execute(Player player, String[] args) {

        StringBuilder rankBuilder = new StringBuilder();
        StringBuilder nameBuilder = new StringBuilder();

        for (Rank rank : ImmutableList.copyOf(Rank.values()).reverse()) {
            if (rankBuilder.length() > 0) {
                rankBuilder.append("&f, ");
            }

            for (Player players : Bukkit.getOnlinePlayers()) {
                PlayerData data = PlayerData.getByName(players.getName());

                if (data.getRank().equals(rank)) {
                    if (nameBuilder.length() > 0) {
                        nameBuilder.append("&e, ");
                    }

                    if (player.canSee(players)) {
                        nameBuilder.append(data.getRank().getColor() + players.getName());
                    }
                }
            }
        }

        player.sendMessage(Color.translate("&7&m----------------------------------------------------"));
        player.sendMessage(Color.translate("&4Owner&e, &bDeveloper&e, &4&oManager&e, &cSenior Admin&e, &cAdmin&7, &3&oSenior Mod&e, &3Mod&e, &e&oTrial Mod&e, &6&oHost&e, &aBuilder&e, &5&oPartner&e, &6Famous&e, &5YouTuber&e, &dMedia&e, &dRuby&e, &9Platinum&e, &6Gold&e, &7Silver&e, &bBasic&e, &aDefault&e."));
        player.sendMessage("");
        player.sendMessage(CC.SECONDARY + "There are currently " + CC.PRIMARY + Bukkit.getOnlinePlayers().size() + CC.SECONDARY + " players online.");
        player.sendMessage("");
        player.sendMessage(CC.B_PRIMARY + "Detailed List: ");
        player.sendMessage(Color.translate(" &7" + MessageUtils.CIRCLE + " " + nameBuilder.toString()));
        player.sendMessage(Color.translate("&7&m----------------------------------------------------"));

    }
}


