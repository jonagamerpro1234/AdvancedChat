package jss.advancedchat.hooks;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.listeners.utils.EventUtils;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.interfaces.Hook;
import org.bukkit.Bukkit;

public class PlaceholderApiHook implements Hook {
  private final AdvancedChat plugin = AdvancedChat.getInstance();

  private final HookManager hooksManager;

  private final EventUtils eventUtils = new EventUtils(this.plugin);

  private boolean isEnabled;

  public PlaceholderApiHook(HookManager hooksManager) {
    this.hooksManager = hooksManager;
  }

  public void setup() {
    if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
      this.isEnabled = false;
      Logger.warning("&ePlaceholderAPI not enabled! - Disable Features...");
      return;
    }
    this.isEnabled = true;
    Utils.sendColorMessage(this.eventUtils.getConsoleSender(), Utils.getPrefix() + "&aLoading PlaceholderAPI features...");
  }

  public boolean isEnabled() {
    return this.isEnabled;
  }

  public HookManager getHooksManager() {
    return this.hooksManager;
  }
}
