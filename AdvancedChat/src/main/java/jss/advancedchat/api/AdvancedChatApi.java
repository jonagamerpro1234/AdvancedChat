package jss.advancedchat.api;

import org.bukkit.entity.Player;

import jss.advancedchat.manager.GroupManager;
import jss.advancedchat.manager.PlayerManager;

/**
 * Here are the available methods to use the advancedchat api
 * @author jonagamerpro1234
 * @version 1.0
 */
public class AdvancedChatApi {
	
	/**
	 * With this method you can get different options of the player that are stored in a file with the name of the player
	 * 
	 * @param player the player required to access method options
	 * @return PlayerManager
	 * @since 1.0
	 */
	public PlayerManager getPlayerManager(Player player) {
		return new PlayerManager(player);
	}
	
	/**
	 * With this method, different parameters of the group system can be obtained.
	 * @return GroupManager
	 * @since 1.0
	 */
	public GroupManager getGroupManager() {
		return new GroupManager();
	}
	
}
