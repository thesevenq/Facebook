package me.thesevenq.facebook.nms;

import me.thesevenq.facebook.nms.npc.NPC;
import me.thesevenq.facebook.utils.Tasks;
import net.minecraft.server.v1_7_R4.EnumEntityUseAction;
import net.minecraft.server.v1_7_R4.Packet;
import net.minecraft.server.v1_7_R4.PacketPlayInCustomPayload;
import net.minecraft.server.v1_7_R4.PacketPlayInUseEntity;
import net.minecraft.server.v1_7_R4.PlayerConnection;
import network.secondlife.spigot.packet.PacketInterceptor;

public class NMSPacketHandler extends PacketInterceptor {
   public void onPacketReceive(PlayerConnection connection, Packet packet) {
      if (packet instanceof PacketPlayInUseEntity) {
         PacketPlayInUseEntity use = (PacketPlayInUseEntity)packet;
         if (use.c() != EnumEntityUseAction.INTERACT) {
            return;
         }

         NPC npc = NPC.getById(use.a);
         if (npc == null) {
            return;
         }

         if (npc.getCommand() != null && !npc.getCommand().equals("")) {
            Tasks.run(() -> {
               connection.getPlayer().chat(npc.getCommand());
            });
         }
      } else if (packet instanceof PacketPlayInCustomPayload) {
         PacketPlayInCustomPayload var5 = (PacketPlayInCustomPayload)packet;
      }

   }
}
