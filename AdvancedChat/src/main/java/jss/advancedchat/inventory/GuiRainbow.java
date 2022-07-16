package jss.advancedchat.inventory;

import com.cryptomorin.xseries.XMaterial;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GuiRainbow {

	@SuppressWarnings("unused")
	private final AdvancedChat plugin = AdvancedChat.get();
	private ItemStack item;
	private Inventory inv;
	
	public void open(Player player, String target) {
		create();
		setItems(target);
		player.openInventory(inv);
	}
	
	public void create() {
		inv = Bukkit.createInventory(null, 54, Util.color("&b&lMenu &8&l> &e&lRainbow"));
	}
	
	public void setItems(String target) {
		setDecoration();
	
		item = Util.getPlayerHead(target);
		inv.setItem(4, item);
		
	}
	
	public List<String> coloredLore(List<String> lore) {
		List<String> coloredlore = new ArrayList<>();
		lore.forEach((line) -> {
			String lineColored = Util.color(line);
			coloredlore.add(lineColored);
		});
		return coloredlore;
	}

	public void setDecoration() {
		for (int i = 0; i < 54; i++) {
			item = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(Util.color(" "));
			item.setItemMeta(meta);
			item.setAmount(1);
			inv.setItem(i, item);

		}
	}
	
}
