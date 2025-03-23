package jss.advancedchat;

import jss.advancedchat.commands.*;
import jss.advancedchat.commands.oldcommands.AdvancedChatCmd;
import jss.advancedchat.files.BadWordFile;
import jss.advancedchat.files.ConfigFile;
import jss.advancedchat.files.GroupFile;
import jss.advancedchat.files.MessageFile;
import jss.advancedchat.files.gui.ChannelGuiFile;
import jss.advancedchat.files.gui.ColorFile;
import jss.advancedchat.files.gui.GradientColorFile;
import jss.advancedchat.files.gui.PlayerGuiFile;
import jss.advancedchat.files.log.LogFile;
import jss.advancedchat.files.player.PlayerFile;
import jss.advancedchat.files.utils.PreConfigLoader;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.listeners.JoinListener;
import jss.advancedchat.listeners.TaskLoader;
import jss.advancedchat.listeners.chat.ChatLogListener;
import jss.advancedchat.listeners.chat.CommandListener;
import jss.advancedchat.listeners.inventory.*;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.storage.mysql.MySqlConnection;
import jss.advancedchat.test.ChatListenerTest;
import jss.advancedchat.update.UpdateChecker;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.logger.Logger;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.inventory.InventoryView;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AdvancedChat extends AdvancedChatPlugin {

    private static AdvancedChat instance;
    private final MessageFile messageFile = new MessageFile(this, "messages.yml");
    private final ConfigFile configFile = new ConfigFile(this, "config.yml");
    private final GroupFile groupFile = new GroupFile(this, "groups.yml");
    private final BadWordFile badWordFile = new BadWordFile(this, "badword.yml");
    private final ColorFile colorFile = new ColorFile(this, "color-gui.yml", "Gui");
    private final PlayerGuiFile playerGuiFile = new PlayerGuiFile(this, "player-gui.yml", "Gui");
    private final ChannelGuiFile channelGuiFile = new ChannelGuiFile(this, "channel-gui.yml", "Gui");
    private final GradientColorFile gradientColorFile = new GradientColorFile(this, "gradient-gui.yml", "Gui");
    private final PlayerFile playerFile = new PlayerFile(this);
    private final LogFile logFile = new LogFile(this);
    public EventUtils eventUtils;
    public Metrics metrics;
    public HookManager HookManager;
    public Map<String, InventoryView> inventoryView;
    public boolean isLegacyConfig = false;
    public String latestVersion;
    private PreConfigLoader preConfigLoader;
    private BukkitAudiences adventure;
    private MySqlConnection mySqlConnection;
    private boolean debug;

    public static AdvancedChat get() {
        return instance;
    }

    public void onLoad() {
        instance = this;
        getMetric();
        Util.setTitle(version);
        this.eventUtils = new EventUtils();
        inventoryView = new HashMap<>();
        preConfigLoader = new PreConfigLoader(this);
        HookManager = new HookManager(this);
        getConfigFile().saveDefaultConfig();
        getConfigFile().create();
        if (!(getConfigFile().getConfig().getInt("Settings.File-Version") <= 2)) {
            isLegacyConfig = true;
        }
        getMessageFile().saveDefault();
        getMessageFile().createFile();
        createFolder("Data");
        createFolder("Players");
        createFolder("Gui");
        createFolder("Log");
        preConfigLoader.loadConfig();
        preConfigLoader.loadMessage();
        if(!preConfigLoader.loadLangs()){
            Bukkit.getPluginManager().disablePlugins();
        }
        debug = getConfigFile().getConfig().getBoolean("Settings.Debug");
    }

    public void onEnable() {
        Util.setEnabled(version);
        this.adventure = BukkitAudiences.create(this);
        if (isLegacyConfig) {
            Logger.warning("&e!Please update your config.yml!");
        }

        createAllFiles();
        HookManager.load();
        HookManager.loadProtocol();

        if (Settings.mysql) {
            mySqlConnection = new MySqlConnection();
            mySqlConnection.setup();
        }

        //Dev New System command
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.register();

        //old command
        onCommands();
        //playerFile = new PlayerFile(this);
        // listeners
        onListeners();

        if (Settings.boolean_protocollib) {
            if (HookManager.isLoadProtocolLib()) {
                HookManager.InitPacketListening();
            } else {
                Logger.warning(Settings.message_depend_plugin + " " + "&e[&bProtocolLib&e]");
            }
        }

        //logFile.create();

        this.onUpdate();
    }

    public BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }

    public void onDisable() {
        instance = null;
        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
        metrics = null;
        isLegacyConfig = false;

        if(mySqlConnection != null){
            mySqlConnection.close();
        }

        Bukkit.getScheduler().cancelTasks(this);
        Util.setDisabled(version);
    }

    public void onCommands() {
        new AdvancedChatCmd();
        new ClearChatCmd();
        new MuteCmd();
        new UnMuteCmd();
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
        new TaskLoader();
    }

    public void reloadAllFiles() {
        this.preConfigLoader.loadConfig();
        this.preConfigLoader.loadMessage();
        this.preConfigLoader.loadLangs();
        this.getConfigFile().reloadConfig();
        this.getPlayerGuiFile().reloadConfig();
        this.getColorFile().reloadConfig();
        this.getGradientColorFile().reloadConfig();
        this.getChannelGuiFile().reloadConfig();
        this.getGroupFile().reloadConfig();
        this.getBadWordFile().reloadConfig();
        this.getMessageFile().reload();
    }

    public void createAllFiles() {
        groupFile.saveDefault();
        groupFile.create();
        colorFile.create();
        gradientColorFile.create();
        playerGuiFile.create();
        //channelGuiFile.create();
    }

    private void onUpdate() {
        new UpdateChecker(this, 83889).getUpdateVersionSpigot(version -> {
            latestVersion = version;
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                Logger.success("&a" + this.name + " is up to date!");
            } else {
                //Logger.outline("&5<||" + Util.getLine("&5"));
                for (String msg : Settings.lang_updateAlert_console){
                    Logger.warning(msg.replace("{newversion}", this.latestVersion));
                }
                //Logger.outline("&5<||" + Util.getLine("&5"));
                /*
                Logger.outline("&5<||" + Util.getLine("&5"));
                Logger.warning("&5<||" + "&b" + this.name + " is outdated!");
                Logger.warning("&5<||" + "&bNewest version: &a" + version);
                Logger.warning("&5<||" + "&bYour version: &d" + this.version);
                Logger.warning("&5<||" + "&bUpdate Here on Spigot: &e" + UpdateSettings.URL_PLUGIN[0]);
                Logger.warning("&5<||" + "&bUpdate Here on Songoda: &e" + UpdateSettings.URL_PLUGIN[1]);
                Logger.warning("&5<||" + "&bUpdate Here on GitHub: &e" + UpdateSettings.URL_PLUGIN[2]);
                Logger.outline("&5<||" + Util.getLine("&5"));
                */
            }
        });
    }

    public void getMetric() {
        metrics = new Metrics(this, 8826);
    }

    public void addInventoryView(@NotNull Player player, String inventoryName){
        if(!inventoryView.containsKey(player.getName())){
            inventoryView.put(player.getName(), new InventoryView(player, inventoryName));
        }
    }

    public void removeInventoryView(@NotNull Player player){
        inventoryView.remove(player.getName());
    }

    public InventoryView getInventoryView(@NotNull Player player){
        return inventoryView.get(player.getName());
    }

    public PlayerFile getPlayerFile() {
        return playerFile;
    }

    public boolean isDebug() {
        return this.debug;
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

    public ConfigFile getConfigFile() {
        return configFile;
    }

    public ColorFile getColorFile() {
        return colorFile;
    }

    public PlayerGuiFile getPlayerGuiFile() {
        return playerGuiFile;
    }

    public LogFile getLogFile(){
        return logFile;
    }

    public ChannelGuiFile getChannelGuiFile() {
        return channelGuiFile;
    }

    public Connection getConnection() throws SQLException {
        return mySqlConnection.getConnection();
    }

}