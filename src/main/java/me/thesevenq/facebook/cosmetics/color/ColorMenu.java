package me.thesevenq.facebook.cosmetics.color;

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
public class ColorMenu extends PaginatedMenu {

    private final PlayerData data;
    private NormalPrefixType prefix;

    @Override
    public String getPrePaginatedTitle(Player player) {
        return CC.PRIMARY + "Cosmetics -> Colors";
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
                lore.add(CC.SECONDARY + "Available&7: " + CC.PRIMARY + ColorType.values().length);
                lore.add("");
                lore.add(CC.GRAY + "Click one of the colors");
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
                lore.add(CC.GRAY + "current color from profile..");
                return new ItemBuilder(Material.BLAZE_POWDER).lore(Color.translate(lore)).name(CC.PRIMARY + "Reset").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                data.setColor(null);
                player.sendMessage(CC.SECONDARY + "You color has been removed.");
                playSound(player, ButtonSound.SUCCESS);
            }
        });

        surroundButtons(false, buttons, new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").durability(7).build());

        return buttons;
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        Stream.of(ColorType.values()).forEach(colorType -> {
            buttons.put(buttons.size(), new Button() {
                @Override
                public ItemStack getButtonItem(Player player) {
                    ItemStack dye = new ItemStack(Material.INK_SACK);
                    ItemBuilder item = new ItemBuilder(dye).durability(10);
                    List<String> lore = new ArrayList<>();
                    lore.add("");
                    lore.add(CC.B_PRIMARY + "Chat Preview&7:");
                    lore.add((data.getPrefix() == null ? "" : data.getPrefix().getStyle()) + " " + data.getRank().getPrefix() + colorType.getColor() + player.getName());
                    lore.add("");
                    lore.add(CC.GREEN + "Click here to equip this color.");

                    return item.name(colorType.getColor() + colorType.getName()).lore(Color.translate(lore)).build();
                }

                @Override
                public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                    if (data.getRank() != Rank.DEFAULT) {
                        data.setColor(colorType);
                        player.sendMessage(CC.PRIMARY + data.getColor().getName() + CC.SECONDARY + " is now set as your prefix.");
                        playSound(player, ButtonSound.SUCCESS);
                        playSound(player, ButtonSound.SUCCESS);

                        player.closeInventory();
                    } else {
                        playSound(player, ButtonSound.FAIL);
                        player.sendMessage(CC.RED + "You don't own any color. Buy it at our store " + CC.B_RED + Facebook.getInstance().getConfig().getString("SERVER_STORE"));
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
