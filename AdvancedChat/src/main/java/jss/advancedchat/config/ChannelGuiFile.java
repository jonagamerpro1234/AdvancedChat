package jss.advancedchat.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Logger.Level;
import jss.advancedchat.utils.file.FileManager;
import jss.advancedchat.utils.interfaces.IFileHelper;
import jss.advancedchat.utils.interfaces.IFolderHelper;

public class ChannelGuiFile extends FileManager implements IFileHelper, IFolderHelper {

    private AdvancedChat plugin;
    private File file;
    private FileConfiguration config;
    private String path;
    private String folderpath;
    private Logger logger = new Logger(plugin);

    public ChannelGuiFile(AdvancedChat plugin, String path, String folderpath) {
        super(plugin);
        this.plugin = plugin;
        this.file = null;
        this.config = null;
        this.path = path;
        this.folderpath = folderpath;
    }

    public String getFolderPath() {
        return this.folderpath;
    }

    public void create() {
        this.file = new File(getDataFolder() + File.separator + this.folderpath, this.path);
        if (!this.file.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    public FileConfiguration getConfig() {
        if (this.config == null) {
            reloadConfig();
        }
        return this.config;
    }

    public void saveConfig() {
        try {
            this.config.save(this.file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void reloadConfig() {
        if (this.config == null) {
            this.file = new File(getDataFolder() + File.separator + this.folderpath, this.path);
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
        Reader defaultConfigStream;
        try {
            defaultConfigStream = new InputStreamReader(getResources(this.path), "UTF8");
            BufferedReader in = new BufferedReader(defaultConfigStream);
            if (defaultConfigStream != null) {
                YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(in);
                config.setDefaults(defaultConfig);
            }
        }catch(UnsupportedEncodingException e) {
        	logger.Log(Level.ERROR, "!!Error Load File!! &b[&e"+this.path+"&b]");
        	e.printStackTrace();
        }catch(NullPointerException e) {
        	logger.Log(Level.ERROR, "!!Error Load File!! &b[&e"+this.path+"&b]");
        	e.printStackTrace();
        }catch(IllegalArgumentException e) {
        	logger.Log(Level.ERROR, "!!Error Load File!! &b[&e"+this.path+"&b]");
        	e.printStackTrace();
        }

    }

    public String getPath() {
        return this.path;
    }

    public AdvancedChat getPlugin() {
        return plugin;
    }

    public void saveDefaultConfig() {
        if (this.file == null) {
            this.file = new File(getDataFolder() + File.separator + this.folderpath, this.path);
        }
        if (!this.file.exists()) {
            saveResources(this.path, false);
        }
    }

    public void resetConfig() {
        if (this.file == null) {
            this.file = new File(getDataFolder() + File.separator + this.folderpath, this.path);
        }
        if (!this.file.exists()) {
            saveResources(this.path, true);
        }
    }

    public boolean isFileExists() {
        this.file = new File(getDataFolder() + File.separator + this.folderpath, this.path);
        return this.file.exists();
    }

}
