package jss.advancedchat;

import jss.advancedchat.commands.CommandHandler;
import jss.advancedchat.commands.MuteCmd;
import jss.advancedchat.files.utils.PreConfigLoader;
import jss.advancedchat.listeners.JoinListener;
import jss.advancedchat.listeners.TaskLoader;
import jss.advancedchat.listeners.chat.ChatListener;
import jss.advancedchat.storage.json.JsonStorage;
import jss.advancedchat.storage.yaml.YamlStorage;
import jss.advancedchat.utils.Utils;
import jss.commandapi.CommandApi;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class AdvancedChat extends JavaPlugin {

    private static AdvancedChat instance;
    private final PluginDescriptionFile jss  = getDescription();
    public Metrics metrics;
    public final String name = jss.getName();
    public final String version = jss.getVersion();
    private final PreConfigLoader preConfigLoader = new PreConfigLoader();
    private final JsonStorage jsonStorage = new JsonStorage(this);
    private final YamlStorage yamlStorage = new YamlStorage(this);
    private BukkitAudiences adventure;

    public void onLoad(){
        instance = this;
        Utils.sendLoad();
    }

    public void onEnable() {
        metrics = new Metrics(this,8826);
        this.adventure = BukkitAudiences.create(this);

        //preConfigLoader.loadSettings();
        if(!preConfigLoader.loadLangs()){
            Bukkit.getPluginManager().disablePlugin(this);
        }
        //yamlStorage.create("jonagamerpro1234");
        jsonStorage.save("jonagamerpro1234");
        jsonStorage.create("jonagamerpro1234");
        jsonStorage.save("test_000");
        jsonStorage.create("test_000");


        //message
        //Utils.sendEnable();

        //Command Api
        CommandApi.init(this);
        CommandApi.setDebug(true);

        // commands and listeners
        registerCommandAndListeners();
    }

    public void onDisable() {
        Utils.sendDisable();
        instance = null;
        metrics = null;
        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }

    public void registerCommandAndListeners(){

        //Listeners (Events
        registerListeners(
                new JoinListener(),
                new ChatListener()
        );

        //Commands init
        new CommandHandler().register();
        new MuteCmd().register(this);

        //Init Task
        new TaskLoader();
    }

    public BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }

    //main instance
    public static AdvancedChat get(){
        return instance;
    }

    /**
     * Registers one or more Bukkit event listeners.
     * <p>
     * This method iterates through all provided Listener instances
     * and registers them in the Bukkit PluginManager using the current
     * plugin instance.
     * <p>
     * Using varargs allows registering multiple listeners in a single call,
     * improving code readability and reducing repetition in onEnable().
     *
     * @param listeners One or more classes that implement the Listener interface.
     *                  This parameter must not be null.
     */
    private void registerListeners(Listener @NotNull ...listeners) {
        // Iterate through each listener provided
        for (Listener listener : listeners) {
            // Register the listener to the Bukkit event system
            // 'this' refers to the current JavaPlugin instance
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }


}
