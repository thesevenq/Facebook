package me.thesevenq.facebook.commands.impl.staff.gamemode;

import me.thesevenq.facebook.commands.BaseCommand;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.StringUtil;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;

public class GamemodeCommand extends BaseCommand {

   public GamemodeCommand() {
      super("gamemode", Arrays.asList(new String[]{"gm"}), "facebook.admin", true);
   }

   public void execute(CommandSender sender, String[] args) {
      if (args.length == 0) {
         sender.sendMessage(CC.RED + "Usage: /gamemode <c|s|a> <player>");
      } else if (args.length == 1) {
         if (!(sender instanceof Player)) {
            sender.sendMessage(StringUtil.FOR_PLAYER_ONLY);
         } else {
            Player player = (Player)sender;
            String var10 = args[0].toLowerCase();
            byte var8 = -1;
            switch(var10.hashCode()) {
            case 48:
               if (var10.equals("0")) {
                  var8 = 2;
               }
               break;
            case 49:
               if (var10.equals("1")) {
                  var8 = 0;
               }
               break;
            case 50:
               if (var10.equals("2")) {
                  var8 = 4;
               }
               break;
            case 97:
               if (var10.equals("a")) {
                  var8 = 5;
               }
               break;
            case 99:
               if (var10.equals("c")) {
                  var8 = 1;
               }
               break;
            case 115:
               if (var10.equals("s")) {
                  var8 = 3;
               }
            }

            switch(var8) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
               GameMode gameMode = Arrays.asList(new String[]{"1", "c"}).contains(args[0].toLowerCase()) ? GameMode.CREATIVE : (Arrays.asList(new String[]{"0", "s"}).contains(args[0].toLowerCase()) ? GameMode.SURVIVAL : (Arrays.asList(new String[]{"2", "a"}).contains(args[0].toLowerCase()) ? GameMode.ADVENTURE : GameMode.SURVIVAL));
               player.setGameMode(gameMode);
               player.sendMessage(CC.SECONDARY + "Your gamemode has been updated to " + CC.PRIMARY + WordUtils.capitalizeFully(gameMode.name()));
               break;
            default:
               player.sendMessage(CC.RED + "Usage: /gamemode <c|s|a>");
            }

         }
      } else {
         if (args.length == 2) {
            String var3 = args[0].toLowerCase();
            byte var4 = -1;
            switch(var3.hashCode()) {
            case 48:
               if (var3.equals("0")) {
                  var4 = 2;
               }
               break;
            case 49:
               if (var3.equals("1")) {
                  var4 = 0;
               }
               break;
            case 50:
               if (var3.equals("2")) {
                  var4 = 4;
               }
               break;
            case 97:
               if (var3.equals("a")) {
                  var4 = 5;
               }
               break;
            case 99:
               if (var3.equals("c")) {
                  var4 = 1;
               }
               break;
            case 115:
               if (var3.equals("s")) {
                  var4 = 3;
               }
            }

            switch(var4) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
               GameMode gameMode = Arrays.asList(new String[]{"1", "c"}).contains(args[0].toLowerCase()) ? GameMode.CREATIVE : (Arrays.asList(new String[]{"0", "s"}).contains(args[0].toLowerCase()) ? GameMode.SURVIVAL : (Arrays.asList(new String[]{"2", "a"}).contains(args[0].toLowerCase()) ? GameMode.ADVENTURE : GameMode.SURVIVAL));
               Player target = Bukkit.getPlayer(args[1]);
               if (target == null) {
                  sender.sendMessage(StringUtil.NO_PLAYER_FOUND.replace("<player>", args[1]));
                  return;
               }

               target.setGameMode(gameMode);
               target.sendMessage(CC.SECONDARY + "Your gamemode has been updated to " + CC.PRIMARY + WordUtils.capitalizeFully(gameMode.name()) + CC.SECONDARY + " by " + (sender instanceof Player ? PlayerData.getByName(((Player) sender).getName()).getRank().getName() : CC.BD_RED + "CONSOLE") + CC.SECONDARY + '.');
               sender.sendMessage(CC.SECONDARY + "You've updated gamemode of " + target.getDisplayName() + CC.SECONDARY + " to " + CC.PRIMARY + WordUtils.capitalizeFully(gameMode.name()) + CC.SECONDARY + '.');
               break;
            default:
               sender.sendMessage(CC.RED + "Usage: /gamemode <c|s|a> <player>");
            }
         }

      }
   }
}
