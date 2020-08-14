package me.thesevenq.facebook;

import com.google.gson.Gson;
import lombok.Getter;
import me.thesevenq.facebook.adapters.nametags.NametagManager;
import me.thesevenq.facebook.player.events.PlayerMessageEvent;
import me.thesevenq.facebook.player.freeze.FreezeManager;
import me.thesevenq.facebook.server.ServerManager;
import me.thesevenq.facebook.utils.register.FacebookRegister;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

@Getter
public class Facebook extends JavaPlugin implements PluginMessageListener {

    public static Gson GSON = new Gson();

    @Getter private PlayerMessageEvent playerMessageEvent;
    @Getter private NametagManager nametagManager;
    private FreezeManager freezeManager;

    private ServerManager serverManager;

    @Getter
    public static Facebook instance;

    public void onEnable() {
        instance = this;

        serverManager = new ServerManager();
        enableLunar();
        freezeManager = new FreezeManager(this);
        new FacebookRegister().hook();
    }

    public void onDisable() {
        new FacebookRegister().unHook();
    }

    public void enableLunar() {
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "Lunar-Client", this);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "Lunar-Client");
    }

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
        //nothing to specify for now
    }
}
