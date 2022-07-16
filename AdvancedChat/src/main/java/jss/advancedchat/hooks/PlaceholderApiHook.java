package jss.advancedchat.hooks;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.interfaces.IHook;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlaceholderApiHook implements IHook{
	
	private final AdvancedChat plugin = AdvancedChat.get();
	private final HookManager hooksManager;
	private boolean isEnabled;
	
	public PlaceholderApiHook(HookManager hooksManager) {
		this.hooksManager = hooksManager;
	}

	public void setup() {
		if(!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			this.isEnabled = false;
			Logger.warning("&ePlaceHolderAPI not enabled! - Disable Features...");
			return;
		}
		
		this.isEnabled = true;
		new AdvancedChatExtend(plugin).register();
		Util.sendColorMessage(EventUtils.getConsoleSender(), Util.getPrefix(true) + "&aLoading PlaceHolderAPI features...");
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}

	public HookManager getHooksManager() {
		return hooksManager;
	}
	
	public class AdvancedChatExtend extends PlaceholderExpansion{

		private final AdvancedChat plugin;
		
		public AdvancedChatExtend(AdvancedChat plugin) {
			this.plugin = plugin;
		}
		
		public boolean canRegister() {
			return true;
		}
				
		public boolean persist() {
			return true;
		}
		
		public String getAuthor() {
			return "jonagamerpro1234";
		}
		
		public String getIdentifier() {
			return "advancedchat";
		}
		
		public String getVersion() {
			return plugin.version;
		}
		
		public String onPlaceholderRequest(Player player, String args) {
			PlayerManager playerManager = new PlayerManager(player);
			if(args.equals("is_mute")) {
				if(Settings.mysql) {
				}else {
					if(playerManager.isMute()){
						return "true";
					}else {
						return "false";
					}
				}
			}
			
			if(args.equals("color")) {
				if(Settings.mysql) {
					//return plugin.getMySQL().getColor(player);
				}else {
					return playerManager.getColor();
				}
			}

			if(args.startsWith("channel")) {
				
				if(Settings.mysql) {
				//	return plugin.getMySQL().getColor(player);
				}else {
					return playerManager.getChannel();
				}
			}
			
			return "N/A";
		}
		
	}
}
