package jss.advancedchat.listeners;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.json.manager.JsonPlayerStorage;
import jss.advancedchat.storage.json.model.PlayerData;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.files.utils.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class TaskLoader {

    private final AdvancedChat plugin = AdvancedChat.get();
    private final EventUtils eventUtils = new EventUtils();
    private int chatTaskID;
    private int groupTaskID;

    public TaskLoader() {
        this.onClearChat();
        this.onUpdateGroup();
    }

    private void onClearChat() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        chatTaskID = scheduler.scheduleSyncRepeatingTask(plugin, () -> {
            if (Settings.boolean_chatclear_autoclear) {
                eventUtils.getClearChatAction();
                eventUtils.getServerMessage();
            } else {
                scheduler.cancelTask(chatTaskID);
            }
        }, Settings.clearchat_started_ticks, Settings.clearchat_delay_ticks);
    }

    private void updateGroup(){
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        JsonPlayerStorage storage = new JsonPlayerStorage(plugin.getJsonPlayerFile());
        PlayerManager manager = new PlayerManager(storage);

        for( Player p : Bukkit.getOnlinePlayers()) {
            PlayerData data = manager.loadPlayerData(p);

            if(data == null) break;

            groupTaskID = scheduler.scheduleSyncRepeatingTask(plugin, () -> {
                LuckPermsHook hook = HookManager.get().getLuckPermsHook();

                if(hook.isEnabled() && Settings.hook_luckperms_autoUpdate_group) {



                }else {
                    scheduler.cancelTask(groupTaskID);
                }

            }, 100L, 800L);
        }
    }

    private void onUpdateGroup() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        for (Player p : Bukkit.getOnlinePlayers()) {
            /*PlayerManager playerManager = new PlayerManager(p);

            groupTaskID = scheduler.scheduleSyncRepeatingTask(plugin, () -> {
                LuckPermsHook hook = HookManager.get().getLuckPermsHook();
                if (hook.isEnabled() && Settings.hook_luckperms_autoUpdate_group) {
                    String group = Objects.requireNonNull(LuckPermsHook.getApi().getUserManager().getUser(p.getName())).getPrimaryGroup();

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
            }, 0, 600L);
*/
        }
    }

}
