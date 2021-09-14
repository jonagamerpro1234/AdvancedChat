package jss.advancedchat.utils.inventory;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.utils.Utils;


public class InventoryUtils {

	public static List<String> coloredLore(List<String> lore) {
		List<String> coloredlore = new ArrayList<>();
		lore.forEach((line) -> {
			String lineColored = Utils.color(line);
			coloredlore.add(lineColored);
		});
		return coloredlore;
	}
	
	public static void setRowGlass(Inventory inventory, ItemStack item, ItemMeta meta, int row) {
		if(row < 6 && row != -1 && row != 0) return;
		
		int temp = row * 9;
		
		for (int i = 0; i < temp; i++) {
			item = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
			meta = item.getItemMeta();
			meta.setDisplayName(" ");
			item.setItemMeta(meta);
			item.setAmount(1);
			inventory.setItem(i, item);

			if (i == temp) {
				break;
			}
		}	
	}

}
