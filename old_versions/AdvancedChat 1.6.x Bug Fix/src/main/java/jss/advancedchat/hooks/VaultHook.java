package jss.advancedchat.hooks;

import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.listeners.utils.EventUtils;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.interfaces.Hook;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultHook implements Hook {
    private static VaultHook vaultHook;
    private Permission permission;
    private Chat chat;
    private Economy economy;
    private final HookManager hookManager;
    private boolean isEnabled;

    public VaultHook(HookManager hookManager) {
        this.hookManager = hookManager;
        vaultHook = this;
    }

    public static VaultHook getVaultHook() {
        return vaultHook;
    }

    public String name() {
        return "Vault";
    }

    public void setup() {
        if (!Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            this.isEnabled = false;
            Logger.warning("&eVault not enabled! - Disable Features...");
            return;
        }
        if (!Settings.hook_vault) {
            this.isEnabled = false;
            Logger.warning("&eVault not enabled! - Disable Features...");
            return;
        }
        this.isEnabled = true;
        RegisteredServiceProvider<Permission> rspP = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
        RegisteredServiceProvider<Chat> rspC = Bukkit.getServer().getServicesManager().getRegistration(Chat.class);
        RegisteredServiceProvider<Economy> rspE = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rspP != null)
            this.permission = rspP.getProvider();
        if (rspC != null)
            this.chat = rspC.getProvider();
        if (rspE != null)
            this.economy = rspE.getProvider();
        Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + "&aLoading Vault features...");
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public Chat getChat() {
        return this.chat;
    }

}
