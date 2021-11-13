package jss.advancedchat.api;

import org.bukkit.entity.Player;

/**
 * @author jonagamerpro1234
 * @version 1.0 
 */
public interface IPlayerData {
	
	/**
	 * 
	 * @param player
	 * @return
	 */
	String getUUID(Player player);
	
	/**
	 * 
	 * @param player
	 * @return string
	 */
	String getColor(Player player);
	
	/**
	 * 
	 * @param player
	 * @return string
	 */
	String getFirstGradient(Player player);
	
	/**
	 * 
	 * @param player
	 * @return string
	 */
	String getSecondGradient(Player player);
	
	/**
	 * 
	 * @param player
	 * @return boolean
	 */
	boolean isMute(Player player);
	
	/**
	 * 
	 * @param player
	 * @param uuid
	 */
	void setUUID(Player player);
	
	/**
	 * 
	 * @param player
	 * @param color
	 */
	void setColor(Player player, String color);
	
	/**
	 * 
	 * @param player
	 * @param firstgradient
	 */
	void setFirstGradient(Player player, String firstgradient);
	
	/**
	 * 
	 * @param player
	 * @param secondgradient
	 */
	void setSecondGradient(Player player, String secondgradient);
	
	/**
	 * 
	 * @param player
	 * @param value
	 */
	void setMute(Player player, boolean value);
	
}
