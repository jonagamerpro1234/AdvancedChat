package jss.advancedchat.inventory;

import java.util.ArrayList;
import java.util.Arrays;
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
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.inventory.InventoryUtils;
import jss.advancedchat.utils.inventory.TSkullUtils;

public class GuiColor {

	private final AdvancedChat plugin = AdvancedChat.get();
	private Inventory inv;
	private ItemStack item;
	private ItemMeta meta;
	
	public void open(Player player, String target) {
		plugin.addInventoryView(player, "colorGui");
		create();
		setItems(target);
		player.openInventory(inv);
	}
	
	private void create() {
		FileConfiguration config = plugin.getColorFile().getConfig();

		String title = config.getString("Title");
		inv = Bukkit.createInventory(null, 54, Util.color(title));
	}
	
	private void setItems(String target) {
		FileConfiguration config = plugin.getColorFile().getConfig();

		setDecoration(inv, XMaterial.BLACK_STAINED_GLASS_PANE.toString());

		item = Util.getPlayerHead(target);
		inv.setItem(4, item);
		
		PlayerManager playerManager = new PlayerManager(Bukkit.getPlayer(target));
		
		Set<String> section = config.getConfigurationSection("Items").getKeys(false);
		
		for(String key : section) {
			String name = config.getString("Items." + key + ".Name");
			int slot = config.getInt("Items." + key + ".Slot");
			List<String> lore = key.contains("Lore") ? new ArrayList<>() : config.getStringList("Items." + key + ".Lore");
			
			if(!playerManager.isLowMode()) {
				String textures = config.getString("Items." + key + ".Texture");
				textures = TSkullUtils.replace(textures);
				item = Util.createSkull(textures);
			}else {
				String mat = config.getString("Items." + key + ".Item").toUpperCase();
				item = XMaterial.valueOf(mat).parseItem();
			}
			
			
			meta = item.getItemMeta();
			meta.setDisplayName(Util.color(name));
			meta.setLore(coloredLore(lore));
			item.setItemMeta(meta);
			item.setAmount(1);
			inv.setItem(slot, item);
		}
		
		InventoryUtils.setItemChecker(inv, 45, playerManager.isColor());
	}
	
	@SuppressWarnings("unused")
	private void setCustomItem(PlayerManager playerManager) {
		if(playerManager.isColor()) {
			item = XMaterial.LIME_DYE.parseItem();
			meta = item.getItemMeta();
			meta.setDisplayName(Util.color("&aEnable"));
			List<String> lore = Arrays.asList("&7Click to &cdisable");
			meta.setLore(coloredLore(lore));
			item.setItemMeta(meta);
			inv.setItem(45, item);
		}else {
			item = XMaterial.GRAY_DYE.parseItem();
			meta = item.getItemMeta();
			meta.setDisplayName(Util.color("&cDisable"));
			List<String> lore = Arrays.asList("&7Click to &aenable");
			meta.setLore(coloredLore(lore));
			item.setItemMeta(meta);
			inv.setItem(45, item);
		}
	}
	
	private List<String> coloredLore(List<String> lore) {
		List<String> coloredlore = new ArrayList<>();
		lore.forEach((line) -> {
			String lineColored = Util.color(line);
			coloredlore.add(lineColored);
		});
		return coloredlore;
	}

	private void setDecoration(Inventory inv, String path) {
		for (int i = 0; i < 54; i++) {
			item = XMaterial.valueOf(path).parseItem();
			meta = item.getItemMeta();
			meta.setDisplayName(Util.color(" "));
			item.setItemMeta(meta);
			item.setAmount(1);
			inv.setItem(i, item);

		}
	}
	

} 