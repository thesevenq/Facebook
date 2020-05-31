package me.thesevenq.facebook.server.tips;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tips {

    STORE(new String[]{
            "&7&m--------------------------------------------",
            "&3&lStore",
            "&7This is the place to enhance your player experience and also",
            "&7to support us. We offer permanent and temporary ranks",
            "&7cosmetics and lots of gamemode features.", "", "&e&ostore.sicaro.club",
            "&7&m--------------------------------------------"}),
    TEAMSPEAK(new String[]{
            "&7&m--------------------------------------------",
            "&3&lTeamSpeak",
            "&7Come on and our TeamSpeak server if you need help!",
            "&7There is our staff to help you for anything.",
            "", "&e&ots.sicaro.club",
            "&7&m--------------------------------------------"}),
    DISCORD(new String[]{
            "&7&m--------------------------------------------",
            "&3lDiscord",
            "&7Come on and join to our Discord server to participate",
            "&7in our giveaways and to keep up with the",
            "&7updates coming to the server.",
            "", "&e&odiscord.sicaro.club",
            "&7&m--------------------------------------------"});

    private String[] text;
}
