package jss.advancedchat.hooks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.common.interfaces.IHook;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.storage.MySQL;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PlaceholderApiHook implements IHook{
	
	private AdvancedChat plugin = AdvancedChat.get();
	private HookManager hooksManager;
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
		Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix(true) + "&aLoading PlaceHolderAPI features...");
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}

	public HookManager getHooksManager() {
		return hooksManager;
	}
	
	public class AdvancedChatExtend extends PlaceholderExpansion{

		private AdvancedChat plugin;
		
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
				if(Settings.mysql_use) {
					if(MySQL.isMute(plugin, player.getUniqueId().toString())) {
						return "true";
					}else {
						return "false";
					}
				}else {
					if(playerManager.isMute()){
						return "true";
					}else {
						return "false";
					}
				}
			}
			
			if(args.equals("color")) {
				if(Settings.mysql_use) {
					return MySQL.getColor(plugin, player.getUniqueId().toString());
				}else {
					return playerManager.getColor();
				}
			}
			
			if(args.equals("gradient_first")) {
				if(Settings.mysql_use) {
					return MySQL.getFirstGradient(plugin, player.getUniqueId().toString());
				}else {
					return playerManager.getFirstGradient();
				}
			}
			if(args.equals("gradient_second")) {
				if(Settings.mysql_use) {
					return MySQL.getSecondGradient(plugin, player.getUniqueId().toString());
				}else {
					return playerManager.getSecondGradient();
				}
			}
			
			if(args.startsWith("channel")) {
				
				if(Settings.mysql_use) {
					return MySQL.getColor(plugin, player.getUniqueId().toString());
				}else {
					return playerManager.getChannel();
				}
			}
			
			return "N/A";
		}
		
	}
}
