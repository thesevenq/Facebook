package me.thesevenq.facebook.player.settings;

import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.cosmetics.CosmeticsMenu;
import me.thesevenq.facebook.cosmetics.scoreboard.ScoreboardType;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.Color;
import me.thesevenq.facebook.utils.ItemBuilder;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class SettingsMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return CC.PRIMARY + "Settings";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        PlayerData data = PlayerData.getByName(player.getName());
        buttons.put(10, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.ITEM_FRAME);

                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");

                    lore.add(CC.GRAY + "Change scoreboard");
                    lore.add(CC.GRAY + "format to your likings.");
                    lore.add("");
                    lore.add(CC.SECONDARY + " Current: " + CC.WHITE + data.getScoreboard().getName());
                    lore.add("");
                    lore.add(CC.GREEN + "Click here to change scoreboard.");

                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");

                return item.name(CC.B_PRIMARY + "Scoreboard Format").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                if (data.getScoreboard().getName() == "Normal") {
                    data.setScoreboard(ScoreboardType.OLDSCHOOL);
                    player.sendMessage(CC.PRIMARY + "OldSchool " + CC.SECONDARY + " is now selected as your board design.");
                } else {
                    data.setScoreboard(ScoreboardType.NORMAL);
                    player.sendMessage(CC.PRIMARY + "Normal " + CC.SECONDARY + " is now selected as your board design.");
                }
            }
        });

        buttons.put(11, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.PAPER);

                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");

                lore.add(CC.GRAY + "Don't want to receive");
                lore.add(CC.GRAY + "private messages?");
                lore.add("");
                lore.add(CC.GREEN + "Click here to toggle messages.");

                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");

                return item.name(CC.B_PRIMARY + "Private Messages").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.performCommand("togglepm");
            }
        });

        buttons.put(12, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.GOLD_NUGGET);

                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");

                lore.add(CC.GRAY + "Don't want to receive");
                lore.add(CC.GRAY + "server tips?");
                lore.add("");
                lore.add(CC.GREEN + "Click here to toggle tips.");

                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");

                return item.name(CC.B_PRIMARY + "Server Tips").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                player.performCommand("toggletips");
            }
        });


        buttons.put(16, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                ItemStack dye = new ItemStack(Material.INK_SACK);
                ItemBuilder item = new ItemBuilder(dye).durability(10);
                lore.add("&7&m----------------------------------------");
                lore.add(CC.RED + "Click here to go back.");
                lore.add("&7&m----------------------------------------");
                return new ItemBuilder(Material.ARROW).lore(Color.translate(lore)).name(CC.D_RED + "Back").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                World world = player.getWorld();
                world.playSound(player.getLocation(), Sound.WOOD_CLICK, 10, 1);

                new CosmeticsMenu().openMenu(player);
            }
        });

        buttons.put(0, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });

        buttons.put(1, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });

        buttons.put(2, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });

        buttons.put(3, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });

        buttons.put(4, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });

        buttons.put(5, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });

        buttons.put(6, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });

        buttons.put(7, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });

        buttons.put(8, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });



        buttons.put(9, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });

        buttons.put(17, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });

        buttons.put(18, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });

        buttons.put(19, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });

        buttons.put(20, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });

        buttons.put(21, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });

        buttons.put(22, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });

        buttons.put(23, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });

        buttons.put(24, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });

        buttons.put(25, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });

        buttons.put(26, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(CC.D_RED + "-").build();
            }
        });



        return buttons;
    }
}
