package jss.advancedchat.config;

import java.io.File;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.config.FileList.FileName;
public class ConfigManager extends FileManager{
	

	private AdvancedChat plugin;
	@SuppressWarnings("unused")
	private File file;
	private HashMap<String, YamlConfiguration> configs;
	@SuppressWarnings("unused")
	private FileConfiguration config , players, colorgui, playergui;
	//private boolean removeExist;
	
	public ConfigManager(AdvancedChat plugin) {
		super(plugin);
		this.plugin = plugin;
		this.file = null;
	}
	
	public void create() {
		if(!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
		
		this.file = new File(getDataFolder(), "config.yml");
		this.file = new File(getDataFolder() + getFolderData(), "players.yml");
		this.file = new File(getDataFolder() + getFolderGui(), "color-gui.yml");
		this.file = new File(getDataFolder() + getFolderGui(), "player-gui.yml");
		this.file = new File(getDataFolder() + getFolderLog() , "chat-log.yml");
		
	}
	
	public YamlConfiguration getConfig(FileName name) {
		return (YamlConfiguration) this.configs.get(FileList.getName(name));
	}
	
	public FileConfiguration getConfig(String name) {
		if(name.equalsIgnoreCase("config")) {
			if(config == null) {
				reloadConfig("config.yml");
			}
			return config;
		}
		
		if(name.equalsIgnoreCase("config")) {
			if(config == null) {
				reloadConfig("players.yml");
			}
			return players;
		}
		if(name.equalsIgnoreCase("config")) {
			if(config == null) {
				reloadConfig("config.yml");
			}
			return config;
		}
		
		return null;
	}
	
	public void save(String filename) {
		
	}
	
	public void reloadConfig(String filename) {
		
	}
	
	public void reloadAllConfig() {
		
	}
	
	public AdvancedChat getPlugin() {
		return this.plugin;
	}
	
	public String getFolderData() {
		return File.separator + "Data";
	}
	
	public String getFolderGui() {
		return File.separator + "Gui";
	}
	
	public String getFolderLog() {
		return File.separator + "Log";
	}
}
