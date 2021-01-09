package jss.advancedchat.events;


import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.ConfigFile;
import jss.advancedchat.test.PlayerManager;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.EventUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatListener implements Listener {
	
	private AdvancedChat plugin;
	public Map<String,Long> delaywords = new HashMap<String,Long>();
	private EventUtils eventsUtils = new EventUtils(plugin);
	
	public ChatListener(AdvancedChat plugin) {
		this.plugin = plugin;
		eventsUtils.getEventManager().registerEvents(this, plugin);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onChatFormat(AsyncPlayerChatEvent e) {
		PlayerManager manager = new PlayerManager(plugin);
		ConfigFile configFile = plugin.getConfigfile();
		FileConfiguration config = configFile.getConfig();
		Player j = e.getPlayer();
		try {
			String path = "Settings.ChatFormat-Type";
			if(config.getString(path).equals("default")) {
				e.setFormat("<"+j.getName()+">" + " " + e.getMessage());
			}else if(config.getString(path).equals("custom")) {
				String format = config.getString("Custom-Format.Text");
				String exformat = config.getString("Custom-Format.Experimental-Text");
				String pathtype = "Custom-Format.Type";
				String hovertext = config.getString("Custom-Format.HoverEvent.Text");
				String hovermode = config.getString("Custom-Format.HoverEvent.Mode");
				format = Utils.getVar(format, j);
				format = format.replace("<msg>", e.getMessage());
				exformat = Utils.getVar(exformat, j);
				hovertext = Utils.getVar(hovertext, j);
				if((j.isOp()) || (j.hasPermission("AdvancedChat.Chat.Color"))) {
					format = Utils.hexcolor(format);
				}
				if(config.getString(pathtype).equals("normal")) {
					e.setFormat(format.replace("<name>", j.getName()).replace("<msg>", e.getMessage()));
				}else if(config.getString(pathtype).equals("experimental")) {
					String msg = e.getMessage();
					e.setFormat(Utils.color(exformat + "&r" + manager.getColor(j,msg)));
				}else if(config.getString(pathtype).equals("hover")) {
					TextComponent tc = new TextComponent(e.getFormat());
					HoverEvent hover = new HoverEvent(HoverEvent.Action.valueOf(getActionHoverType(hovermode)), new ComponentBuilder(Utils.color(hovertext)).create());
					tc.setHoverEvent(hover);
					tc.setText(format);
					e.setCancelled(true);
					sendAllPlayer(tc);

				}
			}else if(config.getString(path).equals("group")) {
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
					if(!(j.hasPermission("AdvancedChat.Chat.Color")) || !(j.isOp())) {
							format = Utils.hexcolor(format);
					}
					if(config.getString(pathtype).equals("normal")) {
						if(j.hasPermission(perm)) {
							e.setFormat(format.replace("<name>", j.getName()).replace("<msg>", e.getMessage()));
							
						}
					}else if(config.getString(pathtype).equals("experimental")) {
						if(j.hasPermission(perm)) {
							e.setFormat(format.replace("<name>", j.getName()).replace("<msg>", e.getMessage()));
							String msg = e.getMessage();
							
							e.setMessage(Utils.color(manager.getColor(j,msg)));	
						}
					}else if(config.getString(pathtype).equals("hover")) {
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

	//@EventHandler //1.5
	public void onChatFilter(AsyncPlayerChatEvent e) {
		Player j = e.getPlayer();
		FileConfiguration config = plugin.getConfigfile().getConfig();
		
		@SuppressWarnings("unused")
		String path = "Filter-Chat.Enabled";
		List<String> list = config.getStringList("Filter-Chat.BadWords");
		String censorship = config.getString("Filter-Chat.Form-Of-Censorship");
		String msg = config.getString("Filter-Chat.Message");
		String usemsg = config.getString("Filter-Chat.Use-Custom-Msg");
		String message = e.getMessage();
		for(int i = 0; i < list.size(); i++) {
			if(usemsg.equals("true")) {
				e.setCancelled(true);
				Utils.sendColorMessage(j, msg);
			}else if(usemsg.equals("false")){
				if(message.toLowerCase().contains(list.get(i))) {
					String a = "";
					for(int c = 0; c < list.size(); c++) {
						a = a +censorship;
					}
					
					message = message.replace(list.get(i), a);
					
				}
			}
			
		}
		e.setMessage(message);

	}
	
	@EventHandler
	public void onChatMute(AsyncPlayerChatEvent e) {
		FileConfiguration config = plugin.getPlayerDataFile().getConfig();
		FileConfiguration cconfig = plugin.getConfigfile().getConfig();
		Player j = e.getPlayer();
		for(String key :config.getConfigurationSection("Players").getKeys(false)) {
			if(key.contains(j.getName())) {
				String mute = config.getString("Players."+key+".Mute");
				if((j.isOp()) || (j.hasPermission("AdvancedChat.Chat.Bypass"))) {
					/////////////////////////////////////////////////////////
				}else {
					if(mute.equals("true")) {
						Utils.sendColorMessage(j, cconfig.getString("AdvancedChat.Alert-Mute").replace("<name>", j.getName()));
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
	
	@Deprecated
	public String FormatChatHover(Player player, String hovertext, String hovermode, String hovercolor) {
		TextComponent msg = new TextComponent();
		msg.setText(player.getName());
		msg.setColor(Utils.fixColor(hovercolor));
		return null;
	}
	
	
	public String replacePlaceholderAPI(Player p, String message){
	    String holders = message;
	    if ((plugin.placeholder) && (PlaceholderAPI.containsPlaceholders(holders))) {
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
			msg = msg.replace("<Version>",  plugin.getServer().getBukkitVersion());
			msg = msg.replace("<World>", j.getWorld().getName());
			msg = msg.replace("<Player_Ip>", j.getAddress().getHostName());
			
			msg = msg.replace("<name>", j.getName());
			msg = msg.replace("<displayname>", j.getDisplayName());
			msg = msg.replace("<maxPlayer>", "" + plugin.getServer().getMaxPlayers());
			msg = msg.replace("<online>", "" + playersOnline);
			msg = msg.replace("<version>",  plugin.getServer().getBukkitVersion());
			msg = msg.replace("<world>", j.getWorld().getName());
		}catch(Exception  e) {}
		return msg;
	}
}
