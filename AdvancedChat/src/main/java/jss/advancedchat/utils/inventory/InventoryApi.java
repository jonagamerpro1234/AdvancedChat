package jss.advancedchat.utils.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import jss.advancedchat.AdvancedChat;

public class InventoryApi {

	private AdvancedChat plugin;
	private Inventory inv;
	private String id;
	
	public InventoryApi(AdvancedChat plugin) {
		this.plugin = plugin;
	}
	
	public void createInventory(String title, int row, String id) {
		this.id = id;
		if(row != -1 && row != 0) {
			if(row <= 6) {
				inv = Bukkit.createInventory(null, 9 * row);
				
			}
		}else {
			inv = Bukkit.createInventory(null, 9);
			return;
		}
	}
	
	public void open(Player player) {
		plugin.addInventoryView(player, this.id);
		player.openInventory(this.inv);
	}
	
}
