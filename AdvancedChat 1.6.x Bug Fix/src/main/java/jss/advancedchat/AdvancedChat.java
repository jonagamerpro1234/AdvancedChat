package jss.advancedchat;

import jss.advancedchat.commands.AdvancedChatCmd;
import jss.advancedchat.commands.ClearChatCmd;
import jss.advancedchat.commands.MuteCmd;
import jss.advancedchat.commands.UnMuteCmd;
import jss.advancedchat.config.ConfigManager;
import jss.advancedchat.files.utils.PreConfigLoader;
import jss.advancedchat.listeners.ChatListener;
import jss.advancedchat.listeners.EventLoader;
import jss.advancedchat.listeners.JoinListener;
import jss.advancedchat.listeners.utils.EventUtils;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.inventory.InventoryView;
import jss.advancedchat.utils.update.UpdateChecker;
import jss.advancedchat.utils.update.UpdateSettings;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.ArrayList;

public class AdvancedChat extends AdvancedChatPlugin {
    private static AdvancedChat plugin;

    private final Logger logger = new Logger();
    public final PreConfigLoader preConfigLoader = new PreConfigLoader(this);
    private final HookManager hookManager = new HookManager(this);
    private final PluginDescriptionFile jss = getDescription();
    public Metrics metrics;
    public String latestversion;
    public boolean isLegacyVersion = false;
    public EventUtils eventUtils;
    public String name = this.jss.getName();
    public String version = this.jss.getVersion();
    private ArrayList<InventoryView> InventoryView;

    public void onLoad() {
        Utils.setTitle(this.version);
        Utils.setLoad();
        this.eventUtils = new EventUtils(this);
        this.InventoryView = new ArrayList<>();
        Utils.setLineLoad("&eLoading EventUtils");
        Utils.setTitleLoad("&bLoading All Files");
        Utils.setLineLoad("&eLoad Config.yml");
        ConfigManager.createAllFiles();
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
        plugin = this;
        this.metrics = new Metrics(this, 8826);

        Utils.setEnabled(this.version);

        this.hookManager.load();

        setupCommandsAndEvents();

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

    private void setupCommandsAndEvents() {
        registerEvent(
                new JoinListener(),
                new ChatListener()
        );
        EventLoader eventLoader = new EventLoader(this);
        eventLoader.runClearChat();

        new AdvancedChatCmd();
        new ClearChatCmd();
        new MuteCmd();
        new UnMuteCmd();
    }


    public boolean isDebug() {
        return true;
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

    public static AdvancedChat getInstance() {
        return plugin;
    }

    public static AdvancedChat get() {
        return plugin;
    }

}
