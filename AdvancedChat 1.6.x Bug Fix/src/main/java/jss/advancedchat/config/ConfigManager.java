package jss.advancedchat.config;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.*;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private final static AdvancedChat plugin = AdvancedChat.get();
    private final static ConfigFile configfile = new ConfigFile(plugin, "config.yml");
    private final static ColorFile colorFile = new ColorFile(plugin, "color-gui.yml", "Gui");
    private final static PlayerGuiFile playerGuiFile = new PlayerGuiFile(plugin, "player-gui.yml", "Gui");
    private final static ChatLogFile chatLogFile = new ChatLogFile(plugin, "chat.yml", "Log");
    private final static CommandLogFile commandLogFile = new CommandLogFile(plugin, "command.yml", "Log");
    
    public static void createAllFiles(){
        configfile.saveDefault();
        configfile.create();
        colorFile.create();
        playerGuiFile.create();
        chatLogFile.create();
        commandLogFile.create();
    }
    
    public static void reloadAllFiles(){
        configfile.reload();
        colorFile.reload();
        playerGuiFile.reload();
        chatLogFile.reload();
        commandLogFile.reload();
        plugin.preConfigLoader.load();
    }

    public static FileConfiguration getConfig(){
        return configfile.config();
    }

    public static FileConfiguration getColorGuiConfig(){
        return colorFile.config();
    }

    public static FileConfiguration getPlayerGuiConfig(){
        return playerGuiFile.config();
    }

    public static FileConfiguration getChatLogConfig(){
        return chatLogFile.config();
    }

    public static FileConfiguration getCommandLogConfig(){
        return commandLogFile.config();
    }

    public static ConfigFile getConfigFile() {
        return configfile;
    }

    public static ColorFile getColorFile() {
        return colorFile;
    }

    public static PlayerGuiFile getPlayerGuiFile() {
        return playerGuiFile;
    }

    public static ChatLogFile getChatLogFile() {
        return chatLogFile;
    }

    public static CommandLogFile getCommandLogFile() {
        return commandLogFile;
    }
    
}
