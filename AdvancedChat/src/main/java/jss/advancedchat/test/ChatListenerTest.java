package jss.advancedchat.test;

import java.util.HashMap;
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
import jss.advancedchat.manager.ColorManager;
import jss.advancedchat.manager.GroupHelper;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Util;

public class ChatListenerTest implements Listener {

	private AdvancedChat plugin = AdvancedChat.get();
	private ColorManager colorManager = new ColorManager();
	private boolean badword;
	private boolean ismention;
	private final Pattern MAGIC_REGEN = Pattern.compile("(?i)&([K])");
	private final Pattern BOLD_REGEX = Pattern.compile("(?i)&([L])");
	private final Pattern STRIKETHROUGH_REGEX = Pattern.compile("(?i)&([M])");
	private final Pattern UNDERLINE_REGEX = Pattern.compile("(?i)&([N])");
	private final Pattern ITALIC_REGEX = Pattern.compile("(?i)&([O])");
	@SuppressWarnings("unused")
	private HashMap<String, String> channel = new HashMap<>();
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e) {
		FileConfiguration config = plugin.getConfigFile().getConfig();
		DiscordSRVHook discordSRVHook = HookManager.get().getDiscordSRVHook();

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
		
		if (Settings.mysql) {
		//	message = " &r" + colorManager.convertColor(j, plugin.getMySQL().getColor0(j.getUniqueId().toString()), msg);
		} else {
			message = " &r" + colorManager.addFormat(j, msg);//colorManager.convertColor(j, playerManager.getColor(), colorManager.converSpecialColor(playerManager.getSpecialColor(), msg));
		}

		format = Util.getVar(j, format);
		message = Util.getVar(j, message);
		
		boolean isMute = false;
		
        if(Settings.mysql) {
        //	isMute = plugin.getMySQL().isMute(j.getUniqueId().toString());
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
		
		if(!playerManager.isChat()) {
			Util.sendColorMessage(j, Settings.message_alert_disable_chat);
			return;
		}
		
		if (isDefault) {
			return;
		} else if (isNormal) {
			e.setCancelled(true);
			Json json = new Json(j, format, message);
			
			if(config.getString("Settings.Show-Chat-In-Console").equals("true")) {
				Logger.chat(json.getText() + json.getExtraText());
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
						
			cmd_action = Util.getVar(j, cmd_action);
			suggest_action = Util.getVar(j, suggest_action);
			
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
			
			GroupHelper groupHelper = GroupHelper.get().setGroup(playerManager.getGroup());
			groupHelper.sendGroup(j, message);
		}
	}

	@EventHandler 
	public void chatMention(AsyncPlayerChatEvent e){
		e.setCancelled(true);
		Player j = e.getPlayer();
		String message = e.getMessage();
		
		PlayerManager playerManager = new PlayerManager(j);
		
		if(message.contains(j.getName()) && !playerManager.isMention()) {
			Util.sendColorMessage(j, Settings.message_alert_disable_mention);
			return;
		}
		
		if(Settings.mention) {
			Util.sendColorMessage(j, Settings.mention_send);
			if(message.contains(j.getName())) {
				this.ismention = true;
				
				Bukkit.getOnlinePlayers().forEach( (p) -> {
					p.playSound(p.getLocation(), Sound.valueOf(Settings.mention_sound_name), Settings.mention_sound_volume, Settings.mention_sound_pitch);
					Util.sendColorMessage(p, Settings.mention_receive);
				});
			}
		}
	}
	
	public String formatColor(String msg, Player player) {
		if (msg == null) {
			return "";
		} else {
			boolean canReset = false;
			if (!player.isOp() || !player.hasPermission("AdvancedChat.Chat.Color")) {
				msg = Util.color(msg);
				canReset = true;
			}

			if (!player.isOp() || !player.hasPermission("AdvancedChat.Chat.Magic")) {
				msg = this.MAGIC_REGEN.matcher(msg).replaceAll("§$1");
				canReset = true;
			}

			if (!player.isOp() || !player.hasPermission("AdvancedChat.Chat.Bold")) {
				msg = this.BOLD_REGEX.matcher(msg).replaceAll("§$1");
				canReset = true;
			}

			if (!player.isOp() || !player.hasPermission("AdvancedChat.Chat.Strikethrough")) {
				msg = this.STRIKETHROUGH_REGEX.matcher(msg).replaceAll("§$1");
				canReset = true;
			}

			if (!player.isOp() || !player.hasPermission("AdvancedChat.Chat.Underline")) {
				msg = this.UNDERLINE_REGEX.matcher(msg).replaceAll("§$1");
				canReset = true;
			}

			if (!player.isOp() || !player.hasPermission("AdvancedChat.Chat.Italic")) {
				msg = this.ITALIC_REGEX.matcher(msg).replaceAll("§$1");
				canReset = true;
			}

			if (canReset) {
				msg = Util.colorless(msg);
			}
			return msg;
		}
	}

}
