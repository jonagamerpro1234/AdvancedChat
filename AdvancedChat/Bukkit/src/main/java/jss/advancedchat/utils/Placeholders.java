package jss.advancedchat.utils;

import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.manager.PlayerManager;

public class Placeholders {
	
	private AdvancedChat plugin;
	
	public Placeholders(AdvancedChat plugin) {
		this.plugin = plugin;
	}
	
	private String getRange() {
		return Settings.int_range_chat + "";
	}

	public String placeholders(Player player, String str) {
		PlayerManager playerManager = new PlayerManager(player);
		str = str.replace("<range>", getRange());
		str = str.replace("<name>", player.getName());
		str = str.replace("<displayname>", player.getDisplayName());
		str = str.replace("<channel>", playerManager.getChannel(player));
		str = Utils.getOnlinePlayers(str);
		
		if(HookManager.getInstance().getLuckPermsHook().isEnabled()) {
			str = str.replace("<luckperms_group>", this.getLuckPerms(str, player));
		}
		
		return str;
	}
	
	public String getLuckPerms(String str, Player player) {
		LuckPermsHook luckPermsHook = HookManager.getInstance().getLuckPermsHook();
		str = luckPermsHook.getGroup(player);
		return str;
	}
	
	public AdvancedChat getPlugin() {
		return plugin;
	}
	
}
