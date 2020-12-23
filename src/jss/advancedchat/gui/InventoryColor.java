package jss.advancedchat.gui;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.ColorFile;
import jss.advancedchat.utils.Utils;

public class InventoryColor {
	
	private AdvancedChat plugin;
	
	public InventoryColor(AdvancedChat plugin) {
		this.plugin = plugin;
	}

	public void openGuiColor(Player player) {
		ColorFile colorFile = plugin.getColorFile();
		FileConfiguration config = colorFile.getConfig();
		
		String title = config.getString("Title"); 
		
		Inventory inv = Bukkit.createInventory(null, 27, Utils.color(title));
		
		player.openInventory(inv);
	}
	
}
