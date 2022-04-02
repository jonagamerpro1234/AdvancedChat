package jss.advancedchat.listeners;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.common.update.UpdateSettings;
import jss.advancedchat.config.ChatDataFile;
import jss.advancedchat.config.ChatLogFile;
import jss.advancedchat.config.CommandLogFile;
import jss.advancedchat.config.player.PlayerFile;
import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.MySQL;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.UpdateChecker;
import jss.advancedchat.utils.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private AdvancedChat plugin;

    public JoinListener(AdvancedChat plugin) {
        this.plugin = plugin;
    }
    
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
        playerFile.create();
        
        PlayerManager playerManager = new PlayerManager(j);
        playerManager.create(j, group);
        
        if(!playerManager.getGroup().equalsIgnoreCase(LuckPermsHook.getApi().getUserManager().getUser(j.getName()).getPrimaryGroup())) {
        	playerManager.setGroup(LuckPermsHook.getApi().getUserManager().getUser(j.getName()).getPrimaryGroup());
        }
        
        if (Settings.mysql_use) if(!MySQL.existsPlayer(plugin, j.getUniqueId().toString())) MySQL.createPlayer(plugin, j.getName(), j.getUniqueId().toString(), Settings.default_color, "FFFFFF", "FFFFFF", false);

        if (!chat.contains("Players." + j.getName())) {
            chat.set("Players." + j.getName() + ".UUID", j.getUniqueId().toString());
            chat.set("Players." + j.getName() + ".Log", null);
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


    @SuppressWarnings("deprecation")
    @EventHandler
    public void onUpdatePlayer(PlayerJoinEvent e) {
        FileConfiguration config = plugin.getConfigFile().getConfig();
        Player j = e.getPlayer();

        if (config.getString("Settings.Update").equals("true")) {
            if (j.isOp() && j.hasPermission("AdvancedChat.Update.Notify")) {
                new UpdateChecker(AdvancedChat.get(), 83889).getUpdateVersionSpigot(version -> {
                    if (!AdvancedChat.get().getDescription().getVersion().equalsIgnoreCase(version)) {
                        TextComponent component = new TextComponent(Utils.color(Utils.getPrefix(true) + " &aThere is a new version available for download"));
                        component.setClickEvent(new ClickEvent(Action.OPEN_URL, UpdateSettings.URL_PLUGIN[0]));
                        component.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utils.color("&6Click on this message to copy the link")).create()));
                        j.spigot().sendMessage(component);
                    }
                });
            }
        }
    }
    
}
