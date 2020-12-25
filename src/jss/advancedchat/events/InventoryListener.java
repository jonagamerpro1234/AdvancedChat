package jss.advancedchat.events;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.InventoryPlayer;

public class InventoryListener implements Listener{
	
	private AdvancedChat plugin;
	private EventUtils eventUtils = new EventUtils(plugin);
	
	public InventoryListener(AdvancedChat plugin) {
		this.plugin = plugin;
		eventUtils.getEventManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onInventoryColor(InventoryClickEvent e) {
		FileConfiguration config = plugin.getPlayerDataFile().getConfig();
		Player p = (Player) e.getWhoClicked();
		InventoryPlayer inventoryPlayer = plugin.getInventoryPlayer(p);
		if(inventoryPlayer != null) {
			if(inventoryPlayer.getInventory().equals("color")) {
		        if (e.getCurrentItem() == null) {
		            return;
		          }
		          if ((e.getCurrentItem().getType() == Material.AIR) || (e.getSlotType() == null)) {
		            return;
		          }
				if(e.getClickedInventory() != null && e.getClickedInventory().equals(p.getOpenInventory().getTopInventory())) {
					e.setCancelled(true);
					int slot = e.getSlot();
					if(slot == 10) {
						e.setCancelled(true);
						config.set("Players."+p.getName()+".Color", "Dark_Red");
						plugin.getPlayerDataFile().saveConfig();
					}
				}
			}
		}
	}
	public void onInventoryDrapColor(InventoryDragEvent e) {
		Player p = (Player) e.getWhoClicked();
		InventoryPlayer inventoryPlayer = plugin.getInventoryPlayer(p);
		if(inventoryPlayer != null) {
			if(inventoryPlayer.getInventory().equals("color")) {
				e.setCancelled(true);
			}
		}
	}
	
}
