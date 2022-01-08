package jss.advancedchat.bungee;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class AdvancedChatPlugin extends Plugin {

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
