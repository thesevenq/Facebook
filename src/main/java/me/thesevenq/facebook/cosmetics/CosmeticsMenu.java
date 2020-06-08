package me.thesevenq.facebook.cosmetics;

import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.FacebookAPI;
import me.thesevenq.facebook.cosmetics.color.ColorMenu;
import me.thesevenq.facebook.cosmetics.deathanimation.KillEffectMenu;
import me.thesevenq.facebook.cosmetics.prefix.submenus.NormalPrefixesMenu;
import me.thesevenq.facebook.cosmetics.ranks.RankMenu;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.ranks.Rank;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.ItemBuilder;
import me.thesevenq.facebook.utils.string.MessageUtils;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.ButtonSound;
import me.thesevenq.facebook.utils.menu.Menu;
import me.thesevenq.facebook.utils.menu.buttons.CloseButton;
import me.thesevenq.facebook.utils.menu.buttons.Glass;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

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

        buttons.put(18, new CloseButton());
        buttons.put(19, new Glass());
        buttons.put(10, new Glass());
        buttons.put(28, new Glass());

        buttons.put(27, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.DOUBLE_PLANT);

                return item.name(CC.SECONDARY + "Coins: " + CC.PRIMARY + FacebookAPI.getCoins(player)).build();
            }
        });


        surroundButtons(true, buttons, new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").durability(7).build());

        PlayerData data = PlayerData.getByName(player.getName());


        buttons.put(22, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = (data.getRank() == Rank.DEFAULT ? new ItemBuilder(Material.REDSTONE_BLOCK) : new ItemBuilder(Material.NAME_TAG));

                List<String> lore = new ArrayList<>();

                lore.add("");

                if (data.getRank() != Rank.DEFAULT) {
                    lore.add(CC.GRAY + "Here you can select");
                    lore.add(CC.GRAY + "custom chat prefix to");
                    lore.add(CC.GRAY + "to outstand from other players.");
                    lore.add("");
                    lore.add(CC.SECONDARY + " Available: " + CC.PRIMARY + "13");
                    lore.add(CC.SECONDARY + " Unlocked: " + CC.PRIMARY + "13");
                    //lore.add(CC.SECONDARY + " Selected: " + CC.PRIMARY + (data.getColor() != null ? data.getPrefix().getName() : "None"));
                    lore.add("");
                    lore.add(CC.GRAY + "Click here to browse prefixes.");
                } else {
                    lore.add(CC.RED + MessageUtils.X + " You don't own any prefixes!");
                    lore.add("");
                    lore.add(CC.GRAY + "Buy rank on our store");
                    lore.add(CC.GRAY + "to gain access to prefixes.");
                    lore.add(CC.PRIMARY + "store.cobalt.cf");

                }

                return item.name(CC.PRIMARY + "Prefixes").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                if (data.getRank() != Rank.DEFAULT) {
                    new NormalPrefixesMenu(data).openMenu(player);
                    playSound(player, ButtonSound.CLICK);
                } else {
                    player.sendMessage(CC.RED + "You don't own any prefix. Buy a rank at our store to get access to prefixes " + CC.B_RED + Facebook.getInstance().getConfig().getString("SERVER_STORE"));
                    playSound(player, ButtonSound.FAIL);
                }
            }
        });


        buttons.put(21, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = (data.getRank() == Rank.DEFAULT ? new ItemBuilder(Material.REDSTONE_BLOCK) : new ItemBuilder(Material.INK_SACK).durability(6));

                List<String> lore = new ArrayList<>();

                lore.add("");

                if (data.getRank() != Rank.DEFAULT) {
                    lore.add(CC.GRAY + "Here you can select");
                    lore.add(CC.GRAY + "custom chat color to");
                    lore.add(CC.GRAY + "outstand from other players.");
                    lore.add("");
                    lore.add(CC.SECONDARY + " Available: " + CC.PRIMARY + "12");
                    lore.add(CC.SECONDARY + " Unlocked: " + CC.PRIMARY + "12");
                    lore.add("");
                    lore.add(CC.GRAY + "Click here to browse colors.");
                } else {
                    lore.add(CC.RED + MessageUtils.X + " You don't own any colors!");
                    lore.add("");
                    lore.add(CC.GRAY + "Buy rank on our store");
                    lore.add(CC.GRAY + "to gain access to colors.");
                    lore.add(CC.PRIMARY + "store.cobalt.cf");
                }


                return item.name(CC.PRIMARY + "Colors").lore(Color.translate(lore)).build();
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

        buttons.put(23, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = (data.getRank() == Rank.DEFAULT ? new ItemBuilder(Material.REDSTONE_BLOCK) : new ItemBuilder(Material.BLAZE_POWDER));

                List<String> lore = new ArrayList<>();

                lore.add("");

                if (data.getRank() != Rank.DEFAULT) {
                    lore.add(CC.GRAY + "Here you can select");
                    lore.add(CC.GRAY + "custom animations which");
                    lore.add(CC.GRAY + "deploy after kill.");
                    lore.add("");
                    lore.add(CC.SECONDARY + " Available: " + CC.PRIMARY + "21");
                    lore.add(CC.SECONDARY + " Unlocked: " + CC.PRIMARY + "21");
                    lore.add("");
                    lore.add(CC.GRAY + "Click here to browse effects.");
                } else {
                    lore.add(CC.RED + MessageUtils.X + " You don't own any animations!");
                    lore.add("");
                    lore.add(CC.GRAY + "Buy rank on our store");
                    lore.add(CC.GRAY + "to gain access to animations.");
                    lore.add(CC.PRIMARY + "store.cobalt.cf");
                }


                return item.name(CC.PRIMARY + "Death Animations").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                if (data.getRank() != Rank.DEFAULT) {
                    new KillEffectMenu(data).openMenu(player);
                    playSound(player, ButtonSound.CLICK);
                } else {
                    player.sendMessage(CC.RED + "You don't own any animations. Buy a rank at our store to get access to animations " + CC.B_RED + Facebook.getInstance().getConfig().getString("SERVER_STORE"));
                    playSound(player, ButtonSound.FAIL);

                }
            }
        });

        buttons.put(24, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = (data.getRank() == Rank.DEFAULT ? new ItemBuilder(Material.REDSTONE_BLOCK): new ItemBuilder(Material.LEATHER_CHESTPLATE).color(org.bukkit.Color.AQUA));

                List<String> lore = new ArrayList<>();

                lore.add("");

                if (data.getRank() != Rank.DEFAULT) {
                    lore.add(CC.GRAY + "Here you can select");
                    lore.add(CC.GRAY + "custom lobby armor to");
                    lore.add(CC.GRAY + "really outstand from other players.");
                    lore.add("");
                    lore.add(CC.SECONDARY + " Available: " + CC.PRIMARY + "6");
                    lore.add(CC.SECONDARY + " Unlocked: " + CC.PRIMARY + "6");
                    lore.add("");
                    lore.add(CC.GRAY + "Click here to browse armors.");
                } else {
                    lore.add(CC.RED + MessageUtils.X + " You don't own any armors!");
                    lore.add("");
                    lore.add(CC.GRAY + "Buy rank on our store");
                    lore.add(CC.GRAY + "to gain access to armors.");
                    lore.add(CC.PRIMARY + "store.cobalt.cf");

                }


                return item.name(CC.PRIMARY + "Armors").lore(Color.translate(lore)).build();
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
                    playSound(player, ButtonSound.FAIL);
                }
            }
        });

        buttons.put(9, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.ITEM_FRAME);

                List<String> lore = new ArrayList<>();

                lore.add("");

                lore.add(CC.GRAY + "Here you can buy");
                lore.add(CC.GRAY + "30d rank to get nice");
                lore.add(CC.GRAY + "perks and utilties.");
                lore.add("");
                lore.add(CC.SECONDARY + " Available: " + CC.PRIMARY + "5");
                lore.add("");
                lore.add(" " + CC.SECONDARY + "Your current coins");
                lore.add(" " + CC.SECONDARY + "balance is " + CC.PRIMARY + FacebookAPI.getCoins(player));
                lore.add("");
                lore.add(CC.BD_RED + " Note: " + CC.RED + "Ingame ranks can only be bought via coins.");
                lore.add("");
                lore.add(CC.GRAY + "Click here to browse ranks.");


                return item.name(CC.PRIMARY + "Ranks").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                new RankMenu(data).openMenu(player);
                playSound(player, ButtonSound.CLICK);
            }
        });


        return buttons;
    }

    @Override
    public int getSize() {
        return 9 * 5;
    }
}
