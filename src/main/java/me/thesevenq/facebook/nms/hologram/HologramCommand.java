package me.thesevenq.facebook.nms.hologram;

import com.google.common.base.Joiner;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.Color;
import me.thesevenq.facebook.utils.StringUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HologramCommand extends BaseCommand {

   public HologramCommand() {
      super("hologram", Arrays.asList(new String[]{"holograms", "holo"}), "facebook.admin", true);
   }

   public void execute(CommandSender sender, String[] args) {
      Player player = (Player)sender;
      if (args.length == 0) {
         this.sendUsage(player);
      } else {
         String var4 = args[0].toLowerCase();
         byte var5 = -1;
         switch(var4.hashCode()) {
         case -1687115739:
            if (var4.equals("removelineabove")) {
               var5 = 3;
            }
            break;
         case -1686105927:
            if (var4.equals("removelinebelow")) {
               var5 = 5;
            }
            break;
         case -1360201941:
            if (var4.equals("teleport")) {
               var5 = 8;
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
         case -1201821355:
            if (var4.equals("newlocation")) {
               var5 = 10;
            }
            break;
         case -934594754:
            if (var4.equals("rename")) {
               var5 = 6;
            }
            break;
         case 3708:
            if (var4.equals("tp")) {
               var5 = 9;
            }
            break;
         case 3322014:
            if (var4.equals("list")) {
               var5 = 7;
            }
            break;
         case 3357649:
            if (var4.equals("move")) {
               var5 = 11;
            }
            break;
         case 358348968:
            if (var4.equals("addlineabove")) {
               var5 = 2;
            }
            break;
         case 359358780:
            if (var4.equals("addlinebelow")) {
               var5 = 4;
            }
         }

         Hologram hologram;
         String message;
         switch(var5) {
         case 0:
            if (args.length < 3) {
               player.sendMessage(CC.RED + "Usage: /hologram create <name> <message>");
               return;
            }

            if (Hologram.getByName(args[1]) != null) {
               player.sendMessage(CC.B_RED + args[1] + CC.RED + " hologram already exist.");
               return;
            }

            (new Hologram(Color.translate(Joiner.on(" ").skipNulls().join(Arrays.copyOfRange(args, 2, args.length))), args[1], player.getLocation(), true)).spawn(false);
            player.sendMessage(CC.SECONDARY + "You've created hologram named " + CC.PRIMARY + args[1] + CC.SECONDARY + '.');
            break;
         case 1:
            if (args.length < 2) {
               player.sendMessage(CC.RED + "Usage: /hologram delete <name>");
               return;
            }

            hologram = Hologram.getByName(args[1]);
            if (hologram == null) {
               player.sendMessage(CC.B_RED + args[1] + CC.RED + " hologram doesn't exist.");
               return;
            }

            hologram.delete(true);
            player.sendMessage(CC.SECONDARY + "You've deleted hologram named " + CC.PRIMARY + args[1] + CC.SECONDARY + '.');
            break;
         case 2:
            if (args.length < 3) {
               player.sendMessage(CC.RED + "Usage: /hologram addlineabove <name> <message>");
               return;
            }

            hologram = Hologram.getByName(args[1]);
            if (hologram == null) {
               player.sendMessage(CC.B_RED + args[1] + CC.RED + " hologram doesn't exist.");
               return;
            }

            message = Joiner.on(" ").skipNulls().join(Arrays.copyOfRange(args, 2, args.length));
            hologram.addLineAbove(Color.translate(message));
            player.sendMessage(CC.SECONDARY + "You've added line above " + CC.PRIMARY + args[1] + CC.SECONDARY + " hologram with text " + Color.translate(message.toString()) + CC.SECONDARY + '.');
            break;
         case 3:
            if (args.length < 3) {
               player.sendMessage(CC.RED + "Usage: /hologram removelineabove <name> <message>");
               return;
            }

            hologram = Hologram.getByName(args[1]);
            if (hologram == null) {
               player.sendMessage(CC.B_RED + args[1] + CC.RED + " hologram doesn't exist.");
               return;
            }

            message = Joiner.on(" ").skipNulls().join(Arrays.copyOfRange(args, 2, args.length));
            if (hologram.getLinesByMessage(message, hologram.getLinesAbove()) == null) {
               player.sendMessage(Color.translate(message) + CC.RED + " hologram doesn't exist.");
               return;
            }

            hologram.removeLineAbove(Color.translate(message));
            player.sendMessage(CC.SECONDARY + "You've removed line above " + CC.PRIMARY + args[1] + CC.SECONDARY + " hologram with text " + Color.translate(message.toString()) + CC.SECONDARY + '.');
            break;
         case 4:
            if (args.length < 3) {
               player.sendMessage(CC.RED + "Usage: /hologram addlinebelow <name> <message>");
               return;
            }

            hologram = Hologram.getByName(args[1]);
            if (hologram == null) {
               player.sendMessage(CC.B_RED + args[1] + CC.RED + " hologram doesn't exist.");
               return;
            }

            message = Joiner.on(" ").skipNulls().join(Arrays.copyOfRange(args, 2, args.length));
            hologram.addLineBelow(Color.translate(message));
            player.sendMessage(CC.SECONDARY + "You've added line below " + CC.PRIMARY + args[1] + CC.SECONDARY + " hologram with text " + Color.translate(message) + CC.SECONDARY + '.');
            break;
         case 5:
            if (args.length < 3) {
               player.sendMessage(CC.RED + "Usage: /hologram removelinebelow <name> <message>");
               return;
            }

            hologram = Hologram.getByName(args[1]);
            if (hologram == null) {
               player.sendMessage(CC.B_RED + args[1] + CC.RED + " hologram doesn't exist.");
               return;
            }

            message = Joiner.on(" ").skipNulls().join(Arrays.copyOfRange(args, 2, args.length));
            if (hologram.getLinesByMessage(message, hologram.getLinesBelow()) == null) {
               player.sendMessage(Color.translate(message) + CC.RED + " hologram doesn't exist.");
               return;
            }

            hologram.removeLineBelow(Color.translate(message));
            player.sendMessage(CC.SECONDARY + "You've removed line below " + CC.PRIMARY + args[1] + CC.SECONDARY + " hologram with text " + Color.translate(message.toString()) + CC.SECONDARY + '.');
            break;
         case 6:
            if (args.length < 3) {
               player.sendMessage(CC.RED + "Usage: /hologram rename <name> <message>");
               return;
            }

            hologram = Hologram.getByName(args[1]);
            if (hologram == null) {
               player.sendMessage(CC.B_RED + args[1] + CC.RED + " hologram doesn't exist.");
               return;
            }

            message = Joiner.on(" ").skipNulls().join(Arrays.copyOfRange(args, 2, args.length));
            hologram.setMessage(Color.translate(message));
            player.sendMessage(CC.SECONDARY + "You've renamed hologram named " + CC.PRIMARY + args[1] + CC.SECONDARY + " to " + Color.translate(message) + CC.SECONDARY + '.');
            break;
         case 7:
            player.sendMessage("");
            player.sendMessage(CC.SECONDARY + "Showing you all holograms...");
            StringBuilder builder = new StringBuilder();
            if (!Hologram.getHolograms().isEmpty()) {
               builder.append(StringUtil.niceBuilder((Collection)Hologram.getHolograms().values().stream().filter(Objects::nonNull).map((hologramx) -> {
                  return CC.PRIMARY + hologramx.getName() + CC.SECONDARY + " (" + CC.PRIMARY + hologramx.getLinesAbove().size() + " above" + CC.SECONDARY + ", " + CC.PRIMARY + hologramx.getLinesBelow().size() + " below" + CC.SECONDARY + ")";
               }).collect(Collectors.toList()), CC.SECONDARY));
            } else {
               builder.append(CC.I_GRAY).append("There is no holograms created yet.");
            }

            player.sendMessage(builder.toString());
            player.sendMessage("");
            break;
         case 8:
         case 9:
            if (args.length < 2) {
               player.sendMessage(CC.RED + "Usage: /hologram tp <name>");
               return;
            }

            hologram = Hologram.getByName(args[1]);
            if (hologram == null) {
               player.sendMessage(CC.B_RED + args[1] + CC.RED + " npc doesn't exist.");
               return;
            }

            player.teleport(hologram.getLocation());
            player.sendMessage(CC.SECONDARY + "You've teleported to hologram named " + CC.PRIMARY + args[1] + CC.SECONDARY + '.');
            break;
         case 10:
         case 11:
            if (args.length < 2) {
               player.sendMessage(CC.RED + "Usage: /hologram move <name>");
               return;
            }

            hologram = Hologram.getByName(args[1]);
            if (hologram == null) {
               player.sendMessage(CC.B_RED + args[1] + CC.RED + " npc doesn't exist.");
               return;
            }

            hologram.teleportToNewLocation(player.getLocation());
            player.sendMessage(CC.SECONDARY + "You've updated location of hologram named " + CC.PRIMARY + args[1] + CC.SECONDARY + '.');
            break;
         default:
            this.sendUsage(player);
         }

      }
   }

   private void sendUsage(Player player) {
      player.sendMessage(CC.RED + "Hologram Command - Help");
      player.sendMessage(CC.RED + "/hologram create <name> <message> ");
      player.sendMessage(CC.RED + "/hologram delete <name> ");
      player.sendMessage(CC.RED + "/hologram addlineabove <name> <message> ");
      player.sendMessage(CC.RED + "/hologram removelineabove <name> <message>");
      player.sendMessage(CC.RED + "/hologram addlinebelow <name> <message> ");
      player.sendMessage(CC.RED + "/hologram removelinebelow <name> <message> ");
      player.sendMessage(CC.RED + "/hologram rename <name> <message>");
      player.sendMessage(CC.RED + "/hologram tp <name> ");
      player.sendMessage(CC.RED + "/hologram move <name> ");
      player.sendMessage(CC.RED + "/hologram list ");
      player.sendMessage("");
   }
}
