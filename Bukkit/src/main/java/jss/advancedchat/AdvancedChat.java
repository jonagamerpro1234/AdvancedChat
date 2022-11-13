package jss.advancedchat;

import jss.advancedchat.commands.CommandHandler;
import jss.advancedchat.utils.Utils;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
public final class AdvancedChat extends JavaPlugin {

    private static AdvancedChat instance;
    private final PluginDescriptionFile jss  = getDescription();
    public Metrics metrics;
    public final String name = jss.getName();
    public final String version = jss.getVersion();

    public void onLoad(){
        instance = this;
        Utils.sendLoad();
    }
    public void onEnable() {
        metrics = new Metrics(this,8826);

        //message
        Utils.sendEnable();

        registerCommandAndListeners();
    }

    public void onDisable() {
        Utils.sendDisable();
        instance = null;
        metrics = null;
    }

    public void registerCommandAndListeners(){
        new CommandHandler();
    }

    public static AdvancedChat get(){
        return instance;
    }

}
