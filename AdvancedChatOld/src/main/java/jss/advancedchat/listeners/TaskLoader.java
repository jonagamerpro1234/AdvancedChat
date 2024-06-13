package jss.advancedchat.listeners;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.mysql.MySql;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class TaskLoader {

    private AdvancedChat plugin = AdvancedChat.get();
    private EventUtils eventUtils = new EventUtils(plugin);
    private int chatTaskID;
    private int groupTaskID;

    public TaskLoader() {
        this.onClearChat();
        this.onUpdateGroup();
    }

    private void onClearChat() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        chatTaskID = scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
            public void run() {
                if (Settings.boolean_chatclear_autoclear) {
                    eventUtils.getClearChatAction();
                    eventUtils.getServerMessage();
                } else {
                    scheduler.cancelTask(chatTaskID);
                }
            }
        }, Settings.long_clearchat_start_tick, Settings.long_clearchat_tick);
    }

    private void onUpdateGroup() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        for (Player p : Bukkit.getOnlinePlayers()) {
            PlayerManager playerManager = new PlayerManager(p);

            groupTaskID = scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
                public void run() {
                    LuckPermsHook hook = HookManager.get().getLuckPermsHook();
                    if (hook.isEnabled() && Settings.hook_luckperms_autoupdate_group) {
                        String group = LuckPermsHook.getApi().getUserManager().getUser(p.getName()).getPrimaryGroup();

                        if (Settings.mysql) {
                            if (MySql.existsInPlayerDataBase(p)) {
                                MySql.setGroup(p, group);
                            }
                        } else {
                            if (!playerManager.getGroup().equalsIgnoreCase(group)) {
                                playerManager.setGroup(group);
                            } else {
                                if (plugin.isDebug()) return;
                                Logger.debug("&eThe player already has the same group!");
                            }
                        }
                    } else {
                        Logger.debug("&eTask: LuckPerms Update Group Cancel!");
                        scheduler.cancelTask(groupTaskID);
                    }
                }
            }, 0, 600L);

        }
    }

}
