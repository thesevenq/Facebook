package me.thesevenq.facebook.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import me.thesevenq.facebook.utils.CC;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

public class StringUtil {
    private static List COLORS;
    public static final char NICE_CHAR = '●';
    public static final char HEART = '❤';
    public static final String SERVER_NAME = "Sicaro Network";
    public static final String WEBSITE = "www.sicaromc.info";
    public static final String FACEBOOK = "www.facebook.com/Sicaronetwork";
    public static final String STORE_LINK = "store.sicaromc.info";
    public static final String TEAMSPEAK = "ts.sicaromc.info";
    public static final String APPEAL = "bit.ly/2YAukIF";
    public static final String DISCORD = "discord.sicaromc.info";
    public static final String RULES = "bit.ly/2KoNj0j";
    public static final String TWITTER = "www.twitter.com/SicaroNetwork";
    public static final String TWITTER_GAME_FEED = "www.twitter.com/SicaroFeed";
    public static final String NO_PERMISSION;
    public static final String FOR_PLAYER_ONLY;
    public static final String NO_PLAYER_FOUND;
    public static final String NO_ITEM_FOUND;
    public static final String NO_WORLD_FOUND;
    public static final String INTEGER_NOT_VALID;
    public static final String LOAD_ERROR_1;
    public static final String LOAD_ERROR_2;
    public static final String LOAD_ERROR_3;

    public static String formatInteger(int value) {
        return String.format("%,d", new Object[]{value});
    }

    public static int convertChatColorToWoolData(ChatColor color) {
        return color != ChatColor.DARK_RED && color != ChatColor.RED ? (color == ChatColor.DARK_GREEN ? 13 : (color == ChatColor.BLUE ? 11 : (color == ChatColor.DARK_PURPLE ? 10 : (color == ChatColor.DARK_AQUA ? 9 : (color == ChatColor.DARK_GRAY ? 7 : COLORS.indexOf(color)))))) : 14;
    }

    public static Enchantment getEnchantmentByName(Object object) {
        String value = object.toString().replace("_", "").trim();
        Enchantment[] var2 = Enchantment.values();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Enchantment enchant = var2[var4];
            if (value.equals(String.valueOf(enchant.getId())) || value.equalsIgnoreCase(enchant.getName().replace("_", "")) || value.equalsIgnoreCase(enchant.getName())) {
                return enchant;
            }
        }

        String var6 = value.toUpperCase();
        byte var7 = -1;
        switch(var6.hashCode()) {
            case -1232635772:
                if (var6.equals("FEATHERFALLING")) {
                    var7 = 9;
                }
                break;
            case -1199765799:
                if (var6.equals("PROTECTION")) {
                    var7 = 1;
                }
                break;
            case -640798031:
                if (var6.equals("BLASTPROTECTION")) {
                    var7 = 12;
                }
                break;
            case -532083813:
                if (var6.equals("KNOCKBACK")) {
                    var7 = 16;
                }
                break;
            case -360727062:
                if (var6.equals("SILKTOUCH")) {
                    var7 = 29;
                }
                break;
            case 70:
                if (var6.equals("F")) {
                    var7 = 24;
                }
                break;
            case 76:
                if (var6.equals("L")) {
                    var7 = 21;
                }
                break;
            case 2126:
                if (var6.equals("BP")) {
                    var7 = 11;
                }
                break;
            case 2235:
                if (var6.equals("FA")) {
                    var7 = 18;
                }
                break;
            case 2246:
                if (var6.equals("FL")) {
                    var7 = 8;
                }
                break;
            case 2250:
                if (var6.equals("FP")) {
                    var7 = 5;
                }
                break;
            case 2650:
                if (var6.equals("SM")) {
                    var7 = 32;
                }
                break;
            case 2657:
                if (var6.equals("ST")) {
                    var7 = 27;
                }
                break;
            case 68549:
                if (var6.equals("EFF")) {
                    var7 = 30;
                }
                break;
            case 69691:
                if (var6.equals("FLA")) {
                    var7 = 36;
                }
                break;
            case 72641:
                if (var6.equals("INF")) {
                    var7 = 34;
                }
                break;
            case 84169:
                if (var6.equals("UNB")) {
                    var7 = 2;
                }
                break;
            case 2158134:
                if (var6.equals("FIRE")) {
                    var7 = 19;
                }
                break;
            case 2163915:
                if (var6.equals("FORT")) {
                    var7 = 25;
                }
                break;
            case 2342568:
                if (var6.equals("LOOT")) {
                    var7 = 22;
                }
                break;
            case 2464615:
                if (var6.equals("PROT")) {
                    var7 = 0;
                }
                break;
            case 2545237:
                if (var6.equals("SILK")) {
                    var7 = 28;
                }
                break;
            case 40766497:
                if (var6.equals("FORTUNE")) {
                    var7 = 26;
                }
                break;
            case 66902219:
                if (var6.equals("FIREA")) {
                    var7 = 17;
                }
                break;
            case 66902234:
                if (var6.equals("FIREP")) {
                    var7 = 4;
                }
                break;
            case 66975507:
                if (var6.equals("FLAME")) {
                    var7 = 37;
                }
                break;
            case 71665844:
                if (var6.equals("KNOCK")) {
                    var7 = 15;
                }
                break;
            case 76320997:
                if (var6.equals("POWER")) {
                    var7 = 39;
                }
                break;
            case 76491022:
                if (var6.equals("PUNCH")) {
                    var7 = 38;
                }
                break;
            case 78862282:
                if (var6.equals("SHARP")) {
                    var7 = 13;
                }
                break;
            case 79018976:
                if (var6.equals("SMITE")) {
                    var7 = 33;
                }
                break;
            case 258536974:
                if (var6.equals("FIREASPECT")) {
                    var7 = 20;
                }
                break;
            case 386541967:
                if (var6.equals("FIREPROTECTION")) {
                    var7 = 6;
                }
                break;
            case 491568163:
                if (var6.equals("FEATHERF")) {
                    var7 = 7;
                }
                break;
            case 955800104:
                if (var6.equals("INFINITY")) {
                    var7 = 35;
                }
                break;
            case 1068039194:
                if (var6.equals("LOOTING")) {
                    var7 = 23;
                }
                break;
            case 1215489313:
                if (var6.equals("SHARPNESS")) {
                    var7 = 14;
                }
                break;
            case 1446310505:
                if (var6.equals("EFFICIENCY")) {
                    var7 = 31;
                }
                break;
            case 1961730424:
                if (var6.equals("BLASTP")) {
                    var7 = 10;
                }
                break;
            case 2088664092:
                if (var6.equals("UNBREAKING")) {
                    var7 = 3;
                }
        }

