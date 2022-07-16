package jss.advancedchat.inventory;

import com.cryptomorin.xseries.XMaterial;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.inventory.InventoryUtils;
import jss.advancedchat.utils.inventory.TSkullUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class GuiPlayer {

	private final AdvancedChat plugin = AdvancedChat.get();
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

		String title = config.getString("Title");

		inv = Bukkit.createInventory(null, 54, Util.color(title));

		setGlass(inv, XMaterial.BLACK_STAINED_GLASS_PANE.toString());
	}

	public void setItems(Player player, String target) {
		FileConfiguration config = plugin.getPlayerGuiFile().getConfig();
		
		inv.setItem(4, Util.getPlayerHead(target));		
		PlayerManager playerManager = new PlayerManager(Bukkit.getPlayer(target));
				
		for(String key : config.getConfigurationSection("Items").getKeys(false)) {
			int slot = config.getInt("Items." + key + ".Slot");
			String name = config.getString("Items." + key + ".Name");

			if (!playerManager.isLowMode()) {
				String textures = config.getString("Items." + key + ".Texture");
				textures = TSkullUtils.replace(textures);
				item = Util.createSkull(textures);
			} else {
				String mat = config.getString("Items." + key + ".Item").toUpperCase();
				item = XMaterial.valueOf(mat).parseItem();
			}
			
			meta = item.getItemMeta();
			meta.setDisplayName(Util.color(name));

			item.setItemMeta(meta);
			item.setAmount(1);

			inv.setItem(slot, item);
		}

		int slotmute = config.getInt("Especial-Items.Mute.Slot");
		String name = config.getString("Especial-Items.Mute.Name");
		
		List<String> lore = Arrays.asList(Settings.message_NoPermission);
		
		if (player.isOp() || player.hasPermission("AdvancedChat.Gui.Player.Mute")) {
			item = getMuteItem(player);
			meta = item.getItemMeta();
			meta.setDisplayName(Util.color("&eThe player has bypass"));
			meta.setLore(InventoryUtils.coloredLore(lore));
			meta.addEnchant(Enchantment.DURABILITY, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(meta);
			inv.setItem(slotmute, item);
		} else {
			item = getMuteItem(player);
			meta = item.getItemMeta();
			meta.setDisplayName(Util.color(Util.getVar(player, name)));
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

		}
	}

	public ItemStack getMuteItem(Player player) {
		PlayerManager playerManager = new PlayerManager(player);
		if (playerManager.existsPlayer("Name")) {
			if (player.isOp() || player.hasPermission("AdvancedChat.Mute.Bypass")) {
				return item = XMaterial.BARRIER.parseItem();
			} else {
				if(Settings.mysql) {
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
