package jss.advancedchat;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
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
	public Metrics metrics;
	public String latestversion;
	public boolean placeholders = false;
	private CommandSender c= Bukkit.getConsoleSender();
	private boolean debug = false;
	private FileManager filemanager = new FileManager(this);
	private PlayerDataFile playerdata = new  PlayerDataFile(this, "Data", "players.data");
	private ConfigFile configfile = new ConfigFile(this, "config.yml");
	public String nmsversion;
	public boolean uselegacyversion = false;
	
	public void onEnable() {
		Utils.getEnable(version);;
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
		configfile.saveDefaultConfig();
        configfile.create();
		filemanager.createVoidFolder("Data");
		playerdata.create();
        metrics = new Metrics(this);
		setupCommands();
		setupEvents();
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
	
	public PlayerDataFile getPlayerDataFile() {
		return this.playerdata;
	}
	
	public ConfigFile getConfigfile() {
		return configfile;
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
