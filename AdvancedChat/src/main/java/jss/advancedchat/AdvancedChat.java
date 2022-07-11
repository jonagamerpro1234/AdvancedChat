package jss.advancedchat;

import java.sql.Connection;
import java.util.ArrayList;

import javax.annotation.Nonnull;

import org.bukkit.entity.Player;

import jss.advancedchat.commands.AdvancedChatCmd;
import jss.advancedchat.commands.ClearChatCmd;
import jss.advancedchat.commands.MsgCmd;
import jss.advancedchat.commands.MuteCmd;
import jss.advancedchat.commands.UnMuteCmd;
import jss.advancedchat.config.BadWordFile;
import jss.advancedchat.config.ConfigFile;
import jss.advancedchat.config.GroupFile;
import jss.advancedchat.config.InventoryDataFile;
import jss.advancedchat.config.MessageFile;
import jss.advancedchat.config.PreConfigLoader;
import jss.advancedchat.config.gui.ChannelGuiFile;
import jss.advancedchat.config.gui.ColorFile;
import jss.advancedchat.config.gui.GradientColorFile;
import jss.advancedchat.config.gui.PlayerGuiFile;
import jss.advancedchat.config.log.LogFile;
import jss.advancedchat.config.player.PlayerFile;
import jss.advancedchat.listeners.EventLoader;
import jss.advancedchat.listeners.JoinListener;
import jss.advancedchat.listeners.chat.ChatLogListener;
import jss.advancedchat.listeners.chat.CommandListener;
import jss.advancedchat.listeners.inventory.ColorInventoryListener;
import jss.advancedchat.listeners.inventory.ErrorInventoryListener;
import jss.advancedchat.listeners.inventory.GradientInventoryListener;
import jss.advancedchat.listeners.inventory.PlayerInventoryListener;
import jss.advancedchat.listeners.inventory.RainbowInventoryListener;
import jss.advancedchat.listeners.inventory.SettingsInventoryListener;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.storage.MySQL;
import jss.advancedchat.storage.MySQLConnection;
import jss.advancedchat.test.ChatListenerTest;
import jss.advancedchat.update.UpdateChecker;
import jss.advancedchat.update.UpdateSettings;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.inventory.InventoryView;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import jss.advancedchat.utils.Util;

public class AdvancedChat extends AdvancedChatPlugin {
	
	private static AdvancedChat instance;
	private PreConfigLoader preConfigLoad;
	public Logger logger = new Logger();
	private MySQL mySQL;
	public MySQLConnection connection = new MySQLConnection();
	public EventUtils eventUtils;
	public Metrics metrics;
	public HookManager HookManager;
	public ArrayList<InventoryView> inventoryView;
	private MessageFile messageFile = new MessageFile(this, "messages.yml");
	private ConfigFile configFile = new ConfigFile(this, "config.yml");
	private GroupFile groupFile = new GroupFile(this, "groups.yml");
	private BadWordFile badWordFile = new BadWordFile(this, "badword.yml");
	private ColorFile colorFile = new ColorFile(this, "color-gui.yml", "Gui");
	private PlayerGuiFile playerGuiFile = new PlayerGuiFile(this, "player-gui.yml", "Gui");
	private ChannelGuiFile channelGuiFile = new ChannelGuiFile(this, "channel-gui.yml", "Gui");
	private GradientColorFile gradientColorFile = new GradientColorFile(this, "gradient-gui.yml", "Gui");
	private InventoryDataFile inventoryDataFile = new InventoryDataFile(this, "inventory.data", "Data");
	private PlayerFile playerFile = new PlayerFile(this);
	public boolean uselegacyversion = false;
	public boolean uselatestversion = false;
	public boolean uselatestConfig = false;
	public static boolean debug = true;
	public String latestversion;

	private BukkitAudiences adventure;
	
	public BukkitAudiences getAdventure() {
		if(this.adventure == null) {
			throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
		}
		return this.adventure;
	}
	
	public void onLoad() {
		instance = this;
		Util.setTitle(version);
		if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
		this.eventUtils = new EventUtils(this);
		inventoryView = new ArrayList<>();
		preConfigLoad = new PreConfigLoader(this);
		HookManager = new HookManager(this);
		getConfigFile().saveDefaultConfig();
		getConfigFile().create();
		if(!getConfigFile().getConfig().getString("Settings.Config-Version").equals("2")) {
			uselatestConfig = true;
		}
		getMessageFile().saveDefault();
		getMessageFile().createFile();
		createFolder("Data");
		createFolder("Players");
		createFolder("Gui");
		createFolder("Log");
		preConfigLoad.loadConfig();
		preConfigLoad.loadMessage();
		Util.setEndLoad();
	}
	
