package jss.advancedchat.file;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.file.utils.FileCreator;
import jss.advancedchat.utils.Settings;
import org.jetbrains.annotations.NotNull;

public class MessagesFile extends FileCreator {

    public MessagesFile(@NotNull AdvancedChat plugin) {
        super(plugin.getDataFolder(), "messages.yml");
    }

    public void preloadMessages(){
        Settings.msg_prefix = getConfig().getString("Prefix");
    }

}
