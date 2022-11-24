package jss.advancedchat;

import jss.advancedchat.commands.CommandHandler;
import jss.advancedchat.files.LangFile;
import jss.advancedchat.files.utils.PreConfigLoader;
import jss.advancedchat.listeners.TaskLoader;
import jss.advancedchat.utils.Utils;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class AdvancedChat extends JavaPlugin {

    private static AdvancedChat instance;
    private final PluginDescriptionFile jss  = getDescription();
    public Metrics metrics;
    public final String name = jss.getName();
    public final String version = jss.getVersion();
    private final PreConfigLoader preConfigLoader = new PreConfigLoader();

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
        new CommandHandler();
        new TaskLoader();
    }

    public static AdvancedChat get(){
        return instance;
    }

}
