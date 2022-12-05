package jss.advancedchat.utils;

import org.bukkit.entity.Player;

public class PlayerManagerUtils {
    private Player player;

    private Player player2;

    public PlayerManagerUtils(Player player, Player player2) {
        this.player = player;
        this.player2 = player2;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer2() {
        return this.player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
