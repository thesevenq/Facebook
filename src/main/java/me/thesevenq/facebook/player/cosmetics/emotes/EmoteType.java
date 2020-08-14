package me.thesevenq.facebook.player.cosmetics.emotes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.Arrays;

@Getter @AllArgsConstructor

public enum EmoteType {
   WAVE("Wave", 0),
   HANDS_UP("Hands Up", 1),
   FLOSS("Floss", 2),
   DAB("Dab", 3),
   T_POSE("T Pose", 4),
   SHRUG("Shrug", 5),
   FACEPALM("Facepalm", 6);

   private String name;
   private int id;

   public static EmoteType getByName(String input) {
      return (EmoteType)Arrays.stream(values()).filter((type) -> {
         return type.name().equalsIgnoreCase(input) || type.getName().equalsIgnoreCase(input);
      }).findFirst().orElse(null);
   }

   public boolean hasPermission(Player player) {
      return player.hasPermission(this.getPermissionForAll()) || player.hasPermission(this.getPermission());
   }

   public String getPermissionForAll() {
      return "cosmetics.emotes.*";
   }

   public String getPermission() {
      return "cosmetics.emotes." + this.name().toLowerCase();
   }


}
