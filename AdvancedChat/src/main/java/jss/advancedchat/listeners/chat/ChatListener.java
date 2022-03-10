package jss.advancedchat.listeners.chat;

import java.util.List;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import github.scarsz.discordsrv.util.DiscordUtil;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.chat.Json;
import jss.advancedchat.hooks.DiscordSRVHook;
import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.hooks.VaultHook;
import jss.advancedchat.manager.ColorManager;
import jss.advancedchat.manager.GroupHelper;
import jss.advancedchat.manager.GroupManager;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.MySQL;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;

public class ChatListener implements Listener {

	private AdvancedChat plugin;
	private ColorManager colorManager;
	private boolean badword;
	private boolean ismention;
	private final Pattern COLOR_REGEX = Pattern.compile("(?i)&([0-9A-F])");
	private final Pattern MAGIC_REGEN = Pattern.compile("(?i)&([K])");
	private final Pattern BOLD_REGEX = Pattern.compile("(?i)&([L])");
	private final Pattern STRIKETHROUGH_REGEX = Pattern.compile("(?i)&([M])");
	private final Pattern UNDERLINE_REGEX = Pattern.compile("(?i)&([N])");
	private final Pattern ITALIC_REGEX = Pattern.compile("(?i)&([O])");
	private final Pattern RESET_REGEX = Pattern.compile("(?i)&([R])");
	
	public ChatListener(AdvancedChat plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void chatMute(AsyncPlayerChatEvent e) {
		Player j = e.getPlayer();
		PlayerManager playerManager = new PlayerManager(j);
		
		if (Settings.mysql_use) {
			if (j.isOp() || j.hasPermission("AdvancedChat.Mute.Bypass")) return;
				if (MySQL.isMute(plugin, j.getUniqueId().toString())) {
					Utils.sendColorMessage(j, Utils.getVar(j, Settings.message_Alert_Mute));
					e.setCancelled(true);
				}
		} else {
			if (j.isOp() || j.hasPermission("AdvancedChat.Mute.Bypass")) return;
				if (playerManager.isMute()) {
					Utils.sendColorMessage(j, Utils.getVar(j, Settings.message_Alert_Mute));
					e.setCancelled(true);
				}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e) {
		FileConfiguration config = plugin.getConfigFile().getConfig();
		VaultHook vaultHook = HookManager.get().getVaultHook();
		DiscordSRVHook discordSRVHook = HookManager.get().getDiscordSRVHook();
		LuckPermsHook luckPermsHook = HookManager.get().getLuckPermsHook();

		Player j = e.getPlayer();
		PlayerManager playerManager = new PlayerManager(j);
		String path = Settings.chatformat_chattype;

		boolean isDefault = path.equalsIgnoreCase("default");
		boolean isNormal = path.equalsIgnoreCase("normal");
		boolean isGroup = path.equalsIgnoreCase("group");

		String format = config.getString("ChatFormat.Format");
		String message = "";
		
		String msg = formatColor(e.getMessage(), j);
		
		Logger.debug(msg);
		
		if (Settings.mysql_use) {
			message = " &r" + colorManager.convertColor(j, MySQL.getColor(plugin, j.getUniqueId().toString()), msg);
		} else {
			message = " &r" + colorManager.convertColor(j, playerManager.getColor(), msg);
		}

		format = Utils.getVar(j, format);
		message = Utils.getVar(j, message);
		
		boolean isMute;
		
        if(Settings.mysql_use) {
        	isMute = MySQL.isMute(plugin, j.getUniqueId().toString());
        } else {
        	isMute = playerManager.isMute();
        }
		
		if (isMute || this.badword) {
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
			e.setCancelled(true);
			Json json = new Json(j, format, message);
			
			if(config.getString("Settings.Show-Chat-In-Console").equals("true")) {
				Logger.info(json.getText() + json.getExtraText());
			}
			
			if(discordSRVHook.isEnabled()) {
				if(Settings.hook_discordsrv_channelid.equalsIgnoreCase("none")) return;
				
				DiscordUtil.sendMessage(DiscordUtil.getTextChannelById(Settings.hook_discordsrv_channelid), json.getFormat());
			}
			
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
					if (click_mode.equalsIgnoreCase("command")) {
						json.setHover(hovertext).setExecuteCommand(cmd_action).sendDoubleToAll();
					} else if (click_mode.equalsIgnoreCase("url")) {
						json.setHover(hovertext).setOpenURL(url_action).sendDoubleToAll();
					} else if (click_mode.equalsIgnoreCase("suggest")) {
						json.setHover(hovertext).setSuggestCommand(suggest_action).sendDoubleToAll();
					}
				} else {
					json.setHover(hovertext).sendDoubleToAll();
				}
			} else {
				if (click) {
					if (click_mode.equalsIgnoreCase("command")) {
						json.setExecuteCommand(cmd_action).sendDoubleToAll();
					} else if (click_mode.equalsIgnoreCase("url")) {
						json.setOpenURL(url_action).sendDoubleToAll();
					} else if (click_mode.equalsIgnoreCase("suggest")) {
						json.setSuggestCommand(suggest_action).sendDoubleToAll();
					}
				} else {
					json.sendDoubleToAll();
				}
			}
			return;
		} else if (isGroup) {
			e.setCancelled(true);
			
			String group = "";
			
			if (luckPermsHook.isEnabled() || vaultHook.isEnabled()) {
				Logger.error("&cThe Vault or LuckPerms could not be found to activate the group system");
				Logger.warning("&eplease check that luckperms is active or inside your plugins folder");
				return;
			}

			if (Settings.hook_luckperms_use_group) {
				group = LuckPermsHook.getApi().getUserManager().getUser(j.getName()).getPrimaryGroup();
			} else if (Settings.hook_luckperms_use_group) {
				group = VaultHook.getVaultHook().getChat().getPrimaryGroup(j);
			}
			
			
			GroupHelper groupHelper = GroupHelper.get().setGroup(GroupManager.get().getGroupPermission(j));
			groupHelper.sendGroup(j, message);
		}
	}
	
