package me.thesevenq.facebook.server.tips;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tips {

    STORE(new String[]{
            "&6[TIP] &eWant nice perks? Visit our store &6store.cobaltnetwork.cf"}),
    TEAMSPEAK(new String[]{
            "&6[TIP] &eNeed assitance? Join support room on our teamspeak &6ts.cobaltnetwork.cf"}),
    DISCORD(new String[]{
            "&6[TIP] &eIf you want to stay updated join our discord &6discord.cobaltnetwork.cf"});

    private String[] text;
}
