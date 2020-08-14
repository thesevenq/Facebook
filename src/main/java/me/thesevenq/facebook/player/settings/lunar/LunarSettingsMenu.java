package me.thesevenq.facebook.player.settings.lunar;

import me.thesevenq.facebook.player.lunar.LunarAPI;
import me.thesevenq.facebook.player.lunar.impl.StaffModule;
import me.thesevenq.facebook.utils.ItemBuilder;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.ButtonSound;
import me.thesevenq.facebook.utils.menu.Menu;
import me.thesevenq.facebook.utils.string.CC;
import me.thesevenq.facebook.utils.string.MessageUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LunarSettingsMenu extends Menu {
    @Override
    public String getTitle(Player player) {
        return CC.PRIMARY + "Lunar Settings";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        surroundButtons(true, buttons, new ItemBuilder(Material.STAINED_GLASS_PANE).durability(7).name(" ").build());

        buttons.put(10, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.DIAMOND_ORE).name(CC.PRIMARY + "Toggle Xray").lore(Arrays.asList(
                        "",
                        CC.SECONDARY + "Do you want to see if",
                        CC.SECONDARY + "other players are xraying?",
                        "",
                        CC.GRAY + "Click here to toggle.")).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                LunarAPI.StaffData data = (LunarAPI.StaffData)LunarAPI.getUsers().get(player.getUniqueId());
                data.setXray(!data.isXray());
                try {
                    LunarAPI.toggleStaffModule(player, StaffModule.XRAY, data.isXray());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                player.sendMessage(CC.SECONDARY + "You have toggled " + CC.PRIMARY + "X-RAY " + CC.SECONDARY + "module.");
                playSound(player, ButtonSound.SUCCESS);
            }

        });

        buttons.put(12, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.SUGAR).name(CC.PRIMARY + "Toggle Bunny Hop").lore(Arrays.asList(
                        "",
                        CC.SECONDARY + "Do you want to bunny hop?",
                        "",
                        CC.GRAY + "Click here to toggle.")).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton){
                LunarAPI.StaffData data = (LunarAPI.StaffData)LunarAPI.getUsers().get(player.getUniqueId());
                data.setBunnyHop(!data.isBunnyHop());
                try {
                    LunarAPI.toggleStaffModule(player, StaffModule.BUNNY_HOP, data.isBunnyHop());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                player.sendMessage(CC.SECONDARY + "You have toggled " + CC.PRIMARY + "Bunny-Hop " + CC.SECONDARY + "module.");
                playSound(player, ButtonSound.SUCCESS);
            }

        });

        buttons.put(14, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.NAME_TAG).name(CC.PRIMARY + "Toggle Name Tags").lore(Arrays.asList(
                        "",
                        CC.SECONDARY + "Do you want to see",
                        CC.SECONDARY + "other players nametags?",
                        "",
                        CC.GRAY + "Click here to toggle.")).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                LunarAPI.StaffData data = (LunarAPI.StaffData)LunarAPI.getUsers().get(player.getUniqueId());
                data.setNameTags(!data.isNameTags());
                try {
                    LunarAPI.toggleStaffModule(player, StaffModule.NAME_TAGS, data.isNameTags());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                player.sendMessage(CC.SECONDARY + "You have toggled " + CC.PRIMARY + "NameTags " + CC.SECONDARY + "module.");
                playSound(player, ButtonSound.SUCCESS);
            }

        });

        buttons.put(16, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.DIAMOND_BOOTS).name(CC.PRIMARY + "Toggle No Clip").lore(Arrays.asList(
                        "",
                        CC.SECONDARY + "Do you want to walk",
                        CC.SECONDARY + "like a fucking god?",
                        "",
                        CC.GRAY + "Click here to toggle.")).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                LunarAPI.StaffData data = (LunarAPI.StaffData)LunarAPI.getUsers().get(player.getUniqueId());
                data.setNoClip(!data.isNoClip());
                try {
                    LunarAPI.toggleStaffModule(player, StaffModule.NO_CLIP, data.isNoClip());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                player.sendMessage(CC.SECONDARY + "You have toggled " + CC.PRIMARY + "NO-CLIP " + CC.SECONDARY + "module.");
                playSound(player, ButtonSound.SUCCESS);
            }

        });

        buttons.put(getSize()-1, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.EMERALD).name(CC.PRIMARY + "Click to enable all!").lore(Arrays.asList(
                        "",
                        CC.SECONDARY + "Enables your staff options",
                        "",
                        CC.PRIMARY + MessageUtils.CIRCLE + " " + CC.WHITE + "x-Ray",
                        CC.PRIMARY + MessageUtils.CIRCLE + " " + CC.WHITE + "Bunny Hop",
                        CC.PRIMARY + MessageUtils.CIRCLE + " " + CC.WHITE + "No Clip",
                        CC.PRIMARY + MessageUtils.CIRCLE + " " + CC.WHITE + "Name Tags",
                        "",
                        CC.GRAY + "Click here to enable all.")).build();
            }

            @Override
            public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                LunarAPI.StaffData data = (LunarAPI.StaffData)LunarAPI.getUsers().get(player.getUniqueId());

                //player.sendMessage(CC.SECONDARY + "You have toggled " + CC.PRIMARY + "NO-CLIP " + CC.SECONDARY + "module.");
                playSound(player, ButtonSound.SUCCESS);
            }

        });


        return buttons;
    }

    @Override
    public int getSize() {
        return 9 * 3;
    }
}
