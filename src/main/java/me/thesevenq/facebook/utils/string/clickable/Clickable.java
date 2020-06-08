package me.thesevenq.facebook.utils.string.clickable;

import java.util.ArrayList;
import java.util.List;

import me.thesevenq.facebook.utils.string.Color;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class Clickable {

   private List<TextComponent> components = new ArrayList<>();

   public TextComponent add(String msg, String hoverMsg, String clickString) {
      TextComponent message = new TextComponent(Color.translate(msg));
      if (hoverMsg != null) {
         message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Color.translate(hoverMsg)).create()));
      }
      if (clickString != null) {
         message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, clickString));
      }
      components.add(message);
      return message;
   }

   public void add(String message) {
      components.add(new TextComponent(message));
   }

   public void sendToPlayer(Player player) {
      player.spigot().sendMessage((BaseComponent[])asComponents());
   }

   public TextComponent[] asComponents() {
      return components.toArray(new TextComponent[0]);
   }
}