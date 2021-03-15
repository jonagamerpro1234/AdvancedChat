package jss.advancedchat.utils;

import jss.advancedchat.AdvancedChat;

public class UpdateSettings {

    private static AdvancedChat plugin = AdvancedChat.getPlugin();
    public static String[] URL_PLUGIN = new String[] {"https://www.spigotmc.org/resources/advancedchat-1-7-x-1-16-x.83889/","https://songoda.com/marketplace/product/advancedchat-chat-related.542"};
    public static int[] ID = new int[] {83889,542};
    public static String NAME = plugin.name;
    public static String VERSION = plugin.version;
    public static String SPIGOT_UPDATE_API = "https://api.spigotmc.org/legacy/update.php?resource=";
    
}
