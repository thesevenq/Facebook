package me.thesevenq.facebook.commands.impl.chatcontrol;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.string.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ClearChatCommand extends BaseCommand {

    public ClearChatCommand() {
        super("clearchat", Arrays.asList(new String[]{"cc", "cchat", "chatclear"}), "facebook.staff",false);
    }

    @Override
    public void execute(Player player, String[] args) {
        PlayerData data = PlayerData.getByName(player.getName());

        Bukkit.getOnlinePlayers().forEach(players -> players.sendMessage(new String[100]));
        Bukkit.broadcastMessage(CC.SECONDARY + "Chat has been cleared by " + data.getRank().getColor() + player.getName() + CC.SECONDARY + ".");
    }
}
