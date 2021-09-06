package jss.advancedchat.utils.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class Gui {
	
	private UUID uuid;
	private Inventory inventory;
	private Map<Integer, IAction> actions;
	
	private static Map<UUID, Gui> inventoriesByUUID = new HashMap<>();
	private static Map<UUID, UUID> openIventories = new HashMap<>();
	
	public Gui(int size, String name) {
		uuid = UUID.randomUUID();
		inventory = Bukkit.createInventory(null, size, name);
		actions = new HashMap<>();
		inventoriesByUUID.put(getUuid(), this);
	}
	
	public void setItem(int slot, ItemStack itemstack, IAction action) {
		inventory.setItem(slot, itemstack);
		
		if(action != null) {
			actions.put(slot, action);
		}
	}
	
	public void setItem(int slot, ItemStack itemStack) {
		setItem(slot, itemStack, null);
	}
	
	public void open(Player player) {
		openIventories.put(player.getUniqueId(), getUuid());
		player.openInventory(inventory);
	}
	
	public UUID getUuid() {
		return uuid;
	}
	
	public static Map<UUID, Gui> getInventoriesByUUID() {
		return inventoriesByUUID;
	}

	public static Map<UUID, UUID> getOpenIventories() {
		return openIventories;
	}
	
	public Map<Integer, IAction> getActions() {
		return actions;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
}
