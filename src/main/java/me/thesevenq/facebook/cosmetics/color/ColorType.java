package me.thesevenq.facebook.cosmetics.color;

import java.util.Arrays;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public enum ColorType {
    DARK_PURPLE("Dark Purple", ChatColor.DARK_PURPLE),
    LIGHT_PURPLE("Light Purple", ChatColor.LIGHT_PURPLE),
    BLUE("Blue", ChatColor.BLUE),
    AQUA("Aqua", ChatColor.AQUA),
    DARK_AQUA("Dark Aqua", ChatColor.DARK_AQUA),
    LIGHT_GRAY("Light Gray", ChatColor.GRAY),
    DARK_GRAY("Dark Gray", ChatColor.DARK_GRAY),
    LIGHT_GREEN("Light Green", ChatColor.GREEN),
    DARK_GREEN("Dark Green", ChatColor.DARK_GREEN),
    GOLD("Orange", ChatColor.GOLD),
    YELLOW("Yellow", ChatColor.YELLOW),
    RED("Red", ChatColor.RED);

    private String name;
    private ChatColor color;

    public static ColorType getByName(String input) {
        return Arrays.stream(values()).filter((type) -> type.name().equalsIgnoreCase(input) || type.getName().equalsIgnoreCase(input)).findFirst().orElse(null);
    }

    public String getName() {
        return this.name;
    }

    public ChatColor getColor() {
        return this.color;
    }

    ColorType(String name, ChatColor color) {
        this.name = name;
        this.color = color;
    }
}
