package jss.advancedchat.v2.file;

import jss.advancedchat.v2.AdvancedChat;
import jss.advancedchat.v2.file.utils.FileCreator;
import org.jetbrains.annotations.NotNull;

public class SettingsFile extends FileCreator {

    public SettingsFile(@NotNull AdvancedChat plugin) {
        super(plugin.getDataFolder(), "settings.yml");
    }

    public void preloadSettigns(){

    }
}
