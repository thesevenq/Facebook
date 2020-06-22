package me.thesevenq.facebook.player.cosmetics.ranks;

import java.util.Arrays;

import me.thesevenq.facebook.ranks.Rank;
import org.apache.commons.lang.WordUtils;

public enum RankType {
    BASIC(30000),
    SILVER(35000),
    GOLD(40000),
    PLATINUM(45000),
    RUBY(60000);

    private int price;

    public static RankType getByName(String input) {
        return Arrays.stream(values()).filter((type) -> {
            return type.name().equalsIgnoreCase(input) || type.getName().equalsIgnoreCase(input);
        }).findFirst().orElse(null);
    }

    public String getName() {
        return WordUtils.capitalizeFully(this.name().toLowerCase());
    }

    public Rank getRank() {
        return Rank.valueOf(this.name());
    }

    public int getPrice() {
        return this.price;
    }

    RankType(int price) {
        this.price = price;
    }
}
