package jss.advancedchat;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import jss.advancedchat.config.FileManager;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Logger.Level;
import jss.advancedchat.utils.interfaces.FileHelper;
import jss.advancedchat.utils.interfaces.FolderHelper;

public class InventoryFile extends FileManager implements FileHelper, FolderHelper{

	private AdvancedChat plugin;
	private Logger logger = new Logger(plugin);
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
        }catch(IOException e) {
        	logger.Log(Level.ERROR, "!!Error Load File!! &b[&e"+this.path+"&b]");
        	e.printStackTrace();
        }catch(InvalidConfigurationException e) {
        	logger.Log(Level.ERROR, "!!Error Load File!! &b[&e"+this.path+"&b]");
        	e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        if (this.config == null) {
            reloadConfig();
        }
        return this.config;
    }

    public void saveConfig() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
        	logger.Log(Level.ERROR, "!!Error Load File!! &b[&e"+this.path+"&b]");
        	e.printStackTrace();
        }
    }

    public void reloadConfig() {
        if (this.config == null) {
            this.file = new File(getDataFolder() + File.separator + folderpath, this.path);
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
        Reader defaultConfigStream;
        try {
            defaultConfigStream = new InputStreamReader(getResources(this.folderpath+ File.separator + this.path), "UTF8");
            if (defaultConfigStream != null) {
                YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultConfigStream);
                config.setDefaults(defaultConfig);
            }
        }catch(UnsupportedEncodingException e) {
        	logger.Log(Level.ERROR, "!!Error Load File!! &b[&e"+this.path+"&b]");
        	e.printStackTrace();
        }catch(NullPointerException e) {
        	logger.Log(Level.ERROR, "!!Error Load File!! &b[&e"+this.path+"&b]");
        	e.printStackTrace();
        }catch(IllegalArgumentException e) {
        	logger.Log(Level.ERROR, "!!Error Load File!! &b[&e"+this.path+"&b]");
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
