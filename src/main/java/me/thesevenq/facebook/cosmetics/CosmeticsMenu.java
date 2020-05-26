package me.thesevenq.facebook.cosmetics;

import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.FacebookAPI;
import me.thesevenq.facebook.cosmetics.color.ColorMenu;
import me.thesevenq.facebook.cosmetics.prefix.submenus.NormalPrefixesMenu;
import me.thesevenq.facebook.cosmetics.ranks.RankMenu;
import me.thesevenq.facebook.cosmetics.scoreboard.ScoreboardMenu;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.player.settings.SettingsMenu;
import me.thesevenq.facebook.ranks.Rank;
import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.Color;
import me.thesevenq.facebook.utils.ItemBuilder;
import me.thesevenq.facebook.utils.MessageUtils;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.ButtonSound;
import me.thesevenq.facebook.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import sun.plugin2.message.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class CosmeticsMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return CC.PRIMARY + "Cosmetics";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        PlayerData data = PlayerData.getByName(player.getName());

        buttons.put(10, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.NAME_TAG);

                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");
                if (data.getRank() != Rank.DEFAULT) {
                    lore.add(CC.GRAY + "Here you can select");
                    lore.add(CC.GRAY + "custom chat prefix to");
                    lore.add(CC.GRAY + "to outstand from other players.");
                    lore.add("");
                    lore.add(CC.B_PRIMARY + "Normal Prefixes");
                    lore.add(CC.SECONDARY + " Available: " + CC.WHITE + "13");
                    lore.add(CC.SECONDARY + " Unlocked: " + CC.WHITE + "13");
                    lore.add("");
                    lore.add(CC.B_PRIMARY + "Special Prefixes");
                    lore.add(CC.SECONDARY + " Available: " + CC.WHITE + "3");
                    lore.add(CC.SECONDARY + " Unlocked: " + CC.WHITE + "3");
                    lore.add("");
                    lore.add(CC.GREEN + "Click here to browse prefixes.");
                } else {
                    lore.add(CC.RED + "You don't own any prefixes!");
                    lore.add("");
                    lore.add(CC.GRAY + "Buy rank on our store");
                    lore.add(CC.GRAY + "to gain access to prefixes.");
                    lore.add("");
                    lore.add(CC.B_PRIMARY + "Store&7:");
                    lore.add(CC.SECONDARY + " " + Facebook.getInstance().getConfig().getString("SERVER_STORE"));
                }

                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");

                return item.name(CC.B_PRIMARY + "Prefixes").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                if (data.getRank() != Rank.DEFAULT) {
                    new NormalPrefixesMenu(data).openMenu(player);
                    playSound(player, ButtonSound.CLICK);
                } else {
                    player.sendMessage(CC.RED + "You don't own any prefix. Buy a rank at our store to get access to prefixes " + CC.B_RED + Facebook.getInstance().getConfig().getString("SERVER_STORE"));
                    playSound(player, ButtonSound.CLICK);
                }
            }
        });


        buttons.put(12, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.BLAZE_POWDER);

                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");
                if (data.getRank() != Rank.DEFAULT) {
                    lore.add(CC.GRAY + "Here you can select");
                    lore.add(CC.GRAY + "custom particle trail to");
                    lore.add(CC.GRAY + "really outstand from other players.");
                    lore.add("");
                    lore.add(CC.SECONDARY + " Available: " + CC.WHITE + "5");
                    lore.add(CC.SECONDARY + " Unlocked: " + CC.WHITE + "5");
                    lore.add("");
                    lore.add(CC.GREEN + "Click here to browse particles.");
                } else {
                    lore.add(CC.RED + "You don't own any particles!");
                    lore.add("");
                    lore.add(CC.GRAY + "Buy rank on our store");
                    lore.add(CC.GRAY + "to gain access to particles.");
                    lore.add("");
                    lore.add(CC.B_PRIMARY + "Store&7:");
                    lore.add(CC.SECONDARY + " " + Facebook.getInstance().getConfig().getString("SERVER_STORE"));
                }

                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");


                return item.name(CC.B_PRIMARY + "Particle Trails").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                //new PrefixMenu().openMenu(player);
            }
        });

        buttons.put(14, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.INK_SACK).durability(5);

                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");
                if (data.getRank() != Rank.DEFAULT) {
                    lore.add(CC.GRAY + "Here you can select");
                    lore.add(CC.GRAY + "custom chat color to");
                    lore.add(CC.GRAY + "outstand from other players.");
                    lore.add("");
                    lore.add(CC.SECONDARY + " Available: " + CC.WHITE + "12");
                    lore.add(CC.SECONDARY + " Unlocked: " + CC.WHITE + "12");
                    lore.add("");
                    lore.add(CC.GREEN + "Click here to browse colors.");
                } else {
                    lore.add(CC.RED + "You don't own any colors!");
                    lore.add("");
                    lore.add(CC.GRAY + "Buy rank on our store");
                    lore.add(CC.GRAY + "to gain access to colors.");
                    lore.add("");
                    lore.add(CC.B_PRIMARY + "Store&7:");
                    lore.add(CC.SECONDARY + " " + Facebook.getInstance().getConfig().getString("SERVER_STORE"));
                }

                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");


                return item.name(CC.B_PRIMARY + "Colors").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                if (data.getRank() != Rank.DEFAULT) {
                    new ColorMenu(data).openMenu(player);
                    playSound(player, ButtonSound.CLICK);
                } else {
                    player.sendMessage(CC.RED + "You don't own any color. Buy a rank at our store to get access to colors " + CC.B_RED + Facebook.getInstance().getConfig().getString("SERVER_STORE"));
                    playSound(player, ButtonSound.FAIL);

                }
            }
        });

        buttons.put(16, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.LEATHER_CHESTPLATE).color(org.bukkit.Color.AQUA);

                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");
                if (data.getRank() != Rank.DEFAULT) {
                    lore.add(CC.GRAY + "Here you can select");
                    lore.add(CC.GRAY + "custom lobby armor to");
                    lore.add(CC.GRAY + "really outstand from other players.");
                    lore.add("");
                    lore.add(CC.SECONDARY + " Available: " + CC.WHITE + "6");
                    lore.add(CC.SECONDARY + " Unlocked: " + CC.WHITE + "6");
                    lore.add("");
                    lore.add(CC.GREEN + "Click here to browse armors.");
                } else {
                    lore.add(CC.RED + "You don't own any armors!");
                    lore.add("");
                    lore.add(CC.GRAY + "Buy rank on our store");
                    lore.add(CC.GRAY + "to gain access to armors.");
                    lore.add("");
                    lore.add(CC.B_PRIMARY + "Store&7:");
                    lore.add(CC.SECONDARY + " " + Facebook.getInstance().getConfig().getString("SERVER_STORE"));
                }

                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");


                return item.name(CC.B_PRIMARY + "Armors").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                if(data.getRank() != Rank.DEFAULT) {
                    if (Facebook.getInstance().getConfig().getString("SERVERNAME").equalsIgnoreCase("Lobby")) {
                        player.performCommand("armors");
                        playSound(player, ButtonSound.CLICK);
                    } else {
                        player.sendMessage(CC.SECONDARY + "Armors can only be used on lobby servers.");
                        playSound(player, ButtonSound.FAIL);
                    }
                } else {
                    player.sendMessage(CC.RED + "You don't own any armor. Buy a rank at our store to get access to armors " + CC.B_RED + Facebook.getInstance().getConfig().getString("SERVER_STORE"));
                    playSound(player, ButtonSound.CLICK);
                }
            }
        });

        buttons.put(11, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.ITEM_FRAME);

                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");

                lore.add(CC.GRAY + "Here you can buy");
                lore.add(CC.GRAY + "30d rank to get nice");
                lore.add(CC.GRAY + "perks and utilties.");
                lore.add("");
                lore.add(CC.SECONDARY + " Available: " + CC.WHITE + "5");
                lore.add("");
                lore.add(" " + CC.SECONDARY + "Your current coins");
                lore.add(" " + CC.SECONDARY + "balance is " + CC.PRIMARY + FacebookAPI.getCoins(player));
                lore.add("");
                lore.add(CC.BD_RED + " Note: " + CC.RED + "Ingame ranks can only be bought via coins.");
                lore.add("");
                lore.add(CC.GREEN + "Click here to browse ranks.");

                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");


                return item.name(CC.B_PRIMARY + "Ranks").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                new RankMenu(data).openMenu(player);
                playSound(player, ButtonSound.CLICK);
            }
        });

        buttons.put(15, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.GOLD_NUGGET);

                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");

                lore.add(CC.GRAY + "Here you can buy");
                lore.add(CC.GRAY + "change your board design.");
                lore.add("");
                lore.add(CC.SECONDARY + " Available: " + CC.WHITE + "2");
                lore.add("");
                lore.add(" " + CC.SECONDARY + "Your current design: " + CC.WHITE + data.getScoreboard().getName());
                lore.add("");
                lore.add(CC.GREEN + "Click here to browse designs.");

                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");


                return item.name(CC.B_PRIMARY + "Scoreboard Design").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                new ScoreboardMenu(data).openMenu(player);
                playSound(player, ButtonSound.CLICK);
            }
        });

        buttons.put(18, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.BOOK_AND_QUILL);

                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");

                lore.add(CC.GRAY + "Here you can change");
                lore.add(CC.GRAY + "your prefered settings.");
                lore.add("");
                lore.add(CC.GREEN + "Click here to see settings.");

                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");


                return item.name(CC.B_PRIMARY + "Settings").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                new SettingsMenu().openMenu(player);
                playSound(player, ButtonSound.CLICK);
            }
        });


        buttons.put(26, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                ItemStack dye = new ItemStack(Material.INK_SACK);
                ItemBuilder item = new ItemBuilder(dye).durability(10);
                lore.add("&7&m----------------------------------------");
                lore.add(CC.RED + "Click here to close menu.");
                lore.add("&7&m----------------------------------------");
                return new ItemBuilder(Material.INK_SACK).durability(1).lore(Color.translate(lore)).name(CC.D_RED + "Close").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                playSound(player, ButtonSound.CLICK);

                player.closeInventory();
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

        return buttons;
    }
}
