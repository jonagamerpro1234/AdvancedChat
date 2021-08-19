package jss.advancedchat.utils;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AdvancedChatPlugin extends JavaPlugin {
	
	public void registerEvent(Listener listener) {
		this.getPluginManager().registerEvents(listener, this);
	}
	
	public PluginManager getPluginManager() {
		return Bukkit.getPluginManager();
	}
	
}
