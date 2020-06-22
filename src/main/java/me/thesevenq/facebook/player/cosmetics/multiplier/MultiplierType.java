package me.thesevenq.facebook.player.cosmetics.multiplier;

import java.util.Arrays;

public enum MultiplierType {
    X1_5(1.5D),
    X2_0(2.0D),
    X2_5(2.5D);

    private double multiply;

    public static MultiplierType getByName(String input) {
        return (MultiplierType)Arrays.stream(values()).filter((type) -> {
            return type.name().equalsIgnoreCase(input) || type.getName().equalsIgnoreCase(input);
        }).findFirst().orElse(null);
    }

    public String getName() {
        return this.name().toLowerCase().replace("_", ".");
    }

    public double getMultiply() {
        return this.multiply;
    }

    MultiplierType(double multiply) {
        this.multiply = multiply;
    }
}
