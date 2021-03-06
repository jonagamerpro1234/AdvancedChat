package jss.advancedchat.hooks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Logger.Level;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.interfaces.Hook;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class VaultHook implements Hook{

	private final HooksManager hooksManager;
	private AdvancedChat plugin = AdvancedChat.getPlugin();
	private Logger logger = new Logger(plugin);
	private EventUtils eventUtils = new EventUtils(plugin);
	private Permission permission;
	private Economy economy;
	private Chat chat;

	public VaultHook(HooksManager hooksManager) {
		this.hooksManager = hooksManager;
	}

	public void setup() {
		if(!Bukkit.getPluginManager().isPluginEnabled("Vault")) {
			logger.Log(Level.WARNING, "vault not enabled! - Disable Features...");
			return;
		}
		
		/*if(!Settings.hook_vault) {
			return;
		}*/
		
		Utils.sendColorConsoleMessage(eventUtils.getConsoleSender(), Utils.getPrefix() + "&aLoading vault features...");
		RegisteredServiceProvider<Economy> rspE = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
		RegisteredServiceProvider<Permission> rspP = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
		RegisteredServiceProvider<Chat> rspC = Bukkit.getServer().getServicesManager().getRegistration(Chat.class);
		
		if(rspE != null) {
			economy = rspE.getProvider();
		}
		if(rspP != null) {
			permission = rspP.getProvider();
		}
		if(rspC != null) {
			chat = rspC.getProvider();
		}
	}

	public Permission getPermission() {
		return permission;
	}

	public Economy getEconomy() {
		return economy;
	}

	public Chat getChat() {
		return chat;
	}
	
	public HooksManager getHooksManager() {
		return hooksManager;
	}
}
