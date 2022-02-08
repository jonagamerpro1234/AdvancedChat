package jss.advancedchat.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.chat.Json;
import jss.advancedchat.hooks.DiscordSRVHook;
import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.hooks.VaultHook;
import jss.advancedchat.manager.ColorManager;
import jss.advancedchat.manager.GroupManager;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.MySQL;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public class ChatListenerTest implements Listener {

	private AdvancedChat plugin;
	public Map<String, Long> delaywords = new HashMap<String, Long>();
	private ColorManager colorManager = new ColorManager();
	private boolean badword;
	private boolean ismention;

	// experimental
	@SuppressWarnings("unused")
	private final Pattern COLOR_REGEX = Pattern.compile("(?i)&([0-9A-F])");
	private final Pattern MAGIC_REGEN = Pattern.compile("(?i)&([K])");
	private final Pattern BOLD_REGEX = Pattern.compile("(?i)&([L])");
	private final Pattern STRIKETHROUGH_REGEX = Pattern.compile("(?i)&([M])");
	private final Pattern UNDERLINE_REGEX = Pattern.compile("(?i)&([N])");
	private final Pattern ITALIC_REGEX = Pattern.compile("(?i)&([O])");
	@SuppressWarnings("unused")
	private final Pattern RESET_REGEX = Pattern.compile("(?i)&([R])");

	public ChatListenerTest(AdvancedChat plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("unused")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void chatFormat(AsyncPlayerChatEvent e) {
		FileConfiguration config = plugin.getConfigFile().getConfig();
		GroupManager groupManager = new GroupManager();
		VaultHook vaultHook = HookManager.getInstance().getVaultHook();
		DiscordSRVHook discordSRVHook = HookManager.getInstance().getDiscordSRVHook();
		LuckPermsHook luckPermsHook = HookManager.getInstance().getLuckPermsHook();

		Player j = e.getPlayer();
		PlayerManager playerManager = new PlayerManager(j);
		String path = Settings.chatformat_chattype;

		boolean isDefault = path.equalsIgnoreCase("default");
		boolean isNormal = path.equalsIgnoreCase("normal");
		boolean isGroup = path.equalsIgnoreCase("group");

		String format = config.getString("ChatFormat.Format");
		String message = "";
		
		String msg = isFormatColor(e.getMessage(), j);
		
		Logger.debug(msg);
		
		if (Settings.mysql_use) {
			message = " &r"
					+ colorManager.convertColor(j, MySQL.getColor(plugin, j.getUniqueId().toString()), msg);
		} else {
			message = " &r" + colorManager.convertColor(j, playerManager.getColor(j), msg);
		}

		format = Utils.getVar(j, format);
		message = Utils.getVar(j, message);

		/* remove old code!
		 * if ((j.isOp()) || (j.hasPermission("AdvancedChat.Chat.Color"))) {
			message = Utils.color(message);
		}*/

		if (playerManager.isMute(j) || this.badword) {
			this.badword = false;
			return;
		}

		if (this.ismention) {
			this.ismention = false;
			return;
		}

		if (isDefault) {
			return;
		} else if (isNormal) {

			Json json = new Json(j, format, message);

			boolean hover = config.getString("ChatFormat.HoverEvent.Enabled").equals("true");
			List<String> hovertext = config.getStringList("ChatFormat.HoverEvent.Hover");

			boolean click = config.getString("ChatFormat.ClickEvent.Enabled").equals("true");
			String cmd_action = config.getString("ChatFormat.ClickEvent.Actions.Command");
			String click_mode = config.getString("ChatFormat.ClickEvent.Mode");
			String url_action = config.getString("ChatFormat.ClickEvent.Actions.Url");
			String suggest_action = config.getString("ChatFormat.ClickEvent.Actions.Suggest-Command");

			cmd_action = Utils.getVar(j, cmd_action);
			suggest_action = Utils.getVar(j, suggest_action);

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
			return;
		} else if (isGroup) {

			LuckPerms luckPerms = LuckPermsProvider.get();

			String vaultGroup = VaultHook.getVaultHook().getChat().getPrimaryGroup(j);
			String luckpermsGroup = luckPerms.getUserManager().getUser(j.getName()).getPrimaryGroup();

			String group = "";

			if (vaultHook.isEnabled()) {
				group = vaultGroup;
			} else if (luckPermsHook.isEnabled()) {
				group = luckpermsGroup;
			} else {
				Logger.error("&cThe Vault or LuckPerms could not be found to activate the group system");
				Logger.warning("&eplease check that Vault or LuckPerms is active or inside your plugins folder");
				return;
			}

			Json json = new Json(j, format, message);

			boolean hover = groupManager.isHover(group);
			List<String> hovertext = groupManager.getHover(group);

			boolean click = groupManager.isClick(group);
			String click_mode = groupManager.getClickMode(group);
			String cmd_action = groupManager.getClickCommand(group);
			String url_action = groupManager.getClickUrl(group);
			String suggest_action = groupManager.getClickSuggestCommand(group);

			cmd_action = Utils.getVar(j, cmd_action);
			suggest_action = Utils.getVar(j, suggest_action);

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
			return;
		} else {
			e.setFormat("<" + j.getName() + ">" + " " + e.getMessage());
			Logger.error("");
		}
	}

	public String isFormatColor(String msg, Player player) {
		if (msg == null) {
			return "";
		} else {
			boolean canReset = false;
			if (!player.isOp() || !player.hasPermission("AdvancedChat.Chat.Color")) {
				msg = Utils.color(msg);
				canReset = true;
				Logger.debug("Chat => Color: " + true);
			}

			if (!player.isOp() || !player.hasPermission("AdvancedChat.Chat.Magic")) {
				msg = this.MAGIC_REGEN.matcher(msg).replaceAll("§$1");
				canReset = true;
				Logger.debug("Chat => Magic: " + true);
			}

			if (!player.isOp() || !player.hasPermission("AdvancedChat.Chat.Bold")) {
				msg = this.BOLD_REGEX.matcher(msg).replaceAll("§$1");
				canReset = true;
				Logger.debug("Chat => Bold: " + true);
			}

			if (!player.isOp() || !player.hasPermission("AdvancedChat.Chat.Strikethrough")) {
				msg = this.STRIKETHROUGH_REGEX.matcher(msg).replaceAll("§$1");
				canReset = true;
				Logger.debug("Chat => Strikethrough: " + true);
			}

			if (!player.isOp() || !player.hasPermission("AdvancedChat.Chat.Underline")) {
				msg = this.UNDERLINE_REGEX.matcher(msg).replaceAll("§$1");
				canReset = true;
				Logger.debug("Chat => UnderLine: " + true);
			}

			if (!player.isOp() || !player.hasPermission("AdvancedChat.Chat.Italic")) {
				msg = this.ITALIC_REGEX.matcher(msg).replaceAll("§$1");
				Logger.debug("Chat => Italic: " + true);
				canReset = true;
			}

			if (canReset) {
				msg = Utils.colorless(msg);
			}
			Logger.debug("Chat => CanReset: " + canReset);
			return msg;
		}
	}

}
