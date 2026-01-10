package jss.advancedchat.listeners;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.MessageUtils;
import jss.advancedchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

public class TaskLoader {

    private final AdvancedChat plugin = AdvancedChat.get();
    private int task_ClearChat, task_GroupCheck;
    private final BukkitScheduler bukkitScheduler = Bukkit.getServer().getScheduler();

    public TaskLoader() {
        onClearChat();
        onGroupCheck();
    }

    //Clean the chat every so often, which is 20 minutes, this can be modified in the config
    public void onClearChat(){
        task_ClearChat = bukkitScheduler.scheduleSyncRepeatingTask(plugin, () -> {
            if(Settings.settings_clearchat_auto_clear){
                MessageUtils.sendColorMessage(Bukkit.getConsoleSender(), "Auto Clear Chat tacks");
            }else{
                bukkitScheduler.cancelTask(task_ClearChat);
            }
        },Settings.settings_clearchat_started_tick, Settings.settings_clearchat_period_tick);
    }

    // Detect a player party every 5 or 10 seconds, this can be changed in settings
    public void onGroupCheck(){
        task_GroupCheck = bukkitScheduler.scheduleSyncRepeatingTask(plugin, () -> {
            if(Settings.settings_group_auto_update){
                MessageUtils.sendColorMessage(Bukkit.getConsoleSender(), "Group Check Update tacks");
            }else{
                bukkitScheduler.cancelTask(task_GroupCheck);
            }
        }, Settings.settings_group_started_tick, Settings.settings_group_period_tick);
    }

}
