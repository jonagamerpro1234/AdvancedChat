package jss.advancedchat.inventory.utils;

public class ItemId {
	
	private String itemid;
	private int slot;
	private String inventory;

	public ItemId(String itemid, int slot, String inventory) {
		this.itemid = itemid;
		this.slot = slot;
		this.inventory = inventory;
	}

	public String getItemid() {
		return itemid;
	}

	public int getSlot() {
		return slot;
	}

	public String getInventory() {
		return inventory;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

}
