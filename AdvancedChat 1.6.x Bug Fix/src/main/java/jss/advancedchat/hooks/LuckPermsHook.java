package jss.advancedchat.hooks;

import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.listeners.utils.EventUtils;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.interfaces.Hook;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LuckPermsHook implements Hook {

    public boolean isEnabled;
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

        this.isEnabled = true;
        Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + "&aLoading LuckPerms features...");
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    @Contract(pure = true)
    public static @NotNull LuckPerms getApi(){
        return LuckPermsProvider.get();
    }

    public String getGroup(Player player){
        if (player != null){
            Logger.debug("[Method] -> {getGroup} Is the player not exists!");
        }
        assert player != null;
        return Objects.requireNonNull(getApi().getUserManager().getUser(player.getName())).getPrimaryGroup();
    }

}
