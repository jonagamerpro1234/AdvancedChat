package jss.advancedchat.manager;

import org.bukkit.entity.Player;

public class ChatManager {
    private String message;

    private final Player player;

    private String date;

    private String time;

    public ChatManager(String message, Player player) {
        this.message = message;
        this.player = player;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
