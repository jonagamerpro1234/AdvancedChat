package jss.advancedchat.inventory;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.inventory.utils.InventoryUtils;
import jss.advancedchat.utils.Utils;

public class GuiChannel {
	
	private AdvancedChat plugin;
	private ItemStack item;
	private ItemMeta meta;
	
	public GuiChannel(AdvancedChat plugin) {
		this.plugin = plugin;
	}
	
	public void open(Player player, String target) {
		FileConfiguration config = plugin.getChannelGuiFile().getConfig();
		FileConfiguration invData = plugin.getInventoryDataFile().getConfig();
		
		String title = config.getString("Title");
		Set<String> section = config.getConfigurationSection("Items").getKeys(false);
		
		int amount = invData.getInt("Amount-Items");
		
		title = Utils.color(title);
		
		Inventory inv = Bukkit.createInventory(null, 54, title);
		
		InventoryUtils.setRowGlass(inv, item, meta, 6);
		
		section.forEach( (key) -> {
			
			String mat = config.getString("Items." + key + ".Item"); 
			
			item = XMaterial.valueOf(mat).parseItem();
			
			inv.setItem(amount, item);
		});
		
		player.openInventory(inv);
	}
}