        switch(var7) {
            case 0:
            case 1:
                return Enchantment.PROTECTION_ENVIRONMENTAL;
            case 2:
            case 3:
                return Enchantment.DURABILITY;
            case 4:
            case 5:
            case 6:
                return Enchantment.PROTECTION_FIRE;
            case 7:
            case 8:
            case 9:
                return Enchantment.PROTECTION_FALL;
            case 10:
            case 11:
            case 12:
                return Enchantment.PROTECTION_EXPLOSIONS;
            case 13:
            case 14:
                return Enchantment.DAMAGE_ALL;
            case 15:
            case 16:
                return Enchantment.KNOCKBACK;
            case 17:
            case 18:
            case 19:
            case 20:
                return Enchantment.FIRE_ASPECT;
            case 21:
            case 22:
            case 23:
                return Enchantment.LOOT_BONUS_MOBS;
            case 24:
            case 25:
            case 26:
                return Enchantment.LOOT_BONUS_BLOCKS;
            case 27:
            case 28:
            case 29:
                return Enchantment.SILK_TOUCH;
            case 30:
            case 31:
                return Enchantment.DIG_SPEED;
            case 32:
            case 33:
                return Enchantment.DAMAGE_UNDEAD;
            case 34:
            case 35:
                return Enchantment.ARROW_INFINITE;
            case 36:
            case 37:
                return Enchantment.ARROW_FIRE;
            case 38:
                return Enchantment.ARROW_KNOCKBACK;
            case 39:
                return Enchantment.ARROW_DAMAGE;
            default:
                return null;
        }
    }

    public static String niceBuilder(Collection collection) {
        return niceBuilder(collection, ", ", " and ", ".");
    }

    public static String niceBuilder(Collection collection, String color) {
        return niceBuilder(collection, color + ", ", color + " and ", color + '.');
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

    public static int generateRandomNumber(int min, int max) {
        return min + (int)(Math.random() * (double)(max - min + 1));
    }

    public static String getSexyTime(long millis) {
        long seconds = millis / 1000L;
        if (seconds <= 0L) {
            return "0 seconds";
        } else {
            long minutes = seconds / 60L;
            seconds %= 60L;
            long hours = minutes / 60L;
            minutes %= 60L;
            long day = hours / 24L;
            hours %= 24L;
            long years = day / 365L;
            day %= 365L;
            StringBuilder time = new StringBuilder();
            if (years != 0L) {
                time.append(years).append(years == 1L ? " year" : " years").append(day == 0L ? " " : ", ");
            }

            if (day != 0L) {
                time.append(day).append(day == 1L ? " day" : " days").append(hours == 0L ? " " : ", ");
            }

            if (hours != 0L) {
                time.append(hours).append(hours == 1L ? " hour" : " hours").append(minutes == 0L ? " " : ", ");
            }

            if (minutes != 0L) {
                time.append(minutes).append(minutes == 1L ? " minute" : " minutes").append(seconds == 0L ? " " : ", ");
            }

            if (seconds != 0L) {
                time.append(seconds).append(seconds == 1L ? " second" : " seconds");
            }

            return time.toString().trim();
        }
    }

    static {
        COLORS = new ArrayList(Arrays.asList(new ChatColor[]{ChatColor.WHITE, ChatColor.GOLD, ChatColor.LIGHT_PURPLE, ChatColor.AQUA, ChatColor.YELLOW, ChatColor.GREEN, ChatColor.DARK_GRAY, ChatColor.GRAY, ChatColor.DARK_AQUA, ChatColor.DARK_PURPLE, ChatColor.BLUE, ChatColor.BLACK, ChatColor.DARK_GREEN, ChatColor.RED}));
        NO_PERMISSION = CC.RED + "No permission.";
        FOR_PLAYER_ONLY = CC.RED + "Only players can perform this command.";
        NO_PLAYER_FOUND = CC.RED + "No player with the name '<player>' found.";
        NO_ITEM_FOUND = CC.RED + "No item with the name '<item>' found.";
        NO_WORLD_FOUND = CC.RED + "No world with the name '<world>' found.";
        INTEGER_NOT_VALID = CC.RED + "<source> isn't a valid number.";
        LOAD_ERROR_1 = CC.RED + "Error found while loading your data. (1)\n\nTry again later or contact a staff member.";
        LOAD_ERROR_2 = CC.RED + "Error found while loading your data. (2)\n\nTry again later or contact a staff member.";
        LOAD_ERROR_3 = CC.RED + "Error found while loading your data. (3)\n\nTry again later or contact a staff member.";
    }
}
