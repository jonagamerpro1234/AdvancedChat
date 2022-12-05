package jss.advancedchat.utils.inventory;

import jss.advancedchat.utils.interfaces.InventoryHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryUtils implements InventoryHelper {
    private int size;

    private int defaultsize;

    private int row;

    private String title;

    private String id;

    private String texture;

    public InventoryUtils(Player player, int size, String title) {
        this.size = 0;
        this.defaultsize = 9;
        this.row = 1;
        this.title = title;
        this.id = null;
        this.texture = null;
        Inventory inv = Bukkit.createInventory(null, getSize());
        open(player, inv);
    }

    public Build build() {
        return new Build();
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = 9 * row;
    }

    public int getDefaultSize() {
        return this.defaultsize;
    }

    public void setDefaultSize(int defaultsize) {
        this.defaultsize = defaultsize;
    }

    public void setLegacyItemRow(Inventory inventory, Material material, int data, int amount, int slot0, int slot1, String name) {
    }

    public void setItemRow(Inventory inventory, Material material, int amount, int slot0, int slot1, String name) {
    }

    public boolean CheckVersion() {
        return false;
    }

    public boolean setGlowItem(ItemMeta meta) {
        return false;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTexture() {
        return this.texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public void open(Player player, Inventory inventory) {
        player.openInventory(inventory);
    }

    private final class Build {
    }
}
