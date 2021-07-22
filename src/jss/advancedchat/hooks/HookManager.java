package jss.advancedchat.hooks;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;
//import jss.advancedchat.utils.interfaces.Hook;

public class HookManager {
	
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
	
	/*public void load() {
		initHooks(
				new VaultHook(this),
				new PlaceholderApiHook(this));
	}
	
	public void initHooks(Hook... hooks) {
		for(Hook hook : hooks) {
			hook.setup();
		}
	}*/
	
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
