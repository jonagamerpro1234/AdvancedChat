package jss.advancedchat.inventory;

import com.cryptomorin.xseries.XMaterial;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GuiChannel {
	
	private final AdvancedChat plugin;
	private ItemStack item;

	public GuiChannel(AdvancedChat plugin) {
		this.plugin = plugin;
	}
	
	public void open(Player player, String target) {
		FileConfiguration config = plugin.getChannelGuiFile().getConfig();
		
		String title = config.getString("Title");
		Set<String> section = config.getConfigurationSection("Items").getKeys(false);
		
		title = Util.color(title);
		
		Inventory inv = Bukkit.createInventory(null, 4 * 9, title);
		
		setDecoration(inv, XMaterial.BLACK_STAINED_GLASS_PANE.toString());
		
		item = Util.getPlayerHead(target);
		inv.setItem(4, item);
		
		section.forEach( (key) -> {
			
			String mat = config.getString("Items." + key + ".Item");
			int slot = config.getInt("Items." + key + ".Slot");
			
			item = XMaterial.valueOf(mat.toUpperCase()).parseItem();
			
			item.setAmount(1);
			inv.setItem(slot, item);
		});
		
		plugin.addInventoryView(player, "channel");
		player.openInventory(inv);
	}
	
	@SuppressWarnings("unused")
	private List<String> coloredLore(List<String> lore) {
		List<String> coloredlore = new ArrayList<>();
		lore.forEach((line) -> {
			String lineColored = Util.color(line);
			coloredlore.add(lineColored);
		});
		return coloredlore;
	}

	private void setDecoration(Inventory inv, String path) {
		for (int i = 0; i < 36; i++) {
			item = XMaterial.valueOf(path).parseItem();
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(Util.color(" "));
			item.setItemMeta(meta);
			item.setAmount(1);
			inv.setItem(i, item);

		}
	}
}
