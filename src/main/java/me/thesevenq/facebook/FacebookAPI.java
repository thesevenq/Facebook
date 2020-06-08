package me.thesevenq.facebook;

import lombok.Getter;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.register.FacebookRegister;
import org.bukkit.entity.Player;

import java.text.NumberFormat;

public class FacebookAPI {

    @Getter
    public static FacebookAPI instance;

    public static NumberFormat format = NumberFormat.getInstance();

    private FacebookRegister facebookRegister = FacebookRegister.getInstance();

    public FacebookAPI() {
        instance = this;
    }

    public static String getIP() {
        return Facebook.getInstance().getConfig().getString("SERVER_IP");
    }

    public static String getCurrentRank(Player player) {
        PlayerData data = PlayerData.getByName(player.getName());
        return data.getRank().getColor() + data.getRank().getName();
    }

    public static String getRankColor(Player player) {
        PlayerData data = PlayerData.getByName(player.getName());

        return data.getRank().getColor();
    }

    public static String getServerName() {
        return Facebook.getInstance().getConfig().getString("SERVERNAME");
    }

    public static String getColoredName(Player player) {
        PlayerData data = PlayerData.getByName(player.getName());
        return data.getRank().getColor() + player.getName();
    }

    public static String getCoins(Player player) {
        PlayerData data = PlayerData.getByName(player.getName());

        return format.format(data.getCoins()).replace(".", ",");
    }

    public static int getCoinsInt(Player player) {
        PlayerData data = PlayerData.getByName(player.getName());

        return data.getCoins();
    }

    public static void addCoins(Player player, int number) {
        PlayerData data = PlayerData.getByName(player.getName());

        int amount = data.getCoins() + number;

        data.setCoins(amount);
        data.save();
    }

    public static void setCoins(Player player, int number) {
        PlayerData data = PlayerData.getByName(player.getName());

        int amount = number;

        data.setCoins(amount);
        data.save();
    }

    public static void removeCoins(Player player, int number) {
        PlayerData data = PlayerData.getByName(player.getName());

        int amount = data.getCoins() - number;

        data.setCoins(amount);
        data.save();
    }

}
