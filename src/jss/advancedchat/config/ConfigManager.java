package jss.advancedchat.config;

import java.io.File;
import java.util.HashMap;
import org.bukkit.configuration.file.YamlConfiguration;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.config.Files.FileName;
@SuppressWarnings("unused")
public class ConfigManager extends FileManager{
	

	private AdvancedChat plugin;
	private File file;
	private String[] folderNames = new String[]{"Data","Gui","Log"};
	private HashMap<String, YamlConfiguration> configs;
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
		return (YamlConfiguration) this.configs.get(Files.getName(name));
	}
	

	
}
