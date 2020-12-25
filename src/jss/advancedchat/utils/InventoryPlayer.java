package jss.advancedchat.utils;

import org.bukkit.entity.Player;

public class InventoryPlayer {
	
	private Player player;
	private String inventory;
	
	public InventoryPlayer(Player player, String inventory) {
		super();
		this.player = player;
		this.inventory = inventory;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	
}
