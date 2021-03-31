package jss.advancedchat.events;

import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import jss.advancedchat.AdvancedChat;
import net.md_5.bungee.event.EventHandler;

public class InvListener implements Listener{

	@SuppressWarnings("unused")
	private AdvancedChat plugin;

	public InvListener(AdvancedChat plugin) {
		super();
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
	}
	
}
