package me.thesevenq.facebook.cosmetics.ranks;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import me.thesevenq.facebook.FacebookAPI;
import me.thesevenq.facebook.cosmetics.CosmeticsMenu;
import me.thesevenq.facebook.cosmetics.prefix.types.NormalPrefixType;
import me.thesevenq.facebook.player.PlayerData;
import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.Color;
import me.thesevenq.facebook.utils.ItemBuilder;
import me.thesevenq.facebook.utils.MessageUtils;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.ButtonSound;
import me.thesevenq.facebook.utils.menu.pagination.PaginatedMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class RankMenu extends PaginatedMenu {
    private final PlayerData data;

    private NormalPrefixType prefix;

    @ConstructorProperties({"data"})
    public RankMenu(PlayerData data) {
        this.data = data;
    }

    public String getPrePaginatedTitle(Player player) {
        return CC.PRIMARY + "Cosmetics -> Ranks";
    }

    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        Stream.<RankType>of(RankType.values()).forEach(rankType -> buttons.put(Integer.valueOf(buttons.size()), new Button() {
            public ItemStack getButtonItem(Player player) {
                ItemStack dye = new ItemStack(Material.INK_SACK);
                ItemBuilder item = (new ItemBuilder(dye)).durability(10);
                List<String> lore = new ArrayList();
                int more = rankType.getPrice() - FacebookAPI.getCoinsInt(player);
                if (FacebookAPI.getCoinsInt(player) < rankType.getPrice()) {
                    lore.add(CC.GRAY + CC.STRIKE_THROUGH + "-----------------------------------");
                    lore.add(CC.SECONDARY + "Price: " + CC.WHITE + rankType.getPrice());
                    lore.add("");
                    lore.add(CC.B_PRIMARY + "Rank Preview");
                    lore.add(CC.SECONDARY + " Color: " + rankType.getRank().getColor() + "Color");
                    lore.add(CC.SECONDARY + " Prefix: " + rankType.getRank().getPrefix());
                    lore.add("");
                    lore.add(" " + CC.BD_RED + MessageUtils.WARNING + " Warning");
                    lore.add(" " + CC.SECONDARY + "You don't have enough");
                    lore.add(" " + CC.SECONDARY + "coins to buy this rank.");
                    lore.add("");
                    lore.add(CC.RED + "You are not able to buy this rank.");
                    lore.add(CC.RED + "You need " + CC.B_RED + more + CC.RED + " coins more.");
                    lore.add(CC.GRAY + CC.STRIKE_THROUGH + "-----------------------------------");
                } else {
                    lore.add(CC.GRAY + CC.STRIKE_THROUGH + "-----------------------------------");
                    lore.add(CC.SECONDARY + "Price: " + CC.WHITE + rankType.getPrice());
                    lore.add("");
                    lore.add(CC.B_PRIMARY + "Rank Preview");
                    lore.add(CC.SECONDARY + " Color: " + rankType.getRank().getColor() + "Color");
                    lore.add(CC.SECONDARY + " Prefix: " + rankType.getRank().getPrefix());
                    lore.add("");
                    lore.add(CC.GREEN + "Click here to buy this rank.");
                    lore.add(CC.GRAY + CC.STRIKE_THROUGH + "-----------------------------------");
                }
                return item.name(CC.B_PRIMARY + rankType.getRank().getName()).lore(Color.translate(lore)).build();
            }

            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                int more = rankType.getPrice() - FacebookAPI.getCoinsInt(player);
                if (FacebookAPI.getCoinsInt(player) < rankType.getPrice()) {
                    player.sendMessage(CC.RED + "You can't buy this rank because you don't have enough coins!");
                    player.sendMessage(" " + CC.RED + "You need " + CC.B_RED + more + CC.RED + " coins more.");
                    playSound(player, ButtonSound.FAIL);

                } else {
                    ConsoleCommandSender console = Bukkit.getConsoleSender();
                    String command = "setrank " + player.getName() + " " + rankType.getName() + " 30d Ingame Purchase";
                    Bukkit.dispatchCommand((CommandSender)console, command);
                    Bukkit.broadcastMessage(CC.GRAY + "[" + CC.B_PRIMARY + "Name" + CC.GRAY + "] " + CC.PRIMARY + player.getName() + CC.SECONDARY + " has just bought " + rankType.getRank().getColor() + rankType.getRank().getName() + CC.SECONDARY + " rank via ingame shop.");
                    FacebookAPI.removeCoins(player, rankType.getPrice());
                    playSound(player, ButtonSound.SUCCESS);
                    player.closeInventory();
                }
            }
        }));
        buttons.put(Integer.valueOf(17), new Button() {
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                ItemStack dye = new ItemStack(Material.INK_SACK);
                ItemBuilder item = (new ItemBuilder(dye)).durability(10);
                lore.add("&7&m------------------------------------------------");
                lore.add(CC.RED + "Click here to go to previous menu.");
                lore.add("&7&m------------------------------------------------");
                return (new ItemBuilder(Material.ARROW)).lore(Color.translate(lore)).name(CC.D_RED + "Back").build();
            }

            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                World world = player.getWorld();
                world.playSound(player.getLocation(), Sound.WOOD_CLICK, 10.0F, 1.0F);
                (new CosmeticsMenu()).openMenu(player);
            }
        });
        return buttons;
    }
}
