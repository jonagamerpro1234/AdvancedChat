package jss.advancedchat.utils;

import org.bukkit.configuration.file.FileConfiguration;

public interface FileHelper {
	
	public void create();
	
	public FileConfiguration getConfig();
	
	public void reloadConfig();
	
	public void saveConfig();
	
	public String getPath();
	
	
}
