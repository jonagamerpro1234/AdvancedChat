package jss.advancedchat.manager;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.HookLoader;
import jss.advancedchat.hooks.DiscordSRVHook;
import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.hooks.PlaceholderApiHook;
import jss.advancedchat.hooks.ProtocolLibHook;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Util;

public class HookManager extends HookLoader {

    private static ProtocolLibHook protocolLib;
    private static HookManager hookManager;
    private final AdvancedChat plugin;
    private final DiscordSRVHook discordSRVHook = new DiscordSRVHook(this);
    private final LuckPermsHook luckPermsHook = new LuckPermsHook(this);
    private final PlaceholderApiHook placeholderApiHook = new PlaceholderApiHook(this);

    public HookManager(AdvancedChat plugin) {
        this.plugin = plugin;
        hookManager = this;
    }

    public static HookManager getInstance() {
        return hookManager;
    }

    public static HookManager get() {
        return hookManager;
    }

    public void loadProtocol() {
        if (Util.doesPluginExist("ProtocolLib", "Loading ProtocolLib Features", Settings.boolean_protocollib)) {
            protocolLib = new ProtocolLibHook();
        }
    }

    public boolean isLoadProtocolLib() {
        return protocolLib != null;
    }

    public void InitPacketListening() {
        if (isLoadProtocolLib()) {
            protocolLib.initPacketListening();
        }
    }

    public void load() {
        initHooks(
                new PlaceholderApiHook(this),
                new LuckPermsHook(this),
                new DiscordSRVHook(this));
        loadRegisteredHook();
    }

    public DiscordSRVHook getDiscordSRVHook() {
        return discordSRVHook;
    }

    public LuckPermsHook getLuckPermsHook() {
        return luckPermsHook;
    }

    public ProtocolLibHook getProtocolLib() {
        return protocolLib;
    }

    public PlaceholderApiHook getPlaceholderApiHook() {
        return placeholderApiHook;
    }

    public AdvancedChat getPlugin() {
        return this.plugin;
    }
}
