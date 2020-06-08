package me.thesevenq.facebook.utils.string;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.database.DatabaseManager;
import me.thesevenq.facebook.database.jedis.JedisPublisher;
import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MessageUtils {

    public static String STAR = StringEscapeUtils.unescapeJava("\u2606");
    public static String CHECKMARK = Color.translate("&a&l" + StringEscapeUtils.unescapeJava("\u2713"));
    public static String X = Color.translate("&c&l" + StringEscapeUtils.unescapeJava("\u2717"));
    public static String LUNAR = StringEscapeUtils.unescapeJava("\u272A");
    public static String HEART = StringEscapeUtils.unescapeJava("\u2764");
    public static String LINE = StringEscapeUtils.unescapeJava("\u2503");
    public static String RADIOACTIVE = StringEscapeUtils.unescapeJava("\u2622");
    public static String WARNING = StringEscapeUtils.unescapeJava("\u26A0");
    public static String ARROW_RIGHT = "»";
    public static String ARROW_LEFT = "«";

    public static char CIRCLE = '●';

    public static String noPermission() {
        return CC.RED + "No permission!";
    }

    public static String forPlayersOnly() {
        return CC.RED + "This command is only for players!";
    }

    public static String wrongUsage() {
        return CC.RED + "Sorry! That's not correct usage.";
    }

    public static String playerOffline() {
        return CC.RED + "Sorry! That player can't be found.";
    }

    public static String neverPlayedBefore() {
        return CC.RED + "Sorry! That player never played before.";
    }

    public static String invalidRank() {
        return CC.RED + "Sorry! That rank doesn't exist.";
    }

    public static String niceBuilder(Collection collection) {
        return niceBuilder(collection, ", ", " and ", ".");
    }

    public static String niceBuilder(Collection collection, String color) {
        return niceBuilder(collection, color + ", ", color + " and ", color + '.');
    }

    public static boolean checkOffline(Player player, String args) {
        Player offline = Bukkit.getPlayer(args);

        if(offline == null) {
            player.sendMessage(Color.translate("&cNo player with the name \"" + args + "\" found."));
            return true;
        }

        return false;
    }

    public static void sendMessage(String text) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player != null) {
                player.sendMessage(Color.translate(text));
            }
        }

        Msg.logConsole(text);
    }

    public static void sendMessage(String text, String permission) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player != null) {
                if(player.hasPermission(permission)) {
                    player.sendMessage(Color.translate(text));
                }
            }
        }

        Msg.logConsole(text);
    }

    // announces a message to current server
    public static void announce(final String text) {
        DatabaseManager.getInstance().getPublisher().write(Facebook.getInstance().getConfig().getString("SERVERNAME").toLowerCase(), "announce;" + text);
    }

    // announces a message to a specified server
    public static void announce(final String server, final String text) {
        DatabaseManager.getInstance().getPublisher().write(Facebook.getInstance().getConfig().getString("SERVERNAME").toLowerCase(), "announce;" + text);
    }

    // announces a message to the network // globally
    public static void announceGlobally(final String text) {
        DatabaseManager.getInstance().getPublisher().write(JedisPublisher.GLOBAL, "announce;" + text);
    }

    public static String niceBuilder(Collection collection, String delimiter, String and, String dot) {
        if (collection != null && !collection.isEmpty()) {
            List contents = new ArrayList(collection);
            String last = null;
            if (contents.size() > 1) {
                last = (String)contents.remove(contents.size() - 1);
            }

            StringBuilder builder = new StringBuilder();

            String name;
            for(Iterator iterator = contents.iterator(); iterator.hasNext(); builder.append(name)) {
                name = (String)iterator.next();
                if (builder.length() > 0) {
                    builder.append(delimiter);
                }
            }

            if (last != null) {
                builder.append(and).append(last);
            }

            return builder.append(dot != null ? dot : "").toString();
        } else {
            return "";
        }
    }
}
