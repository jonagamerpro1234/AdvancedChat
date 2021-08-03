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
import jss.advancedchat.config.files.ChatDataFile;
import jss.advancedchat.config.files.ChatLogFile;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.SQLGetter;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Settings;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatListener implements Listener {

	private AdvancedChat plugin;
	public Map<String, Long> delaywords = new HashMap<String, Long>();
	private EventUtils eventsUtils = new EventUtils(plugin);
	private boolean badword = false;
	private boolean ismention = false;
	private final Pattern COLOR_REGEX = Pattern.compile("(?i)&([0-9A-F])");
	private final Pattern MAGIC_REGEN = Pattern.compile("(?i)&([K])");
	private final Pattern BOLD_REGEX = Pattern.compile("(?i)&([L])");
	private final Pattern STRIKETHROUGH_REGEX = Pattern.compile("(?i)&([M])");
	private final Pattern UNDERLINE_REGEX = Pattern.compile("(?i)&([N])");
	private final Pattern ITALIC_REGEX = Pattern.compile("(?i)&([O])");
	private final Pattern RESET_REGEX = Pattern.compile("(?i)&([R])");
	
	public ChatListener(AdvancedChat plugin) {
		this.plugin = plugin;
		eventsUtils.getEventManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void chatFormat(AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		FileConfiguration config = plugin.getConfigFile().getConfig();
		Player j = e.getPlayer();
		String msg = e.getMessage();
		String formatprefix = "";
		String formatmessage = "";
		List<String> hover = new ArrayList<String>();
		SQLGetter sql = plugin.getSQLGetter();
		PlayerManager manager = new PlayerManager(plugin);

		if (config.getBoolean("ChatFormat.Enabled")) {
			
			formatprefix = config.getString("ChatFormat.Default.Format.Prefix");
			formatmessage = config.getString("ChatFormat.Default.Format.Message");
			List<String> list = config.getStringList("ChatFormat.Default.HoverEvent");
			for (int i = 0; i < list.size(); i++) {
				String text = (String) list.get(i);
				hover.add(text);
			}

			formatprefix = formatprefix.replace("<player>", j.getDisplayName());
			formatmessage = formatmessage.replace("<message>", msg);
			formatmessage = Utils.color(formatmessage);
			formatmessage = formatColor(formatmessage, j);

			if (Settings.mysql_use) {
				if (sql.getMuted(plugin.getMySQL(), j.getUniqueId().toString()) || manager.isMute(j) == true
						|| Settings.boolean_filter_use_msg) {
					return;
				} else {
					Json json = new Json(j, formatprefix, formatmessage);
					json.setHover(hover).sendDoubleToAll();
				}
			} else {
				if (manager.isMute(j) == true || this.badword || this.ismention) {
					this.badword = false;
					this.ismention = false;
					return;
				} else {
					Json json = new Json(j, formatprefix, formatmessage);
					json.setHover(hover).sendDoubleToAll();
				}
			}
		}
	}
	
	//@EventHandler [Disabled] next update 
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
				for (int i = 0; i < listh.size(); i++) {
					String text = (String) listh.get(i);
					hover.add(text);
				}
			}
		} else {
			formatprefix = config.getString("ChatFormat.Default.Format.Prefix");
			formatmessage = config.getString("ChatFormat.Default.Format.Message");
			List<String> listh = config.getStringList("ChatFormat.Default.HoverEvent");
			for (int i = 0; i < listh.size(); i++) {
				String text = (String) listh.get(i);
				hover.add(text);
			}
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

	@Deprecated
	@SuppressWarnings({ "unused",  })
	// @EventHandler(priority = EventPriority.HIGH)
	public void chatFormatOld(AsyncPlayerChatEvent e) {
		PlayerManager manager = new PlayerManager(plugin);
		FileConfiguration config = plugin.getConfigFile().getConfig();
		Player j = e.getPlayer();
		SQLGetter sql = plugin.getSQLGetter();
		Json json;

		String path = config.getString("Settings.ChatFormat-Type");

		try {
			if (path.equals("default")) {
				e.setFormat("<" + j.getName() + ">" + " " + e.getMessage());
			} else if (path.equals("custom")) {
				String format = config.getString("Custom-Format.Text");
				String exformat = config.getString("Custom-Format.Experimental-Text");
				String pathtype = "Custom-Format.Type";
				String hovermode = config.getString("Custom-Format.HoverEvent.Mode");
				String clickaction = config.getString("Custom-Format.ClickEvent.Action");
				String clickmode = config.getString("Custom-Format.ClickEvent.Mode");
				String colorbeta = "&r" + manager.getColor(j, e.getMessage());

				List<String> hovertext = config.getStringList("Custom-Format.HoverEvent.Text");

				format = Utils.getVar(j, format);
				format = format.replace("<msg>", e.getMessage());
				exformat = Utils.getVar(j, exformat);

				if ((j.isOp()) || (j.hasPermission("AdvancedChat.Chat.Color"))) {
					format = Utils.hexcolor(format);
				}

				colorbeta = Utils.hexcolor(colorbeta);
				exformat = Utils.hexcolor(exformat);

				if (config.getString(pathtype).equals("normal")) {
					e.setFormat(format.replace("<name>", j.getName()).replace("<msg>", e.getMessage()));
				} else if (config.getString(pathtype).equals("experimental")) {
					e.setCancelled(true);
					if ((j.isOp()) || (j.hasPermission("AdvancedChat.Chat.Bypass"))) {
						json = new Json(j, exformat, colorbeta);
						json.setHover(hovertext).sendDoubleToAll();
					} else {
						if (Settings.mysql_use) {
							if (sql.getMuted(plugin.getMySQL(), j.getUniqueId().toString()) || manager.isMute(j) == true
									|| Settings.boolean_filter_use_msg) {
								return;
							} else {
								json = new Json(j, exformat, colorbeta);
								json.setHover(hovertext).sendDoubleToAll();
							}
						} else {
							if (manager.isMute(j) == true || this.badword) {
								this.badword = false;
								return;
							} else {
								json = new Json(j, exformat, colorbeta);
								json.setHover(hovertext).sendDoubleToAll();
							}
						}
					}
				} else if (config.getString(pathtype).equals("hover")) {
					TextComponent tc = new TextComponent(e.getFormat());
					tc.setText(format);
					e.setCancelled(true);
					if ((j.isOp()) || (j.hasPermission("AdvancedChat.Chat.Bypass"))) {
						Utils.sendAllPlayerBaseComponent(tc);
					} else {
						if (sql.getMuted(plugin.getMySQL(), j.getUniqueId().toString()) || manager.isMute(j) == true) {
							return;
						} else {
							Utils.sendAllPlayerBaseComponent(tc);
						}
					}

				} else if (config.getString(pathtype).equals("click")) {
					TextComponent tc = new TextComponent(format);
					ClickEvent click = new ClickEvent(ClickEvent.Action.valueOf(Utils.getActionClickType(clickmode)),
							clickaction);
					tc.setClickEvent(click);
					e.setCancelled(true);
					if (sql.getMuted(plugin.getMySQL(), j.getUniqueId().toString()) || manager.isMute(j) == true) {
						return;
					} else {
						Utils.sendAllPlayerBaseComponent(tc);
					}
				} else if (config.getString(pathtype).equals("double")) {
					TextComponent tc = new TextComponent(e.getFormat());
					// HoverEvent hover = new
					// HoverEvent(HoverEvent.Action.valueOf(Utils.getActionHoverType(hovermode)),
					// new ComponentBuilder(Utils.color(hovertext)).create());
					ClickEvent click = new ClickEvent(ClickEvent.Action.valueOf(Utils.getActionClickType(clickmode)),
							clickaction);
					// tc.setHoverEvent(hover);
					tc.setClickEvent(click);
					tc.setText(format);
					e.setCancelled(true);
					if (sql.getMuted(plugin.getMySQL(), j.getUniqueId().toString()) || manager.isMute(j) == true) {
						return;
					} else {
						Utils.sendAllPlayerBaseComponent(tc);
					}
				}
			} else if (path.equals("group")) {
				for (String key : config.getConfigurationSection("Groups").getKeys(false)) {
					String pathtype = config.getString("Groups." + key + ".Type");
					String format = config.getString("Groups." + key + ".Format");
					String perm = config.getString("Groups." + key + ".Permission");
					String hovertext = config.getString("Groups." + key + "HoverEvent.Text");
					String hovermode = config.getString("Groups." + key + "HoverEvent.Mode");
					String clickaction = config.getString("Groups." + key + ".ClickEvent.Action");
					String clickmode = config.getString("Groups." + key + ".ClickEvent.Mode");
					hovertext = Utils.getVar(j, hovertext);
					format = Utils.getVar(j, format);
					format = format.replace("<msg>", e.getMessage());
					if (!(j.hasPermission("AdvancedChat.Chat.Color")) || !(j.isOp())) {
						format = Utils.hexcolor(format);
					}
					if (config.getString(pathtype).equals("normal")) {
						if (j.hasPermission(perm)) {
							e.setFormat(format.replace("<name>", j.getName()).replace("<msg>", e.getMessage()));
						}
					} else if (config.getString(pathtype).equals("experimental")) {
						if (j.hasPermission(perm)) {
							e.setFormat(format.replace("<name>", j.getName()).replace("<msg>", e.getMessage()));
							String msg = e.getMessage();
							e.setMessage(Utils.color(manager.getColor(j, msg)));
						}
					} else if (config.getString(pathtype).equals("hover")) {
						if (j.hasPermission(perm)) {
						}
						TextComponent tc = new TextComponent(e.getFormat());
						HoverEvent hover = new HoverEvent(
								HoverEvent.Action.valueOf(Utils.getActionHoverType(hovermode)),
								new ComponentBuilder(Utils.color(hovertext)).create());
						tc.setHoverEvent(hover);
						tc.setText(format);
						e.setCancelled(true);
						if (sql.getMuted(plugin.getMySQL(), j.getUniqueId().toString()) || manager.isMute(j) == true) {
							return;
						} else {
							Utils.sendAllPlayerBaseComponent(tc);
						}
						// Utils.sendAllPlayerBaseComponent(tc);
					} else if (config.getString(pathtype).equals("click")) {
						TextComponent tc = new TextComponent(e.getFormat());
						ClickEvent click = new ClickEvent(
								ClickEvent.Action.valueOf(Utils.getActionClickType(clickmode)), clickaction);
						tc.setClickEvent(click);
						tc.setText(format);
						e.setCancelled(true);
						if (sql.getMuted(plugin.getMySQL(), j.getUniqueId().toString()) || manager.isMute(j) == true) {
							return;
						} else {
							Utils.sendAllPlayerBaseComponent(tc);
						}
						// Utils.sendAllPlayerBaseComponent(tc);
					} else if (config.getString(pathtype).equals("double")) {
						TextComponent tc = new TextComponent(e.getFormat());
						HoverEvent hover = new HoverEvent(
								HoverEvent.Action.valueOf(Utils.getActionHoverType(hovermode)),
								new ComponentBuilder(Utils.color(hovertext)).create());
						ClickEvent click = new ClickEvent(
								ClickEvent.Action.valueOf(Utils.getActionClickType(clickmode)), clickaction);
						tc.setHoverEvent(hover);
						tc.setClickEvent(click);
						tc.setText(format);
						e.setCancelled(true);
						if (sql.getMuted(plugin.getMySQL(), j.getUniqueId().toString()) || manager.isMute(j) == true) {
							return;
						} else {
							Utils.sendAllPlayerBaseComponent(tc);
						}
						// Utils.sendAllPlayerBaseComponent(tc);
					}
				}
			} else {
				e.setFormat(Utils.color(config.getString("Default-Format").replace("<name>", j.getName())
						.replace("<msg>", e.getMessage())));
			}

		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
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
