package jss.advancedchat;

import jss.advancedchat.commands.utils.CommandManager;
import jss.advancedchat.commands.utils.CommandUtils;
import jss.advancedchat.utils.Utils;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedChat extends JavaPlugin {

    private static AdvancedChat instance;
    private static CommandManager commandManager;
    private final PluginDescriptionFile jss  = getDescription();
    public Metrics metrics;
    public String name = jss.getName();
    public String version = jss.getVersion();

    public void onLoad(){
        instance = this;
        Utils.sendLoad();
    }

    public void onEnable() {
        metrics = new Metrics(this,8826);

        //message
        Utils.sendEneble();

        registerCommandAndListeners();
    }

    public void onDisable() {
        Utils.sendDisable();
        instance = null;
        metrics = null;
    }

    public void registerCommandAndListeners(){
        commandManager = new CommandUtils();
        commandManager.registerCommandManager(this,"AdvancedChat");
    }

    public static AdvancedChat get(){
        return instance;
    }

    public static CommandManager getCommandManager(){
        return  commandManager;
    }
}
