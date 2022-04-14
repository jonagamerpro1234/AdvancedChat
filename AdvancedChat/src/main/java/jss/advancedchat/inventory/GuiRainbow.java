package jss.advancedchat.inventory;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Utils;

public class GuiRainbow {

	@SuppressWarnings("unused")
	private AdvancedChat plugin = AdvancedChat.get();
	private ItemStack item;
	private ItemMeta meta;
	private Inventory inv;
	
	public void open(Player player, String target) {
		create();
		setItems(target);
		player.openInventory(inv);
	}
	
	public void create() {
		inv = Bukkit.createInventory(null, 54, Utils.color("&b&lMenu &8&l> &e&lRainbow"));
	}
	
	public void setItems(String target) {
		setDecoration();
	
		item = Utils.getPlayerHead(target);
		inv.setItem(4, item);
		
	}
	
	public List<String> coloredLore(List<String> lore) {
		List<String> coloredlore = new ArrayList<>();
		lore.forEach((line) -> {
			String lineColored = Utils.color(line);
			coloredlore.add(lineColored);
		});
		return coloredlore;
	}

	public void setDecoration() {
		for (int i = 0; i < 54; i++) {
			item = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
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
