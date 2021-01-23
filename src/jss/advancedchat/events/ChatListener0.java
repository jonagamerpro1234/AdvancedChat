package jss.advancedchat.events;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.test.PlayerManager;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Utils;
import net.md_5.bungee.api.chat.TextComponent;

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
		PlayerManager playerManager = new PlayerManager(plugin);
		FileConfiguration config = plugin.getConfigFile().getConfig();
		Player j = e.getPlayer();
		String path = "Settings.Chat-Format-Type";
		String message = e.getMessage();
		
		if(config.getString(path).equals("normal")) {
			e.setFormat("<"+j.getName()+"> " + message);
		}else if (config.getString(path).equals("custom")) {
			
			String format = config.getString("Custom-Format.Text");
			String pathType	= config.getString("Custom-Format.Type");
			List<String> hover = config.getStringList("Custom-Format.HoverEvent.Text");
			String hovermode = config.getString("Custom-Format.HoverEvent.Mode");
			String clickaction = config.getString("Custom-Format.");
			
			
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
	

	//@EventHandler
	public void test(AsyncPlayerChatEvent e) {
		PlayerManager manager = new PlayerManager(plugin);
		Player j = e.getPlayer();
		e.setCancelled(true);
		
		String message = e.getMessage();
		String name = j.getName();
		String text = Utils.combineText(name + " " +manager.getColor(j, message));
		
		text = text.replace("<name>", name).replace("<message>", message);
		
		if((j.isOp()) || (j.hasPermission("AdvancedChat.Chat.Color"))) {
			text = Utils.color(text);
		}
		
		
		TextComponent component = new TextComponent(text);
		
		Utils.sendAllPlayerBaseComponent(component);
	}
	
}
