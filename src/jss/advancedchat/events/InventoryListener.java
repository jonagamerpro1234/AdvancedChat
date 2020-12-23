package jss.advancedchat.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.EventUtils;

public class InventoryListener implements Listener{

	private AdvancedChat plugin;
	private EventUtils eventUtils = new EventUtils(plugin);
	
	public InventoryListener(AdvancedChat plugin) {
		this.plugin = plugin;
		eventUtils.getEventManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onClickInventoryColor(InventoryClickEvent e) {
		
		
	}
	
}
