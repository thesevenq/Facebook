package me.thesevenq.facebook.adapters.nametags;

import net.minecraft.server.v1_7_R4.PacketPlayOutScoreboardTeam;

public class NametagInfo {
   private int count;
   private String name;
   private String prefix;
   private String suffix;
   private PacketPlayOutScoreboardTeam packet;

   public NametagInfo(String name, String prefix, String suffix) {
      this.name = name;
      this.prefix = prefix;
      this.suffix = suffix;
      this.count = NametagManager.COUNT++;
      this.packet = new PacketPlayOutScoreboardTeam();
      String teamName = "§8§" + this.count + name;
      if (teamName.length() > 16) {
         teamName = teamName.substring(0, 16);
      }

      this.packet.a = teamName;
      this.packet.f = 0;
      this.packet.b = teamName;
      this.packet.c = prefix;
      this.packet.d = suffix;
      this.packet.g = 3;
   }

   public boolean equals(Object other) {
      if (!(other instanceof NametagInfo)) {
         return false;
      } else {
         NametagInfo otherNametag = (NametagInfo)other;
         return this.name.equals(otherNametag.name) && this.prefix.equals(otherNametag.prefix) && this.suffix.equals(otherNametag.suffix);
      }
   }

   public int getCount() {
      return this.count;
   }

   public String getName() {
      return this.name;
   }

   public String getPrefix() {
      return this.prefix;
   }

   public String getSuffix() {
      return this.suffix;
   }

   public PacketPlayOutScoreboardTeam getPacket() {
      return this.packet;
   }
}
