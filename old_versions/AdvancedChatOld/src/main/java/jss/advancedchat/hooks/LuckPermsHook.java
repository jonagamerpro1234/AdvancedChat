package jss.advancedchat.hooks;

import jss.advancedchat.manager.HookManager;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.logger.Logger;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.interfaces.IHook;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LuckPermsHook implements IHook {

    private final HookManager hooksManager;
    private boolean isEnabled;

    public LuckPermsHook(HookManager hooksManager) {
        this.hooksManager = hooksManager;
    }

    @Contract(pure = true)
    public static @NotNull LuckPerms getApi() {
        return LuckPermsProvider.get();
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
        Util.sendColorMessage(EventUtils.getConsoleSender(), Util.getPrefix(true) + "&aLoading LuckPerms features...");
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public HookManager getHooksManager() {
        return hooksManager;
    }

    public boolean isGroup(@NotNull Player player, @NotNull String name) {
        LuckPerms api = LuckPermsProvider.get();
        String group = Objects.requireNonNull(api.getUserManager().getUser(player.getName())).getPrimaryGroup();
        return name.equals(group);
    }

    public String getGroup(Player player) {
        if (player != null) {
            Logger.debug("&e{Group} &9-> &7Is the player not exists");
        }
        LuckPerms api = LuckPermsProvider.get();
        assert player != null;
        return Objects.requireNonNull(api.getUserManager().getUser(player.getName())).getPrimaryGroup();
    }

}
