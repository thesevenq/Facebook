package me.thesevenq.facebook.commands.impl.toggle;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ToggleStaffChatCommand extends BaseCommand {

    public ToggleStaffChatCommand() {
        super("staffchat", Arrays.asList(new String[]{"sc", "togglestaffchat"}), "facebook.staff", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        final PlayerData data = PlayerData.getByName(player.getName());

        if (data.isStaffChat()) {
            data.setStaffChat(false);
            data.save();
            player.sendMessage(Color.translate(data.isStaffChat() ? CC.RED + "Something went wrong while trying to disable your staffchat." : CC.SECONDARY + "You have successfully disabled your staffchat."));
        } else {
            data.setStaffChat(true);
            data.save();
            player.sendMessage(Color.translate(data.isStaffChat() ? CC.SECONDARY + "You have successfully enabled your staffchat." : CC.RED + "Something went wrong while trying to enable your staffchatl."));
        }
    }
}

