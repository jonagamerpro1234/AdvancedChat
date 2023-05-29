package jss.advancedchat;

import jss.advancedchat.commands.CommandHandler;
import jss.advancedchat.files.utils.PreConfigLoader;
import jss.advancedchat.listeners.JoinListener;
import jss.advancedchat.listeners.TaskLoader;
import jss.advancedchat.listeners.chat.ChatListener;
import jss.advancedchat.storage.json.JsonStorage;
import jss.advancedchat.storage.yaml.YamlStorage;
import jss.advancedchat.utils.Utils;
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

    public void onLoad(){
        instance = this;
        Utils.sendLoad();
    }

    public void onEnable() {
        metrics = new Metrics(this,8826);

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

        registerCommandAndListeners();
    }

    public void onDisable() {
        Utils.sendDisable();
        instance = null;
        metrics = null;
    }

    public void registerCommandAndListeners(){
        registerListeners(
                new JoinListener(),
                new ChatListener()
        );
        new CommandHandler();
        new TaskLoader();
    }

    public static AdvancedChat get(){
        return instance;
    }

    private void registerListeners(Listener @NotNull ...listeners){
        for(Listener listener : listeners){
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }

}
