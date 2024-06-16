package jss.advancedchat.utils;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.utils.Settings;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

public class EventUtils {

    private final AdvancedChat plugin;

    public EventUtils(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public static @NotNull ConsoleCommandSender getConsoleSender() {
        return Bukkit.getConsoleSender();
    }


    public void initEvent(Listener @NotNull ... listeners) {
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
        } catch (NullPointerException e) {
            Logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}