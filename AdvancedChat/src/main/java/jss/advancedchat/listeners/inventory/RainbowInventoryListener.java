package jss.advancedchat.listeners.inventory;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
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
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.inventory.InventoryActionHelper;
import jss.advancedchat.utils.inventory.InventoryUtils;
import jss.advancedchat.utils.inventory.InventoryView;
import jss.advancedchat.utils.inventory.InventoryActionHelper.InventoryType;

public class RainbowInventoryListener implements Listener {
	
	private AdvancedChat plugin = AdvancedChat.get();
	
	@EventHandler 
	public void onClick(InventoryClickEvent e) {
		Player j = (Player) e.getWhoClicked();
		InventoryView inventoryView = plugin.getInventoryView(j);
		
		if(inventoryView == null) return;
		if(!inventoryView.getInventoryName().contains("rainbowGui")) return;
		if(e.getCurrentItem() == null || e.getCurrentItem().getType().name().contains("AIR")) {
			e.setCancelled(true);
			return;
		}
		
		if((e.getSlotType() == null)) {
			e.setCancelled(true);
			return;
		}else {
			
			if(!e.getClickedInventory().equals(j.getOpenInventory().getTopInventory())) return;
			
			int slot = e.getSlot();
			e.setCancelled(true);
			
			String playerName = Util.colorless(e.getClickedInventory().getItem(4).getItemMeta().getDisplayName());
			Player target = Bukkit.getPlayer(playerName);
			PlayerManager playerManager = new PlayerManager(target);
			InventoryActionHelper actionHelper = new InventoryActionHelper(j, target, playerManager, e);
			
			if(slot == 19) {

			}
			
			if(slot == 3) {
				plugin.removeInvetoryView(j);
				actionHelper.setOpenInventoryAction(playerName, InventoryType.Player);
			}
			
			if(slot == 0) {
				j.closeInventory();
			}
			
			if(slot == 45) {
				setRainbowItem(playerManager, e.getInventory());
			}
		}
	}
	
	private void setRainbowItem(PlayerManager playerManager, Inventory inv) {
		ItemStack item;
		ItemMeta meta;
		
		if(playerManager.isRainbow()) {
			item = XMaterial.GRAY_DYE.parseItem();
			meta = item.getItemMeta();
			meta.setDisplayName(Util.color("&cDisable"));
			List<String> lore = Arrays.asList("&7Click to &aenable");
			meta.setLore(InventoryUtils.coloredLore(lore));
			item.setItemMeta(meta);
			inv.setItem(45, item);
			playerManager.setGradient(false);
		}else {
			item = XMaterial.GREEN_DYE.parseItem();
			meta = item.getItemMeta();
			meta.setDisplayName(Util.color("&aEnable"));
			List<String> lore = Arrays.asList("&7Click to &cdisable");
			meta.setLore(InventoryUtils.coloredLore(lore));
			item.setItemMeta(meta);
			inv.setItem(45, item);
			playerManager.setGradient(true);
		}
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		Player j = (Player) e.getPlayer();
		plugin.removeInvetoryView(j);
	}
	
}
