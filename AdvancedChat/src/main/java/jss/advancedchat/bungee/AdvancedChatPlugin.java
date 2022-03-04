package jss.advancedchat.bungee;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class AdvancedChatPlugin extends Plugin {

    public void registerCommand(Command...commands) {
        for(Command command : commands) {
        	getPluginManager().registerCommand(this, command);
        }
    }
    
    public void registerListener(Listener...listeners) {
        for(Listener listener : listeners) {
        	getPluginManager().registerListener(this, listener);
        }
    }

    public PluginManager getPluginManager() {
        return getServer().getPluginManager();
    }

    public ProxyServer getServer() {
        return ProxyServer.getInstance();
    }
	
}
