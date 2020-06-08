package me.thesevenq.facebook.commands.impl.chatcontrol;

import lombok.Getter;
import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.string.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Getter
public class MuteChatCommand extends BaseCommand {

    public MuteChatCommand() {
        super("mutechat", "facebook.staff", false);
    }

    @Getter public static Boolean muted = false;

    @Override
    public void execute(Player player, String[] args) {
        PlayerData data = PlayerData.getByName(player.getName());

        if(muted) {
            muted = false;
            Bukkit.broadcastMessage(CC.SECONDARY + "Chat has been unmuted by " + data.getRank().getColor() + player.getName() + CC.SECONDARY + ".");
        } else {
            muted = true;
            Bukkit.broadcastMessage(CC.SECONDARY + "Chat has been muted by " + data.getRank().getColor() + player.getName() + CC.SECONDARY + ".");
        }
    }
}
