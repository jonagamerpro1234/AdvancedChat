package jss.advancedchat.manager;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.hooks.DiscordSRVHook;
import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.hooks.PlaceholderApiHook;
import jss.advancedchat.hooks.VaultHook;
import jss.advancedchat.utils.interfaces.Hook;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class HookManager {
    private static HookManager hookManager;
    private final AdvancedChat plugin;

    private final Map<String, Hook> hooks = new HashMap<>();

    private final DiscordSRVHook discordSRV = new DiscordSRVHook();
    private final LuckPermsHook luckPermsHook = new LuckPermsHook(this);
    private final VaultHook vaultHook = new VaultHook(this);

    public HookManager(AdvancedChat plugin) {
        this.plugin = plugin;
        hookManager = this;
    }

    public static HookManager get() {
        return hookManager;
    }

    public void load() {

        Hook[] hooksList = { new PlaceholderApiHook(this), new DiscordSRVHook(), new LuckPermsHook(this), new VaultHook(this) };

        for (Hook hook : hooksList){
            hook.setup();
            hooks.put(hook.name(),hook);
        }
        //initHooks(new PlaceholderApiHook(this), new DiscordSRVHook(), new LuckPermsHook(this), new VaultHook(this));
    }

    public Hook getHook(String name){
        return hooks.get(name);
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
