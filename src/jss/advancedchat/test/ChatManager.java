package jss.advancedchat.test;

import org.bukkit.entity.Player;

public class ChatManager {
	
	private String message;
	private Player player;
	
	public ChatManager(String message, Player player) {
		this.message = message;
		this.player = player;
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
	
}
