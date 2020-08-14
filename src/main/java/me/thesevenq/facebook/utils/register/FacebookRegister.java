package me.thesevenq.facebook.utils.register;

import lombok.Getter;
import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.auth.AuthListener;
import me.thesevenq.facebook.commands.CommandManager;
import me.thesevenq.facebook.player.cosmetics.CosmeticListeners;
import me.thesevenq.facebook.player.freeze.FreezeManager;
import me.thesevenq.facebook.server.database.DatabaseManager;
import me.thesevenq.facebook.server.database.jedis.JedisPublisher;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.ranks.Rank;
import me.thesevenq.facebook.server.ServerData;
import me.thesevenq.facebook.player.PlayerListeners;
import me.thesevenq.facebook.player.freeze.FreezeListener;
import me.thesevenq.facebook.player.freeze.FreezeTask;
import me.thesevenq.facebook.ranks.procedure.GrantProcedureListener;
import me.thesevenq.facebook.server.tasks.MessageTask;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.ConsoleUtils;
import me.thesevenq.facebook.utils.files.ConfigFile;
import me.thesevenq.facebook.utils.menu.ButtonListener;
import me.thesevenq.facebook.utils.menu.MenuUpdateTask;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;

import java.lang.reflect.Field;

@Getter
public class FacebookRegister {

    @Getter public ConfigFile config;
    @Getter public ConfigFile holograms;
    @Getter public ConfigFile npcs;

    private CommandMap commandMap;
    private FreezeManager freezeManager;

    private ServerData serverData;

    @Getter public static FacebookRegister instance;

    public FacebookRegister() {
        instance = this;
    }

    public void hook() {
        registerListeners();
        registerManagers();
        registerTasks();
        registerUtilties();

        setupCommandMap();

        new CommandManager();

        Rank.importRanks();
        ConsoleUtils.log(CC.PRIMARY + "[Facebook] " + CC.SECONDARY + "Core has started successfully.");
        ConsoleUtils.log(CC.PRIMARY + "[Facebook] " + CC.GREEN + "All databases connected successfully.");

        if (Facebook.getInstance().getConfig().getBoolean("ANNOUNCE_SERVER_STATUS")) Bukkit.getScheduler().runTaskLater(Facebook.getInstance(), () -> DatabaseManager.getInstance().getPublisher().write(JedisPublisher.GLOBAL, "serverstart"), 20L * 3);
        ConsoleUtils.log(CC.D_PURPLE + "[SM] Server is now ready for use!");
    }

    public void unHook() {
        PlayerData.getDataMap().values().forEach(PlayerData::save);
        DatabaseManager.getInstance().getClient().close();
    }

    public void registerUtilties() {
        serverData = new ServerData();
    }

    public void registerListeners() {
        new PlayerListeners();
        new ButtonListener();
        new GrantProcedureListener();
        new FreezeListener();
        new CosmeticListeners();
        new AuthListener();
    }


    public void registerTasks() {
        new MenuUpdateTask();
        new FreezeTask();
        new MessageTask();
    }


    public void registerManagers() {
        new DatabaseManager();
    }

    private void setupCommandMap() {
        try {
            if (Bukkit.getServer() instanceof CraftServer) {
                Field field = CraftServer.class.getDeclaredField("commandMap");
                field.setAccessible(true);
                commandMap = (CommandMap) field.get(Bukkit.getServer());
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            Bukkit.getLogger().info("[Facebook] CommandMap failed to register.");
            System.exit(1);
        }
    }
}
