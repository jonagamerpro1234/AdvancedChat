package jss.advancedchat.listeners.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import jss.advancedchat.api.InventoryLoader;


public class InventoryManager {
	
	private static InventoryManager instance;
	private Map<UUID, InventoryLoader> list = new HashMap<UUID, InventoryLoader>();
	
	public InventoryManager() {
		instance = this;
	}
	
	public static InventoryManager getInstance() {
		return instance;
	}
	
	public void register(InventoryLoader... inventoryLoaders) {
		for(InventoryLoader loader : inventoryLoaders) {
			list.put(UUID.randomUUID(), loader);
		}
	}
	
	public void unregister() {
		list.clear();
	}
}
