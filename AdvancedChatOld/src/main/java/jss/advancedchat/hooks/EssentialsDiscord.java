package jss.advancedchat.hooks;

import jss.advancedchat.manager.HookManager;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.interfaces.IHook;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EssentialsDiscord implements IHook {

    private final HookManager hooksManager;
    private boolean isEnabled;

    public EssentialsDiscord(HookManager hooksManager) {
        this.hooksManager = hooksManager;
    }

    public void setup() {
        if (!Bukkit.getPluginManager().isPluginEnabled("EssentialsDiscord")) {
            this.isEnabled = false;
            Logger.warning("&eLuckPerms not enabled! - Disable Features...");
            return;
        }

        if (!Settings.hook_luckperms) {
            this.isEnabled = false;
            Logger.warning("&eLuckPerms not enabled! - Disable Features...");
            return;
        }

        this.isEnabled = true;
        Util.sendColorMessage(EventUtils.getConsoleSender(), Util.getPrefix(true) + "&aLoading LuckPerms features...");
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public HookManager getHooksManager() {
        return hooksManager;
    }

    public boolean isGroup(Player player, String name) {
        LuckPerms api = LuckPermsProvider.get();
        String group = api.getUserManager().getUser(player.getName()).getPrimaryGroup();
        boolean a = name.equals(group);
        return a;
    }


    public String getGroup(Player player) {
        LuckPerms api = LuckPermsProvider.get();
        String group = api.getUserManager().getUser(player.getName()).getPrimaryGroup();
        return group;
    }


}
