package jss.advancedchat.utils.interfaces;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;

public interface InventoryHelper {
	
	public int getSize();
	
	public void setSize(int size);
	
	public String getTitle();
	
	public void setTitle(String title);
	
	public void setRow(int row);
	
	public int getRow();
	
	public void setDefaultSize(int defaultsize);

	public int getDefaultSize();
	
	public void setLegacyItemRow(Inventory inventory, Material material, int data, int amount, int slot0, int slot1, String name);

	public void setItemRow(Inventory inventory, Material material, int amount, int slot0, int slot1, String name);

	public boolean CheckVersion();
	
	public boolean setGlowItem(ItemMeta meta);
	
	public String getId();
	
	public void setId(String id);
	
	public String getTexture();
	
	public void setTexture(String texture);
	
	public void open(Player player, Inventory inventory);
}
