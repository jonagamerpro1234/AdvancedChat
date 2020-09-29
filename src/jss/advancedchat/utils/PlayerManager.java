package jss.advancedchat.utils;

import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerManager {
	
	String name;
	Player player;
	UUID uuid;
	boolean mute;
	
	
	
	public PlayerManager(String name, Player player, UUID uuid, boolean mute) {
		this.name = name;
		this.player = player;
		this.uuid = uuid;
		this.mute = mute;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
