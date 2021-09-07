package jss.advancedchat.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.AdvancedChat;
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
		String colorglass = invData.getString("Color-Glass.Channel");
		
		title = Utils.color(title);
		
		Inventory inv = Bukkit.createInventory(null, 4*9, title);
		
		setDecoration(inv, colorglass);
		
		item = Utils.getPlayerHead(target);
		inv.setItem(4, item);
		
		section.forEach( (key) -> {
			
			String mat = config.getString("Items." + key + ".Item");
			int slot = config.getInt("Items." + key + ".Slot");
			
			item = XMaterial.valueOf(mat.toUpperCase()).parseItem();
			
			item.setAmount(amount);
			inv.setItem(slot, item);
		});
		
		plugin.addInventoryView(player, "channel");
		player.openInventory(inv);
	}
	
	@SuppressWarnings("unused")
	private List<String> coloredLore(List<String> lore) {
		List<String> coloredlore = new ArrayList<>();
		lore.forEach((line) -> {
			String lineColored = Utils.color(line);
			coloredlore.add(lineColored);
		});
		return coloredlore;
	}

	private void setDecoration(Inventory inv, String path) {
		for (int i = 0; i < 36; i++) {
			item = XMaterial.valueOf(path).parseItem();
			meta = item.getItemMeta();
			meta.setDisplayName(Utils.color(" "));
			item.setItemMeta(meta);
			item.setAmount(1);
			inv.setItem(i, item);

			if (i == 36) {
				break;
			}
		}
	}
}
