package jss.advancedchat.utils.update;

import jss.advancedchat.AdvancedChat;

public class UpdateSettings {
    public static String[] URL_PLUGIN = new String[]{"https://www.spigotmc.org/resources/advancedchat-1-7-x-1-16-x.83889/", "https://songoda.com/marketplace/product/advancedchat-chat-related.542", "https://github.com/jonagamerpro1234/AdvancedChat/releases"};
    public static String SPIGOT_UPDATE_API = "https://api.spigotmc.org/legacy/update.php?resource=";
    private static final AdvancedChat plugin = AdvancedChat.getInstance();
    public static String VERSION = plugin.version;
}
