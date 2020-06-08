package me.thesevenq.facebook.player.events;

import lombok.Getter;
import lombok.Setter;
import me.thesevenq.facebook.utils.string.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
public class PlayerMessageEvent extends Event implements Cancellable {

    public static HandlerList handlers = new HandlerList();
    public boolean cancelled = false;
    @Getter private PlayerMessageEvent instance;

    public Player player;
    public Player recipient;
    public String message;
    public boolean isReply;

    public PlayerMessageEvent(Player player, Player recipient, String message, boolean isReply) {
        instance = this;
        this.player = player;
        this.recipient = recipient;
        this.message = message;
        this.isReply = isReply;
    }

    @Getter private HashMap<UUID, UUID> lastReplied = new HashMap<>();

    public void send() {
        getLastReplied().put(player.getUniqueId(), recipient.getUniqueId());
        getLastReplied().put(recipient.getUniqueId(), player.getUniqueId());

        this.player.sendMessage(Color.translate("&e(To " + this.recipient.getDisplayName() + "&e) &f") + this.message);
        this.recipient.sendMessage(Color.translate("&e(From " + this.player.getDisplayName() + "&e) &f") + this.message);

    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
