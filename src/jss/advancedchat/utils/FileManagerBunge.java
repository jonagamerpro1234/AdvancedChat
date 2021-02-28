package jss.advancedchat.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import jss.advancedchat.bungee.AdvancedChat;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;


public class FileManagerBunge {

    private AdvancedChat plugin;

    public FileManagerBunge(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public void createVoidFolder(String name) {
        File folder = new File(getDataFolder(), name);
        if (!folder.exists()) {
            try {
                folder.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void createFolderAndFile(String namefolder, String namefile) {
        File folder = new File(getDataFolder(), namefolder);
        if (!folder.exists()) {
            try {
                folder.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        File file = new File(folder, namefile);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public ConfigurationProvider getConfigProvider() {
        return ConfigurationProvider.getProvider(YamlConfiguration.class);
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public void saveResources(Configuration saveconfig, File savefile) {
        try {
            getConfigProvider().save(saveconfig, savefile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InputStream getResources(String filename) {
        return plugin.getResourceAsStream(filename);
    }

    public File getDataFolder() {
        return plugin.getDataFolder();
    }
}
