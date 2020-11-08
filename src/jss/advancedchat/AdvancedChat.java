package jss.advancedchat;

import java.io.File;
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
import jss.advancedchat.utils.FileManager;
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
	private boolean debug = false;
	private FileManager filemanager;
	private PlayerData playerdata = new  PlayerData(this, "Player-Data.yml");
	public String nmsversion;
	public boolean uselegacyversion = false;
	
	public void onEnable() {
		Utils.getEnable(version);
		saveDefaultConfig();
        nmsversion = Bukkit.getServer().getClass().getPackage().getName();
        nmsversion = nmsversion.substring(nmsversion.lastIndexOf(".") + 1);
        if (nmsversion.equalsIgnoreCase("v1_8_R3")) { 
        	uselegacyversion = true;
        	if(uselegacyversion == true) {
        		Utils.sendColorMessage(c, Utils.getPrefixConsole() + " " + "&7Use " + nmsversion + " &cdisabled &7method &b1.16");
        	}
        }else if(nmsversion.equalsIgnoreCase("v1_16_R2")){
        	Utils.sendColorMessage(c, Utils.getPrefixConsole() + " " + "&7Use " + nmsversion + " &aenabled &7method &b1.16");
        }
		metrics = new Metrics(this);
		setupConfig();
		setupCommands();
		setupEvents();
		filemanager = new FileManager(this);
		filemanager.createVoidFolder("Data");
		playerdata.create();
		SetupSoftDepends();
		UpdateChecker update = new UpdateChecker(this);
		update.Update(c);
	}
	
	public void onDisable() {
		Utils.getDisable(version);
		this.placeholders = false;
		metrics = null;
		uselegacyversion = false;
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
		//new MuteCmd(this);
	}
	
	public void setupEvents() {
		new JoinListener(this);
		new ChatListener(this);
		
		EventsUtils.runAutoClearAction(this);
	}
	
	public PlayerData getPlayerData() {
		return this.playerdata;
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
			Utils.sendColorMessage(c, "&e[&d"+ name +"&e] &5 <|============================================|>");
			Utils.sendColorMessage(c, Utils.getPrefixConsole() + " &5<| &ePlaceHolderAPI:&b" + " " + placeholders);
			Utils.sendColorMessage(c, Utils.getPrefixConsole() + " &5<| &eVars PlaceHolderAPI:&a true");
			Utils.sendColorMessage(c, "&e[&d"+ name +"&e] &5 <|============================================|>");
		}else {
			Utils.sendColorMessage(c, "&e[&d"+ name +"&e] &5 <|============================================|>");
			Utils.sendColorMessage(c, Utils.getPrefixConsole() + " &5<| &ePlaceHolderAPI:&b" + " " + placeholders);
			Utils.sendColorMessage(c, Utils.getPrefixConsole() + " &5<| &eVars PlaceHolderAPI:&c false");
			Utils.sendColorMessage(c, "&e[&d"+ name +"&e] &5 <|============================================|>");
		}		
	}
	
	public boolean isDebug() {
		return debug;
	}	
}
