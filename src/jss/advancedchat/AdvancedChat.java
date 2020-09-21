package jss.advancedchat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import jss.advancedchat.commands.AdvancedChatCmd;
import jss.advancedchat.commands.ClearChatCmd;
import jss.advancedchat.events.ChatListener;
import jss.advancedchat.events.JoinListener;
import jss.advancedchat.utils.EventsUtils;
import jss.advancedchat.utils.PlayerManager;
import jss.advancedchat.utils.UpdateChecker;
import jss.advancedchat.utils.Utils;

public class AdvancedChat extends JavaPlugin{
	
	PluginDescriptionFile jss = getDescription();
	public String name = this.jss.getName();
	public String version = this.jss.getVersion();
	public FileConfiguration config;
	public File configfile;
	public Metrics metrics;
	public String latestversion;
	public boolean placeholders = false;
	private CommandSender c= Bukkit.getConsoleSender();
	public List<PlayerManager> pm = new ArrayList<PlayerManager>();
	private boolean debug = false;
	
	public void onEnable() {
		Utils.getEnable(Utils.getPrefixConsole(), version);
		saveDefaultConfig();
		metrics = new Metrics(this);
		setupConfig();
		setupCommands();
		setupEvents();
		SetupSoftDepends();
		UpdateChecker update = new UpdateChecker(this);
		update.Update(c);
	}
	
	public void onDisable() {
		Utils.getEnable(Utils.getPrefixConsole(), version);
		this.placeholders = false;
		metrics = null;
	}
	public void setupConfig() {
		File config = new File(getDataFolder(), "config.yml");
		if (!config.exists()) {
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
		}
	}
	public void setupCommands() {
		new AdvancedChatCmd(this);
		new ClearChatCmd(this);
	}
	
	public void setupEvents() {
		new JoinListener(this);
		new ChatListener(this);	
		EventsUtils.runAutoClearAction(this);
	}
	
	public boolean getPlaceHolderState() {
		return this.placeholders;
	}
	
	public boolean setupPlaceHolderAPI(){
		if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			this.placeholders = true;
		}
		return this.placeholders;
	}
	public void SetupSoftDepends() {
		if(setupPlaceHolderAPI()) {
			Utils.sendColorMessage(c, "&e[&d"+ name +"&e]&5 <|============================================|>");
			Utils.sendColorMessage(c, Utils.getPrefixConsole() + "&5<| &ePlaceHolderAPI:&b" + " " + placeholders);
			Utils.sendColorMessage(c, Utils.getPrefixConsole() + "&5<| &eVars PlaceHolderAPI:&a true");
			Utils.sendColorMessage(c, "&e[&d"+ name +"&e]&5 <|============================================|>");
		}else {
			Utils.sendColorMessage(c, "&e[&d"+ name +"&e]&5 <|============================================|>");
			Utils.sendColorMessage(c, Utils.getPrefixConsole() + "&5<| &ePlaceHolderAPI:&b" + " " + placeholders);
			Utils.sendColorMessage(c, Utils.getPrefixConsole() + "&5<| &eVars PlaceHolderAPI:&c false");
			Utils.sendColorMessage(c, "&e[&d"+ name +"&e]&5 <|============================================|>");
		}		
	}

	public List<PlayerManager> getPlayerManager() {
		return pm;
	}

	public void setPlayerManager(List<PlayerManager> pm) {
		this.pm = pm;
	}

	public boolean isDebug() {
		return debug;
	}	
}