	public void onEnable() {	
		
		this.adventure = BukkitAudiences.create(this);
		
		this.getMetric();
		Util.setEnabled(version);
				
		if(uselatestConfig) {
			Logger.warning("&e!Please update your config.yml!");
		}
		
		createAllFiles();
		preConfigLoad.loadGradientInv();
		preConfigLoad.loadColorInv();
		preConfigLoad.loadPlayerInv();
		HookManager.load();
		HookManager.loadProtocol();
		
		onCommands();
		onListeners();
		
		if (Settings.boolean_protocollib) {
			if (HookManager.isLoadProtocolLib()) {
				HookManager.InitPacketListening();
			} else {
				Logger.warning(Settings.message_depend_plugin+ " " + "&e[&bProtocolLib&e]");
			}
		}		
		
		new UpdateChecker(this, 83889).getUpdateVersionSpigot(version -> {
			latestversion = version;
			if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
				Logger.success("&a" + this.name + " is up to date!");
			} else {
				Logger.outline("&5<||" + Util.getLine("&5"));
				Logger.warning("&5<||" + "&b" + this.name + " is outdated!");
				Logger.warning("&5<||" + "&bNewest version: &a" + version);
				Logger.warning("&5<||" + "&bYour version: &d" + this.version);
				Logger.warning("&5<||" + "&bUpdate Here on Spigot: &e" + UpdateSettings.URL_PLUGIN[0]);
				Logger.warning("&5<||" + "&bUpdate Here on Songoda: &e" + UpdateSettings.URL_PLUGIN[1]);
				Logger.warning("&5<||" + "&bUpdate Here on GitHub: &e" + UpdateSettings.URL_PLUGIN[2]);
				Logger.outline("&5<||" + Util.getLine("&5"));
			}
		});

		LogFile logFile = new LogFile(this);
		logFile.create();

		
	}
	
	public void onDisable() {
		Util.setDisabled(version);
		if(this.adventure != null) {
			this.adventure.close();
			this.adventure = null;
		}
		
		metrics = null;
		uselegacyversion = false;
		uselatestConfig = false;
	}
	
	public void onCommands() {
		new AdvancedChatCmd(this);
		new ClearChatCmd(this);
		new MuteCmd(this);
		new UnMuteCmd(this);
		new MsgCmd();
	}
		
	public void onListeners() {
		registerListeners(
		new JoinListener(),
		new ChatListenerTest(),
		//new ChatListener(),
		new CommandListener(),
		new ChatLogListener(),
		new ColorInventoryListener(),
		new GradientInventoryListener(),
		new PlayerInventoryListener(),
		new SettingsInventoryListener(),
		new RainbowInventoryListener());
		new ErrorInventoryListener();
		EventLoader ev = new EventLoader();
		ev.runClearChat();
	}
	
	public void reloadAllFiles() {
		this.preConfigLoad.loadConfig();
		this.preConfigLoad.loadMessage();
		this.getConfigFile().reloadConfig();
		this.getPlayerGuiFile().reloadConfig();
		this.getColorFile().reloadConfig();
		this.getGradientColorFile().reloadConfig();
		this.getChannelGuiFile().reloadConfig();
		this.getInventoryDataFile().reloadConfig();
		this.getGroupFile().reloadConfig();
		this.getBadWordFile().reloadConfig();
		this.getMessageFile().reload();
	}
	
	public void createAllFiles() {
		inventoryDataFile.create();
		groupFile.saveDefault();
		groupFile.create();
		colorFile.create();
		gradientColorFile.create();
		playerGuiFile.create();
		channelGuiFile.create();
	}
	
	public void getMetric() {
		metrics = new Metrics(this, 8826);
	}
	
	public void addInventoryView(Player player, String inventoryname) {
		if (this.getInventoryView(player) == null) {
			this.inventoryView.add(new InventoryView(player, inventoryname));
		}
	}

	public void removeInvetoryView(Player player) {
		for (int i = 0; i < inventoryView.size(); i++) {
			if (((InventoryView) this.inventoryView.get(i)).getPlayer().getName().equals(player.getName())) {
				this.inventoryView.remove(i);
			}
		}
	}

	public InventoryView getInventoryView(Player player) {
		for (int i = 0; i < inventoryView.size(); i++) {
			if (((InventoryView) this.inventoryView.get(i)).getPlayer().getName().equals(player.getName())) {
				return (InventoryView) this.inventoryView.get(i);
			}
		}
		return null;
	}
	
	public PlayerFile getPlayerFile() {
		return playerFile;
	}
	

	
	public HookManager getHookManager() {
		return HookManager;
	}

	public boolean isDebug() {
		return true;
	}
	
	public static AdvancedChat get() {
		return instance;
	}
	

	public EventUtils getEventUtils() {
		return this.eventUtils;
	}
	
	public PreConfigLoader getPreConfigLoader() {
		return this.preConfigLoad;
	}
	
	public MessageFile getMessageFile() {
		return messageFile;
	}
	
	public GroupFile getGroupFile() {
		return groupFile;
	}
	
	public BadWordFile getBadWordFile() {
		return badWordFile;
	}
	
	public GradientColorFile getGradientColorFile() {
		return gradientColorFile;
	}
	
	public InventoryDataFile getInventoryDataFile() {
		return this.inventoryDataFile;
	}	

	public ConfigFile getConfigFile() {
		return configFile;
	}

	public ColorFile getColorFile() {
		return colorFile;
	}

	public PlayerGuiFile getPlayerGuiFile() {
		return playerGuiFile;
	}

	public ChannelGuiFile getChannelGuiFile() {
		return channelGuiFile;
	}

	public MySQL getMySQL() {
		return mySQL;
	}
	
	public Connection getConnection() {
		return connection.getConnetion();
	}
	
}
