package jss.advancedchat.listeners.old;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.chat.Json;
import jss.advancedchat.manager.ColorManager;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.MySQL;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatListener {
	
	private AdvancedChat plugin;
	private boolean badword;
	private ColorManager colorManager;
	
	@SuppressWarnings({ "unused",  "deprecation"})
	// @EventHandler(priority = EventPriority.HIGH)
	public void onChat(AsyncPlayerChatEvent e) {
		
		PlayerManager manager = new PlayerManager(plugin);
		FileConfiguration config = plugin.getConfigFile().getConfig();
		Player j = e.getPlayer();

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
				String colorbeta = " &r" + colorManager.convertColor(j, manager.getColor(j), e.getMessage());

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
							if (MySQL.isMute(plugin, j.getUniqueId().toString()) || manager.isMute(j) == true
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
						if (MySQL.isMute(plugin, j.getUniqueId().toString()) || manager.isMute(j) == true) {
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
					if (MySQL.isMute(plugin, j.getUniqueId().toString()) || manager.isMute(j) == true) {
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
					if (MySQL.isMute(plugin, j.getUniqueId().toString()) || manager.isMute(j) == true) {
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
							e.setMessage(Utils.color(" &r" + colorManager.convertColor(j, manager.getColor(j), e.getMessage())));
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
						if (MySQL.isMute(plugin, j.getUniqueId().toString()) || manager.isMute(j) == true) {
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
						if (MySQL.isMute(plugin, j.getUniqueId().toString()) || manager.isMute(j) == true) {
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
						if (MySQL.isMute(plugin, j.getUniqueId().toString()) || manager.isMute(j) == true) {
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
}
