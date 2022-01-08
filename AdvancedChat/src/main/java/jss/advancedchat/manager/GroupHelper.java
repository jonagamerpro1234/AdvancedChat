package jss.advancedchat.manager;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.chat.Json;
import jss.advancedchat.hooks.DiscordSRVHook;
import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.utils.Logger;

public abstract class GroupHelper {
	
	
	@SuppressWarnings("unused")
	public static void useLuckPermsGroup(GroupManager groupManager, LuckPermsHook luckPermsHook, DiscordSRVHook discordSRVHook, PlayerManager playerManager, FileConfiguration config, Player player, String message) {
		if(!luckPermsHook.isEnabled()) {
			
			String group = luckPermsHook.getGroup(player);
			String format = groupManager.getFormat(group);
			String mode = groupManager.getClickMode(group);
			String command = groupManager.getClickCommand(group);
			String url = groupManager.getClickUrl(group);
			String suggest = groupManager.getClickSuggestCommand(group);
			
			List<String> Hover = groupManager.getHover(group);
			
			boolean isClick = groupManager.isClick(group);
			boolean isHover = groupManager.isHover(group);
			
			Json json = new Json(player, format, message);
			
			
			
		}else {
			Logger.error("&cthe luckperms could not be found to activate the group system");
			Logger.warning("&eplease check that luckperms is active or inside your plugins folder");
		}
	}
}
