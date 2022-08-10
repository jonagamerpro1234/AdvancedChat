package jss.advancedchat.listeners;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.config.ChatDataFile;
import jss.advancedchat.config.ChatLogFile;
import jss.advancedchat.config.CommandLogFile;
import jss.advancedchat.config.player.PlayerFile;
import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.MySQL;
import jss.advancedchat.storage.TableType;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Perms;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.UpdateChecker;
import jss.advancedchat.utils.Util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private AdvancedChat plugin = AdvancedChat.get();
    
    @EventHandler
    public void onJoinPlayer(PlayerJoinEvent e) {
        ChatDataFile chatDataFile = plugin.getChatDataFile();
        FileConfiguration chat = chatDataFile.getConfig();
        ChatLogFile chatLogFile = plugin.getChatLogFile();
        FileConfiguration chatlog = chatLogFile.getConfig();
        CommandLogFile commandLogFile = plugin.getCommandLogFile();
        FileConfiguration command = commandLogFile.getConfig();
		LuckPermsHook luckPermsHook = HookManager.get().getLuckPermsHook();
        
        Player j = e.getPlayer();
        
		String group = "";
		
		if (luckPermsHook.isEnabled()) {
			Logger.error("&cThe LuckPerms could not be found to activate the group system");
			Logger.warning("&eplease check that LuckPerms is active or inside your plugins folder");
		}

		if (Settings.hook_luckperms_use_group) {
			group = LuckPermsHook.getApi().getUserManager().getUser(j.getName()).getPrimaryGroup();
		} else {
			group = "default"; 
		}
        
        PlayerFile playerFile = new PlayerFile(plugin, j.getName());
        PlayerManager playerManager = new PlayerManager(j);
        playerFile.create();
        playerManager.create(j, group);
        
        if(!playerManager.getGroup().equalsIgnoreCase(LuckPermsHook.getApi().getUserManager().getUser(j.getName()).getPrimaryGroup())) {
        	playerManager.setGroup(LuckPermsHook.getApi().getUserManager().getUser(j.getName()).getPrimaryGroup());
        }
        
//        if (Settings.mysql) {
//        	if(!plugin.getMySQL().existsPlayer(TableType.Data,j.getUniqueId().toString())) {
//        		plugin.getMySQL().createDataPlayer(j, group, Settings.default_color, "FFFFFF", "FFFFFF", "rainbow_1", "none");
//        		plugin.getMySQL().createSettingsPlayer(j, false, false, true, true, true);
//        	}
//        }*/

        if (!chat.contains("Players." + j.getName())) {
            chat.set("Players." + j.getName() + ".UUID", j.getUniqueId().toString());
            chat.set("Players." + j.getName() + ".Log", "[]");
            chatDataFile.saveConfig();
        }

        if (!chatlog.contains("Players." + j.getName())) {
            chatlog.set("Players." + j.getName() + ".UUID", j.getUniqueId().toString());
            chatlog.set("Players." + j.getName() + ".Chat", "[]");
            chatLogFile.saveConfig();
        }

        if (!command.contains("Players." + j.getName())) {
            command.set("Players." + j.getName() + ".UUID", j.getUniqueId().toString());
            command.set("Players." + j.getName() + ".Command", "[]");
            commandLogFile.saveConfig();
        }
    }


    @EventHandler
    public void onUpdatePlayer(PlayerJoinEvent e) {
        Player j = e.getPlayer();

        if (Settings.update && j.isOp() && j.hasPermission(Perms.ac_update)) {
                new UpdateChecker(AdvancedChat.get(), 83889).getUpdateVersionSpigot(version -> {
                    if (!AdvancedChat.get().getDescription().getVersion().equalsIgnoreCase(version)) {
                        Util.sendHoverEvent(j, "text", Util.getPrefix(true) + Settings.update_alert, Settings.update_alert_hover);
                    }
                });
        }
    }
 
}