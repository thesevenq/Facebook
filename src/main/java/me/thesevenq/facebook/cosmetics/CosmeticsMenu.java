package me.thesevenq.facebook.cosmetics;

import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.Facebook;
import me.thesevenq.facebook.FacebookAPI;
import me.thesevenq.facebook.cosmetics.color.ColorMenu;
import me.thesevenq.facebook.cosmetics.color.ColorType;
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
import me.thesevenq.facebook.utils.menu.buttons.BackButton;
import me.thesevenq.facebook.utils.menu.buttons.CloseButton;
import me.thesevenq.facebook.utils.menu.pagination.PageButton;
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

        buttons.put(26, new CloseButton());
        surroundButtons(true, buttons, new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").durability(7).build());

        PlayerData data = PlayerData.getByName(player.getName());

        buttons.put(10, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.NAME_TAG);

                List<String> lore = new ArrayList<>();

                lore.add("");

                if (data.getRank() != Rank.DEFAULT) {
                    lore.add(CC.GRAY + "Here you can select");
                    lore.add(CC.GRAY + "custom chat prefix to");
                    lore.add(CC.GRAY + "to outstand from other players.");
                    lore.add("");
                    lore.add(CC.SECONDARY + " Available: " + CC.PRIMARY + "13");
                    lore.add(CC.SECONDARY + " Unlocked: " + CC.PRIMARY + "13");
                    lore.add("");
                    lore.add(CC.GRAY + "Click here to browse prefixes.");
                } else {
                    lore.add(CC.RED + "You don't own any prefixes!");
                    lore.add("");
                    lore.add(CC.GRAY + "Buy rank on our store");
                    lore.add(CC.GRAY + "to gain access to prefixes.");
                    lore.add(CC.PRIMARY + "store.sicaro.club");

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


        buttons.put(12, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.INK_SACK).durability(5);

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
                    lore.add(CC.RED + "You don't own any colors!");
                    lore.add("");
                    lore.add(CC.GRAY + "Buy rank on our store");
                    lore.add(CC.GRAY + "to gain access to colors.");
                    lore.add(CC.PRIMARY + "store.sicaro.club");
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

        buttons.put(14, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.LEATHER_CHESTPLATE).color(org.bukkit.Color.AQUA);

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
                    lore.add(CC.RED + "You don't own any armors!");
                    lore.add("");
                    lore.add(CC.GRAY + "Buy rank on our store");
                    lore.add(CC.GRAY + "to gain access to armors.");
                    lore.add(CC.PRIMARY + "store.sicaro.club");

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

        buttons.put(16, new Button() {
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
        return 9 * 3;
    }
}
