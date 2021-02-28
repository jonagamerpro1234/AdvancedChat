package jss.advancedchat;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import jss.advancedchat.commands.AdvancedChatCmd;
import jss.advancedchat.commands.ClearChatCmd;
import jss.advancedchat.commands.MuteCmd;
import jss.advancedchat.commands.UnMuteCmd;
import jss.advancedchat.database.ConnectionMySQL;
import jss.advancedchat.events.ChatListener;
import jss.advancedchat.events.CommandListener;
import jss.advancedchat.events.EventLoader;
import jss.advancedchat.events.InventoryListener;
import jss.advancedchat.events.JoinListener;
import jss.advancedchat.manager.ChatManager;
import jss.advancedchat.utils.FileManager;
import jss.advancedchat.utils.InventoryPlayer;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.OnlinePlayers;
import jss.advancedchat.utils.Logger.Level;
import jss.advancedchat.utils.UpdateSettings;
import jss.advancedchat.utils.UpdateChecker;
//import jss.advancedchat.utils.UpdateChecker2;
import jss.advancedchat.utils.Utils;

public class AdvancedChat extends JavaPlugin {

    PluginDescriptionFile jss = getDescription();
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
    private ChatDataFile chatDataFile = new ChatDataFile(this, "chat-log.data", "Data");
    private ChatLogFile chatLogFile = new ChatLogFile(this, "chat.yml", "Log");
    private CommandLogFile commandLogFile = new CommandLogFile(this, "command.yml", "Log");
    private CommandFile commandFile = new CommandFile(this, "custom-commands.yml");
    public String nmsversion;
    public boolean uselegacyversion = false;
    public Logger logger = new Logger(this);
    private static AdvancedChat plugin;
    private ArrayList<InventoryPlayer> inventoryPlayers;
    private ArrayList<ChatManager> chatManagers;
    private ArrayList<OnlinePlayers> onlinePlayers;
    private PreConfigLoad preConfigLoad = new PreConfigLoad(this);
    private ConnectionMySQL connectionMySQL;
   
    public void onEnable() {
        Utils.setEnabled(version);
        ;
        nmsversion = Bukkit.getServer().getClass().getPackage().getName();
        nmsversion = nmsversion.substring(nmsversion.lastIndexOf(".") + 1);
        if (nmsversion.equalsIgnoreCase("v1_8_R3")) {
            uselegacyversion = true;
            if (uselegacyversion == true) {
                Utils.sendColorMessage(c, Utils.getPrefix() + " &5<|| &c* &7Use " + nmsversion + " &cdisabled &7method &b1.16");
            }
        } else if (nmsversion.equalsIgnoreCase("v1_16_R2")) {
            Utils.sendColorMessage(c, Utils.getPrefix() + " &5<|| &c* &7Use " + nmsversion + " &aenabled &7method &b1.16");
        }
        plugin = this;
        configfile.saveDefaultConfig();
        configfile.create();
        commandFile.create();
        filemanager.createVoidFolder("Data");
        playerdata.create();
        filemanager.createVoidFolder("Gui");
        colorFile.create();
        playerGuiFile.create();
        chatDataFile.create();
        filemanager.createVoidFolder("Log");
        chatLogFile.create();
        commandLogFile.create();
        preConfigLoad.load();
        loadMySQL();
        /*try {
            if(getConfigFile().getConfig().getString("Settings.Use-DataBase").equals("true")) {
            	loadMySQL();	
            }
        }catch(NullPointerException e) {
        	e.printStackTrace();
        }*/
        metrics = new Metrics(this);
        this.inventoryPlayers = new ArrayList<>();
        this.chatManagers = new ArrayList<>();
        this.onlinePlayers = new ArrayList<>();
        setupCommands();
        setupEvents();
        SetupSoftDepends();
        new UpdateChecker(this, 83889).getUpdateVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.Log(Level.SUCCESS, "&a" + this.name + " is up to date!");
            } else {
                logger.Log(Level.OUTLINE, "&5<||" + Utils.getLine("&5"));
                logger.Log(Level.WARNING, "&5<||" + "&b" + this.name + " is outdated!");
                logger.Log(Level.WARNING, "&5<||" + "&bNewest version: &a" + version);
                logger.Log(Level.WARNING, "&5<||" + "&bYour version: &d" + UpdateSettings.VERSION);
                logger.Log(Level.WARNING, "&5<||" + "&bUpdate Here on Spigot: &e" + UpdateSettings.URL_PLUGIN_SPIGOT);
                logger.Log(Level.WARNING, "&5<||" + "&bUpdate Here on Songoda: &e" + UpdateSettings.URL_PLUGIN_SONGODA);
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

    @SuppressWarnings("unused")
	public void loadMySQL() {
        FileConfiguration config = getConfigFile().getConfig();

        String host = config.getString("DataBase.Host");
        int port = config.getInt("DataBase.Port");
        String database = config.getString("DataBase.DataBase");
        String user = config.getString("DataBase.User");
        String password = config.getString("DataBase.Password");

        //connectionMySQL = new ConnectionMySQL(this, host, port, user, password, database);
        connectionMySQL = new ConnectionMySQL(this, "localhost", 3306, "root", "", "test");

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
}
