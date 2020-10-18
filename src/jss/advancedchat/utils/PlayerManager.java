package jss.advancedchat.utils;

import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerManager {
	
	Player player;
	UUID uuid;
	boolean mute;
	String color;
	
	public PlayerManager(Player player, UUID uuid, boolean mute, String color) {
		this.player = player;
		this.uuid = uuid;
		this.mute = mute;
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	public boolean isMute() {
		return mute;
	}
	
	public void setMute(boolean mute) {
		this.mute = mute;
	}
	
}
