package jss.advancedchat.utils.inventory;

import org.bukkit.entity.Player;

public class InventoryView {
    private Player player;

    private final String inventory;

    public InventoryView(Player player, String inventory) {
        this.player = player;
        this.inventory = inventory;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getInventory() {
        return this.inventory;
    }

}
