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

public class CreativeCommand extends BaseCommand {

   public CreativeCommand() {
      super("creative", Arrays.asList(new String[]{"gmc", "gm1"}), "facebook.admin", true);

   }

   public void execute(CommandSender sender, String[] args) {
      GameMode gameMode = GameMode.CREATIVE;
      Player target;
      if (args.length == 0) {
         if (sender instanceof Player) {
            target = (Player)sender;
            target.setGameMode(gameMode);
            target.sendMessage(CC.SECONDARY + "Your gamemode has been updated to " + CC.PRIMARY + WordUtils.capitalizeFully(gameMode.name()));
         } else {
            sender.sendMessage(CC.RED + "Usage: /gmc <player>");
         }
      } else {
         target = Bukkit.getPlayer(args[0]);
         if (target == null) {
            sender.sendMessage(StringUtil.NO_PLAYER_FOUND.replace("<player>", args[0]));
         } else {
            target.setGameMode(gameMode);
            target.sendMessage(CC.SECONDARY + "Your gamemode has been updated to " + CC.PRIMARY + WordUtils.capitalizeFully(gameMode.name()) + CC.SECONDARY + " by " + (sender instanceof Player ? PlayerData.getByName(((Player) sender).getName()).getRank().getName() : CC.BD_RED + "CONSOLE") + CC.SECONDARY + '.');
            sender.sendMessage(CC.SECONDARY + "You've updated gamemode of " + target.getDisplayName() + CC.SECONDARY + " to " + CC.PRIMARY + WordUtils.capitalizeFully(gameMode.name()) + CC.SECONDARY + '.');
         }
      }
   }
}
