package me.thesevenq.facebook.player.lunar.impl;

public enum ServerRule {
   VOICE_ENABLED("voiceEnabled"),
   MINIMAP_STATUS("minimapStatus"),
   SERVER_HANDLES_WAYPOINTS("serverHandlesWaypoints"),
   COMPETITIVE_GAMEMODE("competitiveGame");

   private final String name;

   public String getName() {
      return this.name;
   }

   private ServerRule(String name) {
      this.name = name;
   }
}
