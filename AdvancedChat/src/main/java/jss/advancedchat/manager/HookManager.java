package jss.advancedchat.manager;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.api.HookLoader;
import jss.advancedchat.hooks.DiscordSRVHook;
import jss.advancedchat.hooks.PlaceholderApiHook;
import jss.advancedchat.hooks.ProtocolLibHook;
import jss.advancedchat.hooks.VaultHook;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;

public class HookManager extends HookLoader{
	
	private AdvancedChat plugin;
	private static ProtocolLibHook protocolLib;
	private VaultHook vaultHook;
	private PlaceholderApiHook placeholderApiHook;
	
	public HookManager(AdvancedChat plugin) {
		this.plugin = plugin;
	}	
	
	public void loadProtocol() {
		if(Utils.doesPluginExist("ProtocolLib", "Loading ProtocolLib Features", Settings.boolean_protocollib)) {
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
				new DiscordSRVHook(this));
		loadRegisteredHook();
	}
	
	public VaultHook getVaultHook() {
		return this.vaultHook;
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
