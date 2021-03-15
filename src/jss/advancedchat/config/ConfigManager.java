package jss.advancedchat.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.config.Files.FileName;
import jss.advancedchat.utils.FileManager;
import jss.advancedchat.utils.interfaces.FileHelper;

@SuppressWarnings("unused")
public class ConfigManager extends FileManager{
	

	private AdvancedChat plugin;
	private File file;
	private String[] folderNames = new String[]{"Data","Gui","Log"};
	private HashMap<String, YamlConfiguration> configs;
	private Files files = new Files();
	private boolean removeExist;
	
	public ConfigManager(AdvancedChat plugin) {
		super(plugin);
		this.plugin = plugin;
	}
	
	/*public void load() {
		this.removeExist = this.configs != null;
		if(!removeExist) {
			this.configs = new HashMap<>();
		}
		
		for(int i = 0; i < files.getName(FileName.valueOf(configs.))) {
			
		}
	}*/
	
	public YamlConfiguration getConfig(FileName name) {
		return (YamlConfiguration) this.configs.get(files.getName(name));
	}
	

	
}
