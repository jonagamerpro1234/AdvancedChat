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

		ItemStack item = null;
		ItemMeta meta = null;

		setDecoration(inv, item, meta, colorglass);

		item = Utils.getPlayerHead(target);
		inv.setItem(4, item);

		Set<String> section = config.getConfigurationSection("Items").getKeys(false);
		
		section.forEach( key -> {
			
			String name = config.getString("Items." + key + ".Name");
			String textures = config.getString("Items." + key + ".Texture");
			int slot = config.getInt("Items." + key + ".Slot");
			List<String> lore = key.contains("Lore") ? new ArrayList<>()
					: config.getStringList("Items." + key + ".Lore");
			textures = SkullUtils.replace(textures);

			ItemStack item0 = Utils.createSkull(textures);
			ItemMeta meta0 = (SkullMeta) item0.getItemMeta();
			meta0.setDisplayName(Utils.color(name));

			meta0.setLore(coloredLore(lore));
			item0.setItemMeta(meta0);
			item0.setAmount(amont);
			inv.setItem(slot, item0);
			
		});
		
		/*for (String key : config.getConfigurationSection("Items").getKeys(false)) {

			String name = config.getString("Items." + key + ".Name");
			String textures = config.getString("Items." + key + ".Texture");
			int slot = config.getInt("Items." + key + ".Slot");
			List<String> lore = key.contains("Lore") ? new ArrayList<>()
					: config.getStringList("Items." + key + ".Lore");
			textures = SkullUtils.replace(textures);

			item = Utils.createSkull(textures);
			meta = (SkullMeta) item.getItemMeta();
			meta.setDisplayName(Utils.color(name));

			meta.setLore(coloredLore(lore));
			item.setItemMeta(meta);
			item.setAmount(amont);
			inv.setItem(slot, item);
		}*/

		plugin.addInventoryPlayer(player, "colorGui");
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

	private void setDecoration(Inventory inv, ItemStack item, ItemMeta meta, String path) {
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

	@Deprecated
	public void openGuiColorOld(Player player, String player2) {
		FileConfiguration config = plugin.getColorFile().getConfig();
		FileConfiguration invData = plugin.getInventoryDataFile().getConfig();

		String title = config.getString("Title");
		title = title.replace("<player>", player2);
		Inventory inv = Bukkit.createInventory(null, 45, Utils.color(title));
		ItemStack item = null;
		SkullMeta skullMeta = null;
		ItemMeta meta = null;

		boolean useSkull = invData.getBoolean("Use-Custom-Skull-Color");
		int amont = invData.getInt("Amount-Items");
		String colorglass = invData.getString("Color-Glass.Color");

		setDecoration(inv, item, meta, colorglass);

		item = Utils.getPlayerHead(player2);
		inv.setItem(36, item);

		for (String key : config.getConfigurationSection("Items").getKeys(false)) {
			String texture = "Items." + key + ".Texture";
			String material = config.getString("Items." + key + ".Item");
			String name = config.getString("Items." + key + ".Name");
			List<String> lore = config.getStringList("Items." + key + ".Lore");
			int slots = config.getInt("Items." + key + ".Slot");

			texture = SkullUtils.replace(texture);

			item = XMaterial.valueOf(material).parseItem();

			if (useSkull) {
				item = Utils.createSkull(texture);
				skullMeta = (SkullMeta) item.getItemMeta();
				skullMeta.setDisplayName(Utils.color(name));
				if (lore != null) {
					for (int i = 0; i < lore.size(); i++) {

						String text = (String) lore.get(i);
						text = Utils.color(text);
						text = Utils.getVar(player, text);
						lore.add(text);
					}
					skullMeta.setLore(lore);
				}

				item.setItemMeta(skullMeta);
			} else {

				meta = item.getItemMeta();
				meta.setDisplayName(Utils.color(name));
				if (lore != null) {
					for (int i = 0; i < lore.size(); i++) {

						String text = (String) lore.get(i);
						text = Utils.color(text);
						text = Utils.getVar(player, text);
						lore.add(text);
					}
					skullMeta.setLore(lore);
				}
				item.setItemMeta(meta);
			}
			item.setAmount(amont);
			inv.setItem(slots, item);
		}

		/*
		 * String nameBack = config.getString("Decoration.Back.Name"); String
		 * materialback = config.getString("Decoration.Back.Item"); item =
		 * XMaterial.valueOf(materialback).parseItem(); meta = item.getItemMeta();
		 * meta.setDisplayName(Utils.color(Utils.getVar(player, nameBack)));
		 * item.setItemMeta(meta); item.setAmount(1); inv.setItem(40, item);
		 */

		player.openInventory(inv);
		plugin.addInventoryPlayer(player, "color");
	}
}
