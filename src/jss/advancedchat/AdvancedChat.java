package jss.advancedchat;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jss.advancedchat.commands.AdvancedChatCmd;
import jss.advancedchat.commands.ClearChatCmd;
import jss.advancedchat.commands.MuteCmd;
import jss.advancedchat.commands.UnMuteCmd;
import jss.advancedchat.config.PreConfigLoad;
import jss.advancedchat.database.ConnectionMySQL;
import jss.advancedchat.events.ChatListener;
import jss.advancedchat.events.CommandListener;
import jss.advancedchat.events.EventLoader;
import jss.advancedchat.events.InventoryListener;
import jss.advancedchat.events.JoinListener;
import jss.advancedchat.manager.ChatManager;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.FileManager;
import jss.advancedchat.utils.InventoryPlayer;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.OnlinePlayers;
import jss.advancedchat.utils.Logger.Level;
import jss.advancedchat.utils.json.handlers.JsonClickEvent;
import jss.advancedchat.utils.json.handlers.JsonHoverEvent;
import jss.advancedchat.utils.json.serializers.SerializerClickEvent;
import jss.advancedchat.utils.json.serializers.SerializerHoverEvent;
import jss.advancedchat.utils.version.nms.IPacketSender;
import jss.advancedchat.utils.UpdateSettings;
import jss.advancedchat.utils.UpdateChecker;
import jss.advancedchat.utils.Utils;

public class AdvancedChat extends JavaPlugin {

    private PluginDescriptionFile jss = getDescription();
    public String name = this.jss.getName();
    public String version = this.jss.getVersion();
    public Metrics metrics;
    public String latestversion;
    public boolean placeholder = false;
    private CommandSender c = Bukkit.getConsoleSender();
    private boolean debug = false;
    private FileManager filemanager = new FileManager(this);
    private PlayerDataFile playerdata = new PlayerDataFile(this, "players.data", "Data");
    private ConfigFile configfile = new ConfigFile(this, "config.yml");
    private ColorFile colorFile = new ColorFile(this, "color-gui.yml", "Gui");
    private PlayerGuiFile playerGuiFile = new PlayerGuiFile(this, "player-gui.yml", "Gui");
    private ChannelGuiFile channelGuiFile = new ChannelGuiFile(this, "channel-gui.yml", "Gui");
    private ChatDataFile chatDataFile = new ChatDataFile(this, "chat-log.data", "Data");
    private ChatLogFile chatLogFile = new ChatLogFile(this, "chat.yml", "Log");
    private CommandLogFile commandLogFile = new CommandLogFile(this, "command.yml", "Log");
    private CommandFile commandFile = new CommandFile(this, "custom-command.yml");
    public String nmsversion;
    public boolean uselegacyversion = false;
    public Logger logger = new Logger(this);
    private static AdvancedChat plugin;
    private ArrayList<InventoryPlayer> inventoryPlayers;
    private ArrayList<ChatManager> chatManagers;
    private ArrayList<OnlinePlayers> onlinePlayers;
    private PreConfigLoad preConfigLoad = new PreConfigLoad(this);
    private ConnectionMySQL connectionMySQL;
    private EventUtils eventUtils;
    public boolean uselatestversion = false;
    private boolean BungeeMode = false;
    private static IPacketSender iPacketSender;
    private static GsonBuilder gsonBuilder;
   
