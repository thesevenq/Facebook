package me.thesevenq.facebook.utils.menu.buttons;

import lombok.AllArgsConstructor;
import me.thesevenq.facebook.utils.string.Color;
import me.thesevenq.facebook.utils.ItemBuilder;
import me.thesevenq.facebook.utils.menu.Button;
import me.thesevenq.facebook.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class BackButton extends Button {

	private Menu back;

	@Override
	public ItemStack getButtonItem(Player player) {
		ItemBuilder item =  new ItemBuilder(Material.ARROW).name(Color.translate("&cGo back"));
		return item.build();
	}

	@Override
	public void clicked(Player player, int i, ClickType clickType, int hb) {
		Button.playNeutral(player);

		this.back.openMenu(player);
	}

}
