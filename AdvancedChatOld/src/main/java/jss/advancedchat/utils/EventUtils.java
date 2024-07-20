package jss.advancedchat.utils;

import jss.advancedchat.files.utils.Settings;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

public class EventUtils {

    public static @NotNull ConsoleCommandSender getConsoleSender() {
        return Bukkit.getConsoleSender();
    }

    public void getServerMessage() {
        Util.sendColorMessage(Util.getPrefix(false) + Settings.message_ClearChat_Server);
    }

    public void getPlayerMessage(Player player) {
        Util.sendColorMessage(Util.getPrefix(false) + Util.getVar(player, Settings.message_ClearChat_Player));
    }

    public void getClearChatAction() {
        this.loopVoidChat(Settings.clearchat_lines);
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