package jss.advancedchat.listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.inventory.GuiColor;
import jss.advancedchat.inventory.GuiGradient;
import jss.advancedchat.inventory.GuiPlayer;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.inventory.Gui;
import jss.advancedchat.utils.inventory.IAction;
import jss.advancedchat.utils.inventory.InventoryView;

@SuppressWarnings("unused")
public class InventoryListener implements Listener {

    private AdvancedChat plugin;
    private EventUtils eventUtils = new EventUtils(plugin);

    public InventoryListener(AdvancedChat plugin) {
        this.plugin = plugin;
    }

	//@EventHandler
    public void onInvetoryTest(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        InventoryView inventoryPlayer = plugin.getInventoryView(p);
    	
        if (inventoryPlayer == null) return;
        if (!inventoryPlayer.getInventoryName().contains("test")) return;
        if (e.getCurrentItem() == null) return;
        if (e.getClickedInventory() == null || !e.getClickedInventory().equals(p.getOpenInventory().getTopInventory())) return;
        if (e.getCurrentItem().getType() == Material.AIR || e.getSlotType() == null) return;
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
    
	//@EventHandler
    public void onInventoryClickPlayer(InventoryClickEvent e) {
    	FileConfiguration config = plugin.getPlayerGuiFile().getConfig();
        Player p = (Player) e.getWhoClicked();
        InventoryView inventoryPlayer = plugin.getInventoryView(p);
        if (inventoryPlayer != null) {
            if (inventoryPlayer.getInventoryName().contains("playerGui")) {
                if (e.getCurrentItem() == null) {
                    return;
                }
                if (e.getClickedInventory() != null && e.getClickedInventory().equals(p.getOpenInventory().getTopInventory())) {
                    if ((e.getCurrentItem().getType() == Material.AIR) || (e.getSlotType() == null)) {
                        return;
                    }
                    e.setCancelled(true);
                    int slot = e.getSlot();

                    String namecolor = e.getClickedInventory().getItem(4).getItemMeta().getDisplayName();
                    String name = Utils.colorless(namecolor);

                    Player target = Bukkit.getPlayer(name);

                    if(slot == config.getInt("Items.Next.Slot")) {
                    	e.setCancelled(true);
                    	p.closeInventory();
                    	GuiColor guiColor = new GuiColor();
                    	guiColor.open(p, name);
                    }
                    
                    if(slot == config.getInt("Items.Exit.Slot")) {
                    	e.setCancelled(true);
                    	p.closeInventory();
                    }
                }
            }
        }
    }


    //@EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        plugin.removeInvetoryView(p);
    }

    //@EventHandler
    public void onInventoryCloseColor(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        plugin.removeInvetoryView(p);
    }

}
