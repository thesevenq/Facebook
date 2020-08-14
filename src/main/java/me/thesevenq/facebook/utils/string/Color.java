package me.thesevenq.facebook.utils.string;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Color {

   public static String translate(String text) {
      return ChatColor.translateAlternateColorCodes('&', text);
   }

   public static List<String> translate(List<String> list) {
      return list.stream().filter(Objects::nonNull).map(Color::translate).collect(Collectors.toList());
   }

   public static String strip(String text) {
      return ChatColor.stripColor(Color.translate(text));
   }

   public static ChatColor getColor(String text) {
      return ChatColor.valueOf(text);
   }

}
