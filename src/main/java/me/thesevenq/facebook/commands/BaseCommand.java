package me.thesevenq.facebook.commands;

import java.util.ArrayList;
import java.util.List;
import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.utils.string.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BaseCommand extends Command {
    public Facebook core;

    public String permission;

    public boolean forPlayerUseOnly;

    public BaseCommand(String command, List<String> aliases, String permission, boolean forPlayerUseOnly) {
        super(command);
        setAliases(aliases);
        this.permission = permission;
        this.forPlayerUseOnly = forPlayerUseOnly;
        this.core = Facebook.getInstance();
    }

    public BaseCommand(String command) {
        this(command, new ArrayList<>(), (String)null, false);
    }

    public BaseCommand(String command, List<String> aliases) {
        this(command, aliases, (String)null, false);
    }

    public BaseCommand(String command, List<String> aliases, boolean forPlayerUseOnly) {
        this(command, aliases, (String)null, forPlayerUseOnly);
    }

    public BaseCommand(String command, String permission) {
        this(command, new ArrayList<>(), permission, false);
    }

    public BaseCommand(String command, boolean forPlayerUseOnly) {
        this(command, new ArrayList<>(), (String)null, forPlayerUseOnly);
    }

    public BaseCommand(String command, String permission, boolean forPlayerUseOnly) {
        this(command, new ArrayList<>(), permission, forPlayerUseOnly);
    }

    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof Player)) {
            if (this.forPlayerUseOnly) {
                sender.sendMessage(MessageUtils.forPlayersOnly());
                return true;
            }
            execute(sender, args);
            return true;
        }
        Player player = (Player)sender;
        if (this.permission != null && !player.hasPermission(this.permission)) {
            player.sendMessage(MessageUtils.noPermission());
            return true;
        }
        execute(sender, args);
        execute(player, args);
        return true;
    }

    public void execute(CommandSender sender, String[] args) {}

    public void execute(Player player, String[] args) {}
}
