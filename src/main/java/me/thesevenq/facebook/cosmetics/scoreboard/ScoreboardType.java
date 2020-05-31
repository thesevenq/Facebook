package me.thesevenq.facebook.cosmetics.scoreboard;

import java.util.Arrays;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public enum ScoreboardType {
    NORMAL("Normal"),
    OLDSCHOOL("OldSchool");

    private String name;

    public static ScoreboardType getByName(String input) {
        return Arrays.stream(values()).filter((type) -> type.name().equalsIgnoreCase(input) || type.getName().equalsIgnoreCase(input)).findFirst().orElse(null);
    }

    public String getName() {
        return this.name;
    }

    ScoreboardType(String name) {
        this.name = name;
    }
}
