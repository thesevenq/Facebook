package me.thesevenq.facebook.cosmetics.scoreboard;

import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.cosmetics.CosmeticsMenu;
import me.thesevenq.facebook.cosmetics.prefix.types.NormalPrefixType;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.ItemBuilder;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.ButtonSound;
import me.thesevenq.facebook.utils.menu.pagination.PaginatedMenu;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class ScoreboardMenu extends PaginatedMenu {

    private final PlayerData data;
    private NormalPrefixType prefix;

    @Override
    public String getPrePaginatedTitle(Player player) {
        return CC.PRIMARY + "Cosmetics -> Scoreboard";
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        Stream.of(ScoreboardType.values()).forEach(scoreboardType -> {
            buttons.put(buttons.size(), new Button() {
                @Override
                public ItemStack getButtonItem(Player player) {
                    ItemStack dye = new ItemStack(Material.INK_SACK);
                    ItemBuilder item = new ItemBuilder(dye).durability(10);
                    List<String> lore = new ArrayList<>();
                    lore.add(CC.GRAY + CC.STRIKE_THROUGH + "-----------------------------------");
                    lore.add(CC.GRAY + "Hate the current design?");
                    lore.add(CC.GRAY + "Pick this " + scoreboardType.getName().toLowerCase());
                    lore.add(CC.GRAY + "scoreboard design");
                    lore.add("");
                    lore.add(CC.GREEN + "Click here to equip this design.");
                    lore.add(CC.GRAY + CC.STRIKE_THROUGH + "-----------------------------------");

                    return item.name(CC.B_PRIMARY + scoreboardType.getName()).lore(Color.translate(lore)).build();
                }

                @Override
                public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                    data.setScoreboard(scoreboardType);
                    player.sendMessage(CC.PRIMARY + data.getScoreboard().getName() + CC.SECONDARY + " is now set as your board design.");
                    playSound(player, ButtonSound.SUCCESS);

                    player.closeInventory();
                }
            });
        });


        buttons.put(17, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                ItemStack dye = new ItemStack(Material.INK_SACK);
                ItemBuilder item = new ItemBuilder(dye).durability(10);
                lore.add("&7&m------------------------------------------------");
                lore.add(CC.RED + "Click here to go to previous menu.");
                lore.add("&7&m------------------------------------------------");
                return new ItemBuilder(Material.ARROW).lore(Color.translate(lore)).name(CC.D_RED + "Back").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                World world = player.getWorld();
                world.playSound(player.getLocation(), Sound.WOOD_CLICK, 10, 1);

                new CosmeticsMenu().openMenu(player);
            }
        });

        return buttons;
    }
}
