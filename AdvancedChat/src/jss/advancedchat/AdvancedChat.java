package jss.advancedchat;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jss.advancedchat.commands.AdvancedChatCmd;
import jss.advancedchat.commands.ClearChatCmd;
import jss.advancedchat.commands.MsgCmd;
import jss.advancedchat.commands.MuteCmd;
import jss.advancedchat.commands.UnMuteCmd;
import jss.advancedchat.config.ChannelGuiFile;
import jss.advancedchat.config.ChatDataFile;
import jss.advancedchat.config.ChatLogFile;
import jss.advancedchat.config.ColorFile;
import jss.advancedchat.config.CommandFile;
import jss.advancedchat.config.CommandLogFile;
import jss.advancedchat.config.ConfigFile;
import jss.advancedchat.config.InventoryDataFile;
import jss.advancedchat.config.PlayerDataFile;
import jss.advancedchat.config.PlayerGuiFile;
import jss.advancedchat.config.PreConfigLoader;
import jss.advancedchat.events.ChatListener;
import jss.advancedchat.events.CommandListener;
import jss.advancedchat.events.EventLoader;
import jss.advancedchat.events.InventoryListener;
import jss.advancedchat.events.JoinListener;
import jss.advancedchat.hooks.HookManager;
import jss.advancedchat.inventory.utils.InventoryView;
import jss.advancedchat.manager.ChatManager;
import jss.advancedchat.old.json.handlers.JsonClickEvent;
import jss.advancedchat.old.json.handlers.JsonHoverEvent;
import jss.advancedchat.old.json.serializers.SerializerClickEvent;
import jss.advancedchat.old.json.serializers.SerializerHoverEvent;
import jss.advancedchat.storage.MySQL;
import jss.advancedchat.storage.SQLGetter;
import jss.advancedchat.utils.AdvancedChatPlugin;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.OnlinePlayers;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Logger.Level;
import jss.advancedchat.utils.file.FileManager;
import jss.advancedchat.utils.UpdateSettings;
import jss.advancedchat.utils.UpdateChecker;
import jss.advancedchat.utils.Utils;

public class AdvancedChat extends AdvancedChatPlugin {

	@SuppressWarnings("unused")
	private FileManager filemanager = new FileManager(this);
	private ConfigFile configfile = new ConfigFile(this, "config.yml");
	private CommandFile commandFile = new CommandFile(this, "custom-command.yml");
	private ColorFile colorFile = new ColorFile(this, "color-gui.yml", "Gui");
	private PlayerGuiFile playerGuiFile = new PlayerGuiFile(this, "player-gui.yml", "Gui");
	private ChannelGuiFile channelGuiFile = new ChannelGuiFile(this, "channel-gui.yml", "Gui");
	private ChatLogFile chatLogFile = new ChatLogFile(this, "chat.yml", "Log");
	private CommandLogFile commandLogFile = new CommandLogFile(this, "command.yml", "Log");
	private ChatDataFile chatDataFile = new ChatDataFile(this, "chat-log.data", "Data");
	private PlayerDataFile playerdata = new PlayerDataFile(this, "players.data", "Data");
	private InventoryDataFile inventoryDataFile = new InventoryDataFile(this, "inventory.data", "Data");
	private PluginDescriptionFile jss = getDescription();
	private static AdvancedChat plugin;
	private PreConfigLoader preConfigLoad = new PreConfigLoader(this);
	public Logger logger = new Logger(this);
	private MySQL mySQL = new MySQL();
	private SQLGetter data = new SQLGetter();
	private EventUtils eventUtils = new EventUtils(this);
	public Metrics metrics;
	private static GsonBuilder gsonBuilder;
	private HookManager HookManager = new HookManager(this);
	public String name = this.jss.getName();
	public String version = this.jss.getVersion();
	public String latestversion;
	public String nmsversion;
	@Deprecated
	public boolean placeholder = false;
	private boolean BungeeMode = false;
	public boolean uselegacyversion = false;
	public boolean uselatestversion = false;
	private static boolean debug;
	private ArrayList<InventoryView> inventoryView;
	private ArrayList<ChatManager> chatManagers;
	private ArrayList<OnlinePlayers> onlinePlayers;
	
