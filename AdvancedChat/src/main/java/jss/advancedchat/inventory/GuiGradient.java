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
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.inventory.InventoryUtils;
import jss.advancedchat.utils.inventory.TSkullUtils;

public class GuiGradient {

	private AdvancedChat plugin = AdvancedChat.get();
	private ItemStack item;
	private ItemMeta meta;
	private Inventory inv;
	
	public void open(Player player, String target) {
		plugin.addInventoryView(player, "gradientGui");
		this.create();
		this.addItems(target);
		player.openInventory(inv);
	}
	
	private void create() {
		FileConfiguration config = plugin.getGradientColorFile().getConfig();
		String title = config.getString("Title");

		inv = Bukkit.createInventory(null, 54, Utils.color(title));
	}
	
	private void addItems(String target) {
		FileConfiguration config = plugin.getGradientColorFile().getConfig();
		
		int amont = 1;
		setDecoration();

		item = Utils.getPlayerHead(target);
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
				item = Utils.createSkull(textures);
			}else {
				String mat = config.getString("Items." + key + ".Item").toUpperCase();
				item = XMaterial.valueOf(mat).parseItem();
			}
			
			meta.setDisplayName(Utils.color(name));
			meta.setLore(coloredLore(lore));
			item.setItemMeta(meta);
			item.setAmount(amont);
			inv.setItem(slot, item);
		}
		
		InventoryUtils.setItemChecker(inv, 45, playerManager.isGradient());
	}
	
	@SuppressWarnings("unused")
	private void setCustomItem(PlayerManager playerManager) {
		if(playerManager.isGradient()) {
			item = XMaterial.LIME_DYE.parseItem();
			meta = item.getItemMeta();
			meta.setDisplayName(Utils.color("&aEnable"));
			List<String> lore = Arrays.asList("&7Click to &cdisable");
			meta.setLore(coloredLore(lore));
			item.setItemMeta(meta);
			inv.setItem(45, item);
		}else {
			item = XMaterial.GRAY_DYE.parseItem();
			meta = item.getItemMeta();
			meta.setDisplayName(Utils.color("&cDisable"));
			List<String> lore = Arrays.asList("&7Click to &aenable");
			meta.setLore(coloredLore(lore));
			item.setItemMeta(meta);
			inv.setItem(45, item);
		}
	}
	
	private List<String> coloredLore(List<String> lore) {
		List<String> coloredlore = new ArrayList<>();
		lore.forEach((line) -> {
			String lineColored = Utils.color(line);
			coloredlore.add(lineColored);
		});
		return coloredlore;
	}

	private void setDecoration() {
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
