package jss.advancedchat;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.api.AdvancedChatApi;
import jss.advancedchat.commands.AdvancedChatCmd;
import jss.advancedchat.commands.ClearChatCmd;
import jss.advancedchat.commands.MsgCmd;
import jss.advancedchat.commands.MuteCmd;
import jss.advancedchat.commands.UnMuteCmd;
import jss.advancedchat.common.update.UpdateSettings;
import jss.advancedchat.config.BadWordFile;
import jss.advancedchat.config.ChatDataFile;
import jss.advancedchat.config.ChatLogFile;
import jss.advancedchat.config.CommandFile;
import jss.advancedchat.config.CommandLogFile;
import jss.advancedchat.config.ConfigFile;
import jss.advancedchat.config.GroupFile;
import jss.advancedchat.config.InventoryDataFile;
import jss.advancedchat.config.MessageFile;
import jss.advancedchat.config.PreConfigLoader;
import jss.advancedchat.config.gui.ChannelGuiFile;
import jss.advancedchat.config.gui.ColorFile;
import jss.advancedchat.config.gui.GradientColorFile;
import jss.advancedchat.config.gui.PlayerGuiFile;
import jss.advancedchat.config.player.PlayerDataFile;
import jss.advancedchat.config.player.PlayerFile;
import jss.advancedchat.config.player.PlayerManagerFile;
import jss.advancedchat.listeners.EventLoader;
import jss.advancedchat.listeners.JoinListener;
import jss.advancedchat.listeners.chat.ChatLogListener;
import jss.advancedchat.listeners.chat.CommandListener;
import jss.advancedchat.listeners.inventory.ColorInventoryListener;
import jss.advancedchat.listeners.inventory.GradientInventoryListener;
import jss.advancedchat.listeners.inventory.PlayerInventoryListener;
import jss.advancedchat.listeners.inventory.RainbowInventoryListener;
import jss.advancedchat.listeners.inventory.SettingsInventoryListener;
import jss.advancedchat.manager.ChatManager;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.storage.MySQLConnection;
import jss.advancedchat.test.ChatListenerTest;
import jss.advancedchat.test.TestCommand;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.inventory.InventoryView;
import jss.advancedchat.utils.UpdateChecker;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.file.FileManager;

public class AdvancedChat extends AdvancedChatPlugin {
	
	private static AdvancedChat instance;
	private PreConfigLoader preConfigLoad;
	public Logger logger = new Logger();
	public MySQLConnection connection;
	public EventUtils eventUtils;
	public Metrics metrics;
	public HookManager HookManager;
	public FileManager fileManager = new FileManager(this);
	public ArrayList<InventoryView> inventoryView;
	public ArrayList<ChatManager> chatManagers;
	private MessageFile messageFile = new MessageFile(this, "messages.yml");
	private ConfigFile configFile = new ConfigFile(this, "config.yml");
	private GroupFile groupFile = new GroupFile(this, "groups.yml");
	private BadWordFile badWordFile = new BadWordFile(this, "badword.yml");
	private CommandFile commandFile = new CommandFile(this, "custom-command.yml");
	private ColorFile colorFile = new ColorFile(this, "color-gui.yml", "Gui");
	private PlayerGuiFile playerGuiFile = new PlayerGuiFile(this, "player-gui.yml", "Gui");
	private ChannelGuiFile channelGuiFile = new ChannelGuiFile(this, "channel-gui.yml", "Gui");
	private GradientColorFile gradientColorFile = new GradientColorFile(this, "gradient-gui.yml", "Gui");
	private ChatLogFile chatLogFile = new ChatLogFile(this, "chat.yml", "Log");
	private CommandLogFile commandLogFile = new CommandLogFile(this, "command.yml", "Log");
	private ChatDataFile chatDataFile = new ChatDataFile(this, "chat-log.data", "Data");
	private PlayerDataFile playerDataFile = new PlayerDataFile(this, "players.data", "Data");
	private InventoryDataFile inventoryDataFile = new InventoryDataFile(this, "inventory.data", "Data");
	private PlayerManagerFile playerManagerFile = new PlayerManagerFile(this);
	private PlayerFile playerFile = new PlayerFile(this);
	public boolean BungeeMode;
	public boolean uselegacyversion = false;
	public boolean uselatestversion = false;
	public boolean uselatestConfig = false;
	public static boolean debug = true;
	public String latestversion;
	public String nmsversion;
	public AdvancedChatApi advancedChatApi;

	
	public void onLoad() {
		
		instance = this;
		
		Utils.setTitle(version);
		Utils.setLoad(version);
		if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
		Utils.setLineLoad("&eCheck DataFolder Exist!");	
		this.eventUtils = new EventUtils(this);
		Utils.setLineLoad("&eLoading EventUtils");
		inventoryView = new ArrayList<>();
		Utils.setLineLoad("&eLoading InventoryView");
		preConfigLoad = new PreConfigLoader(this);
		Utils.setLineLoad("&eLoading PreConfigLoader");
		HookManager = new HookManager(this);
		Utils.setLineLoad("&eLoading HookManager");
		Utils.setTitleLoad("&bLoading Files");
		getConfigFile().saveDefaultConfig();
		getConfigFile().create();
		if(!getConfigFile().getConfig().getString("Settings.Config-Version").equals("2")) {
			uselatestConfig = true;
		}
		Utils.setLineLoad("&eLoad Config.yml");
		getMessageFile().saveDefault();
		getMessageFile().createFile();
		Utils.setLineLoad("&eLoad Message.yml");
		createVoidFolder("Data");
		createVoidFolder("Data" + File.separator + "Players");
		Utils.setLineLoad("&eLoad Data Folder");
		createVoidFolder("Gui");
		Utils.setLineLoad("&eLoad Gui Folder");
		createVoidFolder("Log");
		Utils.setLineLoad("&eLoad Log Folder");
		preConfigLoad.loadConfig();
		preConfigLoad.loadMessage();
		Utils.setLineLoad("&eLoad PreConfig");
		Utils.setEndLoad();

	}
	
