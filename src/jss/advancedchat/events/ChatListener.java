	package jss.advancedchat.events;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitScheduler;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.PlayerData;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.EventsUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

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
				String format = config.getString("Custom-Format.Text");
				String pathtype = "Custom-Format.Type";
				String hovertext = config.getString("Custom-Format.HoverEvent.Text");
				String hovermode = config.getString("Custom-Format.HoverEvent.Mode");
				format = replacePlaceholderAPI(j, format);
				format = getAllVars(j, format);
				format = format.replace("<msg>", e.getMessage());	
				hovertext = replacePlaceholderAPI(j, hovertext);
				hovertext = getAllVars(j, hovertext);
				//format = format.replace("<name>", FormatChatHover(j, hovertext, hovermode, hovercolor));
				format = Utils.color(format);
				if(config.getString(pathtype).equals("Normal")) {
						e.setFormat(Utils.color(format.replace("<name>", j.getName()).replace("<msg>", e.getMessage())));					
				}else if(config.getString(pathtype).equals("Experimental")) {
				TextComponent tc = new TextComponent(e.getFormat());
				
				HoverEvent hover = new HoverEvent(HoverEvent.Action.valueOf(getActionHoverType(hovermode)), new ComponentBuilder(Utils.color(hovertext)).create());
				tc.setHoverEvent(hover);
				tc.setText(format);
				e.setCancelled(true);
				sendAllPlayer(tc);
				}
			}else if(config.getString(path).equals("Group")) {
				for(String key : config.getConfigurationSection("Groups").getKeys(false)) {
					String pathtype = config.getString("Groups."+key+".Type");
					String format = config.getString("Groups."+key+".Format");
					String perm = config.getString("Groups."+key+".Permission");
					String hovertext = config.getString("Groups."+key+"HoverEvent.Text");
					String hovermode = config.getString("Groups."+key+"HoverEvent.Mode");
					hovertext = replacePlaceholderAPI(j, hovertext);
					hovertext = getAllVars(j, hovertext);
					format = replacePlaceholderAPI(j, format);
					format = getAllVars(j, format);
					format = format.replace("<msg>", e.getMessage());
					if(config.getString(pathtype).equals("Normal")) {
						if(j.hasPermission(perm)) {
							e.setFormat(Utils.color(format.replace("<name>", j.getName()).replace("<msg>", e.getMessage())));					
						}
					}else if(config.getString(pathtype).equals("Experimental")) {
						if(j.hasPermission(perm)) {}
						TextComponent tc = new TextComponent(e.getFormat());
						HoverEvent hover = new HoverEvent(HoverEvent.Action.valueOf(getActionHoverType(hovermode)), new ComponentBuilder(Utils.color(hovertext)).create());
						tc.setHoverEvent(hover);
						tc.setText(format);
						e.setCancelled(true);
						sendAllPlayer(tc);
					}
				}
			}else {
				e.setFormat(Utils.color(config.getString("Default-Format").replace("<name>", j.getName()).replace("<msg>", e.getMessage())));
			}
			
		}catch(NullPointerException ex) {	}	
	}
	
	@EventHandler
	public void DenyWordChat(AsyncPlayerChatEvent e) {
		Player j = e.getPlayer();
		FileConfiguration config = plugin.getConfig();
		try {
			String path = "Filter-Chat.";
			List<String> list = config.getStringList(path+"BadWords");
			String censorship = config.getString(path+"Form-Of-Censorship");
			String msg = config.getString(path+"Message");
			String usemsg = config.getString(path+"Use-Custom-Msg");
			int time = config.getInt(path+"Delay");
			
			
			
			if(config.getString(path+"Enabled").equals("true")) {
				
				if(usemsg.equals("true")) {
					for(int i = 0; i < list.size(); i++) {
						
						BukkitScheduler scheduler = plugin.getServer().getScheduler();
						scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
							@Override
							public void run() {
									
							}
						}, 20L * time);
					}
				}else if(usemsg.equals("false")){
					for(int i = 0; i < list.size(); i++) {
						
					}
				}

			}

			
		}catch(NullPointerException ex) {
				
		}
		
	}
	
	@EventHandler
	public void MutePlayer(AsyncPlayerChatEvent e) {
		PlayerData playerdata = plugin.getPlayerData();
		FileConfiguration config = playerdata.getConfig();
		Player j = e.getPlayer();
		
		for(String key : config.getConfigurationSection("Players-Data").getKeys(false)) {
			String path = "Players-Data."+key+".Mute";
			for(String s : plugin.mute) {
				if(s.contains(j.getName())) {
					if(config.getString(path).equals("true")) {
						e.setCancelled(true);
					}
				}
			}
		}
	}
	
	
	public void sendAllPlayer(BaseComponent component) {
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.spigot().sendMessage(component);
		}
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
	
	public String FormatChatHover(Player player, String hovertext, String hovermode, String hovercolor) {
		TextComponent msg = new TextComponent();
		msg.setText(player.getName());
		msg.setColor(ChatColor.valueOf(hovercolor));
		//msg.setHoverEvent(new HoverEvent(HoverEvent.Action.valueOf(getActionHoverType(hovermode)) , new ComponentBuilder(hovertext).create()));
		return null;
	}
	
	
	public String replacePlaceholderAPI(Player p, String message){
	    String holders = message;
	    if ((plugin.placeholders) && (PlaceholderAPI.containsPlaceholders(holders))) {
	      holders = PlaceholderAPI.setPlaceholders(p, holders);
	    }
	    return holders;
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
