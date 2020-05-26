package me.thesevenq.facebook.adapters;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;

public interface NametagAdapter {
   default List getNametags() {
      return new ArrayList();
   }

   void updateNametags(Player var1, Player var2);
}
