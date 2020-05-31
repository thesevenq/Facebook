package me.thesevenq.facebook.adapters.nametags;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.adapters.NametagAdapter;
import me.thesevenq.facebook.utils.PlayerUtils;
import me.thesevenq.facebook.utils.Tasks;
import net.minecraft.server.v1_7_R4.Packet;
import org.bukkit.entity.Player;

public class NametagManager {
   private NametagAdapter adapter;
   private final ExecutorService executor;
   private static List<Packet> registeredTeams;
   static int COUNT = 1;

   public NametagManager(NametagAdapter adapter) {
      this.adapter = adapter;
      this.executor = Executors.newSingleThreadExecutor(Tasks.newThreadFactory("Nametag Update Thread"));
      registeredTeams = this.adapter.getNametags();
   }

   public void join(Player player) {
      registeredTeams.forEach((info) -> {
         PlayerUtils.getNMSPlayer(player).playerConnection.sendPacket(info);
      });
      this.reloadPlayer(player, true);
      this.reloadOthersFor(player, true);
   }

   public void reloadPlayer(Player toRefresh, boolean async) {
      this.apply(new NametagUpdate(toRefresh.getUniqueId(), null), async);
   }

   public void reloadOthersFor(Player refreshFor, boolean async) {
      Facebook.getInstance().getServer().getOnlinePlayers().stream().filter((toRefresh) -> !refreshFor.getUniqueId().equals(toRefresh.getUniqueId())).forEach((toRefresh) -> {
         this.reloadPlayer(toRefresh, refreshFor, async);
      });
   }

   public void reloadPlayer(Player toRefresh, Player refreshFor, boolean async) {
      this.apply(new NametagUpdate(toRefresh.getUniqueId(), refreshFor.getUniqueId()), async);
   }

   private void apply(NametagUpdate update, boolean async) {
      if (async) {
         this.executor.execute(() -> {
            this.applyUpdate(update);
         });
      } else {
         this.applyUpdate(update);
      }

   }

   private void applyUpdate(NametagUpdate update) {
      Player toRefresh = Facebook.getInstance().getServer().getPlayer(update.getToRefresh());
      if (toRefresh != null) {
         if (update.getRefreshFor() != null) {
            Player refreshFor = Facebook.getInstance().getServer().getPlayer(update.getRefreshFor());
            if (refreshFor != null) {
               this.adapter.updateNametags(toRefresh, refreshFor);
            }
         } else {
            Facebook.getInstance().getServer().getOnlinePlayers().forEach((refreshForx) -> {
               this.adapter.updateNametags(toRefresh, refreshForx);
            });
         }

      }
   }


   public NametagAdapter getAdapter() {
      return this.adapter;
   }
}
