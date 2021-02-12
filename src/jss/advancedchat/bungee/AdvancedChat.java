package jss.advancedchat.bungee;

import jss.advancedchat.bungee.commands.AdvancedChatCmd;
import jss.advancedchat.bungee.utils.Utils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginDescription;
import net.md_5.bungee.api.plugin.PluginManager;

public class AdvancedChat extends Plugin{

	private PluginDescription jss = getDescription();
	public String name = this.jss.getName();
	public String version = this.jss.getVersion();
	
	public void onEnable() {
		Utils.setBungeeEnabled(version);
		setupCommands();
	}
	
	public void onDisable() {
		
	}
	
	public void setupCommands() {
		new AdvancedChatCmd(this);
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
