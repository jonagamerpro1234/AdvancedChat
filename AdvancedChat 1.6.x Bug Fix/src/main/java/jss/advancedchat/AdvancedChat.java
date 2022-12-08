package jss.advancedchat;

import jss.advancedchat.files.*;
import jss.advancedchat.files.utils.PreConfigLoader;
import jss.advancedchat.listeners.EventLoader;
import jss.advancedchat.listeners.utils.EventUtils;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.inventory.InventoryView;
import jss.advancedchat.utils.update.UpdateChecker;
import jss.advancedchat.utils.update.UpdateSettings;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.ArrayList;

public class AdvancedChat extends AdvancedChatPlugin {
  private static AdvancedChat plugin;
  private final CommandSender c = Bukkit.getConsoleSender();
  private final PlayerDataFile playerData = new PlayerDataFile(this, "players.data", "Data");
  private final ConfigFile configfile = new ConfigFile(this, "config.yml");
  private final ColorFile colorFile = new ColorFile(this, "color-gui.yml", "Gui");
  private final PlayerGuiFile playerGuiFile = new PlayerGuiFile(this, "player-gui.yml", "Gui");
  private final ChatDataFile chatDataFile = new ChatDataFile(this, "chat-log.data", "Data");
  private final ChatLogFile chatLogFile = new ChatLogFile(this, "chat.yml", "Log");
  private final CommandLogFile commandLogFile = new CommandLogFile(this, "command.yml", "Log");
  private final Logger logger = new Logger();
  private final PreConfigLoader preConfigLoader = new PreConfigLoader(this);
  private final HookManager hookManager = new HookManager(this);
  public Metrics metrics;
  public String latestversion;
  public String nmsVersion;

  public boolean isLegacyVersion = false;
  PluginDescriptionFile jss = getDescription();
  public String name = this.jss.getName();
  public String version = this.jss.getVersion();
  private boolean debug = false;
  private ArrayList<InventoryView> InventoryView;
  public EventUtils eventUtils;

  public static AdvancedChat getInstance() {
    return plugin;
  }

  public static AdvancedChat get() {
    return plugin;
  }

  public void onLoad() {
    Utils.setTitle(this.version);
    Utils.setLoad();
    this.eventUtils = new EventUtils(this);
    Utils.setLineLoad("&eLoading EventUtils");
    Utils.setTitleLoad("&bLoading Files");
    this.configfile.saveDefaultConfig();
    this.configfile.create();
    this.debug = this.configfile.getConfig().getBoolean("Settings.Debug");
    Utils.setLineLoad("&eLoad Config.yml");
    this.preConfigLoader.load();
    Utils.setLineLoad("&eLoad Pre Config");
    createVoidFolder("Gui");
    Utils.setLineLoad("&eLoad Gui Folder");
    createVoidFolder("Data");
    Utils.setLineLoad("&eLoad Data Folder");
    createVoidFolder("Log");
    Utils.setLineLoad("&eLoad Log Folder");
    Utils.setEndLoad();
  }

