package jss.advancedchat;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import jss.advancedchat.utils.Utils;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class TestEvents implements Listener{
	
	
	private AdvancedChat plugin;
	
	public TestEvents(AdvancedChat plugin) {
		this.plugin = plugin;
		
	}
	
	
	@EventHandler
	public void tes(PlayerJoinEvent e) {
		Player j = e.getPlayer();
		PlayerDataFile playerdata = plugin.getPlayerDataFile();
		FileConfiguration config = playerdata.getConfig();
		
		String path = "Players-Data.";
		
		config.createSection("Players-Data");
		config.set(path+j.getName()+".UUID", j.getUniqueId().toString());
		config.set(path+j.getName()+".Color-Chat", "NONE");
		config.set(path+j.getName()+".Mute", false);
		config.set(path+j.getName()+".Mute-Reason", "none");
		config.set(path+j.getName()+".Mute-Time", "none");
		//plugin.addPlayerList(j, j.getUniqueId(), false, "none");
		playerdata.saveConfig();
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void ChatFormat(AsyncPlayerChatEvent e) {
		FileConfiguration config = plugin.getConfig();
		Player j = e.getPlayer();
		//try {
			String path = "Settings.ChatFormat-Type";
			if(config.getString(path).equals("Default")) {
				e.setFormat("<"+j.getName()+">" + " " + e.getMessage());
			}else if(config.getString(path).equals("Custom")) {
				String format = config.getString("Custom-Format.Text");
				String pathtype = "Custom-Format.Type";
				format = replacePlaceholderAPI(j, format);
				format = getAllVars(j, format);
				format = format.replace("<msg>", e.getMessage());
				//format = format.replace("<name>", FormatChatHover(j, hovertext, hovermode, hovercolor));
				format = Utils.color(format);
				if(config.getString(pathtype).equals("Normal")) {
						e.setFormat(Utils.color(format.replace("<name>", j.getName()).replace("<msg>", e.getMessage())));					
				}else if(config.getString(pathtype).equals("Experimental")) {
						//e.setFormat(Utils.color(format.replace("<name>", j.getName()).replace("<msg>", e.getMessage())));
						/*e.setFormat(format.replace("<msg>", e.getMessage()));
						Reflection.sendIChatBaseComponent(plugin, j, format, hovertext, hovermode);*/
					
					String dformat = e.getFormat();
					TextComponent newformat = new TextComponent(dformat);
				
					HoverEvent hover = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utils.color("hola")).create());
					
					newformat.setHoverEvent(hover);
					newformat.setText(format);
					e.setCancelled(true);
					sendAllPlayer(newformat);
				}
			}else {
				e.setFormat(Utils.color(config.getString("Default-Format").replace("<name>", j.getName()).replace("<msg>", e.getMessage())));
			}
			
		/*}catch(NullPointerException ex) {	
		}	*/
	}
	
	public void sendAllPlayer(BaseComponent component) {
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.spigot().sendMessage(component);
		}
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
			//msg = msg.replace("<Server>", plugin.getServer().getServerName());
			msg = msg.replace("<Version>",  plugin.getServer().getBukkitVersion());
			msg = msg.replace("<World>", j.getWorld().getName());
			msg = msg.replace("<Player_Ip>", j.getAddress().getHostName());
			
			msg = msg.replace("<name>", j.getName());
			msg = msg.replace("<displayname>", j.getDisplayName());
			msg = msg.replace("<maxPlayer>", "" + plugin.getServer().getMaxPlayers());
			msg = msg.replace("<online>", "" + playersOnline);
			//msg = msg.replace("<server>", plugin.getServer().getServerName());
			msg = msg.replace("<version>",  plugin.getServer().getBukkitVersion());
			msg = msg.replace("<world>", j.getWorld().getName());
		}catch(Exception  e) {}
		return msg;
	}
}
