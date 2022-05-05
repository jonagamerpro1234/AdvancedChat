package jss.advancedchat.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.MySQL;
import me.clip.placeholderapi.PlaceholderAPI;

public class Placeholders {
	
	private static String getRange() {
		return Settings.int_range_chat + "";
	}

	public static String placeholders(Player player, String str) {
		PlayerManager playerManager = new PlayerManager(player);

		if (str == null) {
			return "N/A";
		}

		str = str.replace("<range>", getRange());
		str = str.replace("<name>", player.getName());
		str = str.replace("<displayname>", player.getDisplayName());
		str = str.replace("<channel>", playerManager.getChannel());
		str = str.replace("<is_lowmode>", getLowMode(player, str));
		str = str.replace("<is_mute>", getMute(player, str));
		str = str.replace("<mute_state_invert>", getInvertStateMute(player, str));
		str = Utils.getOnlinePlayers(str);
		str = placeholderReplace(str, player);

		if (HookManager.getInstance().getLuckPermsHook().isEnabled()) {
			str = str.replace("<luckperms_group>", getLuckPerms(str, player));
		}

		return str;
	}

	private static String getMute(Player player, String str) {
		String temp = str;

		if (Settings.mysql) {
			if (MySQL.get().isMute(player.getUniqueId().toString())) {
				temp = "&atrue";
			} else {
				temp = "&cfalse";
			}
		} else {
			PlayerManager playerManager = new PlayerManager(player);
			if (playerManager.isMute()) {
				temp = "&atrue";
			} else {
				temp = "&cfalse";
			}
		}
		return temp;
	}
	
	private static String getLowMode(Player player, String str) {
		String temp = str;

		PlayerManager playerManager = new PlayerManager(player);
		if (playerManager.isLowMode()) {
			temp = "&atrue";
		} else {
			temp = "&cfalse";
		}
		return temp;
	}

	private static String getInvertStateMute(Player player, String str) {
		String temp = str;

		if (Settings.mysql) {
			if (MySQL.get().isMute(player.getUniqueId().toString())) {
				temp = "&cfalse";
			} else {
				temp = "&atrue";
			}
		} else {
			PlayerManager playerManager = new PlayerManager(player);
			if (playerManager.isMute()) {
				temp = "&cfalse";
			} else {
				temp = "&atrue";
			}
		}
		return temp;
	}

	public static String getLuckPerms(String str, Player player) {
		LuckPermsHook luckPermsHook = HookManager.getInstance().getLuckPermsHook();
		str = luckPermsHook.getGroup(player);
		return str;
	}

	private static String placeholderReplace(String text, Player player) {
		return Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI") ? PlaceholderAPI.setPlaceholders(player, text): text;
	}
}