package me.thesevenq.facebook.player.quests;

import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.cosmetics.CosmeticsMenu;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.ItemBuilder;
import me.thesevenq.facebook.utils.string.MessageUtils;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.ButtonSound;
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
public class QuestsMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return CC.PRIMARY + "Quests";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        PlayerData data = PlayerData.getByName(player.getName());
        buttons.put(10, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.MAP);

                List<String> lore = new ArrayList<>();
                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");

                lore.add(CC.GRAY + "Welcome to our network.");
                lore.add(CC.GRAY + "You can claim free reward");
                lore.add(CC.GRAY + "for joining on our server.");
                lore.add("");
                lore.add(CC.SECONDARY + " Claimable: " + (data.isRewardClaimable() ? CC.B_GREEN + MessageUtils.CHECKMARK : CC.BD_RED + MessageUtils.X));
                lore.add("");
                lore.add(CC.GREEN + "Click here to claim.");

                lore.add(CC.GRAY + CC.STRIKE_THROUGH + "----------------------------");

                return item.name(CC.B_PRIMARY + "Join our network!").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                if (data.isRewardClaimable()) {
                    data.setRewardClaimable(false);
                    player.sendMessage("");
                    player.sendMessage(CC.GREEN + "You have claimed your free reward.");
                    player.sendMessage(CC.B_PRIMARY + "1,000 " + CC.SECONDARY + "coins have been added to your account.");
                    player.sendMessage("");
                    data.setCoins(data.getCoins() + 1000);
                    playSound(player, ButtonSound.SUCCESS);
                } else {
                    playSound(player, ButtonSound.FAIL);
                    player.sendMessage(CC.RED + "You have already claimed this reward.");
                }
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
