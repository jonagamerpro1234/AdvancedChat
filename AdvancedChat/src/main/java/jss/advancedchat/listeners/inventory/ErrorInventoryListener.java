package jss.advancedchat.listeners.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.inventory.InventoryView;

public class ErrorInventoryListener implements Listener {
	
	private final AdvancedChat plugin = AdvancedChat.get();
	
	@EventHandler 
	public void onClick(InventoryClickEvent e) {
		Player j = (Player) e.getWhoClicked();
		InventoryView inventoryView = plugin.getInventoryView(j);
		
		if(inventoryView == null) return;
		if(!inventoryView.getInventoryName().contains("errorGui")) return;
		if(e.getCurrentItem() == null || e.getCurrentItem().getType().name().contains("AIR")) {
			e.setCancelled(true);
			return;
		}

		e.getSlotType();
		if(!e.getClickedInventory().equals(j.getOpenInventory().getTopInventory())) return;
		e.getSlot();
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		Player j = (Player) e.getPlayer();
		plugin.removeInventoryView(j);
	}
	
}