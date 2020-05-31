package me.thesevenq.facebook.server.nms.npc;

import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.MinecraftServer;
import net.minecraft.server.v1_7_R4.PlayerInteractManager;
import net.minecraft.server.v1_7_R4.WorldServer;
import net.minecraft.util.com.mojang.authlib.GameProfile;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;

final class NPCEntity extends EntityPlayer {
   NPCEntity(MinecraftServer minecraftserver, WorldServer worldserver, GameProfile gameprofile, PlayerInteractManager playerinteractmanager) {
      super(minecraftserver, worldserver, gameprofile, playerinteractmanager);
      this.collidesWithEntities = false;
      new NPCConnection(((CraftServer)Bukkit.getServer()).getHandle().getServer(), new NPCNetworkManager(), this);
   }
}
