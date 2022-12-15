package jss.advancedchat.files;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.utils.FileManager;
import jss.advancedchat.utils.interfaces.FileHelper;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class ColorFile extends FileManager {
    private final AdvancedChat plugin;

    private File file;

    private FileConfiguration config;

    private final String path;

    private final String folderpath;

    public ColorFile(AdvancedChat plugin, String path, String folderpath) {
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
            config().options().copyDefaults(true);
            save();
        }
    }

    public FileConfiguration config() {
        if (this.config == null)
            reload();
        return this.config;
    }

    public void save() {
        try {
            this.config.save(this.file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void reload() {
        if (this.config == null)
            this.file = new File(getDataFolder() + File.separator + this.folderpath, this.path);
        this.config = YamlConfiguration.loadConfiguration(this.file);
        try {
            Reader defaultConfigStream = new InputStreamReader(getResources(this.path), StandardCharsets.UTF_8);
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultConfigStream);
            this.config.setDefaults(defaultConfig);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void saveDefault() {
        if (this.file == null)
            this.file = new File(getDataFolder() + File.separator + this.folderpath, this.path);
        if (!this.file.exists())
            saveResources(this.path, false);
    }

}
