package me.thesevenq.facebook.server.nms.npc;

import com.google.common.base.Joiner;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.string.StringUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class NPCCommand extends BaseCommand {

   public NPCCommand() {
      super("npc", Collections.singletonList("npcs"), "facebook.admin", true);
   }

   public void execute(CommandSender sender, String[] args) {
      Player player = (Player)sender;
      if (args.length == 0) {
         this.sendUsage(player);
      } else {
         String var4 = args[0].toLowerCase();
         byte var5 = -1;
         switch(var4.hashCode()) {
         case -1360201941:
            if (var4.equals("teleport")) {
               var5 = 5;
            }
            break;
         case -1352294148:
            if (var4.equals("create")) {
               var5 = 0;
            }
            break;
         case -1335458389:
            if (var4.equals("delete")) {
               var5 = 1;
            }
            break;
         case -934594754:
            if (var4.equals("rename")) {
               var5 = 2;
            }
            break;
         case 3708:
            if (var4.equals("tp")) {
               var5 = 6;
            }
            break;
         case 3322014:
            if (var4.equals("list")) {
               var5 = 4;
            }
            break;
         case 950394699:
            if (var4.equals("command")) {
               var5 = 3;
            }
         }

         NPC npc;
         String message;
         switch(var5) {
         case 0:
            if (args.length < 4) {
               player.sendMessage(CC.RED + "Usage: /npc create <name> <type> <message>");
               return;
            }

            if (NPC.getByName(args[1]) != null) {
               player.sendMessage(CC.B_RED + args[1] + CC.RED + " npc already exist.");
               return;
            }

            try {
               EntityType.valueOf(args[2].toUpperCase());
            } catch (Exception var8) {
               player.sendMessage(CC.B_RED + args[0] + CC.RED + " doesn't exists.");
               return;
            }

            (new NPC(Color.translate(Joiner.on(" ").skipNulls().join(Arrays.copyOfRange(args, 3, args.length))), args[1], (String)null, player.getLocation(), EntityType.valueOf(args[2].toUpperCase()))).spawn();
            player.sendMessage(CC.SECONDARY + "You've created npc named " + CC.PRIMARY + args[1] + CC.SECONDARY + '.');
            break;
         case 1:
            if (args.length < 2) {
               player.sendMessage(CC.RED + "Usage: /npc delete <name>");
               return;
            }

            npc = NPC.getByName(args[1]);
            if (npc == null) {
               player.sendMessage(CC.B_RED + args[1] + CC.RED + " npc doesn't exist.");
               return;
            }

            npc.delete();
            player.sendMessage(CC.SECONDARY + "You've deleted npc named " + CC.PRIMARY + args[1] + CC.SECONDARY + '.');
            break;
         case 2:
            if (args.length < 3) {
               player.sendMessage(CC.RED + "Usage: /npc rename <name> <message>");
               return;
            }

            npc = NPC.getByName(args[1]);
            if (npc == null) {
               player.sendMessage(CC.B_RED + args[1] + CC.RED + " npc doesn't exist.");
               return;
            }

            message = Joiner.on(" ").skipNulls().join(Arrays.copyOfRange(args, 2, args.length));
            npc.setMessage(Color.translate(message));
            player.sendMessage(CC.SECONDARY + "You've renamed npc named " + CC.PRIMARY + args[1] + CC.SECONDARY + " to " + Color.translate(message) + CC.SECONDARY + '.');
            break;
         case 3:
            if (args.length < 3) {
               player.sendMessage(CC.RED + "Usage: /npc command <name> <message>");
               return;
            }

            npc = NPC.getByName(args[1]);
            if (npc == null) {
               player.sendMessage(CC.B_RED + args[1] + CC.RED + " npc doesn't exist.");
               return;
            }

            message = Joiner.on(" ").skipNulls().join(Arrays.copyOfRange(args, 2, args.length));
            npc.setCommand(message);
            player.sendMessage(CC.SECONDARY + "You've changed command of npc named " + CC.PRIMARY + args[1] + CC.SECONDARY + " to " + message + CC.SECONDARY + '.');
            break;
         case 4:
            player.sendMessage("");
            player.sendMessage(CC.SECONDARY + "Showing you all npcs...");
            StringBuilder builder = new StringBuilder();
            if (!NPC.getNpcs().isEmpty()) {
               builder.append(StringUtil.niceBuilder((Collection)NPC.getNpcs().values().stream().filter(Objects::nonNull).map((npcx) -> CC.PRIMARY + npcx.getName()).collect(Collectors.toList()), CC.SECONDARY));
            } else {
               builder.append(CC.I_GRAY).append("There is no npcs created yet.");
            }

            player.sendMessage(builder.toString());
            player.sendMessage("");
            break;
         case 5:
         case 6:
            if (args.length < 2) {
               player.sendMessage(CC.RED + "Usage: /npc tp <name>");
               return;
            }

            npc = NPC.getByName(args[1]);
            if (npc == null) {
               player.sendMessage(CC.B_RED + args[1] + CC.RED + " npc doesn't exist.");
               return;
            }

            player.teleport(npc.getLocation());
            player.sendMessage(CC.SECONDARY + "You've teleported to npc named " + CC.PRIMARY + args[1] + CC.SECONDARY + '.');
            break;
         default:
            this.sendUsage(player);
         }

      }
   }

   private void sendUsage(Player player) {
      player.sendMessage(CC.RED + "NPC Command - Help");
      player.sendMessage(CC.RED + "/npc create <name> <type> <message> ");
      player.sendMessage(CC.RED + "/npc delete <name> ");
      player.sendMessage(CC.RED + "/npc rename <name> <message> ");
      player.sendMessage(CC.RED + "/npc command <name> <message> ");
      player.sendMessage(CC.RED + "/npc tp <name> ");
      player.sendMessage(CC.RED + "/npc list ");
      player.sendMessage("");
   }
}
