package jss.advancedchat.files;

import jss.advancedchat.AdvancedChat;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class FileCreator {

    private final AdvancedChat plugin = AdvancedChat.get();
    private File file;
    private YamlConfiguration config;

    public void createFile(String name) {
        this.file = new File(plugin.getDataFolder(), name + ".yml");

        if (!plugin.getDataFolder().exists()) {
            //noinspection ResultOfMethodCallIgnored
            plugin.getDataFolder().mkdirs();
        }

        if (!this.file.exists()) {
            try {
                //noinspection ResultOfMethodCallIgnored
                this.file.getParentFile().mkdirs();
                //noinspection ResultOfMethodCallIgnored
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.config = YamlConfiguration.loadConfiguration(file);
        } else {
            try {
                throw new IOException();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean saveFile(@NotNull YamlConfiguration config, String name) {
        try {
            config.save(getFile(name));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public File getFile(String name) {
        this.file = new File(plugin.getDataFolder(), name + ".yml");
        return this.file;
    }

    public YamlConfiguration getConfig(String name) {
        this.file = new File(plugin.getDataFolder(), name + ".yml");
        this.config = YamlConfiguration.loadConfiguration(this.file);
        return this.config;
    }

}
