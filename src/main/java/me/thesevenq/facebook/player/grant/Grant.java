package me.thesevenq.facebook.player.grant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.thesevenq.facebook.ranks.Rank;
import me.thesevenq.facebook.utils.TimeFormatUtils;

@Getter @Setter
@AllArgsConstructor
public class Grant {

    @Getter @Setter
    private Rank rank;
    private long duration;
    private long addedAt;
    private String addedBy;
    private String reason;

    public boolean hasExpired() {
        if (duration == -1L) return false;

        return System.currentTimeMillis() > this.duration;
    }

    public String getNiceDuration() {
        if (duration == -1L) return "Never";
        return TimeFormatUtils.getDetailedTime(this.duration - System.currentTimeMillis());
    }


}
