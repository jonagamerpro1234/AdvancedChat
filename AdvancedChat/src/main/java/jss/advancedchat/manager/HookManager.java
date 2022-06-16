package jss.advancedchat.manager;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.common.HookLoader;
import jss.advancedchat.hooks.DiscordSRVHook;
import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.hooks.PlaceholderApiHook;
import jss.advancedchat.hooks.ProtocolLibHook;
import jss.advancedchat.hooks.VaultHook;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Util;

public class HookManager extends HookLoader{
	
	private AdvancedChat plugin;
	private static ProtocolLibHook protocolLib;
	private DiscordSRVHook discordSRVHook = new DiscordSRVHook(this);
	private LuckPermsHook luckPermsHook = new LuckPermsHook(this);
	private VaultHook vaultHook = new VaultHook(this);
	private PlaceholderApiHook placeholderApiHook = new PlaceholderApiHook(this);
	private static HookManager hookManager;
	
	public HookManager(AdvancedChat plugin) {
		this.plugin = plugin;
		hookManager = this;
	}	
	
	public void loadProtocol() {
		if(Util.doesPluginExist("ProtocolLib", "Loading ProtocolLib Features", Settings.boolean_protocollib)) {
			protocolLib = new ProtocolLibHook();
		}
	}
	
	public boolean isLoadProtocolLib() {
		return protocolLib != null;
	}
	
	public void InitPacketListening() {
		if(isLoadProtocolLib()) {
			protocolLib.initPacketListening();
		}
	}
		
	public void load() {
		initHooks(
				new PlaceholderApiHook(this),
				new LuckPermsHook(this),
				new VaultHook(this),
				new DiscordSRVHook(this));
		loadRegisteredHook();
	}
	
	public VaultHook getVaultHook() {
		return this.vaultHook;
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
	
	public static HookManager getInstance() {
		return hookManager;
	}
	
	public static HookManager get() {
		return hookManager;
	}
}
