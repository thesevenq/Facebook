package me.thesevenq.facebook.player.freeze;

import lombok.Getter;
import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.FacebookAPI;
import me.thesevenq.facebook.server.database.DatabaseManager;
import me.thesevenq.facebook.server.database.jedis.JedisPublisher;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.string.Msg;
import me.thesevenq.facebook.utils.manager.Manager;
import me.thesevenq.facebook.utils.player.Permission;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Getter
public class FreezeManager extends Manager {

    private boolean frozen = false;

    public FreezeManager(Facebook plugin) {
        super(plugin);
    }

    public void handleFreezeServer(CommandSender sender) {
        frozen = !frozen;
        //Msg.sendMessage(Color.translate("&c&lServer has been " + (frozen ? "&4&lfrozen" : "&a&lunfrozen") + " &c&lby &4&l" + sender.getName() + "&c&l."));
    }

    public void handleFreeze(Player player, Player target) {
        PlayerData data = PlayerData.getByName(target.getName());

        if(data.isFrozen()) {
            data.setFrozen(false);
            DatabaseManager.getInstance().getPublisher().write(JedisPublisher.GLOBAL, "unfroze;" + FacebookAPI.getColoredName(player) + ";" + FacebookAPI.getColoredName(target));

            Msg.sendMessage(Facebook.getInstance().getConfig().getString("FREEZE.USER_UNFREEZED").replace("<frozenPlayer>", FacebookAPI.getColoredName(target)).replace("<player>", FacebookAPI.getColoredName(player)), Permission.STAFF);
            return;
        }

        data.setFrozen(true);
        DatabaseManager.getInstance().getPublisher().write(JedisPublisher.GLOBAL, "froze;" + FacebookAPI.getColoredName(player) + ";" + FacebookAPI.getColoredName(target));
        Msg.sendMessage(Facebook.getInstance().getConfig().getString("FREEZE.USER_FREEZED").replace("<frozenPlayer>", FacebookAPI.getColoredName(target)).replace("<player>", FacebookAPI.getColoredName(player)), Permission.STAFF);
    }

    public void setFrozen(Player target) {
        PlayerData data = PlayerData.getByName(target.getName());
        data.setFrozen(true);
        //Msg.sendMessage(Color.translate("&7[&6S&7] " + FacebookAPI.getColoredName(target) + " &ehas been frozen."), Permission.STAFF);
    }

    public void handleMove(Player player, Location from, Location to) {
        if(!PlayerData.getByName(player.getName()).isFrozen()) return;

        if(from.getX() != to.getX() || from.getZ() != to.getZ()) {
            player.teleport(from);
        }
    }
}
