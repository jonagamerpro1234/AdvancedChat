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
import org.bukkit.inventory.meta.SkullMeta;

import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.SkullUtils;
import jss.advancedchat.utils.Utils;

public class GuiColor {

	private AdvancedChat plugin;
	private ItemStack item;
	private ItemMeta meta;

	public GuiColor(AdvancedChat plugin) {
		this.plugin = plugin;
	}

	public void openGuiColor(Player player, String target) {
		FileConfiguration config = plugin.getColorFile().getConfig();
		FileConfiguration invData = plugin.getInventoryDataFile().getConfig();

		String title = config.getString("Title");
		int amont = invData.getInt("Amount-Items");
		String colorglass = invData.getString("Color-Glass.Color");

		Inventory inv = Bukkit.createInventory(null, 54, Utils.color(title));



		setDecoration(inv, colorglass);

		item = Utils.getPlayerHead(target);
		inv.setItem(4, item);

		Set<String> section = config.getConfigurationSection("Items").getKeys(false);
		
		section.forEach( key -> {
			
			String name = config.getString("Items." + key + ".Name");
			String textures = config.getString("Items." + key + ".Texture");
			int slot = config.getInt("Items." + key + ".Slot");
			List<String> lore = key.contains("Lore") ? new ArrayList<>() : config.getStringList("Items." + key + ".Lore");
			textures = SkullUtils.replace(textures);

			item = Utils.createSkull(textures);
			meta = (SkullMeta) item.getItemMeta();
			meta.setDisplayName(Utils.color(name));

			meta.setLore(coloredLore(lore));
			item.setItemMeta(meta);
			item.setAmount(amont);
			inv.setItem(slot, item);
			
		});

		plugin.addInventoryView(player, "colorGui");
		player.openInventory(inv);
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
