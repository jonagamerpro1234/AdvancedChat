package jss.advancedchat.hooks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.database.SQLGetter;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.interfaces.Hook;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import jss.advancedchat.utils.Logger.Level;
import jss.advancedchat.utils.Settings;

public class PlaceholderApiHook implements Hook {
	
	private AdvancedChat plugin = AdvancedChat.getPlugin();
	private HooksManager hooksManager;
	private Logger logger = new Logger(plugin);
	private EventUtils eventUtils = new EventUtils(plugin);
	private boolean isEnabled;
	
	public PlaceholderApiHook(HooksManager hooksManager) {
		this.hooksManager = hooksManager;
	}

	public void setup() {
		if(!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			this.isEnabled = false;
			logger.Log(Level.WARNING, "placeholderapi not enabled! - Disable Features...");
			return;
		}
		
		this.isEnabled = true;
		new AdvancedChatExtend(plugin).register();
		Utils.sendColorConsoleMessage(eventUtils.getConsoleSender(), Utils.getPrefix() + "&aLoading placeholderapi features...");
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}

	public HooksManager getHooksManager() {
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
			return "ac";
		}
		
		public String getVersion() {
			return plugin.version;
		}
		
		public String onPlaceholderRequest(Player player, String args) {
			PlayerManager manager = new PlayerManager(plugin);
			SQLGetter getter = new SQLGetter();
			
			if(args.equals("mute_state")) {
				if(Settings.mysql_use) {
					return getter.getMuted(plugin.getMySQL(), player.getUniqueId().toString()) + "";
				}else {
					return manager.isMute(player) + "";
				}
			}
			
			return null;
		}
		
	}
}
