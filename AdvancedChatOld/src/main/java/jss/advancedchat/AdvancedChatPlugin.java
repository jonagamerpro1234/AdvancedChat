package jss.advancedchat;

import jss.advancedchat.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.io.File;

public class AdvancedChatPlugin extends JavaPlugin {

    private final PluginDescriptionFile pluginDescriptionFile = getDescription();
    public final String name = pluginDescriptionFile.getName();
    public final String version = pluginDescriptionFile.getVersion();
    private final PluginManager pluginManager = Bukkit.getPluginManager();

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    public void registerListeners(@NotNull Listener @NotNull ... listeners) {
        try{
            for (Listener listener : listeners) {
                getPluginManager().registerEvents(listener, this);
            }
        }catch (Exception e){
            Logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("all")
    public void createFolder(@NotNull String name) {
        if (name.isEmpty()) {
            Logger.warning("Folder name cannot be null");
            Logger.info("Method: createFolder | Class: AdvancedChatPlugin");
            return;
        }
        File folder = new File(getDataFolder(), name);
        if (!folder.exists()) {
            try {
                folder.mkdir();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
