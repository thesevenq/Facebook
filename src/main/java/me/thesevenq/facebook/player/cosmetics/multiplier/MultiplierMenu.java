package me.thesevenq.facebook.player.cosmetics.multiplier;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import me.thesevenq.facebook.FacebookAPI;
import me.thesevenq.facebook.player.cosmetics.CosmeticsMenu;
import me.thesevenq.facebook.player.cosmetics.prefix.types.NormalPrefixType;
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

public class MultiplierMenu extends PaginatedMenu {

    private final PlayerData data;

    private NormalPrefixType prefix;

    @ConstructorProperties({"data"})
    public MultiplierMenu(PlayerData data) {
        this.data = data;
    }

    public String getPrePaginatedTitle(Player player) {
        return CC.PRIMARY + "Cosmetics -> Multipliers";
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
                lore.add(CC.SECONDARY + "Available&7: " + CC.PRIMARY + MultiplierType.values().length);
                lore.add("");
                lore.add(CC.GRAY + "Click one of the multipliers.");
                lore.add(CC.GRAY + "to equip it.");
                return new ItemBuilder(Material.NETHER_STAR).lore(Color.translate(lore)).name(CC.PRIMARY + "Information").build();
            }
        });

        return buttons;
    }

    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        Stream.<MultiplierType>of(MultiplierType.values()).forEach(multiplierType -> buttons.put(Integer.valueOf(buttons.size()), new Button() {

            public ItemStack getButtonItem(Player player) {
                ItemStack dye = new ItemStack(Material.INK_SACK);
                ItemBuilder item = (new ItemBuilder(dye)).durability(10);
                List<String> lore = new ArrayList();

                lore.add("");
                lore.add(CC.SECONDARY + "Duration&7: " + CC.PRIMARY + "Permanent");
                lore.add(CC.SECONDARY + "Multiply&7: " + CC.PRIMARY + multiplierType.getMultiply());
                lore.add("");
                lore.add(CC.GRAY + "Click here to equip this multiplier.");

                return item.name(CC.PRIMARY + multiplierType.getName()).lore(Color.translate(lore)).build();

            }
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                data.setMultiplier(multiplierType);
                player.sendMessage(CC.PRIMARY + multiplierType.getName() + CC.SECONDARY + " is now selected as your multiplier.");
            }
        }));
        return buttons;
    }

    @Override
    public int getSize() {
        return 9 * 4;
    }
}
