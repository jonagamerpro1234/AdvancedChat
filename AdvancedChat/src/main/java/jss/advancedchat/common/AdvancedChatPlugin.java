package jss.advancedchat.common;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import jss.advancedchat.utils.Logger;

public class AdvancedChatPlugin extends JavaPlugin {
	
	private PluginDescriptionFile pluginDescriptionFile = getDescription();
	private PluginManager pluginManager = Bukkit.getPluginManager();
	public String name = pluginDescriptionFile.getName();
	public String version = pluginDescriptionFile.getVersion();
	
	public PluginManager getPluginManager() {
		return pluginManager;
	}
	
	public void registerListeners(Listener... listeners) {
		for(Listener listener : listeners) {
			getPluginManager().registerEvents(listener, this);
		}
	}
	
	public void createFolder(String foldername) {
		if(foldername.isEmpty()) {
			Logger.error("");
			return;
		}
		
		File folder = new File(getDataFolder(), foldername);
        if (!folder.exists()) {
            try {
                folder.mkdir();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
	}
    
}
