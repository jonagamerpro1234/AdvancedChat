package jss.advancedchat.bungee;

import java.io.File;
import java.io.IOException;

import jss.advancedchat.bungee.commands.AdvancedChatCmd;
import jss.advancedchat.bungee.utils.Utils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginDescription;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;


public class AdvancedChat extends Plugin {

    private PluginDescription jss = getDescription();
    public String name = this.jss.getName();
    public String version = this.jss.getVersion();
    private File file;
    private Configuration config;

    public void onEnable() {
        Utils.setBungeeEnabled(version);
        setupConfigFile();
        setupCommands();
        setupEvents();
        

    }

    public void onDisable() {
    	Utils.setBungeeDisabled(version);
    }
    
    public void setupConfigFile() {
    	this.file = new File(Utils.getDataFolder(), "bungee-config.yml");
    	try {
        	if(!this.file.exists()) {
        		this.file.createNewFile();
        	}
        	config = load(this.file);
        	Utils.sendBungeeColorMessage(ProxyServer.getInstance().getConsole(), Utils.getPrefix() + "&aLoad Config File");
        	save(this.config, this.file);
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    		
    }
    
    public Configuration load(File file) throws IOException {
    	return ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
    }
    public void save(Configuration config, File file) {
    	try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public Configuration getConfig() {
    	return this.config;
    }
    
    public void setupCommands() {
        registerCommand(new AdvancedChatCmd());
    }

    public void setupEvents() {

    }

    public void registerCommand(Command command) {
        getPluginManager().registerCommand(this, command);
    }

    public PluginManager getPluginManager() {
        return getServer().getPluginManager();
    }

    public ProxyServer getServer() {
        return ProxyServer.getInstance();
    }


}
