package jss.advancedchat.events;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.EventsUtils;
import me.clip.placeholderapi.PlaceholderAPI;

public class ChatListener implements Listener {
	
	private AdvancedChat plugin;
	
	public ChatListener(AdvancedChat plugin) {
		this.plugin = plugin;
		EventsUtils.getManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void ChatFormat(AsyncPlayerChatEvent e) {
		FileConfiguration config = plugin.getConfig();
		Player j = e.getPlayer();
		try {
			String path = "Settings.ChatFormat-Type";
			if(config.getString(path).equals("Default")) {
				e.setFormat("<"+j.getName()+">" + " " + e.getMessage());
			}else if(config.getString(path).equals("Custom")) {
				String format = config.getString("Custom-Format");
				format = replacePlaceholderAPI(j, format);
				format = getAllVars(j, format);
				e.setFormat(Utils.color(format.replace("<name>", j.getName()).replace("<msg>", e.getMessage())));
			}else if(config.getString(path).equals("Group")) {
				for(String key : config.getConfigurationSection("Groups").getKeys(false)) {
					
					String format = config.getString("Groups."+key+".Format");
					String perm = config.getString("Groups."+key+".Permission");
					format = replacePlaceholderAPI(j, format);
					format = getAllVars(j, format);
					if(j.hasPermission(perm)) {
						e.setFormat(Utils.color(format.replace("<name>", j.getName()).replace("<msg>", e.getMessage())));					
					}
				}
			}else {
				e.setFormat(Utils.color(config.getString("Default-Format").replace("<name>", j.getName()).replace("<msg>", e.getMessage())));
			}
			
		}catch(NullPointerException ex) {	
		}	
	}
	
	public String replacePlaceholderAPI(Player p, String message){
	    String holders = message;
	    if ((plugin.placeholders) && (PlaceholderAPI.containsPlaceholders(holders))) {
	      holders = PlaceholderAPI.setPlaceholders(p, holders);
	    }
	    return holders;
	}
	
	public String getActionHoverType(String arg) {
		
		String temp = arg;
		
		if(temp.equalsIgnoreCase("text")) {
			return "SHOW_TEXT";
 		}
		if(temp.equalsIgnoreCase("item")) {
			return "SHOW_ITEM";
		}
		if(temp.equalsIgnoreCase("entity")) {
			return "SHOW_ENTITY";
		}
		
		return null;
	}
	
	public String getActionClickType(String arg) {
		
		String temp = arg;
		
		if(temp.equalsIgnoreCase("url")) {
			return "OPER_URL";
 		}
		if(temp.equalsIgnoreCase("cmd")) {
			return "RUN_COMMAND";
		}
		return null;
	}
	
	public String getAllVars(Player j, String msg) {	
		int playersOnline = 0;
	    try
	    {
	      if (Bukkit.class.getMethod("getOnlinePlayers", new Class[0]).getReturnType() == Collection.class) {
	        playersOnline = ((Collection<?>)Bukkit.class.getMethod("getOnlinePlayers", new Class[0]).invoke(null, new Object[0])).size();
	      } else {
	        playersOnline = ((Player[])Bukkit.class.getMethod("getOnlinePlayers", new Class[0]).invoke(null, new Object[0])).length;
	      }
	    }
	    catch (NoSuchMethodException ex) {}catch (InvocationTargetException ex) {}catch (IllegalAccessException ex) {}		
		try {
			msg = msg.replace("<Name>", j.getName());
			msg = msg.replace("<Displayname>", j.getDisplayName());
			msg = msg.replace("<MaxPlayer>", "" + plugin.getServer().getMaxPlayers());
			msg = msg.replace("<Online>", "" + playersOnline);
			msg = msg.replace("<Server>", plugin.getServer().getServerName());
			msg = msg.replace("<Version>",  plugin.getServer().getBukkitVersion());
			msg = msg.replace("<World>", j.getWorld().getName());
			msg = msg.replace("<Player_Ip>", j.getAddress().getHostName());
			
			msg = msg.replace("<name>", j.getName());
			msg = msg.replace("<displayname>", j.getDisplayName());
			msg = msg.replace("<maxPlayer>", "" + plugin.getServer().getMaxPlayers());
			msg = msg.replace("<online>", "" + playersOnline);
			msg = msg.replace("<server>", plugin.getServer().getServerName());
			msg = msg.replace("<version>",  plugin.getServer().getBukkitVersion());
			msg = msg.replace("<world>", j.getWorld().getName());
		}catch(Exception  e) {}
		return msg;
	}
}
