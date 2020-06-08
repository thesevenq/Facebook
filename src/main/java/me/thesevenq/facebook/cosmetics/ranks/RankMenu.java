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
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.ItemBuilder;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.ButtonSound;
import me.thesevenq.facebook.utils.menu.buttons.BackButton;
import me.thesevenq.facebook.utils.menu.buttons.CloseButton;
import me.thesevenq.facebook.utils.menu.pagination.PageButton;
import me.thesevenq.facebook.utils.menu.pagination.PaginatedMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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

    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(0, new PageButton(-1, this));
        buttons.put(8, new PageButton(1, this));

        buttons.put(27, new BackButton(new CosmeticsMenu()));

        surroundButtons(false, buttons, new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").durability(7).build());

        buttons.put(35, new CloseButton());

        buttons.put(4, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(CC.SECONDARY + "Available&7: " + CC.PRIMARY + RankType.values().length);
                lore.add("");
                lore.add(CC.GRAY + "Click one of the ranks");
                lore.add(CC.GRAY + "to buy it.");
                return new ItemBuilder(Material.NETHER_STAR).lore(Color.translate(lore)).name(CC.PRIMARY + "Information").build();
            }
        });

        return buttons;
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
                    lore.add("");
                    lore.add(CC.SECONDARY + "Price: " + CC.WHITE + rankType.getPrice());
                    lore.add("");
                    lore.add(CC.B_PRIMARY + "Rank Preview");
                    lore.add(CC.SECONDARY + " Color: " + rankType.getRank().getColor() + "Color");
                    lore.add(CC.SECONDARY + " Prefix: " + rankType.getRank().getPrefix());
                    lore.add("");
                    lore.add(CC.RED + "You are not able to buy this rank.");
                    lore.add(CC.RED + "You need " + CC.B_RED + more + CC.RED + " coins more.");
                } else {
                    lore.add(CC.SECONDARY + "Price: " + CC.WHITE + rankType.getPrice());
                    lore.add("");
                    lore.add(CC.B_PRIMARY + "Rank Preview");
                    lore.add(CC.SECONDARY + " Color: " + rankType.getRank().getColor() + "Color");
                    lore.add(CC.SECONDARY + " Prefix: " + rankType.getRank().getPrefix());
                    lore.add("");
                    lore.add(CC.GRAY + "Click here to buy this rank.");
                }
                return item.name(CC.PRIMARY + rankType.getRank().getName()).lore(Color.translate(lore)).build();
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
                    Bukkit.dispatchCommand((CommandSender) console, command);
                    Bukkit.broadcastMessage(CC.GRAY + "[" + CC.B_PRIMARY + "Name" + CC.GRAY + "] " + CC.PRIMARY + player.getName() + CC.SECONDARY + " has just bought " + rankType.getRank().getColor() + rankType.getRank().getName() + CC.SECONDARY + " rank via ingame shop.");
                    FacebookAPI.removeCoins(player, rankType.getPrice());
                    playSound(player, ButtonSound.SUCCESS);
                    player.closeInventory();
                }
            }
        }));
        return buttons;
    }
}
