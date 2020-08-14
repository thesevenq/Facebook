package me.thesevenq.facebook.player;

import com.google.gson.reflect.TypeToken;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Getter;
import lombok.Setter;
import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.player.cosmetics.deathanimation.KillEffectType;
import me.thesevenq.facebook.player.cosmetics.emotes.EmoteType;
import me.thesevenq.facebook.player.cosmetics.multiplier.MultiplierType;
import me.thesevenq.facebook.player.cosmetics.scoreboard.ScoreboardType;
import me.thesevenq.facebook.server.database.DatabaseManager;
import me.thesevenq.facebook.player.cosmetics.color.ColorType;
import me.thesevenq.facebook.player.cosmetics.prefix.types.NormalPrefixType;
import me.thesevenq.facebook.player.grant.Grant;
import me.thesevenq.facebook.ranks.Rank;
import org.bson.Document;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.lang.reflect.Type;
import java.util.*;

@Getter
@Setter
public class PlayerData {

    public static Type GRANT_TYPE = new TypeToken<Grant>() {
    }.getType();
    public static Type GRANTS_TYPE = new TypeToken<List<Grant>>() {
    }.getType();

    @Getter
    public static Map<String, PlayerData> dataMap = new HashMap<>();

    private String name;
    private NormalPrefixType prefix;
    private ColorType color;
    private ScoreboardType scoreboard;
    private KillEffectType killEffectType;
    private MultiplierType multiplier;
    private EmoteType emote;

    private boolean staffChat;
    private boolean frozen;
    private boolean rewardClaimable;
    private boolean toggleMsg = false;
    private boolean tips;
    private boolean registered;
    private boolean authenticated;
    private boolean codeVerified;

    private String password;

    private int coins = 0;
    private String code;

    private Grant grant;
    private List<Grant> grants = new ArrayList<>();
    private List<UUID> friends = new ArrayList<>();

    public PlayerData(String name) {
        this.name = name;

        this.toggleMsg = true;
        this.staffChat = false;
        this.frozen = false;
        this.rewardClaimable = true;
        this.tips = true;
        this.registered = false;
        this.codeVerified = false;

        dataMap.put(this.name, this);
    }

    public Grant getGrant() {
        if (this.grant.hasExpired()) {
            return new Grant(Rank.DEFAULT, -1L, System.currentTimeMillis(), "Console", "Profile creation");
        }
        return this.grant;
    }

    public Rank getRank() {
        return this.getGrant() != null ? this.getGrant().getRank() : Rank.DEFAULT;
    }

    public void save() {
        Document document = new Document();

        document.put("name", name);
        document.put("grant", Facebook.GSON.toJson(this.grant, GRANT_TYPE));
        document.put("grants", Facebook.GSON.toJson(this.grants, GRANTS_TYPE));

        document.put("toggle_messages", toggleMsg);
        document.put("coins", coins);
        document.put("scoreboard", scoreboard.getName());

        document.put("multiplier", multiplier.getName());

        document.put("staffChat", staffChat);
        document.put("frozen", frozen);
        document.put("rewardClaimable", rewardClaimable);
        document.put("tips", tips);

        document.put("registered", registered);
        document.put("authenticated", authenticated);
        document.put("password", password);
        document.put("authCode", code);
        document.put("codeVerified", codeVerified);

        document.put("emote", emote);

        if (killEffectType != null) {
            document.put("killEffect", killEffectType.getName());
        }

        if (prefix != null) {
            document.put("prefix", prefix.getName());
        }

        if (color != null) {
            document.put("color", color.getName());
        }

        DatabaseManager.getInstance().getProfiles().replaceOne(Filters.eq("name", name), document, new ReplaceOptions().upsert(true));
    }

    public void load() {
        Document document = (Document) DatabaseManager.getInstance().getProfiles().find(Filters.eq("name", name)).first();

        if (document == null) {
            grant = new Grant(Rank.DEFAULT, -1L, System.currentTimeMillis(), "Console", "Profile creation");
            save();
            return;
        }

        name = document.getString("name");

        grant = Facebook.GSON.fromJson(document.getString("grant"), GRANT_TYPE);
        grants = Facebook.GSON.fromJson(document.getString("grants"), GRANTS_TYPE);

        toggleMsg = document.getBoolean("toggle_messages");

        coins = document.getInteger("coins");

        scoreboard = ScoreboardType.getByName(document.getString("scoreboard"));
        prefix = NormalPrefixType.getByName(document.getString("prefix"));
        color = ColorType.getByName(document.getString("color"));
        killEffectType = KillEffectType.getByName(document.getString("killEffect"));
        multiplier = MultiplierType.getByName(document.getString("multiplier"));
        emote = EmoteType.getByName(document.getString("emote"));

        staffChat = document.getBoolean("staffChat");
        frozen = document.getBoolean("frozen");
        rewardClaimable = document.getBoolean("rewardClaimable");
        tips = document.getBoolean("tips");

        registered = document.getBoolean("registered");
        authenticated = document.getBoolean("authenticated");
        password = document.getString("password");
        code = document.getString("authCode");
        codeVerified = document.getBoolean("codeVerified");
    }

    public static PlayerData getByName(String name) {
        PlayerData data = dataMap.get(name);

        return data == null ? new PlayerData(name) : data;
    }

    public void setupPermissionsAttachment(Facebook plugin, Player player) {
        for (PermissionAttachmentInfo attachmentInfo : player.getEffectivePermissions()) {
            if (attachmentInfo.getAttachment() == null) {
                continue;
            }
            attachmentInfo.getAttachment().getPermissions().forEach((permission, value) -> {
                attachmentInfo.getAttachment().unsetPermission(permission);
            });
        }

        PermissionAttachment attachment = player.addAttachment(plugin);

        switch (getRank()) {
            case BASIC:
                attachment.setPermission("facebook.donor", true);
            case SILVER:
                attachment.setPermission("facebook.donor", true);
            case GOLD:
                attachment.setPermission("facebook.donor", true);
            case PLATINUM:
                attachment.setPermission("facebook.donor", true);
            case RUBY:
                attachment.setPermission("facebook.donor", true);
            case MEDIA:
                attachment.setPermission("facebook.media", true);
            case YOUTUBER:
                attachment.setPermission("facebook.media", true);
            case FAMOUS:
                attachment.setPermission("facebook.media", true);
            case TRIALMOD:
                attachment.setPermission("facebook.staff", true);
            case MOD:
                attachment.setPermission("facebook.seniorstaff", true);
            case SENIORMOD:
                attachment.setPermission("facebook.seniorstaff", true);
            case ADMIN:
                attachment.setPermission("facebook.admin", true);
            case MANAGER:
                attachment.setPermission("facebook.manager", true);
            case OWNER:
                attachment.setPermission("facebook.staff", true);
                attachment.setPermission("facebook.seniorstaff", true);
                attachment.setPermission("facebook.manager", true);
                attachment.setPermission("facebook.op", true);
            default:

                break;
        }
        player.recalculatePermissions();
    }
}
