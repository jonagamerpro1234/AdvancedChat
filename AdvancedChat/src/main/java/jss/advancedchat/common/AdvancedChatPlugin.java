package jss.advancedchat.common;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AdvancedChatPlugin extends JavaPlugin {
	
	private PluginDescriptionFile jss = getDescription();
	public String name = this.jss.getName();
	public String version = this.jss.getVersion();
	
	public void registerEvent(Listener listener) {
		this.getPluginManager().registerEvents(listener, this);
	}
	
	public PluginManager getPluginManager() {
		return Bukkit.getPluginManager();
	}
	
    public void createVoidFolder(String name) {
        File folder = new File(getDataFolder(), name);
        if (!folder.exists()) {
            try {
                folder.mkdir();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }

    public void createFolderAndFile(String namefolder, String namefile) {
        File folder = new File(getDataFolder(), namefolder);
        if (!folder.exists()) {
            try {
                folder.mkdir();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }

        File file = new File(folder, namefile);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
