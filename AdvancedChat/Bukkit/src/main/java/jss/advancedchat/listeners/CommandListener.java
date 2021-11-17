package jss.advancedchat.listeners;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.config.ChatDataFile;
import jss.advancedchat.config.CommandLogFile;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Utils;

public class CommandListener implements Listener {

    private AdvancedChat plugin;

    public CommandListener(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommandChat(PlayerCommandPreprocessEvent e) {
        FileConfiguration config = plugin.getConfigFile().getConfig();
        Player j = e.getPlayer();
        List<String> list = config.getStringList("Command-Blocker.BlackList");
        String message = e.getMessage();
        String nousecommand = config.getString("AdvancedChat.No-Use-Command");
        String nousecommandmute = config.getString("AdvancedChat.No-Use-Command-Mute");
        String path = "Command-Blocker.Enabled";
        List<String> mutelist = config.getStringList("Command-Blocker.Mute-BlackList");
        
        if((j.isOp()) || (j.hasPermission("AdvancedChat.Chat.Bypass.Commmand"))) return;
        
        if (config.getString(path).equals("true")) {
            for (String a : list) {
                if (message.toLowerCase().contains(a)) {
                    e.setCancelled(true);
                    Utils.sendColorMessage(j, nousecommand.replace("<cmd>", a));
                    break;
                }
            }
        }
        if (PlayerManager.isMute(j)) {
            for (String a : mutelist) {
                if (message.toLowerCase().contains(a)) {
                    e.setCancelled(true);
                    Utils.sendColorMessage(j, nousecommandmute.replace("<cmd>", a));
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onCommandDataLog(PlayerCommandPreprocessEvent e) {
        ChatDataFile chatDataFile = plugin.getChatDataFile();
        FileConfiguration config = chatDataFile.getConfig();
        Player j = e.getPlayer();

        String date = Utils.getDate(System.currentTimeMillis());
        String time = Utils.getTime(System.currentTimeMillis());

        config.set(j.getName() + ".Log." + date + ".Command." + time, Utils.colorless(e.getMessage()));
        chatDataFile.saveConfig();
        //plugin.setChatManagers(new ChatManager(date, time, e.getMessage(), j));
    }

    @EventHandler
    public void onCommandLog(PlayerCommandPreprocessEvent e) {
        CommandLogFile commandLogFile = plugin.getCommandLogFile();
        FileConfiguration config = commandLogFile.getConfig();
        Player j = e.getPlayer();

        String date = Utils.getDate(System.currentTimeMillis());
        String time = Utils.getTime(System.currentTimeMillis());

        config.set("Players." + j.getName() + ".Command." + date + "." + time, Utils.colorless(e.getMessage()));
        commandLogFile.saveConfig();
    }


}
