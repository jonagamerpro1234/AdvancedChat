package jss.advancedchat.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.interfaces.IFileHelper;
import jss.advancedchat.interfaces.IFolderHelper;
import jss.advancedchat.utils.file.FileManager;

public class PlayerDataFile extends FileManager implements IFileHelper, IFolderHelper {
	
	private AdvancedChat plugin;
	private File file;
	private FileConfiguration config;
	private String path;
	private String folderpath;

	public PlayerDataFile(AdvancedChat plugin, String path, String folderpath) {
		super(plugin);
		this.plugin = plugin;
		this.path = path;
		this.folderpath = folderpath;
		this.config = null;
		this.file = null;
	}

	public String getFolderPath() {
		return this.folderpath;
	}
	
	public void create() {
		this.file = new File(getDataFolder() + File.separator + this.folderpath, this.path);
		if(!this.file.exists()) {
			getConfig().options().copyDefaults(true);
			saveConfig();
		}
	}

	public FileConfiguration getConfig() {
		if(this.config == null) {
			reloadConfig();
		}
		return this.config;
	}

	public void saveConfig() {
		try {
			this.config.save(this.file);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	public void reloadConfig() {
		if(this.config == null) {
			this.file = new File(getDataFolder() + File.separator + this.folderpath, this.path);
		}
		this.config = YamlConfiguration.loadConfiguration(this.file);
		Reader defaultConfigStream;
		try {
			defaultConfigStream = new InputStreamReader(getResources(this.path), "UTF8");
			if(defaultConfigStream != null) {
				YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultConfigStream);
				config.setDefaults(defaultConfig);
			}
		}catch(UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		
	}

	public String getPath() {
		return this.path;
	}

	public AdvancedChat getPlugin() {
		return plugin;
	}

	public void saveDefaultConfig() {
		if(this.file == null) {
			this.file = new File(getDataFolder() + File.separator + this.folderpath, this.path);
		}
		if(!this.file.exists()) {
			saveResources(this.path, false);
		}
	}

	public void resetConfig() {
		if(this.file == null) {
			this.file = new File(getDataFolder() + File.separator + this.folderpath, this.path);
		}
		if(!this.file.exists()) {
			saveResources(this.path, true);
		}
	}

	public boolean isFileExists() {
		return false;
	}
}
