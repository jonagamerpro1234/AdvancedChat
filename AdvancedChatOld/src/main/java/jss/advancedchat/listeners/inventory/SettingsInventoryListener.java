package jss.advancedchat.listeners.inventory;

import com.cryptomorin.xseries.XMaterial;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.inventory.InventoryActionHelper;
import jss.advancedchat.utils.inventory.InventoryActionHelper.InventoryType;
import jss.advancedchat.utils.inventory.InventoryView;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SettingsInventoryListener implements Listener {

    private final AdvancedChat plugin = AdvancedChat.get();

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player j = (Player) e.getWhoClicked();
        InventoryView inventoryView = plugin.getInventoryView(j);

        if (inventoryView == null) return;
        if (!inventoryView.getInventoryName().contains("settingsGui")) return;
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
        InventoryActionHelper actionHelper = new InventoryActionHelper(j, target, playerManager, e);

        if (slot == 19) {
            isLowModeItem(playerManager, target, e.getInventory());
        }

        if (slot == 21) {
            isChatItem(playerManager, target, e.getInventory());
        }

        if (slot == 3) {
            plugin.removeInventoryView(j);
            actionHelper.setOpenInventoryAction(playerName, InventoryType.Player);
        }

        if (slot == 45) {
            j.updateInventory();
        }

        if (slot == 0) {
            j.closeInventory();
        }
    }

    public void isLowModeItem(PlayerManager playerManager, Player player, Inventory inv) {
        ItemStack item = XMaterial.REPEATER.parseItem();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Util.color("&eLow Mode"));


        if (!player.isOp() || !player.hasPermission("AdvancedChat.Gui.Settings.LowMode")) return;

        if (playerManager.isLowMode()) {
            playerManager.setLowMode(false);
        } else {
            playerManager.setLowMode(true);
            meta.addEnchant(Enchantment.DURABILITY, 1, false);
        }

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        inv.setItem(19, item);
    }

    public void isChatItem(PlayerManager playerManager, Player player, Inventory inv) {
        ItemStack item = XMaterial.REPEATER.parseItem();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Util.color("&eChat"));


        if (!player.isOp() || !player.hasPermission("AdvancedChat.Gui.Settings.Chat")) return;

        if (playerManager.isChat()) {
            playerManager.setChat(false);
        } else {
            playerManager.setChat(true);
            meta.addEnchant(Enchantment.DURABILITY, 1, false);
        }

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        inv.setItem(21, item);
    }


    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        Player j = (Player) e.getPlayer();
        plugin.removeInventoryView(j);
    }

}