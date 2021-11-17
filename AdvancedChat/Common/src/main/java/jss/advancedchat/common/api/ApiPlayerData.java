package jss.advancedchat.common.api;

import java.util.HashMap;

import org.bukkit.entity.Player;

import jss.advancedchat.api.IPlayerData;

public class ApiPlayerData implements IPlayerData{
	
	private HashMap<Player, String> uuidMap = new HashMap<Player, String>();
	private HashMap<Player, String> colorMap = new HashMap<Player, String>();
	private HashMap<Player, String> firstGradientMap = new HashMap<Player, String>();
	private HashMap<Player, String> secondGradientMap = new HashMap<Player, String>();
	private HashMap<Player, Boolean> muteMap = new HashMap<Player, Boolean>();
	
	@Override
	public String getUUID(Player player) {
		if(uuidMap.containsKey(player)) {
			return uuidMap.get(player);
		}
		return null;
	}

	@Override
	public String getColor(Player player) {
		if(colorMap.containsKey(player)) {
			return colorMap.get(player);
		}
		return null;
	}

	@Override
	public String getFirstGradient(Player player) {
		if(firstGradientMap.containsKey(player)) {
			return firstGradientMap.get(player);
		}
		return null;
	}

	@Override
	public String getSecondGradient(Player player) {
		if(secondGradientMap.containsKey(player)) {
			return secondGradientMap.get(player);
		}
		return null;
	}

	@Override
	public boolean isMute(Player player) {
		boolean a = false;
		if(muteMap.containsKey(player)) {
			a = muteMap.get(player);
		}
		return a;
	}

	@Override
	public void setUUID(Player player) {
		if(!uuidMap.containsKey(player)) {
			uuidMap.put(player, player.getUniqueId().toString());
		}
	}

	@Override
	public void setColor(Player player, String color) {
		if(!colorMap.containsKey(player)) {
			colorMap.put(player, color);
		}
	}

	@Override
	public void setFirstGradient(Player player, String firstgradient) {
		if(!firstGradientMap.containsKey(player)) {
			firstGradientMap.put(player, firstgradient);
		}
	}

	@Override
	public void setSecondGradient(Player player, String secondgradient) {
		if(!secondGradientMap.containsKey(player)) {
			secondGradientMap.put(player, secondgradient);
		}
	}

	@Override
	public void setMute(Player player, boolean value) {
		if(!muteMap.containsKey(player)) {
			muteMap.put(player, value);
		}
	}
	
}
