package jss.advancedchat.file;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.file.utils.FileCreator;
import org.jetbrains.annotations.NotNull;

public class SettingsFile extends FileCreator {

    public SettingsFile(@NotNull AdvancedChat plugin) {
        super(plugin.getDataFolder(), "settings.yml");
    }

    public void preloadSettigns(){

    }
}
