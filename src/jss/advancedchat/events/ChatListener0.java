package jss.advancedchat.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Utils;

public class ChatListener0 implements Listener {
	
	private AdvancedChat plugin;
	private EventUtils eventUtils = new EventUtils(plugin);
	
	public ChatListener0(AdvancedChat plugin) {
		this.plugin = plugin;
		eventUtils.getEventManager().registerEvents(this, plugin);
	}
	
	@SuppressWarnings("unused")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e ) {
		FileConfiguration config = plugin.getConfigFile().getConfig();
		Player j = e.getPlayer();
		String path = "Settings.Chat-Format-Type";
		String message = e.getMessage();
		
		//Custom
		
		//Group
		
		if(config.getString(path).equals("normal")) {
			e.setFormat("<"+j.getName()+"> " + message);
		}else if (config.getString(path).equals("custom")) {
			
			String format = config.getString("");
			String pathType	= config.getString("");
			String hover = config.getString("");
			String hovermode = config.getString("");
			String clickaction = config.getString("");
			
			
			if(pathType.equals("normal")) {
				
			}else if(pathType.equals("hover")) {
				
			}else if(pathType.equals("click")) {
				
			}else if(pathType.equals("double")) {
				
			}else if(pathType.equals("experimental")) {
				
			}else if(pathType.equals("all")) {
				
			}else {
				
			}
			
			
		}else if (config.getString(path).equals("group")) {
			
			for(String key : config.getConfigurationSection("Groups").getKeys(false)) {
				String pathType = config.getString("");
				
				
				
			}
			
			
		}else {
			e.setFormat(Utils.color(config.getString("Default-Format").replace("<name>", j.getName()).replace("<msg>", e.getMessage())));
		}
		
	}
	

	
	
}
