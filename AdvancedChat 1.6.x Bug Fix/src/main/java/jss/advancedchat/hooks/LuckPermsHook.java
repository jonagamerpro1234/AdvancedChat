package jss.advancedchat.hooks;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.listeners.utils.EventUtils;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.interfaces.Hook;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class LuckPermsHook implements Hook {
    public boolean isEnabled;
    public LuckPerms luckPerms;
    private final HookManager hooksManager;

    public LuckPermsHook(HookManager hooksManager) {
        this.hooksManager = hooksManager;
    }

    public void setup() {
        if (!Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            this.isEnabled = false;
            Logger.warning("&eLuckPerms not enabled! - Disable Features...");
            return;
        }
        if (!Settings.hook_luckperms) {
            this.isEnabled = false;
            Logger.warning("&eLuckPerms not enabled! - Disable Features...");
            return;
        }
        RegisteredServiceProvider<LuckPerms> rsp = AdvancedChat.getInstance().getRegistration(LuckPerms.class);
        if (rsp != null)
            this.luckPerms = rsp.getProvider();
        this.isEnabled = true;
        Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + "&aLoading LuckPerms features...");
    }

    public LuckPerms getLuckPerms() {
        return this.luckPerms;
    }

    public LuckPerms getProvided() {
        return this.luckPerms = LuckPermsProvider.get();
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

}
