package jss.advancedchat.inventory;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.MySQL;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.inventory.InventoryUtils;
import jss.advancedchat.utils.inventory.TSkullUtils;

public class GuiPlayer {

	private AdvancedChat plugin = AdvancedChat.get();
	private Inventory inv;
	private ItemStack item;
	private ItemMeta meta;
	
	public void open(Player player, String target) {
		plugin.addInventoryView(player, "playerGui");
		create();
		setItems(player, target);
		player.openInventory(inv);
	}
	
	private void create() {
		FileConfiguration config = plugin.getPlayerGuiFile().getConfig();
		FileConfiguration invData = plugin.getInventoryDataFile().getConfig();

		String title = config.getString("Title");
		String colorglass = invData.getString("Color-Glass.Player");

		inv = Bukkit.createInventory(null, 54, Utils.color(title));

		setGlass(inv, colorglass);
	}

	public void setItems(Player player, String target) {
		FileConfiguration config = plugin.getPlayerGuiFile().getConfig();
		FileConfiguration invData = plugin.getInventoryDataFile().getConfig();

		int amount = invData.getInt("Amount-Items");
		
		inv.setItem(4, Utils.getPlayerHead(target));

		Set<String> section = config.getConfigurationSection("Items").getKeys(false);
		
		PlayerManager playerManager = new PlayerManager(Bukkit.getPlayer(target));
		
		
		for(String key : section) {
			int slot = config.getInt("Items." + key + ".Slot");
			String name = config.getString("Items." + key + ".Name");

			if (!playerManager.isLowMode()) {
				String textures = config.getString("Items." + key + ".Texture");
				textures = TSkullUtils.replace(textures);
				item = Utils.createSkull(textures);
			} else {
				String mat = config.getString("Items." + key + ".Item").toUpperCase();
				item = XMaterial.valueOf(mat).parseItem();
			}
			
			meta = item.getItemMeta();
			meta.setDisplayName(Utils.color(name));

			item.setItemMeta(meta);
			item.setAmount(amount);

			inv.setItem(slot, item);
		}

		int slotmute = config.getInt("Especial-Items.Mute.Slot");
		String name = config.getString("Especial-Items.Mute.Name");
		
		List<String> lore = Arrays.asList(Settings.message_NoPermission);
		
		if (player.isOp() || player.hasPermission("AdvancedChat.Gui.Player.Mute")) {
			item = getMuteItem(player);
			meta = item.getItemMeta();
			meta.setDisplayName(Utils.color("&eThe player has bypass"));
			meta.setLore(InventoryUtils.coloredLore(lore));
			meta.addEnchant(Enchantment.DURABILITY, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(meta);
			inv.setItem(slotmute, item);
		} else {
			item = getMuteItem(player);
			meta = item.getItemMeta();
			meta.setDisplayName(Utils.color(Utils.getVar(player, name)));
			item.setItemMeta(meta);
			inv.setItem(slotmute, item);
		}
	}

	private void setGlass(Inventory inv, String path) {
		for (int i = 0; i < 54; i++) {
			item = XMaterial.valueOf(path.toUpperCase()).parseItem();
			meta = item.getItemMeta();
			meta.setDisplayName(" ");
			item.setItemMeta(meta);
			item.setAmount(1);
			inv.setItem(i, item);

			if (i == 54) {
				break;
			}
		}
	}

	public ItemStack getMuteItem(Player player) {
		PlayerManager playerManager = new PlayerManager(player);
		if (playerManager.existsPlayer("Name")) {
			if (player.isOp() || player.hasPermission("AdvancedChat.Mute.Bypass")) {
				return item = XMaterial.BARRIER.parseItem();
			} else {
				if(Settings.mysql) {
					if(MySQL.get().isMute(player.getUniqueId().toString())) {
						return item = XMaterial.GREEN_DYE.parseItem();
					} else {
						return item = XMaterial.GRAY_DYE.parseItem();
					}
				}else{
					if (playerManager.isMute()) {
						return item = XMaterial.GREEN_DYE.parseItem();
					} else {
						return item = XMaterial.GRAY_DYE.parseItem();
					}
				}
			}
		}
		return null;
	}
	

}
