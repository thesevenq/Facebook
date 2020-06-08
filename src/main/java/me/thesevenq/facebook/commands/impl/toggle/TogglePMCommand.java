package me.thesevenq.facebook.commands.impl.toggle;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.string.Color;
import org.bukkit.entity.Player;

public class TogglePMCommand extends BaseCommand {

    public TogglePMCommand() {
        super("togglepm", true);
    }


    public void execute(Player player, String[] args) {
        PlayerData data = PlayerData.getByName(player.getName());

        data.setToggleMsg(!data.isToggleMsg());

        player.sendMessage(Color.translate("&eYou have " + (data.isToggleMsg() ? "&aenabled" : "&cdisabled") +
                " &eprivate messages."));
    }
}
