package jss.advancedchat.manager;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.hooks.DiscordSRVHook;
import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.hooks.PlaceholderApiHook;
import jss.advancedchat.hooks.VaultHook;
import jss.advancedchat.utils.interfaces.Hook;
import org.jetbrains.annotations.NotNull;

public class HookManager {
    private static HookManager hookManager;
    private final AdvancedChat plugin;
    private final DiscordSRVHook discordSRV = new DiscordSRVHook();
    private final LuckPermsHook luckPermsHook = new LuckPermsHook(this);
    private final VaultHook vaultHook = new VaultHook(this);

    public HookManager(AdvancedChat plugin) {
        this.plugin = plugin;
        hookManager = this;
    }

    @Deprecated
    public static HookManager getHookManager() {
        return hookManager;
    }

    public static HookManager get() {
        return hookManager;
    }

    public void load() {
        initHooks(new PlaceholderApiHook(this), new DiscordSRVHook(), new LuckPermsHook(this), new VaultHook(this));
    }

    private void initHooks(Hook @NotNull ... hooks) {
        for (Hook hook : hooks)
            hook.setup();
    }

    public VaultHook getVaultHook() {
        return this.vaultHook;
    }

    public LuckPermsHook getLuckPermsHook() {
        return this.luckPermsHook;
    }

    public DiscordSRVHook getDiscordSRV() {
        return this.discordSRV;
    }

    public AdvancedChat getPlugin() {
        return this.plugin;
    }
}
