package me.thesevenq.facebook.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.Random;

public class RandomUtil {

    private final static DecimalFormat decimalFormat = new DecimalFormat();

    public static void playSound(Player player, Sound sound) {
        player.playSound(player.getLocation(), sound, 10F, 1F);
    }

    public static String formatInteger(int number) {
        return decimalFormat.format(number).replace(".", ",");
    }

    public static String formatDouble(double number) {
        return decimalFormat.format(number).replace(".", ",");
    }

    public static String getNewCode() {
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            code.append(new Random().nextInt(9) + 1);
        }
        return code.toString();
    }
}
