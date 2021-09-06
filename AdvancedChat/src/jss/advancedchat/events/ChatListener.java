package jss.advancedchat.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.cryptomorin.xseries.XSound;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.chat.Json;
import jss.advancedchat.config.ChatDataFile;
import jss.advancedchat.config.ChatLogFile;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.SQLGetter;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Settings;

@SuppressWarnings("unused")
public class ChatListener implements Listener {

	private AdvancedChat plugin;
	public Map<String, Long> delaywords = new HashMap<String, Long>();
	private EventUtils eventsUtils = new EventUtils(plugin);
	private boolean badword;
	private boolean ismention;
	//experimental
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

	//Chat filter
	@EventHandler(priority = EventPriority.HIGH)
	public void chatFilter(AsyncPlayerChatEvent e) {
		Player j = e.getPlayer();
		FileConfiguration config = plugin.getConfigFile().getConfig();

		String path = "Filter-Chat.Enabled";
		List<String> list = config.getStringList("Filter-Chat.BadWords");
		String censorship = config.getString("Filter-Chat.Form-Of-Censorship");
		String msg = config.getString("Filter-Chat.Message");
		String message = e.getMessage().toLowerCase();
		String formatprefix = "";
		String formatmessage = "";
		List<String> hover = new ArrayList<String>();

		Set<String> key = config.getConfigurationSection("ChatFormat.Groups").getKeys(false);
		if (config.getConfigurationSection("ChatFormat.Groups." + key) != null) {
			formatprefix = config.getString("ChatFormat.Groups." + key + ".Format.Prefix");
			formatmessage = config.getString("ChatFormat.Groups." + key + ".Format.Message");
			if (config.getConfigurationSection("ChatFormat.Groups." + key + ".HoverEvent") != null) {
				List<String> listh = config.getStringList("ChatFormat.Groups." + key + ".HoverEvent");
				listh.forEach( (lines) -> {
					hover.add(lines);
				});
			}
		} else {
			formatprefix = config.getString("ChatFormat.Default.Format.Prefix");
			formatmessage = config.getString("ChatFormat.Default.Format.Message");
			List<String> listh = config.getStringList("ChatFormat.Default.HoverEvent");
			listh.forEach( (lines) -> {
				hover.add(lines);
			});
		}

		formatprefix = formatprefix.replace("<player>", j.getDisplayName());
		formatmessage = formatColor(formatmessage, j);
		
		if (config.getString(path).equals("true")) {
			for (int i = 0; i < list.size(); i++) {

				if (Settings.boolean_filter_use_msg) {
					if (message.contains(list.get(i))) {
						this.badword = true;
						Utils.sendColorMessage(j, msg);
					}
				} else {
					message = message.toLowerCase();
					if (message.contains(list.get(i))) {
						this.badword = true;
						String a = "";
						for (int g = 0; g < list.size(); g++) {
							a = a + censorship;
						}
						message = message.replace(list.get(i), a);
						Json json = new Json(j, formatprefix, formatmessage.replace("<message>", message));
						json.setHover(hover).sendDoubleToAll();
					}
				}
			}
		}
	}
	
	
	//Mute chat
	@EventHandler(priority = EventPriority.HIGH)
	public void chatMute(AsyncPlayerChatEvent e) {
		FileConfiguration config = plugin.getPlayerDataFile().getConfig();
		FileConfiguration cconfig = plugin.getConfigFile().getConfig();
		Player j = e.getPlayer();
		SQLGetter sql = plugin.getSQLGetter();
		
		if (Settings.mysql_use) {
			if ((j.isOp()) || (j.hasPermission("AdvancedChat.Chat.Bypass"))) {
				return;
			} else {
				if (sql.getMuted(plugin.getMySQL(), j.getUniqueId().toString())) {
					Utils.sendColorMessage(j,
							cconfig.getString("AdvancedChat.Alert-Mute").replace("<name>", j.getName()));
					e.setCancelled(true);
				}
			}
		} else {
			
			Set<String> sections = config.getKeys(false);
			
			sections.forEach( key -> {
				if (key.contains(j.getName())) {
					String mute = config.getString(key + ".Mute");
					if (!(j.isOp()) || !(j.hasPermission("AdvancedChat.Chat.Bypass"))) {
						if (mute.equals("true")) {
							Utils.sendColorMessage(j,
									cconfig.getString("AdvancedChat.Alert-Mute").replace("<name>", j.getName()));
							e.setCancelled(true);
						}
					} else {
						return;
					}
				}
			});
			
			/*while(true) {
				while(section.hasNext()) {
					String key =
				}
			}*/
			
			for (String key : config.getConfigurationSection("Players").getKeys(false)) {
				if (key.contains(j.getName())) {
					String mute = config.getString("Players." + key + ".Mute");
					if (!(j.isOp()) || !(j.hasPermission("AdvancedChat.Chat.Bypass"))) {
						if (mute.equals("true")) {
							Utils.sendColorMessage(j,
									cconfig.getString("AdvancedChat.Alert-Mute").replace("<name>", j.getName()));
							e.setCancelled(true);
						}
					} else {
						return;
					}
				}
			}
		}
	}
	
