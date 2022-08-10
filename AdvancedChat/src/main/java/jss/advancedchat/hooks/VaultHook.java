package jss.advancedchat.hooks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import jss.advancedchat.common.interfaces.IHook;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Util;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class VaultHook implements IHook{

    private Permission permission;
    private Chat chat;
    private Economy economy;
	private HookManager hookManager;
	private boolean isEnabled;
	private static VaultHook vaultHook;
	
	public VaultHook(HookManager hookManager) {
		this.hookManager = hookManager;
		vaultHook = this;
	}
	
	public void setup() {
		if(!Bukkit.getPluginManager().isPluginEnabled("Vault")) {
			this.isEnabled = false;
			Logger.warning("&eVault not enabled! - Disable Features...");
			return;
		}
		
		if(!Settings.hook_vault) {
			this.isEnabled = false;
			Logger.warning("&eVault not enabled! - Disable Features...");
			return;
		}
		
		this.isEnabled = true;
		
        RegisteredServiceProvider<Permission> rspP = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
        RegisteredServiceProvider<Chat> rspC = Bukkit.getServer().getServicesManager().getRegistration(Chat.class);
        RegisteredServiceProvider<Economy> rspE = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);

        if (rspP != null) {
            permission = rspP.getProvider();
        }
        if (rspC != null) {
            chat = rspC.getProvider();
        }
        if (rspE != null) {
            economy = rspE.getProvider();
        }
		
		Util.sendColorMessage(EventUtils.getConsoleSender() , Util.getPrefix(true) + "&aLoading Vault features...");
	}
	
	public static VaultHook getVaultHook() {
		return vaultHook;
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}
	
    public Permission getPermissions() {
        return permission;
    }

    public Chat getChat() {
        return chat;
    }

    public Economy getEconomy() {
        return economy;
    }
	
	public HookManager getHookManager() {
		return hookManager;
	}
	
	public void buyColor(Player player) {
				
	}
	
}
