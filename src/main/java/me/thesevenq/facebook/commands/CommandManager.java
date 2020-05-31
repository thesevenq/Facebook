package me.thesevenq.facebook.commands;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.commands.impl.*;
import me.thesevenq.facebook.commands.impl.chatcontrol.ClearChatCommand;
import me.thesevenq.facebook.commands.impl.chatcontrol.MuteChatCommand;
import me.thesevenq.facebook.commands.impl.message.*;
import me.thesevenq.facebook.commands.impl.staff.*;
import me.thesevenq.facebook.commands.impl.staff.gamemode.AdventureCommand;
import me.thesevenq.facebook.commands.impl.staff.gamemode.CreativeCommand;
import me.thesevenq.facebook.commands.impl.staff.gamemode.GamemodeCommand;
import me.thesevenq.facebook.commands.impl.staff.gamemode.SurvivalCommand;
import me.thesevenq.facebook.commands.impl.toggle.TogglePMCommand;
import me.thesevenq.facebook.commands.impl.toggle.ToggleStaffChatCommand;
import me.thesevenq.facebook.commands.impl.toggle.ToggleTipsCommand;
import me.thesevenq.facebook.ranks.commands.*;
import me.thesevenq.facebook.server.nms.hologram.HologramCommand;
import me.thesevenq.facebook.server.nms.npc.NPCCommand;
import me.thesevenq.facebook.player.coins.CoinsCommand;
import me.thesevenq.facebook.server.shutdown.ShutdownCommand;

import java.util.HashSet;
import java.util.Set;

public class CommandManager {

    public CommandManager() {
        registerCommands();
    }

    private void registerCommands() {
        Set<BaseCommand> commands = new HashSet<>();

        commands.add(new CosmeticsCommand());
        commands.add(new ListCommand());
        commands.add(new CoinsCommand());
        commands.add(new QuestsCommand());
        commands.add(new QuickGrantCommand());

        commands.add(new ClearChatCommand());
        commands.add(new MuteChatCommand());
        commands.add(new AnnounceCommand());
        commands.add(new ToggleStaffChatCommand());

        commands.add(new GamemodeCommand());
        commands.add(new NPCCommand());
        commands.add(new HologramCommand());

        commands.add(new AdventureCommand());
        commands.add(new SurvivalCommand());
        commands.add(new CreativeCommand());

        commands.add(new RunCmdCommand());
        commands.add(new SetSpawnCommand());
        commands.add(new PingCommand());
        commands.add(new JoinMeCommand());
        commands.add(new FreezeCommand());
        commands.add(new ToggleTipsCommand());
        commands.add(new JoinCommand());
        commands.add(new ShutdownCommand());

        commands.add(new MessageCommand());
        commands.add(new ReplyCommand());
        commands.add(new TogglePMCommand());

        commands.add(new HealCommand());

        commands.add(new GrantCommand());
        commands.add(new GrantsCommand());
        commands.add(new SetRankCommand());
        commands.add(new RankListCommand());

        commands.forEach(command -> Facebook.getInstance().getCommandMap().register("core", command));
    }
}
