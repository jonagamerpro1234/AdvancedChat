package jss.advancedchat;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

//import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import jss.advancedchat.utils.FileHelper;
import jss.advancedchat.utils.FileManager;

public class PlayerData extends FileManager implements FileHelper {
	
	private AdvancedChat plugin;
	private File file;
	private FileConfiguration config;
	private String path;

	public PlayerData(AdvancedChat plugin, String path) {
		super(plugin);
		this.plugin = plugin;
		this.file = null;
		this.config = null;
		this.path = path;
	}

	public void create(){
		this.file = new File(getDataFolder() + File.separator + "Data", this.path);
		if (!this.file.exists()) {
			getConfig().options().copyDefaults(true);
			saveConfig();
			/*try {
				this.file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
		/*this.config = new YamlConfiguration();
		try {
			this.config.load(this.file);
		}catch(IOException e) {
			e.printStackTrace();
		}catch(InvalidConfigurationException e) {
			e.printStackTrace();
		}*/
	}

	public FileConfiguration getConfig() {
		if (this.config == null) {
			reloadConfig();
		}
		return this.config;
	}

	public String getPath() {
		return this.path;
	}

	public void saveConfig() {
		try {
			this.config.save(this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reloadConfig() {
		if (this.config == null) {
			this.file = new File(getDataFolder() + File.separator + "Data", this.path);
		}
		this.config = YamlConfiguration.loadConfiguration(this.file);
		
		Reader defaultConfigStream;
		try {
			defaultConfigStream = new InputStreamReader(getResources("players.data"), "UTF8");
			if (this.file != null) {
				YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defaultConfigStream);
				this.config.setDefaults(defConfig);
			}
		}catch(UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		
		

	}

	public AdvancedChat getPlugin() {
		return plugin;
	}
}
