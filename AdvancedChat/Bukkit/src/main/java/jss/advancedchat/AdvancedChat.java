package jss.advancedchat;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.commands.AdvancedChatCmd;
import jss.advancedchat.commands.ClearChatCmd;
import jss.advancedchat.commands.MsgCmd;
import jss.advancedchat.commands.MuteCmd;
import jss.advancedchat.commands.UnMuteCmd;
import jss.advancedchat.common.update.UpdateSettings;
import jss.advancedchat.config.BadWordFile;
import jss.advancedchat.config.ChannelGuiFile;
import jss.advancedchat.config.ChatDataFile;
import jss.advancedchat.config.ChatLogFile;
import jss.advancedchat.config.ColorFile;
import jss.advancedchat.config.CommandFile;
import jss.advancedchat.config.CommandLogFile;
import jss.advancedchat.config.ConfigFile;
import jss.advancedchat.config.GradientColorFile;
import jss.advancedchat.config.GroupFile;
import jss.advancedchat.config.InventoryDataFile;
import jss.advancedchat.config.PlayerDataFile;
import jss.advancedchat.config.PlayerGuiFile;
import jss.advancedchat.config.PreConfigLoader;
import jss.advancedchat.listeners.ChatListener;
import jss.advancedchat.listeners.CommandListener;
import jss.advancedchat.listeners.EventLoader;
import jss.advancedchat.listeners.InventoryListener;
import jss.advancedchat.listeners.JoinListener;
import jss.advancedchat.manager.ChatManager;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.storage.MySQLConnection;
import jss.advancedchat.utils.AdvancedChatPlugin;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Logger.Level;
import jss.advancedchat.utils.inventory.InventoryView;
import jss.advancedchat.utils.UpdateChecker;
import jss.advancedchat.utils.Utils;

public class AdvancedChat extends AdvancedChatPlugin {

	private ConfigFile configfile = new ConfigFile(this, "config.yml");
	private GroupFile groupFile = new GroupFile(this, "groups.yml");
	private BadWordFile badWordFile = new BadWordFile(this, "badword.yml");
	@SuppressWarnings("unused")
	private CommandFile commandFile = new CommandFile(this, "custom-command.yml");
	private ColorFile colorFile = new ColorFile(this, "color-gui.yml", "Gui");
	private PlayerGuiFile playerGuiFile = new PlayerGuiFile(this, "player-gui.yml", "Gui");
	private ChannelGuiFile channelGuiFile = new ChannelGuiFile(this, "channel-gui.yml", "Gui");
	private GradientColorFile gradientColorFile = new GradientColorFile(this, "gradient-gui.yml", "Gui");
	private ChatLogFile chatLogFile = new ChatLogFile(this, "chat.yml", "Log");
	private CommandLogFile commandLogFile = new CommandLogFile(this, "command.yml", "Log");
	private ChatDataFile chatDataFile = new ChatDataFile(this, "chat-log.data", "Data");
	private PlayerDataFile playerdata = new PlayerDataFile(this, "players.data", "Data");
	private InventoryDataFile inventoryDataFile = new InventoryDataFile(this, "inventory.data", "Data");
	private static AdvancedChat instance;
	private PreConfigLoader preConfigLoad = new PreConfigLoader(this);
	public Logger logger = new Logger(this);
	private MySQLConnection connection;
	private EventUtils eventUtils = new EventUtils(this);
	public Metrics metrics;
	private HookManager HookManager = new HookManager(this);
	public String latestversion;
	public String nmsversion;
	private boolean BungeeMode;
	public boolean uselegacyversion = false;
	public boolean uselatestversion = false;
	private static boolean debug;
	private ArrayList<InventoryView> inventoryView;
	private ArrayList<ChatManager> chatManagers;
	
