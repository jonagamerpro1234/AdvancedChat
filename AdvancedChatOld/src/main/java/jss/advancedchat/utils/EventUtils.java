package jss.advancedchat.utils;

import jss.advancedchat.AdvancedChat;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class EventUtils {

    private final AdvancedChat plugin;

    public EventUtils(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public static ConsoleCommandSender getConsoleSender() {
        return Bukkit.getConsoleSender();
    }

    public void initEvent(Listener... listeners) {
        for (Listener listener : listeners) {
            getEventManager().registerEvents(listener, plugin);
        }
    }

    public PluginManager getEventManager() {
        return Bukkit.getPluginManager();
    }

    public void getServerMessage() {
        Util.sendColorMessage(Util.getPrefix(false) + Settings.message_ClearChat_Server);
    }

    public void getPlayerMessage(Player player) {
        Util.sendColorMessage(Util.getPrefix(false) + Util.getVar(player, Settings.message_ClearChat_Player));
    }

    public void getClearChatAction() {
        this.loopVoidChat(Settings.int_clearchat_lines);
    }

    public void loopVoidChat(int value) {
        try {
            for (int i = 0; i < value; ++i) {
                Bukkit.broadcastMessage(" ");
            }
        } catch (NullPointerException var3) {
            var3.printStackTrace();
        }
    }

}