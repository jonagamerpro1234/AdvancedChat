package jss.advancedchat.listeners;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.player.PlayerFile;
import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.mysql.MySql;
import jss.advancedchat.update.UpdateChecker;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Perms;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class JoinListener implements Listener {

    private final AdvancedChat plugin = AdvancedChat.get();

    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onJoinPlayer(@NotNull PlayerJoinEvent e) {
        LuckPermsHook luckPermsHook = HookManager.get().getLuckPermsHook();
        Player j = e.getPlayer();
        String group;
        PlayerFile playerFile = new PlayerFile(plugin, j.getName());
        PlayerManager playerManager = new PlayerManager(j);
        playerFile.create();

        if (!luckPermsHook.isEnabled()) {
            Logger.error("&cThe LuckPerms could not be found to activate the group system");
            Logger.warning("&eplease check that LuckPerms is active or inside your plugins folder");
            group = "default";
        } else {
            group = LuckPermsHook.getApi().getUserManager().getUser(j.getName()).getPrimaryGroup();

            if (!playerManager.getGroup().equalsIgnoreCase(LuckPermsHook.getApi().getUserManager().getUser(j.getName()).getPrimaryGroup())) {
                playerManager.setGroup(LuckPermsHook.getApi().getUserManager().getUser(j.getName()).getPrimaryGroup());
            }
        }
        playerManager.create(j, group);

        if (Settings.mysql) {
            if (!MySql.existsInPlayerDataBase(j)) {
                MySql.createPlayer(j, group);
            } else {
                if (plugin.isDebug()) return;
                Logger.debug("The player already exists in the database");
            }
        }
    }

    @EventHandler
    public void onUpdatePlayer(@NotNull PlayerJoinEvent e) {
        Player j = e.getPlayer();
        if (Settings.update && j.isOp() && j.hasPermission(Perms.ac_update)) {
            new UpdateChecker(AdvancedChat.get(), 83889).getUpdateVersionSpigot(version -> {
                if (!AdvancedChat.get().getDescription().getVersion().equalsIgnoreCase(version)) {
                    Util.sendHover(j, "text", Util.getPrefix(true) + Settings.update_alert, Settings.update_alert_hover);
                }
            });
        }
    }

}