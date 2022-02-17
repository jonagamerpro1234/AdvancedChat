package jss.advancedchat.manager;

import java.util.List;

import org.bukkit.entity.Player;

import jss.advancedchat.chat.Json;
import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.hooks.VaultHook;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;

public class GroupHelper {
	
	private LuckPermsHook luckPermsHook = HookManager.get().getLuckPermsHook();
	private VaultHook vaultHook = HookManager.get().getVaultHook();
	private GroupManager groupManager = new GroupManager();
	private String group = "";
	private String format;
	private boolean click;
	private boolean hover;
	private String click_mode;
	private String cmd_action;
	private String url_action;
	private String suggest_action;
	private List<String> hovertext;
	private Json json;
	
	public void sendGroup(Player player, String message) {
		
		if(luckPermsHook.isEnabled() || vaultHook.isEnabled()) {
			Logger.error("&cThe Vault or LuckPerms could not be found to activate the group system");
			Logger.warning("&eplease check that luckperms is active or inside your plugins folder");
			return;
		}
		
		if(useLuckPerms()) {
			group = luckPermsHook.getGroup(player);
		}
		
		if(useVautl()) {
			group = VaultHook.getVaultHook().getChat().getPrimaryGroup(player);
		}
		
		this.getGroupOptions(group);
		json = new Json(player, format, message);
		this.buildMessage();
		
	}
	
	private void getGroupOptions(String group) {
		format = groupManager.getFormat(group);
		click_mode = groupManager.getClickMode(group);
		cmd_action = groupManager.getClickCommand(group);
		url_action = groupManager.getClickUrl(group);
		suggest_action = groupManager.getClickSuggestCommand(group);
		
		hovertext = groupManager.getHover(group);
		
		click = groupManager.isClick(group);
		hover = groupManager.isHover(group);
	}
	
	
	private void buildMessage() {
		if (hover) {
			if (click) {
				if (click_mode.equals("command")) {
					json.setHover(hovertext).setExecuteCommand(cmd_action).sendDoubleToAll();
				} else if (click_mode.equals("url")) {
					json.setHover(hovertext).setOpenURL(url_action).sendDoubleToAll();
				} else if (click_mode.equals("suggest")) {
					json.setHover(hovertext).setSuggestCommand(suggest_action).sendDoubleToAll();
				}
			} else {
				json.setHover(hovertext).sendDoubleToAll();
			}
		} else {
			if (click) {
				if (click_mode.equals("command")) {
					json.setExecuteCommand(cmd_action).sendDoubleToAll();
				} else if (click_mode.equals("url")) {
					json.setOpenURL(url_action).sendDoubleToAll();
				} else if (click_mode.equals("suggest")) {
					json.setSuggestCommand(suggest_action).sendDoubleToAll();
				}
			} else {
				json.sendDoubleToAll();
			}
		}
	}
	
	private boolean useLuckPerms() {
		return Settings.hook_luckperms_use_group;
	}
	
	private boolean useVautl() {
		return Settings.hook_vault_use_group;
	}
	
}
