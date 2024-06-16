package jss.advancedchat.listeners.inventory;

import com.cryptomorin.xseries.XMaterial;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.inventory.InventoryActionHelper;
import jss.advancedchat.utils.inventory.InventoryActionHelper.InventoryType;
import jss.advancedchat.utils.inventory.InventoryUtils;
import jss.advancedchat.utils.inventory.InventoryView;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RainbowInventoryListener implements Listener {

    private final AdvancedChat plugin = AdvancedChat.get();

    @EventHandler
    public void onClick(@NotNull InventoryClickEvent e) {
        Player j = (Player) e.getWhoClicked();
        InventoryView inventoryView = plugin.getInventoryView(j);

        if (inventoryView == null) return;
        if (!inventoryView.getInventoryName().contains("rainbowGui")) return;
        if (e.getCurrentItem() == null || e.getCurrentItem().getType().name().contains("AIR")) {
            e.setCancelled(true);
            return;
        }

        e.getSlotType();

        if (!Objects.equals(e.getClickedInventory(), j.getOpenInventory().getTopInventory())) return;

        int slot = e.getSlot();
        e.setCancelled(true);

        String playerName = Util.colorless(Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(e.getClickedInventory()).getItem(4)).getItemMeta()).getDisplayName());
        Player target = Bukkit.getPlayer(playerName);
        assert target != null;
        PlayerManager playerManager = new PlayerManager(target);
        InventoryActionHelper actionHelper = new InventoryActionHelper(j, target, playerManager, e);

        if (slot == 19) {
            //add
        }

        if (slot == 3) {
            plugin.removeInventoryView(j);
            actionHelper.setOpenInventoryAction(playerName, InventoryType.Player);
        }

        if (slot == 0) {
            j.closeInventory();
        }

        if (slot == 45) {
            setRainbowItem(playerManager, e.getInventory());
        }
    }

    private void setRainbowItem(@NotNull PlayerManager playerManager, Inventory inv) {
        ItemStack item;
        ItemMeta meta;

        if (playerManager.isRainbow()) {
            item = XMaterial.GRAY_DYE.parseItem();
            assert item != null;
            meta = item.getItemMeta();
            assert meta != null;
            meta.setDisplayName(Util.color("&cDisable"));
            List<String> lore = Collections.singletonList("&7Click to &aenable");
            meta.setLore(InventoryUtils.coloredLore(lore));
            item.setItemMeta(meta);
            inv.setItem(45, item);
            playerManager.setGradient(false);
        } else {
            item = XMaterial.GREEN_DYE.parseItem();
            assert item != null;
            meta = item.getItemMeta();
            assert meta != null;
            meta.setDisplayName(Util.color("&aEnable"));
            List<String> lore = Collections.singletonList("&7Click to &cdisable");
            meta.setLore(InventoryUtils.coloredLore(lore));
            item.setItemMeta(meta);
            inv.setItem(45, item);
            playerManager.setGradient(true);
        }
    }

    @EventHandler
    public void onInventoryClose(@NotNull InventoryCloseEvent e) {
        Player j = (Player) e.getPlayer();
        plugin.removeInventoryView(j);
    }

}
