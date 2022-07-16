package jss.advancedchat.test;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.inventory.InventoryView;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class InventoryListener implements Listener {

    private final AdvancedChat plugin = AdvancedChat.get();

	@EventHandler
    public void onInvetoryTest(@NotNull InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        InventoryView inventoryPlayer = plugin.getInventoryView(p);
    	
        if (inventoryPlayer == null) return;
        if (!inventoryPlayer.getInventoryName().contains("test")) return;
        if (e.getCurrentItem() == null) return;
        if (e.getClickedInventory() == null || !e.getClickedInventory().equals(p.getOpenInventory().getTopInventory())) return;
        if (e.getCurrentItem().getType() == Material.AIR) {
            return;
        } else {
            e.getSlotType();
        }
        if (e.getSlot() != 0) return;
     
        e.setCancelled(true);
                          
        if(e.getSlot() == 0) {
        	e.setCancelled(true);
        	if(e.getClick() == ClickType.LEFT) {
        		p.sendMessage("Left click");
        	}else if(e.getClick().isRightClick()) {
        		p.sendMessage("Right click");
        	}
        }    
    }

}
