package me.thesevenq.facebook.utils.string;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class Color {
   public static String translate(String input) {
      return ChatColor.translateAlternateColorCodes('&', input);
   }

   public static List<String> translate(List<String> input) {
      return input.stream().map(Color::translate).collect(Collectors.toList());
   }
}
