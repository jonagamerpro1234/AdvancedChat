package jss.advancedchat.utils.inventory;

import org.bukkit.entity.Player;

public class InventoryView {

    private Player player;
    private String invName;

    public InventoryView(Player player, String invName) {
        this.player = player;
        this.invName = invName;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getInventoryName() {
        return invName;
    }

    public void setInventoryName(String inventory) {
        this.invName = inventory;
    }

}
