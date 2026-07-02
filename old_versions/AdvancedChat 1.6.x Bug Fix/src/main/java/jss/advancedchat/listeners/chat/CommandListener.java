package jss.advancedchat.listeners.chat;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.ConfigManager;
import jss.advancedchat.files.CommandLogFile;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommandListener implements Listener {
    private final AdvancedChat plugin = AdvancedChat.get();

    public CommandListener() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCommandChat(@NotNull PlayerCommandPreprocessEvent e) {
        FileConfiguration config = ConfigManager.getConfig();

        Player j = e.getPlayer();
        PlayerManager manager = new PlayerManager(j);
        List<String> list = config.getStringList("Command-Blocker.BlackList");
        List<String> muteList = config.getStringList("Command-Blocker.Mute-BlackList");
        String message = e.getMessage();
        String noUseCommand = config.getString("AdvancedChat.No-Use-Command");
        String noUseCommandMute = config.getString("AdvancedChat.No-Use-Command-Mute");
        String path = "Command-Blocker.Enabled";
       /* if (j.isOp() || j.hasPermission("AdvancedChat.Chat.Bypass"))
            return;
        if (config.getBoolean(path))
            for (String a : list) {
                if (message.toLowerCase().contains(a)) {
                    e.setCancelled(true);
                    assert noUseCommand != null;
                    Utils.sendColorMessage(j, Utils.getVar(j, noUseCommand.replace("<cmd>", a)));
                    break;
                }
            }
        if (manager.isMute(j))
            for (String a : muteList) {
                if (message.toLowerCase().contains(a)) {
                    e.setCancelled(true);
                    assert noUseCommandMute != null;
                    Utils.sendColorMessage(j, Utils.getVar(j, noUseCommandMute.replace("<cmd>", a)));
                    break;
                }
            }*/
    }

    @EventHandler
    public void onCommandLog(@NotNull PlayerCommandPreprocessEvent e) {
        CommandLogFile commandLogFile = ConfigManager.getCommandLogFile();
        FileConfiguration config  = commandLogFile.config();
        Player j = e.getPlayer();
        String date = Utils.getDate(System.currentTimeMillis());
        String time = Utils.getTime(System.currentTimeMillis());
        config.set("Players." + j.getName() + ".Command." + date + "." + time, Utils.colorless(e.getMessage()));
        commandLogFile.save();
    }

}
