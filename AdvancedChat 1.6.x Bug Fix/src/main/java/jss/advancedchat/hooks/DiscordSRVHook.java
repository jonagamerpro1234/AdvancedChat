package jss.advancedchat.hooks;

import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.listeners.utils.EventUtils;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.interfaces.Hook;
import org.bukkit.Bukkit;

public class DiscordSRVHook implements Hook {

    private boolean isEnabled;

    public String name() {
        return "DiscordSRV";
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
        Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + "&aLoading DiscordSRV features...");
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

}
