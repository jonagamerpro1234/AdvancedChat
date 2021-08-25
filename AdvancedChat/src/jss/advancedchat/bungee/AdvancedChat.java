package jss.advancedchat.bungee;

import jss.advancedchat.bungee.utils.AdvancedChatPlugin;
import jss.advancedchat.bungee.utils.Utils;
import net.md_5.bungee.api.plugin.PluginDescription;

public class AdvancedChat extends AdvancedChatPlugin {

    private PluginDescription jss = getDescription();
    public String name = this.jss.getName();
    public String version = this.jss.getVersion();
    private ConfigFile configFile = new ConfigFile(this);

    public void onEnable() {
        Utils.setBungeeEnabled(version);
        
        if(!getDataFolder().exists()) { 
        	getDataFolder().mkdirs();
        }
        configFile.create();
    }

    public void onDisable() {
    	Utils.setBungeeDisabled(version);
    }
}