	//Chatformat
	//@EventHandler(priority = EventPriority.HIGH)
	public void chatFormat(AsyncPlayerChatEvent e) {
		FileConfiguration config = plugin.getConfigFile().getConfig();
		SQLGetter sql = plugin.getSQLGetter();
		PlayerManager manager = new PlayerManager(plugin);
		Player j = e.getPlayer();
		String msg = e.getMessage();
		String format = "";
		String message = "";
		List<String> hover = new ArrayList<String>();


		if (config.getBoolean("ChatFormat.Enabled")) {
			e.setCancelled(true);
			format = config.getString("ChatFormat.Default.Format.Prefix");
			List<String> list = config.getStringList("ChatFormat.Default.HoverEvent");
			for (int i = 0; i < list.size(); i++) {
				String text = (String) list.get(i);
				hover.add(text);
			}

			format = format.replace("<player>", j.getDisplayName());
			
		}
	}
	
	@EventHandler 
	public void chatMention(AsyncPlayerChatEvent e){
		Player j = e.getPlayer();
		String message = e.getMessage();
		
		if(message.contains(j.getName())) {
			this.ismention = true;
			
			for(Player p : Bukkit.getOnlinePlayers()) {
				
				j.playSound(j.getLocation(), XSound.BLOCK_NOTE_BLOCK_PLING.parseSound(), 1.0f, 0.5f);
				Utils.sendColorMessage(p, "&dTest Mentiaon &b" + j.getName());
			}
			
		}
		
	}

	@EventHandler
	public void chatDataLog(AsyncPlayerChatEvent e) {
		ChatDataFile chatDataFile = plugin.getChatDataFile();
		FileConfiguration config = chatDataFile.getConfig();
		Player j = e.getPlayer();

		String date = Utils.getDate(System.currentTimeMillis());
		String time = Utils.getTime(System.currentTimeMillis());

		config.set("Players." + j.getName() + ".Log." + date + ".Chat." + time, Utils.colorless(e.getMessage()));
		chatDataFile.saveConfig();

	}

	@EventHandler
	public void chatLog(AsyncPlayerChatEvent e) {
		ChatLogFile chatLogFile = plugin.getChatLogFile();
		FileConfiguration config = chatLogFile.getConfig();
		Player j = e.getPlayer();

		String date = Utils.getDate(System.currentTimeMillis());
		String time = Utils.getTime(System.currentTimeMillis());

		config.set("Players." + j.getName() + ".Chat." + date + "." + time, Utils.colorless(e.getMessage()));
		chatLogFile.saveConfig();
	}
	
	private String formatColor(String msg, Player player) {
		if (msg == null)
			return "";

		boolean canReset = false;

		if (!player.hasPermission("AdvancedChat.Format.Color")) {
			msg = COLOR_REGEX.matcher(msg).replaceAll("\u00A7$1");
			canReset = true;
		}

		if (!player.hasPermission("AdvancedChat.Format.Magic")) {
			msg = MAGIC_REGEN.matcher(msg).replaceAll("\u00A7$1");
			canReset = true;
		}

		if (!player.hasPermission("AdvancedChat.Format.Bold")) {
			msg = BOLD_REGEX.matcher(msg).replaceAll("\u00A7$1");
			canReset = true;
		}

		if (!player.hasPermission("AdvancedChat.Format.Strikethrough")) {
			msg = STRIKETHROUGH_REGEX.matcher(msg).replaceAll("\u00A7$1");
			canReset = true;
		}

		if (!player.hasPermission("AdvancedChat.Format.Underline")) {
			msg = UNDERLINE_REGEX.matcher(msg).replaceAll("\u00A7$1");
			canReset = true;
		}

		if (!player.hasPermission("AdvancedChat.Format.italic")) {
			msg = ITALIC_REGEX.matcher(msg).replaceAll("\u00A7$1");
			canReset = true;
		}

		if (canReset) {
			msg = RESET_REGEX.matcher(msg).replaceAll("\u00A7$1");
		}

		return msg;
	}
}
