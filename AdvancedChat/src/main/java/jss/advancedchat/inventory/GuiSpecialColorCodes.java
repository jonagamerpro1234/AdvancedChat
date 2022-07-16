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

public class GuiSpecialColorCodes {
	
	private final AdvancedChat plugin = AdvancedChat.get();
	private Inventory inv;
	
	public void open(Player player, String target) {
		create();
	}
	
	private void create() {
		inv = Bukkit.createInventory(null, 54, "&b&lMenu &8&l> &e&lSpecial Codes");
		
	}
	
	public void setItems() {
		setDecoration(XMaterial.BLACK_STAINED_GLASS_PANE.toString());
	}
	
	
	public List<String> coloredLore(List<String> lore) {
		List<String> coloredlore = new ArrayList<>();
		lore.forEach((line) -> {
			String lineColored = Util.color(line);
			coloredlore.add(lineColored);
		});
		return coloredlore;
	}

	private void setDecoration(String path) {
		for (int i = 0; i < 54; i++) {
			ItemStack item = XMaterial.valueOf(path).parseItem();
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(Util.color(" "));
			item.setItemMeta(meta);
			item.setAmount(1);
			inv.setItem(i, item);

		}
	}
}
