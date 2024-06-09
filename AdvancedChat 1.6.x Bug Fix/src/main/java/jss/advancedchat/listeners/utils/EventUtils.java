package jss.advancedchat.listeners.utils;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

public class EventUtils {
    private final AdvancedChat plugin;

    public EventUtils(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public static @NotNull CommandSender getStaticConsoleSender() {
        return Bukkit.getConsoleSender();
    }

    public void addEventList(Listener listener) {
        getEventManager().registerEvents(listener, this.plugin);
    }

    public PluginManager getEventManager() {
        return Bukkit.getPluginManager();
    }

    public ConsoleCommandSender getConsoleSender() {
        return Bukkit.getConsoleSender();
    }

    public void getServerMessage(@NotNull FileConfiguration config) {
        String path = "Settings.Use-Default-Prefix";
        String text = config.getString("AdvancedChat.ClearChat-Server");
        Utils.sendColorMessage(Utils.getPrefixPlayer() + text);
    }

    public void getPlayerMessage(Player player, @NotNull FileConfiguration config) {
        String path = "Settings.Use-Default-Prefix";
        String text = config.getString("AdvancedChat.ClearChat-Player");
        String prefix = config.getString("Settings.Prefix");
        text = Utils.getVar(player, text);
        Utils.sendColorMessage(player, Utils.getPrefixPlayer() + text);
    }

    public void getClearChatAction(@NotNull String type) {
        if (type.equalsIgnoreCase("player")) {
            loopVoidChat(100);
        } else if (type.equalsIgnoreCase("server")) {
            loopVoidChat(100);
        }
    }

    public void loopVoidChat(int value) {
        try {
            for (int i = 0; i < value; i++) {
                Bukkit.broadcastMessage(" ");
            }
        } catch (NullPointerException e) {
            Logger.error(e.getMessage());
        }
    }
}