	@EventHandler 
	public void chatMention(AsyncPlayerChatEvent e){
		e.setCancelled(true);
		Player j = e.getPlayer();
		String message = e.getMessage();
		
		if(Settings.mention) {
			Utils.sendColorMessage(j, Settings.mention_send);
			if(message.contains(j.getName())) {
				this.ismention = true;
				
				Bukkit.getOnlinePlayers().forEach( (p) -> {
					p.playSound(p.getLocation(), Sound.valueOf(Settings.mention_sound_name), Settings.mention_sound_volume, Settings.mention_sound_pitch);
					Utils.sendColorMessage(p, Settings.mention_receive);
				});
			}
		}
	}
	
	public String formatColor(String msg, Player player) {
		if (msg == null)
			return "";

		boolean canReset = false;

		if (!player.hasPermission("AdvancedChat.Chat.Color")) {
			msg = COLOR_REGEX.matcher(msg).replaceAll("\u00A7$1");
			canReset = true;
		}

		if (!player.hasPermission("AdvancedChat.Chat.Magic")) {
			msg = MAGIC_REGEN.matcher(msg).replaceAll("\u00A7$1");
			canReset = true;
		}

		if (!player.hasPermission("AdvancedChat.Chat.Bold")) {
			msg = BOLD_REGEX.matcher(msg).replaceAll("\u00A7$1");
			canReset = true;
		}

		if (!player.hasPermission("AdvancedChat.Chat.Strikethrough")) {
			msg = STRIKETHROUGH_REGEX.matcher(msg).replaceAll("\u00A7$1");
			canReset = true;
		}

		if (!player.hasPermission("AdvancedChat.Chat.Underline")) {
			msg = UNDERLINE_REGEX.matcher(msg).replaceAll("\u00A7$1");
			canReset = true;
		}

		if (!player.hasPermission("AdvancedChat.Chat.Italic")) {
			msg = ITALIC_REGEX.matcher(msg).replaceAll("\u00A7$1");
			canReset = true;
		}

		if (canReset) {
			msg = RESET_REGEX.matcher(msg).replaceAll("\u00A7$1");
		}

		return msg;
	}
}
