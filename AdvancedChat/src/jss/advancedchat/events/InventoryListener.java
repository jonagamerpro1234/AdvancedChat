package jss.advancedchat.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.inventory.GuiColor;
import jss.advancedchat.inventory.GuiPlayer;
import jss.advancedchat.inventory.utils.InventoryView;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;

@SuppressWarnings("unused")
public class InventoryListener implements Listener {

    private AdvancedChat plugin;
    private EventUtils eventUtils = new EventUtils(plugin);

    public InventoryListener(AdvancedChat plugin) {
        this.plugin = plugin;
    }

	@EventHandler
    public void onInventoryClickPlayer(InventoryClickEvent e) {
    	FileConfiguration config = plugin.getPlayerGuiFile().getConfig();
		PlayerManager manager = new PlayerManager(plugin);
        Player p = (Player) e.getWhoClicked();
        InventoryView inventoryPlayer = plugin.getInventoryPlayer(p);
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
                    	GuiColor guiColor = new GuiColor(plugin);
                    	guiColor.openGuiColor(p, name);
                    }
                    
                    if(slot == config.getInt("Items.Exit.Slot")) {
                    	e.setCancelled(true);
                    	p.closeInventory();
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClickColor(InventoryClickEvent e) {
        PlayerManager playerManager = new PlayerManager(plugin);
        FileConfiguration c = plugin.getColorFile().getConfig();
        Player p = (Player) e.getWhoClicked();
        InventoryView inventoryPlayer = plugin.getInventoryPlayer(p);
        if (inventoryPlayer != null) {
            if (inventoryPlayer.getInventoryName().contains("colorGui")) {
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

                    if (slot == c.getInt("Items.Dark-Red.Slot")) {
                        if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Dark_Red")) {
                            e.setCancelled(true);
                            playerManager.setColor(target, "Dark_Red");
                        } else {
                            Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                            return;
                        }
                    }
                    if (slot == c.getInt("Items.Red.Slot")) {
                        if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Red")) {
                            e.setCancelled(true);
                            playerManager.setColor(target, "Red");
                        } else {
                            Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                            return;
                        }
                    }
                    if (slot == c.getInt("Items.Dark-Blue.Slot")) {
                        if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Dark_Blue")) {
                            e.setCancelled(true);
                            playerManager.setColor(target, "Dark_Blue");
                        } else {
                            Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                            return;
                        }
                    }
                    if (slot == c.getInt("Items.Blue.Slot")) {
                        if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Blue")) {
                            e.setCancelled(true);
                            playerManager.setColor(target, "Blue");
                        } else {
                            Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                            return;
                        }
                    }
                    if (slot == c.getInt("Items.Dark-Green.Slot")) {
                        if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Dark_Green")) {
                            e.setCancelled(true);
                            playerManager.setColor(target, "Dark_Green");
                        } else {
                            Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                            return;
                        }
                    }
                    if (slot == c.getInt("Items.Green.Slot")) {
                        if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Green")) {
                            e.setCancelled(true);
                            playerManager.setColor(target, "Green");
                        } else {
                            Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                            return;
                        }
                    }
                    if (slot == c.getInt("Items.Yellow.Slot")) {
                        if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Yellow")) {
                            e.setCancelled(true);
                            playerManager.setColor(target, "Yellow");
                        } else {
                            Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                            return;
                        }
                    }
                    if (slot == c.getInt("Items.Gold.Slot")) {
                        if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Gold")) {
                            e.setCancelled(true);
                            playerManager.setColor(target, "Gold");
                        } else {
                            Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                            return;
                        }
                    }
                    if (slot == c.getInt("Items.Dark-Aqua.Slot")) {
                        if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Dark_Aqua")) {
                            e.setCancelled(true);
                            playerManager.setColor(target, "Dark_Aqua");
                        } else {
                            Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                            return;
                        }
                    }
                    if (slot == c.getInt("Items.Aqua.Slot")) {
                        if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Aqua")) {
                            e.setCancelled(true);
                            playerManager.setColor(target, "Aqua");
                        } else {
                            Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                            return;
                        }
                    }
                    if (slot == c.getInt("Items.Light-Purple.Slot")) {
                        if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Light_Purple")) {
                            e.setCancelled(true);
                            playerManager.setColor(target, "Light_Purple");
                        } else {
                            Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                            return;
                        }
                    }
                    if (slot == c.getInt("Items.Dark-Purple.Slot")) {
                        if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Dark_Purple")) {
                            e.setCancelled(true);
                            playerManager.setColor(target, "Dark_Purple");
                        } else {
                            Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                            return;
                        }
                    }
                    if (slot == c.getInt("Items.Gray.Slot")) {
                        if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Gray")) {
                            e.setCancelled(true);
                            playerManager.setColor(target, "Gray");
                        } else {
                            Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                            return;
                        }
                    }
                    if (slot == c.getInt("Items.Dark-Gray.Slot")) {
                        if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Dark_Gray")) {
                            e.setCancelled(true);
                            playerManager.setColor(target, "Dark_Gray");
                        } else {
                            Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                            return;
                        }
                    }
                    if (slot == c.getInt("Items.White.Slot")) {
                        if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.White")) {
                            e.setCancelled(true);
                            playerManager.setColor(target, "White");
                        } else {
                            Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                            return;
                        }
                    }
                    if (slot == c.getInt("Items.Rainbow.Slot")) {
                        if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.RainBow")) {
                            e.setCancelled(true);
                            playerManager.setColor(target, "RainBow");
                        } else {
                            Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                            return;
                        }
                    }
                    if (slot == c.getInt("Items.Black.Slot")) {
                        if (p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Black")) {
                            e.setCancelled(true);
                            playerManager.setColor(target, "Black");
                        } else {
                            Utils.sendHoverEvent(p, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                            return;
                        }
                    }
                    if (slot == c.getInt("Items.Exit.Slot")) {
                    	e.setCancelled(true);
                    	p.closeInventory();
                    }
                    if (slot == c.getInt("Items.Last.Slot")) {
                    	e.setCancelled(true);
                    	p.closeInventory();
                    	GuiPlayer guiPlayer = new GuiPlayer(plugin);
                    	guiPlayer.open(p, name);
                    			
                    }
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        plugin.removeInvetoryPlayer(p);
    }

    @EventHandler
    public void onInventoryCloseColor(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        plugin.removeInvetoryPlayer(p);
    }

}
