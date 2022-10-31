package jss.advancedchat.v2.file;

import jss.advancedchat.v2.AdvancedChat;
import jss.advancedchat.v2.file.utils.FileCreator;
import jss.advancedchat.v2.utils.Settings;
import org.jetbrains.annotations.NotNull;

public class MessagesFile extends FileCreator {

    public MessagesFile(@NotNull AdvancedChat plugin) {
        super(plugin.getDataFolder(), "oldfiles/messages.yml");
    }

    public void preloadMessages(){
        Settings.msg_prefix = getConfig().getString("Prefix");
    }

}
