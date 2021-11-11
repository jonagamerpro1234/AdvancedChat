package jss.advancedchat.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Logger.Level;
import jss.advancedchat.utils.file.FileManager;

public class PlayersFile extends FileManager {

	private AdvancedChat plugin;
	
	private File file;
	private FileConfiguration config;
	private Logger logger = new Logger(plugin);
	
	public PlayersFile(AdvancedChat plugin) {
		super(plugin);
		this.plugin = plugin;
	}
	
	public void create(String name) {
		this.file = new File(this.plugin.getDataFolder() + File.separator + "turrets", name);
		if (!this.file.exists()) {
			this.plugin.saveResource("Players" + File.separator + name, false);
		}

		this.config = new YamlConfiguration();

		try {
			this.config.load(this.file);
		} catch (IOException var2) {
			var2.printStackTrace();
		} catch (InvalidConfigurationException var3) {
			var3.printStackTrace();
		}
	}

	public String getFolderPath() {	
		return "Players";
	}
		
	public FileConfiguration getConfig() {
		return this.config;
	}

	public void saveConfig() {
        try {
            this.config.save(file);
        } catch (IOException e) {
        	Logger.error("!!Error Load File!! &b[&e Players Files &b]");
        	e.printStackTrace();
        }		
	}

	   public void reloadConfig(String path) {
	        if (this.config == null) {
	            this.file = new File(getDataFolder(), path);
	        }
	        this.config = YamlConfiguration.loadConfiguration(this.file);
	        Reader defaultConfigStream;
	        try {
	            defaultConfigStream = new InputStreamReader(getResources(path), "UTF8");
	            BufferedReader in = new BufferedReader(defaultConfigStream);
	            if (defaultConfigStream != null) {
	                YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(in);
	                config.setDefaults(defaultConfig);
	            }
	        }catch(UnsupportedEncodingException e) {
	        	logger.Log(Level.ERROR, "!!Error Load File!! &b[&e"+path+"&b]");
	        	e.printStackTrace();
	        }catch(NullPointerException e) {
	        	logger.Log(Level.ERROR, "!!Error Load File!! &b[&e"+path+"&b]");
	        	e.printStackTrace();
	        }catch(IllegalArgumentException e) {
	        	logger.Log(Level.ERROR, "!!Error Load File!! &b[&e"+path+"&b]");
	        	e.printStackTrace();
	        }

	    }
	   
	    public AdvancedChat getPlugin() {
	        return plugin;
	    }

	    public void resetConfig(String path) {
	        if (this.file == null) {
	            this.file = new File(getDataFolder(), path);
	        }
	        if (!this.file.exists()) {
	            saveResources(path, true);
	        }
	    }

	    public boolean isFileExists(String path) {
	    	this.file = new File(getDataFolder(), path);
	    	return this.file.exists();
	    }
	
	
}
