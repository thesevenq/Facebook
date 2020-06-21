package me.thesevenq.facebook.player.coins.menus;

import com.sun.org.apache.regexp.internal.RE;
import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.menu.ButtonSound;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.ItemBuilder;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.Menu;
import me.thesevenq.facebook.utils.menu.buttons.CloseButton;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class CoinsAddMenu extends Menu {

    {
        setAutoUpdate(true);
    }

    private final PlayerData data;

    @Override
    public String getTitle(Player player) {
        return CC.PRIMARY + "Coins Menu";
    }

    public int toAdd = 0;

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(44, new CloseButton());
        surroundButtons(true, buttons, new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").durability(7).build());

        buttons.put(20, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.QUARTZ);

                List<String> lore = new ArrayList<>();

                lore.add("");
                lore.add(CC.SECONDARY + "Every time you left-click");
                lore.add(CC.SECONDARY + "you add 5,000 coins.");
                lore.add("");
                lore.add(CC.RED + "Every time you right-click");
                lore.add(CC.RED + "you add 10,000 coins.");
                lore.add("");
                lore.add(CC.GRAY + "Click here to add.");

                return item.name(CC.GREEN + "Add &7(&65,000&7/&610,000&7)").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                if (clickType == ClickType.RIGHT) {
                    toAdd = toAdd + 10000;
                    playSound(player, ButtonSound.SUCCESS);
                } else if (clickType == ClickType.LEFT) {
                    toAdd = toAdd + 5000;
                    playSound(player, ButtonSound.SUCCESS);
                }
            }
        });

        buttons.put(22, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.BOOK_AND_QUILL);

                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(CC.SECONDARY + "Amount of coins to");
                lore.add(CC.SECONDARY + "be added is " + CC.PRIMARY + toAdd);
                lore.add("");
                lore.add(CC.PRIMARY + "Click here to confirm!");
                lore.add("");
                lore.add(CC.GRAY + "Click to confirm.");

                return item.name(CC.PRIMARY + "Current Amount").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                data.setCoins(data.getCoins() + toAdd);
                player.sendMessage(CC.SECONDARY + "Successfully added " + CC.PRIMARY + toAdd + CC.SECONDARY + " coins to " + CC.PRIMARY + data.getName());
                playSound(player, ButtonSound.SUCCESS);
            }
        });

        buttons.put(24, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemBuilder item = new ItemBuilder(Material.REDSTONE);

                List<String> lore = new ArrayList<>();

                lore.add("");
                lore.add(CC.SECONDARY + "Every time you left-click");
                lore.add(CC.SECONDARY + "you remove 5,000 coins.");
                lore.add("");
                lore.add(CC.RED + "Every time you right-click");
                lore.add(CC.RED + "you remove 10,000 coins.");
                lore.add("");
                lore.add(CC.GRAY + "Click here to remove.");

                return item.name(CC.GREEN + "Remove &7(&65,000&7/&610,000&7)").lore(Color.translate(lore)).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                if (clickType == ClickType.RIGHT) {
                    if (toAdd == 0) {
                        player.sendMessage(CC.RED + "Add amount is 0, you can't remove any coins.");
                    } else {
                        toAdd = toAdd - 10000;
                        playSound(player, ButtonSound.SUCCESS);
                    }
                } else if (clickType == ClickType.LEFT) {
                    if (toAdd == 0) {
                        player.sendMessage(CC.RED + "Add amount is 0, you can't remove any coins.");
                    } else {
                        toAdd = toAdd - 5000;
                        playSound(player, ButtonSound.SUCCESS);
                    }
                }
            }
        });

        return buttons;
    }

    @Override
    public int getSize() {
        return 9 * 3;
    }
}
