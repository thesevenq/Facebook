package me.thesevenq.facebook.ranks;

import lombok.Getter;
import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.Color;
import me.thesevenq.facebook.utils.ConsoleUtils;

import java.util.stream.Stream;

@Getter
public enum Rank {

    DEFAULT("Default", "", CC.GREEN),
    BASIC("Basic", "&7[&3Basic&7] ", CC.DARK_AQUA),
    SILVER("Silver", "&7[&8Silver&7] ", CC.GRAY),
    GOLD("Gold", "&7[&6Gold&7] ", CC.GOLD),
    PLATINUM("Platinum", "&7[&9Platinum&7] ", CC.BLUE),
    RUBY("Ruby", "&7[&dRuby&7] ", CC.PINK),
    MEDIA("Media", "&7[&d&oMedia&7] ", CC.IL_PURPLE),
    YOUTUBER("YouTuber", "&7[&5YouTuber&7] ", CC.D_PURPLE),
    FAMOUS("Famous", "&7[&6Famous&7] ", CC.GOLD),
    PARTNER("Partner", "&7[&5&oPartner&7] ", CC.ID_PURPLE),
    BUILDER("Builder", "&7[&aBuilder&7] ", CC.GREEN),
    HOST("Host", "&7[&6&oHost&7] ", CC.I_GOLD),
    TRIALMOD("TrialMod", "&7[&e&oTrial Mod&7] ", CC.YELLOW),
    MOD("Mod", "&7[&3Mod&7] ", CC.DARK_AQUA),
    SENIORMOD("SeniorMod", "&7[&3&oSenior Mod&7] ", CC.ID_AQUA),
    ADMIN("Admin", "&7[&cAdmin&7] ", CC.I_RED),
    SENIORADMIN("SeniorAdmin", "&7[&c&oSenior Admin&7] ", CC.I_RED),
    MANAGER("Manager", "&7[&4&oManager&7] ", CC.ID_RED),
    DEVELOPER("Developer", "&7[&bDeveloper&7] ", CC.AQUA),
    OWNER("Owner", "&7[&4Owner&7] ", CC.D_RED);

    @Getter
    private String name;
    private String prefix;
    private String color;

    Rank(String name, String prefix, String color) {
        this.name = name;
        this.prefix = prefix;
        this.color = color;
    }

    public static Rank getRankByName(String name) {
        return Stream.of(values()).filter(rank -> rank.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public static void importRanks() {
        ConsoleUtils.log(CC.PRIMARY + "[Facebook] " + CC.SECONDARY + "Successfully imported " + CC.PRIMARY + values().length + CC.SECONDARY + " ranks.");
        ConsoleUtils.log(Color.translate("&b[Facebook] &eImported ranks&7: &4Owner&e, &bDeveloper&e, &4&oManager&e, &cSenior Admin&e, &cAdmin&7, &3&oSenior Mod&e, &3Mod&e, &e&oTrial Mod&e, &6&oHost&e, &aBuilder&e, &5&oPartner&e, &6Famous&e, &5YouTuber&e, &dMedia&e, &dRuby&e, &9Platinum&e, &6Gold&e, &7Silver&e, &bBasic&e, &aDefault&e."));
    }
}
