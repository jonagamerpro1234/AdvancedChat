package jss.advancedchat.listeners.chat;

import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.jetbrains.annotations.NotNull;

public class CommandListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onCommandChat(@NotNull PlayerCommandPreprocessEvent e) {
        Player j = e.getPlayer();
        PlayerManager playerManager = new PlayerManager(j);

        if ((j.isOp()) || (j.hasPermission("AdvancedChat.CommandBlocker.Bypass"))) return;

        if (Settings.boolean_command_blocker) {
            if (Settings.boolean_command_blocker_disable_command) {
                for (String a : Settings.list_command_blocker_no_use) {
                    if (e.getMessage().toLowerCase().contains(a)) {
                        e.setCancelled(true);
                        Util.sendColorMessage(j, Settings.message_No_Use_Command.replace("<cmd>", a));
                        break;
                    }
                }
            }

            if (Settings.boolean_command_blocker_disable_command_mute && playerManager.isMute()) {
                for (String a : Settings.list_command_blocker_no_use_mute) {
                    if (e.getMessage().toLowerCase().contains(a)) {
                        e.setCancelled(true);
                        Util.sendColorMessage(j, Settings.message_No_Use_Command_Mute.replace("<cmd>", a));
                        break;
                    }
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onCommandDataLog(PlayerCommandPreprocessEvent e) {
    }

    @EventHandler(ignoreCancelled = true)
    public void onCommandLog(PlayerCommandPreprocessEvent e) {
    }

}