	public void onEnable() {
		Utils.setEnabled(version);
		nmsversion = Bukkit.getServer().getClass().getPackage().getName();
		nmsversion = nmsversion.substring(nmsversion.lastIndexOf(".") + 1);
		if (nmsversion.equalsIgnoreCase("v1_8_R3")) {
			uselegacyversion = true;
			if (uselegacyversion) {
				Utils.sendColorMessage(eventUtils.getConsoleSender(), Utils.getPrefix() + "&5<|| &c* &7Use " + nmsversion
						+ " &cdisabled &7method &b1.16 &7and &e1.17");
			}
		} else if (nmsversion.equalsIgnoreCase("v1_16_R1") || nmsversion.equalsIgnoreCase("v1_16_R2")
				|| nmsversion.equalsIgnoreCase("v1_16_R3")) {
			uselatestversion = true;
			Utils.sendColorMessage(eventUtils.getConsoleSender(),
					Utils.getPrefix() + "&5<|| &c* &7Use " + nmsversion + " &aenabled &7method &b1.16");
		}
		plugin = this;
		if (isDebug()) {
			plugin.logger.Log(Level.INFO, "Pre Config Load completed");
		} else {
			Logger.Default("&5<|| &c* &ePre Config Load completed");
			Utils.sendLile();
		}
		commandFile.create();
		playerdata.create();
		//Disabled
		/*try {
			if (this.getConfigFile().getConfig().getString("Settings.BungeeMode").equals("false")) {

				BungeeMode = false;
			} else if (this.getConfigFile().getConfig().getString("Settings.BungeeMode").equals("true")) {
				BungeeMode = true;
				logger.Log(Level.INFO, "Bungee Mode can only be used if the database is active and configured");
				// logger.Log(Level.INFO, "Folder [data] and its [files] are not created");
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}*/
		inventoryDataFile.create();
		chatLogFile.create();
		commandLogFile.create();
		colorFile.create();
		playerGuiFile.create();
		chatDataFile.create();
		channelGuiFile.create();
		HookManager.loadProtocol();
		if (Settings.boolean_protocollib) {
			if (HookManager.isLoadProtocolLib()) {
				HookManager.InitPacketListening();
			} else {
				Logger.Warning(getConfigFile().getConfig().getString("AdvancedChat.Depend-Plugin") + " " + "&e[&bProtocolLib&e]");
			}
		}
		loadMySQL();
		metrics = new Metrics(this, 8826);
		this.inventoryView = new ArrayList<>();
		this.chatManagers = new ArrayList<>();
		this.onlinePlayers = new ArrayList<>();
		loadCommands();
		loadEvents();

		new UpdateChecker(this, 83889).getUpdateVersion(version -> {
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
		//remove releases 1.6.0
		placeholder = false;
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

	//unused
	static {
		gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(JsonHoverEvent.class, new SerializerHoverEvent());
		gsonBuilder.registerTypeAdapter(JsonClickEvent.class, new SerializerClickEvent());
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

	public void loadMySQL() {
		try {
			if (Settings.mysql_use_t) {
				mySQL.connect(Settings.mysql_host, Settings.mysql_port, Settings.mysql_database, Settings.mysql_user,
						Settings.mysql_password, Settings.mysql_usessl);
				Logger.Succerss("Connected database");
			}
		} catch (NullPointerException e) {
			logger.Log(Level.ERROR, "the config [database] is null?");
			e.printStackTrace();
		}
	}

	/* remove release v1.6.0
	 * @SuppressWarnings("unused") private void checkNMSVersion(String nmsversion) {
	 * try { Class<?> clazz = Class.forName("jss.advancedchat.nms.versions"+ "." +
	 * nmsversion + "." + "PacketSender"); //iPacketSender = (IPacketSender)
	 * clazz.newInstance(); } catch (ClassNotFoundException e) {
	 * e.printStackTrace(); }
	 * 
	 * }
	 */

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

	@Deprecated
	public boolean getPlaceHolderState() {
		return this.placeholder;
	}

	public HookManager getHookManager() {
		return HookManager;
	}

	public MySQL getMySQL() {
		return this.mySQL;
	}

	@Deprecated
	public boolean setupPlaceHolderAPI() {
		if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			placeholder = true;
		}
		return placeholder;
	}

	@Deprecated
	public void setupSoftDepends() {
		if (setupPlaceHolderAPI()) {
			Utils.sendColorMessage(eventUtils.getConsoleSender(), Utils.getPrefix() + "&5<||============================================-----");
			Utils.sendColorMessage(eventUtils.getConsoleSender(), Utils.getPrefix() + "&5<|| &c* &ePlaceHolderAPI:&b" + " " + placeholder);
			Utils.sendColorMessage(eventUtils.getConsoleSender(), Utils.getPrefix() + "&5<|| &c* &eVars PlaceHolderAPI:&a true");
			Utils.sendColorMessage(eventUtils.getConsoleSender(), Utils.getPrefix() + "&5<||============================================-----");
		} else {
			Utils.sendColorMessage(eventUtils.getConsoleSender(), Utils.getPrefix() + "&5<||============================================-----");
			Utils.sendColorMessage(eventUtils.getConsoleSender(), Utils.getPrefix() + "&5<|| &c* &ePlaceHolderAPI:&b" + " " + placeholder);
			Utils.sendColorMessage(eventUtils.getConsoleSender(), Utils.getPrefix() + "&5<|| &c* &eVars PlaceHolderAPI:&c false");
			Utils.sendColorMessage(eventUtils.getConsoleSender(), Utils.getPrefix() + "&5<||============================================-----");
		}
	}

	public boolean isDebug() {
		return debug;
	}

	public static AdvancedChat getPlugin() {
		return plugin;
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

	public int getTotalPage() {
		if (this.onlinePlayers.size() % 45 == 0) {
			int pag = (this.onlinePlayers.size() / 45);
			return pag;
		} else {
			int pag = (this.onlinePlayers.size() / 45) + 1;
			return pag;
		}
	}

	public ArrayList<OnlinePlayers> getOnlinePlayers() {
		return onlinePlayers;
	}

	public void setOnlinePlayers(OnlinePlayers player) {
		this.onlinePlayers.add(player);
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

	public static Gson getGson() {
		return gsonBuilder.create();
	}

	public PreConfigLoader getPreConfigLoader() {
		return this.preConfigLoad;
	}

	public SQLGetter getSQLGetter() {
		return this.data;
	}
}
