package me.thesevenq.facebook.utils;

import com.google.common.collect.ImmutableSet;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.player.events.PlayerSwitchServerEvent;
import me.thesevenq.facebook.utils.string.DefaultFontInfo;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.text.NumberFormat;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class FacebookUtils {

    public static List<Player> getOnlinePlayers() {
        List<Player> players = new ArrayList<Player>();
        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            players.add(online);
        }
        return players;
    }

    public static void centerText(Player player, String message) {
        if(message == null || message.equals("")) player.sendMessage("");
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
                continue;
            }else if(previousCode == true){
                previousCode = false;
                if(c == 'l' || c == 'L'){
                    isBold = true;
                    continue;
                }else isBold = false;
            }else{
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = 154 - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        player.sendMessage(sb.toString() + message);
    }

    public static void centerText(CommandSender sender, String message) {
        if(message == null || message.equals("")) sender.sendMessage("");
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
                continue;
            }else if(previousCode == true){
                previousCode = false;
                if(c == 'l' || c == 'L'){
                    isBold = true;
                    continue;
                }else isBold = false;
            }else{
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = 154 - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        sender.sendMessage(sb.toString() + message);
    }

    public static String formatInteger(Integer number) {
        return NumberFormat.getInstance().format(number).replace(".", ",");
    }

    public static String formatDouble(Double number) {
        return NumberFormat.getInstance().format(number).replace(".", ",");
    }

    public static boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static List<Document> getAllDocuments(MongoCollection<Document> collection) {
        List<Document> documents = new ArrayList<>();

        FindIterable<Document> find = collection.find();
        MongoCursor<Document> cursor = find.iterator();

        while (cursor.hasNext()) {
            documents.add(cursor.next());
        }
        return documents;
    }



    public static List<String> splitText(int length, List<String> lines, String linePrefix, String wordSuffix) {
        StringBuilder builder = new StringBuilder();

        for (String line : lines) {
            builder.append(line.trim());
            builder.append(" ");
        }

        return splitText(length, builder.substring(0, builder.length() - 1), linePrefix, wordSuffix);
    }

    public static List<String> splitText(int length, String text, String linePrefix, String wordSuffix) {
        if (text.length() <= length) {
            return Collections.singletonList(linePrefix + text);
        }

        List<String> lines = new ArrayList<>();
        String[] split = text.split(" ");
        StringBuilder builder = new StringBuilder(linePrefix);

        for (int i = 0; i < split.length; ++i) {
            if (builder.length() + split[i].length() >= length) {
                lines.add(builder.toString());
                builder = new StringBuilder(linePrefix);
            }

            builder.append(split[i]);
            builder.append(wordSuffix);

            if (i == split.length - 1) {
                builder.replace(builder.length() - wordSuffix.length(), builder.length(), "");
            }
        }

        if (builder.length() != 0) {
            lines.add(builder.toString());
        }

        return lines;
    }

    public static void connectToServer(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        Bukkit.getPluginManager().callEvent(new PlayerSwitchServerEvent(player, server));
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
            out.writeUTF(player.getName());
            player.sendPluginMessage(Facebook.getInstance(), "BungeeCord", out.toByteArray());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static String stringFromList(List<String> source) {
        return Arrays.toString(source.toArray()).replace("[", "").replace("]", "");
    }

    public static List<String> stringToList(String source) {
        final String[] splitted = source.split(", ");
        return Arrays.stream(splitted).collect(Collectors.toList());
    }

    public static String colorPing(int ping) {
        if (ping <= 40) {
            return Color.translate("&a" + ping);
        } else if (ping <= 70) {
            return Color.translate("&e" + ping);
        } else if (ping <= 100) {
            return Color.translate("&6" + ping);
        } else {
            return Color.translate("&c" + ping);
        }
    }

    public static Collection<Class<?>> getClassesInPackage(Plugin plugin, String packageName) {
        Collection<Class<?>> classes = new ArrayList<>();

        CodeSource codeSource = plugin.getClass().getProtectionDomain().getCodeSource();
        URL resource = codeSource.getLocation();
        String relPath = packageName.replace('.', '/');
        String resPath = resource.getPath().replace("%20", " ");
        String jarPath = resPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
        JarFile jarFile;

        try {
            jarFile = new JarFile(jarPath);
        } catch (IOException e) {
            throw (new RuntimeException("Unexpected IOException reading JAR File '" + jarPath + "'", e));
        }

        Enumeration<JarEntry> entries = jarFile.entries();

        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String entryName = entry.getName();
            String className = null;

            if (entryName.endsWith(".class") && entryName.startsWith(relPath) &&
                    entryName.length() > (relPath.length() + "/".length())) {
                className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "");
            }

            if (className != null) {
                Class<?> clazz = null;

                try {
                    clazz = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if (clazz != null) {
                    classes.add(clazz);
                }
            }
        }

        try {
            jarFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (ImmutableSet.copyOf(classes));
    }

    public static String getRandomString(int lenght){
        StringBuilder builder = new StringBuilder();
        String string = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for(int i = 0; i < lenght; i++){
            double index = Math.random() * string.length();
            builder.append(string.charAt((int)index));
        }
        return builder.toString();
    }

}
