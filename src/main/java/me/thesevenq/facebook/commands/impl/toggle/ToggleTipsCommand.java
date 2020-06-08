package me.thesevenq.facebook.commands.impl.toggle;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.string.Color;
import org.bukkit.entity.Player;

public class ToggleTipsCommand extends BaseCommand {

    public ToggleTipsCommand() {
        super("toggletips", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        PlayerData data = PlayerData.getByName(player.getName());

        data.setTips(!data.isTips());

        player.sendMessage(Color.translate("&eYou have " + (data.isTips() ? "&aenabled" : "&cdisabled") +
                " &etips."));
    }
}

