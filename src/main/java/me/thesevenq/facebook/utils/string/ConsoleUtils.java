package me.thesevenq.facebook.utils.string;

import me.thesevenq.facebook.utils.string.Color;
import org.bukkit.Bukkit;

public class ConsoleUtils {

    public static void log(String string) {
        Bukkit.getConsoleSender().sendMessage(Color.translate(string));
    }
}