  public void onEnable() {
    this.nmsVersion = Bukkit.getServer().getClass().getPackage().getName();
    this.nmsVersion = this.nmsVersion.substring(this.nmsVersion.lastIndexOf(".") + 1);
    Utils.setEnabled(this.version);
    if (this.nmsVersion.equalsIgnoreCase("v1_8_R3") || this.nmsVersion.equalsIgnoreCase("v1_7_R4")) {
      this.isLegacyVersion = true;
      Utils.sendColorMessage(this.c, Utils.getPrefix() + "&5<|| &c* &7Use " + this.nmsVersion + " &cdisabled &7method &b1.16 &7and &b1.17");
    } else if (this.nmsVersion.equalsIgnoreCase("v1_16_R1") || this.nmsVersion.equalsIgnoreCase("v1_16_R2") || this.nmsVersion.equalsIgnoreCase("v1_16_R3")) {
      Utils.sendColorMessage(this.c, Utils.getPrefix() + "&5<|| &c* &7Use " + this.nmsVersion + " &aenabled &7method &b1.16");
    } else if (this.nmsVersion.equalsIgnoreCase("v1_17_R1")) {
      Utils.sendColorMessage(this.c, Utils.getPrefix() + "&5<|| &c* &7Use " + this.nmsVersion + " &aenabled &7method &b1.17");
    } else if (this.nmsVersion.equalsIgnoreCase("v1_18_R1")) {
      Utils.sendColorMessage(this.c, Utils.getPrefix() + "&5<|| &c* &7Use " + this.nmsVersion + " &aenabled &7method &b1.17");
    }
    plugin = this;
    this.playerData.create();
    this.colorFile.create();
    this.playerGuiFile.create();
    this.chatDataFile.create();
    this.chatLogFile.create();
    this.commandLogFile.create();
    this.metrics = new Metrics(this, 8826);
    this.InventoryView = new ArrayList<>();
    setupCommands();
    setupEvents();
    this.hookManager.load();
    (new UpdateChecker(this, 83889)).getUpdateVersion(version -> {
      this.latestversion = version;
      if (getDescription().getVersion().equalsIgnoreCase(version)) {
        this.logger.Log(Logger.Level.SUCCESS, "&a" + this.name + " is up to date!");
      } else {
        this.logger.Log(Logger.Level.OUTLINE, "&5<||" + Utils.setLine("&5"));
        this.logger.Log(Logger.Level.WARNING, "&5<||&b" + this.name + " is outdated!");
        this.logger.Log(Logger.Level.WARNING, "&5<||&bNewest version: &a" + version);
        this.logger.Log(Logger.Level.WARNING, "&5<||&bYour version: &d" + UpdateSettings.VERSION);
        this.logger.Log(Logger.Level.WARNING, "&5<||&bUpdate Here on Spigot: &e" + UpdateSettings.URL_PLUGIN[0]);
        this.logger.Log(Logger.Level.WARNING, "&5<||&bUpdate Here on Songoda: &e" + UpdateSettings.URL_PLUGIN[1]);
        this.logger.Log(Logger.Level.WARNING, "&5<||&bUpdate Here on GitHub: &e" + UpdateSettings.URL_PLUGIN[2]);
        this.logger.Log(Logger.Level.OUTLINE, "&5<||" + Utils.setLine("&5"));
      }
    });
  }

  public void onDisable() {
    Utils.setDisabled(this.version);
    this.metrics = null;
    this.isLegacyVersion = false;
  }

  public void setupCommands() {
  }

  public void setupEvents() {
    EventLoader eventLoader = new EventLoader(this);
    eventLoader.runClearChat();
  }

  public void reloadAllFiles() {
    this.preConfigLoader.load();
    getConfigFile().reloadConfig();
    getChatDataFile().reloadConfig();
    getChatLogFile().reloadConfig();
    getPlayerDataFile().reloadConfig();
    getPlayerGuiFile().reloadConfig();
    getColorFile().reloadConfig();
    getCommandLogFile().reloadConfig();
  }

  public PlayerDataFile getPlayerDataFile() {
    return this.playerData;
  }

  public ConfigFile getConfigFile() {
    return this.configfile;
  }

  public ColorFile getColorFile() {
    return this.colorFile;
  }

  public PlayerGuiFile getPlayerGuiFile() {
    return this.playerGuiFile;
  }

  public ChatDataFile getChatDataFile() {
    return this.chatDataFile;
  }

  public ChatLogFile getChatLogFile() {
    return this.chatLogFile;
  }

  public CommandLogFile getCommandLogFile() {
    return this.commandLogFile;
  }

  public boolean isDebug() {
    return this.debug;
  }

  public void addInventoryView(Player player, String inventoryName) {
    if (getInventoryView(player) == null)
      this.InventoryView.add(new InventoryView(player, inventoryName));
  }

  public void removeInventoryView(Player player) {
    for (int i = 0; i < this.InventoryView.size(); i++) {
      if (this.InventoryView.get(i).getPlayer().getName().equals(player.getName()))
        this.InventoryView.remove(i);
    }
  }

  public InventoryView getInventoryView(Player player) {
    for (int i = 0; i < this.InventoryView.size(); i++) {
      if (this.InventoryView.get(i).getPlayer().getName().equals(player.getName()))
        return this.InventoryView.get(i);
    }
    return null;
  }

}
