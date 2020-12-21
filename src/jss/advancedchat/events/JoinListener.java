package jss.advancedchat.events;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.PlayerDataFile;
import jss.advancedchat.utils.EventUtils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
	
	private AdvancedChat plugin;
	private EventUtils eventsUtils = new EventUtils(plugin);
	
	public JoinListener(AdvancedChat plugin) {
		this.plugin = plugin;
		eventsUtils.addEventList(this);
	}
	
	@EventHandler
	public void onJoinPlayer(PlayerJoinEvent e) {
		PlayerDataFile dataFile = plugin.getPlayerDataFile();
		FileConfiguration config = dataFile.getConfig();
		Player j = e.getPlayer();
		
		if(!config.contains(j.getName())) {
			config.set("Players-List."+j.getName()+".UUID", j.getUniqueId().toString());
		}		
	}
	
	@EventHandler
	public void onUpdatePlayer(PlayerJoinEvent e) {
		Player j = e.getPlayer();
		if ((!j.hasPermission("AdvancedChat.Update.Notify")) || (!j.isOp())) {
			return;
		}
	}
}
