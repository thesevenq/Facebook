package me.thesevenq.facebook.utils;

import java.util.ArrayList;
import java.util.Arrays;

import me.thesevenq.facebook.ranks.Rank;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import javax.annotation.Nullable;

public class WoolUtil {

    private static final ArrayList<ChatColor> woolColors = new ArrayList<ChatColor>(Arrays.asList(ChatColor.WHITE, ChatColor.GOLD, ChatColor.LIGHT_PURPLE,
            ChatColor.AQUA, ChatColor.YELLOW, ChatColor.GREEN, ChatColor.LIGHT_PURPLE, ChatColor.DARK_GRAY,
            ChatColor.GRAY, ChatColor.DARK_AQUA, ChatColor.DARK_PURPLE, ChatColor.BLUE, ChatColor.BLACK,
            ChatColor.DARK_GREEN, ChatColor.RED, ChatColor.BLACK));

    public static int convertChatColorToWoolData(ChatColor color) {
        //if(color == ChatColor.DARK_RED) color = ChatColor.RED;

        return WoolUtil.woolColors.indexOf(color);
    }

    @Nullable
    public static Material getMaterial(String value) {
        if (NumberUtils.isInteger(value)) {
            return Material.getMaterial(Integer.parseInt(value));
        } else {
            return Material.getMaterial(value.toUpperCase());
        }
    }

    private static final ArrayList<ChatColor> woolKalors = new ArrayList<>(Arrays.asList(ChatColor.WHITE, ChatColor.GOLD, ChatColor.LIGHT_PURPLE,
            ChatColor.AQUA, ChatColor.YELLOW, ChatColor.GREEN, ChatColor.LIGHT_PURPLE, ChatColor.DARK_GRAY,
            ChatColor.GRAY, ChatColor.DARK_AQUA, ChatColor.DARK_PURPLE, ChatColor.BLUE, ChatColor.BLACK,
            ChatColor.DARK_GREEN, ChatColor.RED, ChatColor.BLACK));

    public static int getWoolData(ChatColor color) {
        if (color == ChatColor.DARK_RED) color = ChatColor.RED;
        if (color == ChatColor.DARK_BLUE) color = ChatColor.BLUE;

        return WoolUtil.woolKalors.indexOf(color);
    }
}


