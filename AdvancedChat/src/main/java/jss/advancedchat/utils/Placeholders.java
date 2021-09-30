package jss.advancedchat.utils;

import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
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
		PlayerManager m = new PlayerManager(plugin);
		str = str.replace("<range>", getRange());
		str = str.replace("<name>", player.getName());
		str = str.replace("<displayname>", player.getDisplayName());
		str = str.replace("<channel>", m.getChannel(player));
		//str = str.replace("<mute_state>", m.getStateMute(player));
		str = Utils.getOnlinePlayers(str);
		return str;
	}

	
}
