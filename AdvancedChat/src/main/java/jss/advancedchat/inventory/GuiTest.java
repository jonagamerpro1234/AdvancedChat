package jss.advancedchat.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.AdvancedChat;

public class GuiTest{

	private AdvancedChat plugin;
	
	public GuiTest(AdvancedChat plugin) {
		this.plugin = plugin;
	}

	public void open(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 9, "Test Gui");
		
		plugin.addInventoryView(player, "test");
		
		ItemStack item = XMaterial.STONE.parseItem();
		inv.setItem(0, item);
		player.openInventory(inv);
	}

}
