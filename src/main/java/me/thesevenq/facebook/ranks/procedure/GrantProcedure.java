package me.thesevenq.facebook.ranks.procedure;

import lombok.Getter;
import lombok.Setter;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.ranks.Rank;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class GrantProcedure {

    @Getter
    @Setter
    private static Set<GrantProcedure> procedures = new HashSet<>();
    private GrantProcedureStage stage;
    private Rank rank;
    private long duration;
    private String reason;
    private PlayerData addedTo;
    private String addedBy;

    public GrantProcedure(Rank rank, PlayerData addedTo, String addedBy, GrantProcedureStage stage) {
        this.rank = rank;
        this.addedTo = addedTo;
        this.addedBy = addedBy;
        this.stage = stage;
        procedures.add(this);
    }

    public static GrantProcedure getByPlayer(String name) {
        for (GrantProcedure procedure : GrantProcedure.getProcedures()) {
            if (procedure.getAddedBy().equalsIgnoreCase(name)) {
                return procedure;
            }
        }
        return null;
    }
}
