package me.thesevenq.facebook.player.grant.menus;

import lombok.RequiredArgsConstructor;
import me.thesevenq.facebook.player.grant.Grant;
import me.thesevenq.facebook.ranks.procedure.GrantProcedure;
import me.thesevenq.facebook.ranks.procedure.GrantProcedureStage;
import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.Color;
import me.thesevenq.facebook.utils.ItemBuilder;
import me.thesevenq.facebook.utils.Tasks;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.ButtonSound;
import me.thesevenq.facebook.utils.menu.Menu;
import org.bukkit.Bukkit;
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
public class GrantConfirmMenu extends Menu {

    private final GrantProcedure grantProcedure;

    @Override
    public String getTitle(Player player) {
        return Color.translate("Are you sure?");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        surroundButtons(true, buttons, new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").durability(7).build());

        int confirm[] = {10, 11, 12, 19, 21, 28, 29, 30};

        for (int i : confirm) {
            buttons.put(i, new Button() {
                @Override
                public ItemStack getButtonItem(Player player) {
                    return new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").durability(13).build();
                }
            });
        }

        buttons.put(20, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add("&7&m------------------------------------------------");
                lore.add(CC.SECONDARY + "Click here to confirm");
                lore.add(CC.SECONDARY + "this current grant.");
                lore.add("&7&m------------------------------------------------");
                return new ItemBuilder(Material.WOOL).durability(5).lore(Color.translate(lore)).name(CC.B_GREEN + "Confirm").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                if (grantProcedure != null && grantProcedure.getStage() == GrantProcedureStage.CONFIRMATION) {
                    Grant grant = new Grant(grantProcedure.getRank(), grantProcedure.getDuration(), System.currentTimeMillis(), grantProcedure.getAddedBy(), grantProcedure.getReason());

                    grantProcedure.getAddedTo().setGrant(grant);
                    grantProcedure.getAddedTo().getGrants().add(grant);
                    Bukkit.getPlayer(grantProcedure.getAddedTo().getName()).sendMessage(Color.translate("&aYou have set &e" + grantProcedure.getAddedTo().getName() + " &arank to " + grantProcedure.getRank().getColor() + grantProcedure.getRank().getName() + "&a."));
                        Tasks.runAsync(() -> Bukkit.getPlayer(grantProcedure.getAddedTo().getName()).setPlayerListName(grantProcedure.getRank().getColor() + grantProcedure.getAddedTo().getName()));
                    player.sendMessage(Color.translate("&aYour rank has been set to " + grantProcedure.getRank().getColor() + grantProcedure.getRank().getName() + "&a."));
                    GrantProcedure.getProcedures().remove(grantProcedure);
                    player.closeInventory();
                    playSound(player, ButtonSound.SUCCESS);
                }
            }
        });

        buttons.put(24, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add("&7&m------------------------------------------------");
                lore.add(CC.SECONDARY + "Click here to cancel");
                lore.add(CC.SECONDARY + "this current grant.");
                lore.add("&7&m------------------------------------------------");
                return new ItemBuilder(Material.WOOL).durability(14).lore(Color.translate(lore)).name(CC.B_RED + "Cancel").build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                if (grantProcedure != null && grantProcedure.getStage() == GrantProcedureStage.CONFIRMATION) {
                    GrantProcedure.getProcedures().remove(grantProcedure);
                    player.sendMessage(CC.RED + "You have cancelled grant procedure.");
                    player.closeInventory();
                    playSound(player, ButtonSound.FAIL);
                }
            }
        });

        int cancel[] = {14, 15, 16, 23, 25, 32, 33, 34};

        for (int i : cancel) {
            buttons.put(i, new Button() {
                @Override
                public ItemStack getButtonItem(Player player) {
                    return new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").durability(14).build();
                }
            });
        }
        return buttons;
    }

    @Override
    public int getSize() {
        return 9 * 5;
    }

}
