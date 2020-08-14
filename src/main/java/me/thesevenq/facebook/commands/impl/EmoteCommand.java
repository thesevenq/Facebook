package me.thesevenq.facebook.commands.impl;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.player.lunar.LunarAPI;
import me.thesevenq.facebook.utils.string.CC;
import org.bukkit.entity.Player;

import java.io.IOException;

public class EmoteCommand extends BaseCommand {

    public EmoteCommand() {
        super("emote", true);
    }

    @Override
    public void execute(Player player, String[] args) {
        PlayerData data = PlayerData.getByName(player.getName());
        if (data.getEmote() == null) {
            player.sendMessage(CC.RED + "You don't have emote set.");
        } else {
            try {
                LunarAPI.performEmote(player, data.getEmote().getId(), true, 10);
                LunarAPI.performEmote(player, data.getEmote().getId(), false, 10);
            } catch (IOException var7) {
                var7.printStackTrace();
            }

            player.sendMessage(CC.SECONDARY + "You've used " + CC.PRIMARY + data.getEmote().getName() + CC.SECONDARY + " emote.");
        }
    }
}

