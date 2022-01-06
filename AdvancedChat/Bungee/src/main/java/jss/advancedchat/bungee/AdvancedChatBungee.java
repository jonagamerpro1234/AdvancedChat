package jss.advancedchat.bungee;

import jss.advancedchat.bungee.utils.AdvancedChatPlugin;
import jss.advancedchat.bungee.utils.LoggerBunge;
import jss.advancedchat.bungee.utils.LoggerBunge.Level;
import jss.advancedchat.common.update.UpdateSettings;
import jss.advancedchat.bungee.utils.UpdateChecker;
import jss.advancedchat.bungee.utils.UtilsBunge;
import net.md_5.bungee.api.plugin.PluginDescription;

public class AdvancedChatBungee extends AdvancedChatPlugin {

    private PluginDescription jss = getDescription();
    public String name = this.jss.getName();
    public String version = this.jss.getVersion();
    private ConfigFile configFile = new ConfigFile(this);
    private LoggerBunge logger = new LoggerBunge(this);
        
    public void onEnable() {
        UtilsBunge.setBungeeEnabled(version);
        
        if(!getDataFolder().exists()) { 
        	getDataFolder().mkdirs();
        }
        configFile.create();
        
        
        getProxy().registerChannel("ac:messagechannel");
        
        new UpdateChecker(this).getUpdateVersionBungee(version -> {
			if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
				logger.Log(Level.SUCCESS, "&a" + this.name + " is up to date!");
			} else {
				logger.Log(Level.OUTLINE, "&5<||" + UtilsBunge.getLine("&5"));
				logger.Log(Level.WARNING, "&5<||" + "&b" + this.name + " is outdated!");
				logger.Log(Level.WARNING, "&5<||" + "&bNewest version: &a" + version);
				logger.Log(Level.WARNING, "&5<||" + "&bYour version: &d" + this.version);
				logger.Log(Level.WARNING, "&5<||" + "&bUpdate Here on Spigot: &e" + UpdateSettings.URL_PLUGIN[0]);
				logger.Log(Level.WARNING, "&5<||" + "&bUpdate Here on Songoda: &e" + UpdateSettings.URL_PLUGIN[1]);
				logger.Log(Level.WARNING, "&5<||" + "&bUpdate Here on GitHub: &e" + UpdateSettings.URL_PLUGIN[2]);
				logger.Log(Level.OUTLINE, "&5<||" + UtilsBunge.getLine("&5"));
			}
        });
        
    }

    public void onDisable() {
    	UtilsBunge.setBungeeDisabled(version);
    }
}
