package me.thesevenq.facebook.register;

import lombok.Getter;
import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.database.DatabaseManager;
import me.thesevenq.facebook.server.nms.hologram.Hologram;
import me.thesevenq.facebook.server.nms.npc.NPC;
import me.thesevenq.facebook.player.PlayerListeners;
import me.thesevenq.facebook.player.freeze.FreezeListener;
import me.thesevenq.facebook.player.freeze.FreezeTask;
import me.thesevenq.facebook.ranks.procedure.GrantProcedureListener;
import me.thesevenq.facebook.server.tips.TipsManager;
import me.thesevenq.facebook.utils.files.ConfigFile;
import me.thesevenq.facebook.utils.menu.ButtonListener;
import me.thesevenq.facebook.utils.menu.MenuUpdateTask;

@Getter
public class FacebookRegister {

    @Getter public ConfigFile config;
    @Getter public ConfigFile holograms;
    @Getter public ConfigFile npcs;

    @Getter public static FacebookRegister instance;

    private NPC npc;
    private Hologram hologram;

    public FacebookRegister() {
        instance = this;
    }

    public void hook() {
        registerListeners();
        registerManagers();
        registerConfigs();
        registerHoloFile();
        registerNpcsFile();
        registerTasks();
    }

    public void registerListeners() {
        new PlayerListeners();
        new ButtonListener();
        new GrantProcedureListener();
        new FreezeListener();
    }


    public void registerTasks() {
        new MenuUpdateTask();
        new FreezeTask();
    }

    private void registerConfigs() {
        config = new ConfigFile(Facebook.getInstance(), "config.yml");
    }

    private void registerHoloFile() {
        holograms = new ConfigFile(Facebook.getInstance(), "holograms.yml");
        this.hologram = new Hologram();
    }

    public void registerNpcsFile() {
        npcs = new ConfigFile(Facebook.getInstance(), "npcs.yml");
        this.npc = new NPC();
    }

    public void registerManagers() {
        new DatabaseManager();
        new TipsManager();
    }
}
