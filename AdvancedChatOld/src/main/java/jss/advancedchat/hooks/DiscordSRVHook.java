package jss.advancedchat.hooks;

import jss.advancedchat.manager.HookManager;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.logger.Logger;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.interfaces.IHook;
import org.bukkit.Bukkit;

public class DiscordSRVHook implements IHook {

    private final HookManager hookManager;
    private boolean isEnabled;

    public DiscordSRVHook(HookManager hookManager) {
        this.hookManager = hookManager;
    }

    public void setup() {
        if (!Bukkit.getPluginManager().isPluginEnabled("DiscordSRV")) {
            Logger.warning("&eDiscordSRV not enabled! - Disable Features...");
            this.isEnabled = false;
            return;
        }

        if (!Settings.hook_discordsrv) {
            this.isEnabled = false;
            Logger.warning("&eDiscordSRV not enabled! - Disable Features...");
            return;
        }
        this.isEnabled = true;
        Util.sendColorMessage(EventUtils.getConsoleSender(), Util.getPrefix(true) + "&aLoading DiscordSRV features...");
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public HookManager getHookManager() {
        return hookManager;
    }

}
