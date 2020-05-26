package me.thesevenq.facebook;

import com.google.gson.Gson;
import lombok.Getter;
import me.thesevenq.facebook.adapters.nametags.NametagManager;
import me.thesevenq.facebook.database.DatabaseManager;
import me.thesevenq.facebook.commands.CommandManager;
import me.thesevenq.facebook.jedis.JedisPublisher;
import me.thesevenq.facebook.nms.hologram.Hologram;
import me.thesevenq.facebook.nms.npc.NPC;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.player.events.PlayerMessageEvent;
import me.thesevenq.facebook.player.freeze.FreezeManager;
import me.thesevenq.facebook.ranks.Rank;
import me.thesevenq.facebook.register.FacebookRegister;
import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.ConsoleUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class Facebook extends JavaPlugin {

    public static Gson GSON = new Gson();

    @Getter private CommandMap commandMap;
    @Getter private PlayerMessageEvent playerMessageEvent;
    @Getter private long enabledAt;
    @Getter private FreezeManager freezeManager;
    @Getter private NametagManager nametagManager;

    private NPC npc;
    private Hologram hologram;

    @Getter
    public static Facebook instance;

    public void onEnable() {
        instance = this;
        freezeManager = new FreezeManager(this);

        ConsoleUtils.log(CC.PRIMARY + "[Facebook] " + CC.SECONDARY + "Core has started successfully.");

        setupCommandMap();
        Rank.importRanks();
        new CommandManager();
        new FacebookRegister().hook();

        this.hologram = new Hologram();
        this.npc = new NPC();

        enabledAt = System.currentTimeMillis();

        if (Facebook.getInstance().getConfig().getBoolean("DEVMODE")) Bukkit.getScheduler().runTaskLater(this, () -> DatabaseManager.getInstance().getPublisher().write(JedisPublisher.GLOBAL, "serverstart"), 20L * 3);
        ConsoleUtils.log(CC.D_PURPLE + "[SM] Server is now ready for use!");

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

    public void onDisable() {
        hologram.save();
        npc.save();
        PlayerData.getDataMap().values().forEach(PlayerData::save);
        DatabaseManager.getInstance().getClient().close();
    }
}
