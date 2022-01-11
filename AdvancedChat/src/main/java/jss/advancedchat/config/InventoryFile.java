package jss.advancedchat.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.file.FileManager;

public class InventoryFile extends FileManager {

	private AdvancedChat plugin;

	private File file;
	private FileConfiguration config;
	private String path;
	private String folderpath;
	
	public InventoryFile(AdvancedChat plugin, String path) {
		super(plugin);
		this.plugin = plugin;
		this.file = null;
		this.config = null;
		this.path = path;
		this.folderpath = "Gui";
	}
	
    public void create() {
        this.file = new File(getDataFolder() + File.separator + folderpath, this.path);
        if (!this.file.exists()) {
        	saveResources(this.folderpath + File.separator + this.path, false);
        }
        
        this.config = new YamlConfiguration();
        
        try {
        	this.config.load(this.file);
        }catch(IOException | InvalidConfigurationException e) {
        	e.printStackTrace();
        }
    }

    public FileConfiguration get() {
        if (this.config == null) {
            this.reload();
        }
        return this.config;
    }

    public void save() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }

    public void reload() {
        if (this.config == null) {
            this.file = new File(getDataFolder() + File.separator + folderpath, this.path);
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
        Reader defaultConfigStream;
        try {
            defaultConfigStream = new InputStreamReader(getResources(this.folderpath+ File.separator + this.path), "UTF8");
            BufferedReader in = new BufferedReader(defaultConfigStream);
            if (defaultConfigStream != null) {
                YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(in);
                config.setDefaults(defaultConfig);
            }
        }catch(Exception e) {
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
        if (this.file == null) {
            this.file = new File(getDataFolder() + File.separator + this.folderpath, this.path);
        }
        if (!this.file.exists()) {
            saveResources(this.path, false);
        }
    }

    public void resetConfig() {
        if (this.file == null) {
            this.file = new File(getDataFolder() + File.separator + this.folderpath, this.path);
        }
        if (!this.file.exists()) {
            saveResources(this.path, true);
        }
    }

    public boolean isFileExists() {
    	this.file = new File(getDataFolder() + File.separator + this.folderpath, this.path);
    	return this.file.exists();
    }
    
    public String getFolderPath() {
    	return this.folderpath;
    }
}
