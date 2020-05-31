package me.thesevenq.facebook.utils;

public class NumberUtils {
   public static boolean isInteger(String input) {
      try {
         Integer.parseInt(input);
         return true;
      } catch (NumberFormatException var2) {
         return false;
      }
   }

   public static boolean isShort(String input) {
      try {
         Short.parseShort(input);
         return true;
      } catch (NumberFormatException var2) {
         return false;
      }
   }

   public static boolean isDouble(String input) {
      try {
         Double.parseDouble(input);
         return true;
      } catch (NumberFormatException var2) {
         return false;
      }
   }
}
