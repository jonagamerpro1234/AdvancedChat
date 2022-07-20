package jss.advancedchat.utils.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * experimental gui
 */
@SuppressWarnings("unused")
public abstract class BaseGui extends InventoryUtils implements InventoryHolder {

    private static final Map<UUID, BaseGui> inventoriesByUUID = new HashMap<>();
    private static final Map<UUID, UUID> openIventories = new HashMap<>();
    private final UUID uuid = UUID.randomUUID();
    private final Inventory inventory;
    private final Map<Integer, GuiAction<?>> actions;
    private GuiAction<InventoryClickEvent> slotAction;
    private GuiAction<InventoryCloseEvent> closeAction;
    private GuiAction<InventoryOpenEvent> openAction;

    public BaseGui(int size, String name) {
        inventory = Bukkit.createInventory(null, size, name);
        actions = new HashMap<>();
        inventoriesByUUID.put(getUuid(), this);
    }

    public static Map<UUID, BaseGui> getInventoriesByUUID() {
        return inventoriesByUUID;
    }

    public static Map<UUID, UUID> getOpenIventories() {
        return openIventories;
    }

    public void setItem(int slot, ItemStack itemstack, GuiAction<?> action) {
        inventory.setItem(slot, itemstack);

        if (action != null) {
            actions.put(slot, action);
        }
    }

    public void setItem(int slot, ItemStack itemStack) {
        setItem(slot, itemStack, null);
    }

    public void open(Player player) {
        openIventories.put(player.getUniqueId(), getUuid());
        player.openInventory(inventory);
    }

    public UUID getUuid() {
        return uuid;
    }

    public Map<Integer, GuiAction<?>> getActions() {
        return actions;
    }

    public Inventory getInventory() {
        return inventory;
    }


}