    public void onEnable() {
        Utils.setEnabled(version);
        nmsversion = Bukkit.getServer().getClass().getPackage().getName();
        nmsversion = nmsversion.substring(nmsversion.lastIndexOf(".") + 1);
        if (nmsversion.equalsIgnoreCase("v1_8_R3")) {
            uselegacyversion = true;
            if (uselegacyversion == true) {
                Utils.sendColorMessage(c, Utils.getPrefix() + " &5<|| &c* &7Use " + nmsversion + " &cdisabled &7method &b1.16");
            }
        } else if (nmsversion.equalsIgnoreCase("v1_16_R1") || nmsversion.equalsIgnoreCase("v1_16_R2") || nmsversion.equalsIgnoreCase("v1_16_R3")) {
            uselatestversion = true;
        	Utils.sendColorMessage(c, Utils.getPrefix() + " &5<|| &c* &7Use " + nmsversion + " &aenabled &7method &b1.16");
        }
        checkNMSVersion(nmsversion);
        plugin = this;
        configfile.saveDefaultConfig();
        configfile.create();
        preConfigLoad.load();
        filemanager.createVoidFolder("Modules");
        commandFile.create();
        try {
        	if(this.getConfigFile().getConfig().getString("Settings.BungeeMode").equals("false")) {
                filemanager.createVoidFolder("Data");
                playerdata.create();
                BungeeMode = false;
        	} else if(this.getConfigFile().getConfig().getString("Settings.BungeeMode").equals("true")) {
        		BungeeMode = true;
        		logger.Log(Level.INFO, "Bungee Mode can only be used if the database is active and configured");
        		logger.Log(Level.INFO, "Folder [data] and its [files] are not created");
        	} else {
        		
        	}
        }catch(NullPointerException e) {
        	e.printStackTrace();
        }
        filemanager.createVoidFolder("Log");
        chatLogFile.create();
        commandLogFile.create();
        filemanager.createVoidFolder("Gui");
        colorFile.create();
        playerGuiFile.create();
        chatDataFile.create();
        channelGuiFile.create();
        try {
            if(getConfigFile().getConfig().getString("Settings.Use-Database").equals("true")) {
            	loadMySQL();	
            }
        }catch(NullPointerException e) {
        	logger.Log(Level.ERROR, "the config [database] is null?");
        	e.printStackTrace();
        }
        metrics = new Metrics(this);
        this.inventoryPlayers = new ArrayList<>();
        this.chatManagers = new ArrayList<>();
        this.onlinePlayers = new ArrayList<>();
        setupCommands();
        setEventUtils(new EventUtils(this));
        setupEvents();
        SetupSoftDepends();
        new UpdateChecker(this, 83889).getUpdateVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.Log(Level.SUCCESS, "&a" + this.name + " is up to date!");
            } else {
                logger.Log(Level.OUTLINE, "&5<||" + Utils.getLine("&5"));
                logger.Log(Level.WARNING, "&5<||" + "&b" + this.name + "is outdated!");
                logger.Log(Level.WARNING, "&5<||" + "&bNewest version: &a" + version);
                logger.Log(Level.WARNING, "&5<||" + "&bYour version: &d" + UpdateSettings.VERSION);
                logger.Log(Level.WARNING, "&5<||" + "&bUpdate Here on Spigot: &e" + UpdateSettings.URL_PLUGIN[0]);
                logger.Log(Level.WARNING, "&5<||" + "&bUpdate Here on Songoda: &e" + UpdateSettings.URL_PLUGIN[1]);
                logger.Log(Level.OUTLINE, "&5<||" + Utils.getLine("&5"));
            }
        });
    }

    public void onDisable() {
        Utils.setDisabled(version);
        placeholder = false;
        metrics = null;
        uselegacyversion = false;
    }
    
    static {
    	gsonBuilder = new GsonBuilder();
    	gsonBuilder.registerTypeAdapter(JsonHoverEvent.class, new SerializerHoverEvent());
    	gsonBuilder.registerTypeAdapter(JsonClickEvent.class, new SerializerClickEvent());
    }

    public void setupCommands() {
        new AdvancedChatCmd(this);
        new ClearChatCmd(this);
        
        new MuteCmd(this);
        new UnMuteCmd(this);
    }

    public void setupEvents() {
        new JoinListener(this);
        new InventoryListener(this);
        new ChatListener(this);
        new CommandListener(this);
        EventLoader eventLoader = new EventLoader(this);
        eventLoader.runClearChat();
    }

    //@SuppressWarnings("unused")
	public void loadMySQL() {
        FileConfiguration config = getConfigFile().getConfig();
        String host = config.getString("DataBase.Host");
        int port = config.getInt("DataBase.Port");
        String database = config.getString("DataBase.Database");
        String user = config.getString("DataBase.User");
        String password = config.getString("DataBase.Password");
        connectionMySQL = new ConnectionMySQL(this, host, port, user, password, database);
        //connectionMySQL = new ConnectionMySQL(this, "localhost", 3306, "root", "", "test");
    }
	
	private void checkNMSVersion(String nmsversion) {
		try {
			Class<?> clazz = Class.forName("jss.advancedchat.utils.version.nms"+ "." + nmsversion + "." + "PacketSender");
			iPacketSender = (IPacketSender) clazz.newInstance();
			logger.Log(Level.DEBUG, "&bLoad Nms:" + " &e" + clazz);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
    public PlayerDataFile getPlayerDataFile() {
        return this.playerdata;
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

	public boolean getPlaceHolderState() {
        return this.placeholder;
    }

    public boolean setupPlaceHolderAPI() {
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            placeholder = true;
        }
        return placeholder;
    }

    public void SetupSoftDepends() {
        if (setupPlaceHolderAPI()) {
            Utils.sendColorMessage(c, Utils.getPrefix() + " &5<||============================================-----");
            Utils.sendColorMessage(c, Utils.getPrefix() + " &5<|| &c* &ePlaceHolderAPI:&b" + " " + placeholder);
            Utils.sendColorMessage(c, Utils.getPrefix() + " &5<|| &c* &eVars PlaceHolderAPI:&a true");
            //Utils.sendColorMessage(c, Utils.getPrefix() + " &5<|| &c* &dAdvancedChat:&a true");
            Utils.sendColorMessage(c, Utils.getPrefix() + " &5<||============================================-----");
        } else {
            Utils.sendColorMessage(c, Utils.getPrefix() + " &5<||============================================-----");
            Utils.sendColorMessage(c, Utils.getPrefix() + " &5<|| &c* &ePlaceHolderAPI:&b" + " " + placeholder);
            Utils.sendColorMessage(c, Utils.getPrefix() + " &5<|| &c* &eVars PlaceHolderAPI:&c false");
            //Utils.sendColorMessage(c, Utils.getPrefix() + " &5<|| &c* &dAdvancedChat:&c false");
            Utils.sendColorMessage(c, Utils.getPrefix() + " &5<||============================================-----");
        }
    }

    public boolean isDebug() {
        return debug;
    }

    public static AdvancedChat getPlugin() {
        return plugin;
    }

    public void addInventoryPlayer(Player player, String inventoryname) {
        if (this.getInventoryPlayer(player) == null) {
            this.inventoryPlayers.add(new InventoryPlayer(player, inventoryname));
        }
    }

    public void removeInvetoryPlayer(Player player) {
        for (int i = 0; i < inventoryPlayers.size(); i++) {
            if (((InventoryPlayer) this.inventoryPlayers.get(i)).getPlayer().getName().equals(player.getName())) {
                this.inventoryPlayers.remove(i);
            }
        }
    }

    public InventoryPlayer getInventoryPlayer(Player player) {
        for (int i = 0; i < inventoryPlayers.size(); i++) {
            if (((InventoryPlayer) this.inventoryPlayers.get(i)).getPlayer().getName().equals(player.getName())) {
                return (InventoryPlayer) this.inventoryPlayers.get(i);
            }
        }
        return null;
    }

    public ConnectionMySQL getConnectionMySQL() {
        return connectionMySQL;
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
        return chatManagers;
    }

    public void setChatManagers(ChatManager chat) {
        this.chatManagers.add(chat);
    }

	public EventUtils getEventUtils() {
		return eventUtils;
	}

	public void setEventUtils(EventUtils eventUtils) {
		this.eventUtils = eventUtils;
	}

	public boolean isBungeeMode() {
		return BungeeMode;
	}

	public void setBungeeMode(boolean isBungeeMode) {
		this.BungeeMode = isBungeeMode;
	}

	public static IPacketSender getiPacketSender() {
		return iPacketSender;
	}

	public static Gson getGson() {
		return gsonBuilder.create();
	}
	
}
