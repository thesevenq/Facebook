package me.thesevenq.facebook.adapters.nametags;

import java.beans.ConstructorProperties;
import java.util.UUID;

final class NametagUpdate {
   private UUID toRefresh;
   private UUID refreshFor;

   public UUID getToRefresh() {
      return this.toRefresh;
   }

   public UUID getRefreshFor() {
      return this.refreshFor;
   }

   @ConstructorProperties({"toRefresh", "refreshFor"})
   public NametagUpdate(UUID toRefresh, UUID refreshFor) {
      this.toRefresh = toRefresh;
      this.refreshFor = refreshFor;
   }
}
