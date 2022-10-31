package jss.advancedchat.v2.file;

import jss.advancedchat.AdvancedChat;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class PlayerFile {

    private final AdvancedChat plugin = AdvancedChat.get();
    private File dir;
    private File file;
    private FileConfiguration config;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void create(Player player){
        dir = new File(plugin.getDataFolder(), "Players");

        if(!dir.isFile()){
            dir.mkdir();
        }

        file = new File(dir, player.getName() + ".yml");

        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public FileConfiguration getConfig(@NotNull Player player){
        file = new File(dir, player.getName() + ".yml");
        config = YamlConfiguration.loadConfiguration(file);
        return config;
    }

    public void save(){
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
