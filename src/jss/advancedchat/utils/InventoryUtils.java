package jss.advancedchat.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;

import jss.advancedchat.utils.interfaces.InventoryHelper;

public class InventoryUtils implements InventoryHelper {

    private int size;
    private int defaultsize;
    private int row;
    private String title;
    private String id;
    private String texture;
    private Player player;

    public InventoryUtils(Player player, int size, String title) {
        this.size = 0;
        this.defaultsize = 9;
        this.row = 1;
        this.title = title;
        this.id = null;
        this.texture = null;
        this.player = player;

    }
    
    public InventoryUtils create() {
        Inventory inv = Bukkit.createInventory(null, getSize());
        open(player, inv);
    	return this;
    }

    //comming soon
    public Build build() {
        return new Build();
    }

    private final class Build {
        
    	public Build() {}
    	
    	

    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setRow(int row) {
        this.row = 9 * row;
    }

    @Override
    public int getRow() {
        return this.row;
    }

    @Override
    public void setDefaultSize(int defaultsize) {
        this.defaultsize = defaultsize;
    }

    @Override
    public int getDefaultSize() {
        return this.defaultsize;
    }

    @Override
    public void setLegacyItemRow(Inventory inventory, Material material, int data, int amount, int slot0, int slot1, String name) {

    }

    @Override
    public void setItemRow(Inventory inventory, Material material, int amount, int slot0, int slot1, String name) {

    }

    @Override
    public boolean CheckVersion() {
        return false;
    }

    @Override
    public boolean setGlowItem(ItemMeta meta) {
        return false;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getTexture() {
        return this.texture;
    }

    @Override
    public void setTexture(String texture) {
        this.texture = texture;
    }

    @Override
    public void open(Player player, Inventory inventory) {
        player.openInventory(inventory);
    }

}
