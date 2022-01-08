package jss.advancedchat.utils.inventory;

import org.bukkit.entity.Player;

public class InventoryView {

    private Player player;
    private String inventoryname;

    public InventoryView(Player player, String inventoryname) {
        this.player = player;
        this.inventoryname = inventoryname;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getInventoryName() {
        return inventoryname;
    }

    public void setInventoryName(String inventory) {
        this.inventoryname = inventory;
    }

}
