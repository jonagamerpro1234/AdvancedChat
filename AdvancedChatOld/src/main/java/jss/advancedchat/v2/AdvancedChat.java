package jss.advancedchat.v2;

import jss.advancedchat.v2.api.AdvancedChatApi;
import jss.advancedchat.v2.file.MessagesFile;
import jss.advancedchat.v2.file.SettingsFile;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedChat extends JavaPlugin {

    private static AdvancedChat instance;
    private SettingsFile settingsConfig;
    private MessagesFile messageConfig;

    private AdvancedChatApi advancedChatApi;

    public void onEnable() {
        instance = this;
        advancedChatApi = new AdvancedChatApi();
        new Metrics(this, 8826);

        settingsConfig = new SettingsFile(this);
        settingsConfig.preloadSettigns();
        messageConfig = new MessagesFile(this);
        messageConfig.preloadMessages();

    }

    public void onDisable() {
        instance = null;
        getServer().getScheduler().cancelTasks(this);
    }

    public SettingsFile getSettingsFile() {
        return settingsConfig;
    }

    public MessagesFile getMessageFile() {
        return messageConfig;
    }

    public AdvancedChatApi getApi() {
        return advancedChatApi;
    }

    public static AdvancedChat get() {
        return instance;
    }
}
