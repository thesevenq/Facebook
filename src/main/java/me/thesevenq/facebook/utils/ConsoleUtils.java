package me.thesevenq.facebook.utils;

import org.bukkit.Bukkit;

public class ConsoleUtils {

    public static void log(String string) {
        Bukkit.getConsoleSender().sendMessage(Color.translate(string));
    }
}
