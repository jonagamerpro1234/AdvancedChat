package jss.advancedchat.listeners.inventory;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.MySQL;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.inventory.InventoryActionHelper;
import jss.advancedchat.utils.inventory.InventoryActionHelper.InventoryType;
import jss.advancedchat.utils.inventory.InventoryView;

public class PlayerInventoryListener implements Listener {
	
	private AdvancedChat plugin;

	public PlayerInventoryListener(AdvancedChat plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player j = (Player) e.getWhoClicked();
		InventoryView inventoryView = plugin.getInventoryView(j);
		
		if(inventoryView == null) return;
		if(!inventoryView.getInventoryName().contains("playerGui")) return;
		if(e.getCurrentItem() == null || e.getCurrentItem().getType().name().contains("AIR")) {
			e.setCancelled(true);
			return;
		}
		
		if(e.getSlotType() == null) {
			e.setCancelled(true);
			return;
		}else {
			
			if(!e.getClickedInventory().equals(j.getOpenInventory().getTopInventory())) return;
			
			int slot = e.getSlot();
			e.setCancelled(true);
			
			String playerName = Utils.colorless(e.getClickedInventory().getItem(4).getItemMeta().getDisplayName());
			Player target = Bukkit.getPlayer(playerName);
			PlayerManager playerManager = new PlayerManager(target);
			InventoryActionHelper actionHelper = new InventoryActionHelper(j, target, playerManager, e);
			
			if(slot == 24) {
				setChangeItemsState(playerManager, target, j.getInventory());
			}
			
			if(slot == Settings.player_inv_slot_next) {
				actionHelper.setOpenInventoryAction(playerName, InventoryType.Color);
			}
			
			if(slot == Settings.player_inv_slot_exit) {
				j.closeInventory();
			}
		}
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		Player j = (Player) e.getPlayer();
		plugin.removeInvetoryView(j);
	}
	
	public void setChangeItemsState(PlayerManager playerManager,Player p, Inventory inv) {
		FileConfiguration config = plugin.getPlayerGuiFile().getConfig();
		
		int slotmute = config.getInt("Items.Mute.Slot");
		String name = config.getString("Items.Mute.Name");
		ItemStack it;
		ItemMeta me;
		
		if(p.isOp() || p.hasPermission("AdvancedChat.Mute.ByPass")) return;
		
		if(Settings.mysql_use) {
			if (MySQL.isMute(plugin, p.getUniqueId().toString())) {
				it = XMaterial.GRAY_DYE.parseItem();
				MySQL.setMute(plugin, p.getUniqueId().toString(), false);
				Logger.debug("Mute off");
			} else {
				it = XMaterial.GREEN_DYE.parseItem();
				MySQL.setMute(plugin, p.getUniqueId().toString(), true);
				Logger.debug("Mute on");
			}
		}else {
			if (playerManager.isMute()) {
				it = XMaterial.GRAY_DYE.parseItem();
				playerManager.setMute(p, false);
				Logger.debug("Mute off");
			} else {
				it = XMaterial.GREEN_DYE.parseItem();
				playerManager.setMute(p, true);
				Logger.debug("Mute on");
			}
		}

		me = it.getItemMeta();
		me.setDisplayName(Utils.color(name));
		it.setItemMeta(me);
		inv.setItem(slotmute, it);
	}
}
