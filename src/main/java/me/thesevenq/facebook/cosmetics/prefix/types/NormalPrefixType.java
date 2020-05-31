package me.thesevenq.facebook.cosmetics.prefix.types;

import lombok.Getter;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.Arrays;

public enum NormalPrefixType {
    VERIFIED("Verified", "&a✔", "Only for discord verified players!"),
    WAVE("Wave", "&4o/", "Just waving."),
    EZ("EZ", "&7[&b&lEZ&7]", "You're EZ man."),
    GOLDSTAR("Star", "&6★", "You deserve a gold star!"),
    RICH("Rich", "&7[&2&l$&7]", "We love money, do you?"),
    QUICKED("Quickied", "&7[&2&lQUICKED&7]", "Easy quick drop!"),
    LOVE("Love", "&4❤", "Love your loved ones!"),
    OG("OG", "&7[&a&lO&2&lG&7]", "You're the real OG!"),
    DAB("Dab", "&9<o/", "Look at my dab yuh!"),
    GG("GG", "&7[&6&lG&e&lG&7]", "Good game!"),
    L("L", "&7[&c&o#L&7]", "Prove that you are willing", "to hold the L."),
    FIRST("First", "&b#1", "You're the number one!"),
    AIRLINES("Airlines", "&a&l✈", "Go and fly for free!");

    @Getter
    private String name;
    private String style;
    private String[] description;


    NormalPrefixType(String name, String style, String... description) {
        this.name = name;
        this.style = StringEscapeUtils.unescapeJava(style);
        this.description = description;
    }

    public static NormalPrefixType getByName(String input) {
        return Arrays.stream(values()).filter(type -> type.getName().equalsIgnoreCase(input)).findAny().orElse(null);
    }

    public String getName() {
        return this.name;
    }

    public String getStyle() {
        return this.style;
    }

    public String[] getDescription() {
        return this.description;
    }
}
