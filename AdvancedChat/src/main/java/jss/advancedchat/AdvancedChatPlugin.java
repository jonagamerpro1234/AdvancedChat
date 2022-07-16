package jss.advancedchat;

import jss.advancedchat.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class AdvancedChatPlugin extends JavaPlugin {
	
	private final PluginDescriptionFile pluginDescriptionFile = getDescription();
	private final PluginManager pluginManager = Bukkit.getPluginManager();
	public final String name = pluginDescriptionFile.getName();
	public final String version = pluginDescriptionFile.getVersion();
	
	public PluginManager getPluginManager() {
		return pluginManager;
	}
	
	public void registerListeners(@NotNull Listener... listeners) {
		for(Listener listener : listeners) {
			getPluginManager().registerEvents(listener, this);
		}
	}
	
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public void createFolder(@NotNull String foldername) {
		if(foldername.isEmpty()) {
			Logger.warning("");
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
