package jss.advancedchat.inventory;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.inventory.utils.InventoryApi;

public class InvChannel {
	
	private AdvancedChat plugin;
	
	public InvChannel(AdvancedChat plugin) {
		this.plugin = plugin;
	}
	
	public void open(Player player) {
		FileConfiguration config = plugin.getChannelGuiFile().getConfig();
		
		String title = config.getString("Title");
		int scale = config.getInt("Row");
		
		InventoryApi api = new InventoryApi(title, scale, player).createInventory();
	
		api.open();
	}
}
