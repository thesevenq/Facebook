package me.thesevenq.facebook;

import com.google.gson.Gson;
import lombok.Getter;
import me.thesevenq.facebook.adapters.nametags.NametagManager;
import me.thesevenq.facebook.player.events.PlayerMessageEvent;
import me.thesevenq.facebook.utils.register.FacebookRegister;
import org.bukkit.plugin.java.JavaPlugin;

public class Facebook extends JavaPlugin {

    public static Gson GSON = new Gson();

    @Getter private PlayerMessageEvent playerMessageEvent;
    @Getter private NametagManager nametagManager;

    @Getter
    public static Facebook instance;

    public void onEnable() {
        instance = this;
        new FacebookRegister().hook();
    }

    public void onDisable() {
        new FacebookRegister().unHook();
    }
}
