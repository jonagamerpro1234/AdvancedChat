package jss.advancedchat.manager;

import org.bukkit.entity.Player;

public class ChatManager {
	
	private String prefix;
    private String message;
    private Player player;
    private String date;
    private String time;

    public ChatManager(String date, String time, String message, Player player) {
        this.message = message;
        this.player = player;
    }
        
    public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
