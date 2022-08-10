package jss.advancedchat.file;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.file.utils.FileCreator;
import org.jetbrains.annotations.NotNull;

public class GroupFile extends FileCreator {

    public GroupFile(@NotNull AdvancedChat plugin) {
        super(plugin.getDataFolder(), "groups.yml");
    }


}
