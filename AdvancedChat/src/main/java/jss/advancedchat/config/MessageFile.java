package jss.advancedchat.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.file.FileManager;

public class MessageFile extends FileManager{
	
	private String path;
	private File file;
	private FileConfiguration config;
	
	public MessageFile(AdvancedChat plugin, String path) {
		super(plugin);
		this.path = path;
		this.file = null;
		this.config = null;
	}
	
	public void createFile() {
		file = new File(getDataFolder(), path);
		if(!file.exists()) {
			get().options().copyDefaults(true);
			save();
		}
	}
	
	public FileConfiguration get() {
		if(config == null) {
			this.reload();
		}
		return config;
	}
	
	public void reload() {
		if(config == null) {
			file = new File(getDataFolder(), path);
		}
		config = YamlConfiguration.loadConfiguration(file);
		Reader reader;
		try {
			reader = new InputStreamReader(getResources(path), "UTF8");
			BufferedReader bufferedReader = new BufferedReader(reader);
			if(reader != null) {
				YamlConfiguration configuration = YamlConfiguration.loadConfiguration(bufferedReader);
				config.setDefaults(configuration);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			config.save(file);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    public void saveDefault() {
        if (this.file == null) {
            this.file = new File(getDataFolder(), this.path);
        }
        if (!this.file.exists()) {
            saveResources(this.path, false);
        }
    }
}
