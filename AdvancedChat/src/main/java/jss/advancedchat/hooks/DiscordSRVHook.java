package jss.advancedchat.hooks;

import org.bukkit.Bukkit;

import jss.advancedchat.manager.HookManager;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.interfaces.IHook;

public class DiscordSRVHook implements IHook{
	
	private HookManager hookManager;
	private boolean isEnabled;
	
	public DiscordSRVHook(HookManager hookManager) {
		this.hookManager = hookManager;
	}

	public void setup() {
		if(!Bukkit.getPluginManager().isPluginEnabled("DiscordSRV")) {
			Logger.warning("&eDiscordSRV not enabled! - Disable Features...");
			this.isEnabled = false;
			return;
		}
		
		if(!Settings.hook_discordsrv) {
			this.isEnabled = false;
			Logger.warning("&eDiscordSRV not enabled! - Disable Features...");
			return;
		}
		this.isEnabled = true;
		Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + "&aLoading DiscordSRV features...");
	}

	public boolean isEnabled() {
		return isEnabled;
	}
	
	public HookManager getHookManager() {
		return hookManager;
	}
	
}