	public void onEnable() {
		Utils.setEnabled(version);

		nmsversion = Bukkit.getServer().getClass().getPackage().getName();
		nmsversion = nmsversion.substring(nmsversion.lastIndexOf(".") + 1);
		if (nmsversion.equalsIgnoreCase("v1_8_R3")) {
			uselegacyversion = true;
			if (uselegacyversion) {
				Utils.sendColorMessage(eventUtils.getConsoleSender(), Utils.getPrefix(true) + "&5<|| &c* &7Use " + nmsversion + " &cdisabled &7method &b1.16 &3- &b1.18");
			}
		} else if (nmsversion.startsWith("v1_16_") || nmsversion.startsWith("v1_17_") || nmsversion.startsWith("v1_18_")) {
			uselatestversion = true;
			Utils.sendColorMessage(eventUtils.getConsoleSender(), Utils.getPrefix(true) + "&5<|| &c* &7Use " + nmsversion + " &aenabled &7method");
		}
		
		checkBungeeMode();
		
		if(uselatestConfig) {
			Logger.warning("&e!Please update your config.yml!");
		}
		
		createAllFiles();
		preConfigLoad.loadGradientInv();
		preConfigLoad.loadColorInv();
		preConfigLoad.loadPlayerInv();
		HookManager.load();
		HookManager.loadProtocol();
		
		if (Settings.boolean_protocollib) {
			if (HookManager.isLoadProtocolLib()) {
				HookManager.InitPacketListening();
			} else {
				Logger.warning(Settings.message_depend_plugin+ " " + "&e[&bProtocolLib&e]");
			}
		}		
		this.getMetric();
		loadCommands();
		loadEvents();
		
		new UpdateChecker(this, 83889).getUpdateVersionSpigot(version -> {
			latestversion = version;
			if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
				Logger.success("&a" + this.name + " is up to date!");
			} else {
				Logger.outline("&5<||" + Utils.getLine("&5"));
				Logger.warning("&5<||" + "&b" + this.name + " is outdated!");
				Logger.warning("&5<||" + "&bNewest version: &a" + version);
				Logger.warning("&5<||" + "&bYour version: &d" + this.version);
				Logger.warning("&5<||" + "&bUpdate Here on Spigot: &e" + UpdateSettings.URL_PLUGIN[0]);
				Logger.warning("&5<||" + "&bUpdate Here on Songoda: &e" + UpdateSettings.URL_PLUGIN[1]);
				Logger.warning("&5<||" + "&bUpdate Here on GitHub: &e" + UpdateSettings.URL_PLUGIN[2]);
				Logger.outline("&5<||" + Utils.getLine("&5"));
			}
		});
	}
	
	public void onDisable() {
		Utils.setDisabled(version);
		metrics = null;
		uselegacyversion = false;
		uselatestConfig = false;
	}
	
	public void loadCommands() {
		new AdvancedChatCmd(this);
		new ClearChatCmd(this);
		new MuteCmd(this);
		new UnMuteCmd(this);
		new MsgCmd();
		new TestCommand();
	}
		
	public void loadEvents() {
		eventUtils.initEvent(
		new JoinListener(this),
		new ChatListenerTest(this),
		//new ChatListener(this),
		new CommandListener(this),
		new ChatLogListener(this),
		new ColorInventoryListener(),
		new GradientInventoryListener(),
		new PlayerInventoryListener(),
		new SettingsInventoryListener(),
		new RainbowInventoryListener());
		EventLoader e = new EventLoader(this);
		e.runClearChat();
	}
	
	public void reloadAllFiles() {
		this.preConfigLoad.loadConfig();
		this.preConfigLoad.loadMessage();
		this.getConfigFile().reloadConfig();
		this.getChatDataFile().reloadConfig();
		this.getChatLogFile().reloadConfig();
		this.getPlayerGuiFile().reloadConfig();
		this.getColorFile().reloadConfig();
		this.getGradientColorFile().reloadConfig();
		this.getChannelGuiFile().reloadConfig();
		this.getCommandLogFile().reloadConfig();
		this.getInventoryDataFile().reloadConfig();
		this.getGroupFile().reloadConfig();
		this.getBadWordFile().reloadConfig();
		this.getMessageFile().reload();
	}
	
	public void createAllFiles() {
		inventoryDataFile.create();
		chatLogFile.create();
		commandLogFile.create();
		groupFile.saveDefault();
		groupFile.create();
		colorFile.create();
		gradientColorFile.create();
		playerGuiFile.create();
		chatDataFile.create();
		channelGuiFile.create();
	}
	
	public void getMetric() {
		metrics = new Metrics(this, 8826);
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
	
	public PlayerManagerFile getPlayerManagerFile() {
		return playerManagerFile;
	}
	
	public HookManager getHookManager() {
		return HookManager;
	}

	public Connection getConnection() {
		return this.connection.getConnetion();
	}

	public boolean isDebug() {
		return true;
	}
	
	public static AdvancedChat get() {
		return instance;
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
	
	@Deprecated
	public PlayerDataFile getPlayerDataFile() {
		return this.playerDataFile;
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
	
	public CommandFile getCommandFile() {
		return commandFile;
	}

}
