package jss.advancedchat.v2.file;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.v2.file.utils.FileCreator;
import org.jetbrains.annotations.NotNull;

public class GroupFile extends FileCreator {

    public GroupFile(@NotNull AdvancedChat plugin) {
        super(plugin.getDataFolder(), "oldfiles/groups.yml");
    }


}