	public void onEnable() {
		Utils.setEnabled(version);
		nmsversion = Bukkit.getServer().getClass().getPackage().getName();
		nmsversion = nmsversion.substring(nmsversion.lastIndexOf(".") + 1);
		if (nmsversion.equalsIgnoreCase("v1_8_R3")) {
			uselegacyversion = true;
			if (uselegacyversion) {
				Utils.sendColorMessage(eventUtils.getConsoleSender(), Utils.getPrefix() + "&5<|| &c* &7Use " + nmsversion + " &cdisabled &7method &b1.16 &7and &e1.17");
			}
		} else if (nmsversion.equalsIgnoreCase("v1_16_R1") || nmsversion.equalsIgnoreCase("v1_16_R2") || nmsversion.equalsIgnoreCase("v1_16_R3")) {
			uselatestversion = true;
			Utils.sendColorMessage(eventUtils.getConsoleSender(), Utils.getPrefix() + "&5<|| &c* &7Use " + nmsversion + " &aenabled &7method &b1.16");
		}
		instance = this;
		checkBungeeMode();
		//commandFile.create();
		playerdata.create();
		inventoryDataFile.create();
		chatLogFile.create();
		commandLogFile.create();
		colorFile.create();
		gradientColorFile.create();
		playerGuiFile.create();
		chatDataFile.create();
		channelGuiFile.create();
		HookManager.loadProtocol();
		if (Settings.boolean_protocollib) {
			if (HookManager.isLoadProtocolLib()) {
				HookManager.InitPacketListening();
			} else {
				Logger.warning(getConfigFile().getConfig().getString("AdvancedChat.Depend-Plugin") + " " + "&e[&bProtocolLib&e]");
			}
		}
		metrics = new Metrics(this, 8826);
		this.inventoryView = new ArrayList<>();
		this.chatManagers = new ArrayList<>();
		loadCommands();
		loadEvents();

		Utils.setEndLoad();
		new UpdateChecker(this, 83889).getUpdateVersionSpigot(version -> {
			if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
				logger.Log(Level.SUCCESS, "&a" + this.name + " is up to date!");
			} else {
				logger.Log(Level.OUTLINE, "&5<||" + Utils.getLine("&5"));
				logger.Log(Level.WARNING, "&5<||" + "&b" + this.name + " is outdated!");
				logger.Log(Level.WARNING, "&5<||" + "&bNewest version: &a" + version);
				logger.Log(Level.WARNING, "&5<||" + "&bYour version: &d" + UpdateSettings.VERSION);
				logger.Log(Level.WARNING, "&5<||" + "&bUpdate Here on Spigot: &e" + UpdateSettings.URL_PLUGIN[0]);
				logger.Log(Level.WARNING, "&5<||" + "&bUpdate Here on Songoda: &e" + UpdateSettings.URL_PLUGIN[1]);
				logger.Log(Level.WARNING, "&5<||" + "&bUpdate Here on GitHub: &e" + UpdateSettings.URL_PLUGIN[2]);
				logger.Log(Level.OUTLINE, "&5<||" + Utils.getLine("&5"));
			}
		});
	}
	
	public void onDisable() {
		Utils.setDisabled(version);
		metrics = null;
		uselegacyversion = false;
	}

	public void onLoad() {
		Utils.setLoad(version);
		if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
		
		Utils.setLineLoad("&eCheck DataFolder Exist!");
		this.eventUtils = new EventUtils(this);
		Utils.setLineLoad("&eLoading EventUtils");
		Utils.setTitleLoad("&bLoading Files");
		configfile.saveDefaultConfig();
		configfile.create();
		debug = configfile.getConfig().getBoolean("Settings.Debug");
		Utils.setLineLoad("&eLoad Config.yml");
		preConfigLoad.load();
		Utils.setLineLoad("&eLoad Pre Config");
		createVoidFolder("Gui");
		Utils.setLineLoad("&eLoad Gui Folder");
		createVoidFolder("Data");
		Utils.setLineLoad("&eLoad Data Folder");
		createVoidFolder("Log");
		Utils.setLineLoad("&eLoad Log Folder");
		Utils.setEndLoad();
	}

	public void loadCommands() {
		new AdvancedChatCmd(this);
		new ClearChatCmd(this);
		new MuteCmd(this);
		new UnMuteCmd(this);
		new MsgCmd(this);
	}
		
	public void loadEvents() {
		eventUtils.initEvent(
		new JoinListener(this),
		new InventoryListener(this),
		new ChatListener(this),
		new CommandListener(this));
		EventLoader e = new EventLoader(this);
		e.runClearChat();
	}
	
	public void reloadAllFiles() {
		this.preConfigLoad.load();
		this.getConfigFile().reloadConfig();
		this.getChatDataFile().reloadConfig();
		this.getChatLogFile().reloadConfig();
		this.getPlayerDataFile().reloadConfig();
		this.getPlayerGuiFile().reloadConfig();
		this.getColorFile().reloadConfig();
		this.getGradientColorFile().reloadConfig();
		this.getChannelGuiFile().reloadConfig();
		this.getCommandLogFile().reloadConfig();
		this.getInventoryDataFile().reloadConfig();
		this.getGroupFile().reloadConfig();
		this.getBadWordFile().reloadConfig();
	}
	
	private void checkBungeeMode() {
		BungeeMode = false;
		try {
			
			try {
				BungeeMode = getServer().spigot().getConfig().getBoolean("settings.bungeecord");
			}catch(NoSuchMethodError e) {
				Logger.warning("Check the spigot.yml file");
				e.printStackTrace();
			}
			
            if (!BungeeMode && new File("spigot.yml").exists()) {
                BungeeMode = YamlConfiguration.loadConfiguration(new File("spigot.yml")).getBoolean("settings.bungeecord");
            }
			
		}catch(Exception e) {}
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

	public PlayerDataFile getPlayerDataFile() {
		return playerdata;
	}

	public ConfigFile getConfigFile() {
		return configfile;
	}

	public ColorFile getColorFile() {
		return colorFile;
	}

	public PlayerGuiFile getPlayerGuiFile() {
		return playerGuiFile;
	}

	public ChatDataFile getChatDataFile() {
		return chatDataFile;
	}

	public ChatLogFile getChatLogFile() {
		return chatLogFile;
	}

	public CommandLogFile getCommandLogFile() {
		return commandLogFile;
	}

	public ChannelGuiFile getChannelGuiFile() {
		return channelGuiFile;
	}

	public HookManager getHookManager() {
		return HookManager;
	}

	public Connection getConnection() {
		return this.connection.getConnetion();
	}

	public boolean isDebug() {
		return debug;
	}

	public static AdvancedChat getInstance() {
		return instance;
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


	public ArrayList<ChatManager> getChatManagers() {
		return this.chatManagers;
	}

	public void setChatManagers(ChatManager chat) {
		this.chatManagers.add(chat);
	}

	public EventUtils getEventUtils() {
		return this.eventUtils;
	}

	public boolean isBungeeMode() {
		return this.BungeeMode;
	}

	public PreConfigLoader getPreConfigLoader() {
		return this.preConfigLoad;
	}
}
