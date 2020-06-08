package me.thesevenq.facebook.server.tips;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tips {

    STORE(new String[]{
            "&7&m--------------------------------------------",
            "&b&lStore",
            "&fThis is the place to enhance your player experience and also",
            "&fto support us. We offer permanent and temporary ranks",
            "&fcosmetics and lots of gamemode features.", "", "&e&ostore.cobalt.cf",
            "&7&m--------------------------------------------"}),
    TEAMSPEAK(new String[]{
            "&7&m--------------------------------------------",
            "&b&lTeamSpeak",
            "&fCome on and our TeamSpeak server if you need help!",
            "&fThere is our staff to help you for anything.",
            "", "&e&ots.cobalt.cf",
            "&7&m--------------------------------------------"}),
    DISCORD(new String[]{
            "&7&m--------------------------------------------",
            "&3lDiscord",
            "&fCome on and join to our Discord server to participate",
            "&fin our giveaways and to keep up with the",
            "&fupdates coming to the server.",
            "", "&b&odiscord.cobalt.cf",
            "&7&m--------------------------------------------"});

    private String[] text;
}
