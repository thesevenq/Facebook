package me.thesevenq.facebook.cosmetics.deathanimation;

import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.cosmetics.CosmeticsMenu;
import me.thesevenq.facebook.cosmetics.prefix.types.NormalPrefixType;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.ranks.Rank;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.ItemBuilder;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.ButtonSound;
import me.thesevenq.facebook.utils.menu.buttons.BackButton;
import me.thesevenq.facebook.utils.menu.buttons.CloseButton;
import me.thesevenq.facebook.utils.menu.pagination.PageButton;
import me.thesevenq.facebook.utils.menu.pagination.PaginatedMenu;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class KillEffectMenu extends PaginatedMenu {

    private final PlayerData data;
    private NormalPrefixType prefix;

    @Override
    public String getPrePaginatedTitle(Player player) {
        return CC.PRIMARY + "Cosmetics -> Death Animations";
    }

    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(0, new PageButton(-1, this));
        buttons.put(8, new PageButton(1, this));

        buttons.put(27, new BackButton(new CosmeticsMenu()));

        buttons.put(35, new CloseButton());

        buttons.put(3, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(CC.SECONDARY + "Available&7: " + CC.PRIMARY + KillEffectType.values().length);
                lore.add("");
                lore.add(CC.GRAY + "Click one of the effects");
                lore.add(CC.GRAY + "to equip it on profile.");
                return new ItemBuilder(Material.NETHER_STAR).lore(Color.translate(lore)).name(CC.PRIMARY + "Information").build();
            }
        });

        buttons.put(5, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(CC.GRAY + "Click here to remove");
                lore.add(CC.GRAY + "current effect from profile..");
                return new ItemBuilder(Material.BLAZE_POWDER).lore(Color.translate(lore)).name(CC.PRIMARY + "Reset").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                data.setKillEffectType(null);
                player.sendMessage(CC.SECONDARY + "You effect has been removed.");
                playSound(player, ButtonSound.SUCCESS);
            }
        });

        surroundButtons(false, buttons, new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").durability(7).build());

        return buttons;
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        Stream.of(KillEffectType.values()).forEach(killEffectType -> {
            buttons.put(buttons.size(), new Button() {
                @Override
                public ItemStack getButtonItem(Player player) {
                    ItemBuilder item = new ItemBuilder(Material.WOOL).durability(10);
                    List<String> lore = new ArrayList<>();
                    lore.add("");
                    lore.add(CC.GRAY + "Middle-Click to preview this effect.");
                    lore.add(CC.GRAY + "Right-Click here to equip this effect.");

                    return item.name(CC.PRIMARY + killEffectType.getName()).lore(Color.translate(lore)).build();
                }

                @Override
                public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                    if (clickType == ClickType.MIDDLE) {
                        killEffectType.getCallable().call(player.getLocation().clone().add(0.0D, 1.0D, 0.0D));
                    } else {
                        if (data.getRank() != Rank.DEFAULT) {
                            data.setKillEffectType(killEffectType);
                            player.sendMessage(CC.PRIMARY + data.getKillEffectType().getName() + CC.SECONDARY + " is now set as your killeffect.");
                            playSound(player, ButtonSound.SUCCESS);
                            playSound(player, ButtonSound.SUCCESS);

                            player.closeInventory();
                        } else {
                            playSound(player, ButtonSound.FAIL);
                            player.sendMessage(CC.RED + "You don't own any effect. Buy it at our store " + CC.B_RED + Facebook.getInstance().getConfig().getString("SERVER_STORE"));
                        }
                    }
                }
            });
        });

        return buttons;
    }

    @Override
    public int getSize() {
        return 9 * 4;
    }
}
