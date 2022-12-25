package jss.advancedchat;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class AdvancedChatPlugin extends JavaPlugin {

    public void registerEvent(Listener @NotNull ... listeners) {
        for (Listener listener : listeners) {
            getPluginManager().registerEvents(listener, this);
        }
    }

    public PluginManager getPluginManager() {
        return Bukkit.getPluginManager();
    }

    public void createVoidFolder(String name) {
        File folder = new File(getDataFolder(), name);
        if (!folder.exists())
            try {
                folder.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void createFolderAndFile(String namefolder, String namefile) {
        File folder = new File(getDataFolder(), namefolder);
        if (!folder.exists())
            try {
                folder.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        File file = new File(folder, namefile);
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public ServicesManager getServiceManager() {
        return Bukkit.getServicesManager();
    }

    public <T> RegisteredServiceProvider<T> getRegistration(Class<T> clazz) {
        return getServiceManager().getRegistration(clazz);
    }
}
