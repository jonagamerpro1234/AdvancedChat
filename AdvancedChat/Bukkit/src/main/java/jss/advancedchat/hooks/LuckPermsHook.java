package jss.advancedchat.hooks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.interfaces.IHook;

public class LuckPermsHook implements IHook{
	
	private HookManager hooksManager;
	private boolean isEnabled;
	
	public LuckPermsHook(HookManager hooksManager) {
		this.hooksManager = hooksManager;
	}

	public void setup() {
		if(!Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
			this.isEnabled = false;
			Logger.warning("&eLuckPerms not enabled! - Disable Features...");
			return;
		}
		
		if(Settings.hook_luckperms) {
			this.isEnabled = false;
			Logger.warning("&eLuckPerms not enabled! - Disable Features...");
			return;
		}
						
		this.isEnabled = true;
		Utils.sendColorMessage(EventUtils.getStaticConsoleSender() , Utils.getPrefix() + "&aLoading LuckPerms features...");
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}

	public HookManager getHooksManager() {
		return hooksManager;
	}
	
	public void getGroup(Player player, String name){
		
	}
		
	
}
