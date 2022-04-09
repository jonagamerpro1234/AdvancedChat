package jss.advancedchat.inventory;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Utils;

public class GuiSpecialColorCodes {
	
	private AdvancedChat plugin = AdvancedChat.get();
	private ItemStack item;
	private ItemMeta meta;
	private Inventory inv;
	
	public void open() {
		
	}
	
	public void create() {
		inv = Bukkit.createInventory(null, 56, "&b&lMenu &8&l> &e&lSpecial Codes");
	}
	
	public void setItems() {
		
	}
	
	
	private List<String> coloredLore(List<String> lore) {
		List<String> coloredlore = new ArrayList<>();
		lore.forEach((line) -> {
			String lineColored = Utils.color(line);
			coloredlore.add(lineColored);
		});
		return coloredlore;
	}

	private void setDecoration(Inventory inv, String path) {
		for (int i = 0; i < 54; i++) {
			item = XMaterial.valueOf(path).parseItem();
			meta = item.getItemMeta();
			meta.setDisplayName(Utils.color(" "));
			item.setItemMeta(meta);
			item.setAmount(1);
			inv.setItem(i, item);

			if (i == 54) {
				break;
			}
		}
	}
}
