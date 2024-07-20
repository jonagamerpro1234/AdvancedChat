package jss.advancedchat.listeners.inventory;

import com.cryptomorin.xseries.XMaterial;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.mysql.MySql;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.inventory.InventoryActionHelper;
import jss.advancedchat.utils.inventory.InventoryView;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class PlayerInventoryListener implements Listener {

    private final AdvancedChat plugin = AdvancedChat.get();

    @EventHandler
    public void onInventoryClick(@NotNull InventoryClickEvent e) {
        Player j = (Player) e.getWhoClicked();
        InventoryView inventoryView = plugin.getInventoryView(j);

        if (inventoryView == null) return;
        if (!inventoryView.getInventoryName().contains("playerGui")) return;
        if (e.getCurrentItem() == null || e.getCurrentItem().getType().name().contains("AIR")) {
            e.setCancelled(true);
            return;
        }

        e.getSlotType();

        if (!e.getClickedInventory().equals(j.getOpenInventory().getTopInventory())) return;

        int slot = e.getSlot();
        e.setCancelled(true);

        String playerName = Util.colorless(e.getClickedInventory().getItem(4).getItemMeta().getDisplayName());
        Player target = Bukkit.getPlayer(playerName);
        PlayerManager playerManager = new PlayerManager(target);
        InventoryActionHelper actionHelper = new InventoryActionHelper(j, playerManager, e);

        if(slot == 38){
            actionHelper.setOpenInventoryAction(playerName, InventoryActionHelper.InventoryType.Gradient);
        }

    }

    @EventHandler
    public void onInventoryClose(@NotNull InventoryCloseEvent e) {
        Player j = (Player) e.getPlayer();
        plugin.removeInventoryView(j);
    }

    public void setChangeItemsState(Player p, Inventory inv) {
        FileConfiguration config = plugin.getPlayerGuiFile().getConfig();

        int slotMute = config.getInt("Items.Mute.Slot");
        String name = config.getString("Items.Mute.Name");
        PlayerManager playerManager = new PlayerManager(p);
        ItemStack it;
        ItemMeta me;

        if (p.isOp() || p.hasPermission("AdvancedChat.Mute.ByPass")) return;

        if (Settings.mysql) {
            if (MySql.isMute(p)) {
                it = XMaterial.GRAY_DYE.parseItem();
                MySql.setMute(p,false);
                Logger.debug("Mute off");
            } else {
                it = XMaterial.GREEN_DYE.parseItem();
                MySql.setMute(p,true);
                Logger.debug("Mute on");
            }
        } else {
            if (playerManager.isMute()) {
                it = XMaterial.GRAY_DYE.parseItem();
                playerManager.setMute(false);
                Logger.debug("Mute off");
            } else {
                it = XMaterial.GREEN_DYE.parseItem();
                playerManager.setMute(true);
                Logger.debug("Mute on");
            }
        }

        me = it.getItemMeta();
        me.setDisplayName(Util.color(Util.getVar(p, name)));
        it.setItemMeta(me);
        inv.setItem(slotMute, it);
    }
}
