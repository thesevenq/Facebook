package me.thesevenq.facebook.player.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
@AllArgsConstructor
public class PlayerSwitchServerEvent extends Event {

    private static HandlerList handlerList = new HandlerList();
    private Player player;
    private String server;

    public static HandlerList getHandlerList() {
        return handlerList;
    }
    @Override public HandlerList getHandlers() {
        return handlerList;
    }
}