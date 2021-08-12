package jss.advancedchat.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.config.files.ColorFile;
import jss.advancedchat.config.files.ConfigFile;
import jss.advancedchat.config.files.PlayerDataFile;
import jss.advancedchat.config.files.PlayerGuiFile;
import jss.advancedchat.inventory.GuiChannel;
import jss.advancedchat.inventory.GuiColor;
import jss.advancedchat.inventory.GuiPlayer;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.SQLGetter;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.UpdateSettings;
import jss.advancedchat.utils.Utils;

public class AdvancedChatCmd implements CommandExecutor, TabCompleter {

	private AdvancedChat plugin;
	private EventUtils eventUtils = new EventUtils(plugin);

	public AdvancedChatCmd(AdvancedChat plugin) {
		this.plugin = plugin;
		plugin.getCommand("AdvancedChat").setExecutor(this);
		plugin.getCommand("AdvancedChat").setTabCompleter(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		ConfigFile configFile = plugin.getConfigFile();
		PlayerDataFile playerDataFile = plugin.getPlayerDataFile();
		ColorFile colorFile = plugin.getColorFile();
		PlayerGuiFile playerGuiFile = plugin.getPlayerGuiFile();
		FileConfiguration config = configFile.getConfig();
		SQLGetter sql = plugin.getSQLGetter();
		PlayerManager manager = new PlayerManager(plugin);
		
		if (!(sender instanceof Player)) {
			if (args.length >= 1) {
				if (args[0].equalsIgnoreCase("info")) {
					Utils.sendColorMessage(eventUtils.getConsoleSender(),
							"&5-=-=-=-=-=[&b" + plugin.name + "&5]=-=-=-=-=-=-");
					Utils.sendColorMessage(eventUtils.getConsoleSender(), "&5> &3Name: &b" + plugin.name);
					Utils.sendColorMessage(eventUtils.getConsoleSender(), "&5> &3Author: &6jonagamerpro1234");
					Utils.sendColorMessage(eventUtils.getConsoleSender(), "&5> &3Version: &6" + plugin.version);
					Utils.sendColorMessage(eventUtils.getConsoleSender(), "&5> &3Update: &a" + plugin.latestversion);
					Utils.sendColorMessage(eventUtils.getConsoleSender(),
							"&5> &6Spigot: &a" + UpdateSettings.URL_PLUGIN[0]);
					Utils.sendColorMessage(eventUtils.getConsoleSender(),
							"&5> &dSongoda: &a" + UpdateSettings.URL_PLUGIN[1]);
					Utils.sendColorMessage(eventUtils.getConsoleSender(),
							"&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
				} else if (args[0].equalsIgnoreCase("help")) {
					List<String> help = config.getStringList("AdvancedChat.Help-Msg");
					Utils.sendColorMessage(eventUtils.getConsoleSender(),
							"&5-=-=-=-=-=-=-=-=-=-=-=&6[&d" + plugin.name + "&6]&5=-=-=-=-=-=-=-=-=-=-=-");
					for (int i = 0; i < help.size(); i++) {
						String text = (String) help.get(i);
						Utils.sendColorMessage(eventUtils.getConsoleSender(), text);
					}
					Utils.sendColorMessage(eventUtils.getConsoleSender(),
							"&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
				} else if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
					configFile.reloadConfig();
					playerDataFile.reloadConfig();
					colorFile.reloadConfig();
					playerGuiFile.reloadConfig();
					plugin.getPreConfigLoader().load();
					if (config.getString("Settings.Use-Default-Prefix").equals("true")) {
						Utils.sendColorMessage(eventUtils.getConsoleSender(),
								Utils.getPrefixPlayer() + " " + config.getString("AdvancedChat.Reload"));
					} else if (config.getString("Settings.Use-Default-Prefix").equals("false")) {
						Utils.sendColorMessage(eventUtils.getConsoleSender(),
								config.getString("Settings.Prefix") + " " + config.getString("AdvancedChat.Reload"));
					}
				} else {
					if (config.getString("Settings.Use-Default-Prefix").equals("true")) {
						Utils.sendColorMessage(eventUtils.getConsoleSender(),
								Utils.getPrefixPlayer() + " " + config.getString("AdvancedChat.Error-Args"));
					} else if (config.getString("Settings.Use-Default-Prefix").equals("false")) {
						Utils.sendColorMessage(eventUtils.getConsoleSender(), config.getString("Settings.Prefix") + " "
								+ config.getString("AdvancedChat.Error-Args"));
					}
				}
				return true;
			}
			if (Settings.boolean_use_default_prefix) {
				Utils.sendColorMessage(eventUtils.getConsoleSender(),
						Utils.getPrefixPlayer() + " " + config.getString("AdvancedChat.Help-Cmd"));
			} else {
				Utils.sendColorMessage(eventUtils.getConsoleSender(),
						config.getString("Settings.Prefix") + " " + config.getString("AdvancedChat.Help-Cmd"));
			}
			return false;
		}
		
		//player
		
		Player j = (Player) sender;
		if ((j.isOp()) || (j.hasPermission("AdvancedChat.Admin"))) {
			if (args.length >= 1) {
				
				//
				if (args[0].equalsIgnoreCase("help")) {
					if ((j.isOp()) || (j.hasPermission("AdvancedChat.Admin.Help"))) {
						List<String> help = config.getStringList("AdvancedChat.Help-Msg");
						Utils.sendColorMessage(j,
								"&5-=-=-=-=-=-=-=-=-=-=-=&6[&d" + plugin.name + "&6]&5=-=-=-=-=-=-=-=-=-=-=-");
						for (int i = 0; i < help.size(); i++) {
							String text = (String) help.get(i);
							Utils.sendColorMessage(j, text);
						}
						Utils.sendColorMessage(j, "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
					} else {
						Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
					}
					return true;
				}
				//
				if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
					if ((j.isOp()) || (j.hasPermission("AdvancedChat.Admin.Reload"))) {
						configFile.reloadConfig();
						playerDataFile.reloadConfig();
						colorFile.reloadConfig();
						plugin.getChannelGuiFile().reloadConfig();
						playerGuiFile.reloadConfig();
						plugin.getPreConfigLoader().load();
						if (config.getString("Settings.Use-Default-Prefix").equals("true")) {
							Utils.sendColorMessage(j,
									Utils.getPrefixPlayer() + " " + config.getString("AdvancedChat.Reload"));
						} else if (config.getString("Settings.Use-Default-Prefix").equals("false")) {
							Utils.sendColorMessage(j, config.getString("Settings.Prefix") + " "
									+ config.getString("AdvancedChat.Reload"));
						}
					} else {
						Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
					}
					return true;
				}
				//
				if (args[0].equalsIgnoreCase("color")) {
					if ((j.isOp()) || (j.hasPermission("AdvancedChat.Admin.Gui.Color"))) {
						GuiColor guiColor = new GuiColor(plugin);

						if (args.length >= 2) {
							String playername = args[1];
							Player p = Bukkit.getPlayer(playername);
							if (p == null) {
								Utils.sendColorMessage(j, config.getString("AdvancedChat.No-Online-Player"));
								return true;
							}
							
							if(args.length >= 3) {
								if(args[2].equalsIgnoreCase("set")) {
									
									String color = args[3];
									
									if(color == null) {
										return true;
									}
									
									if(Settings.mysql_use) {
										sql.setColor(plugin.getMySQL(), playername, color);
									}else {
										manager.setColor(p, color);
									}
									return true;
								}
								return true;
							}
							
							guiColor.openGuiColor(j, p.getName());
							return true;
						}
						guiColor.openGuiColor(j, j.getName());
					} else {
						Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
					}
					return true;
				}
				
				//
				if (args[0].equalsIgnoreCase("player")) {
					if ((j.isOp()) || (j.hasPermission("AdvancedChat.Gui.Player"))) {
						GuiPlayer guiPlayer = new GuiPlayer(plugin);
						if (args.length >= 2) {

							String name = args[1];

							Player p = Bukkit.getPlayer(name);
							if (p == null) {
								Utils.sendColorMessage(j, config.getString("AdvancedChat.No-Online-Player"));
								return true;
							}
							guiPlayer.open(j, p.getName());
						} else {
							guiPlayer.open(j, j.getName());
							return true;
						}
					} else {
						Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);					}
					return true;
				}
				
				//
				if(args[0].equalsIgnoreCase("channel")) {
					if((j.isOp()) || (j.hasPermission("AdvancedChat.Gui.Channel"))) {
						GuiChannel guichannel = new GuiChannel(plugin);
						
						if(args.length >= 2) {
						
							String name = args[1];
							
							Player p = Bukkit.getPlayer(name);
							
							if(p == null) {
								Utils.sendColorMessage(j, config.getString("AdvancedChat.No-Online-Player"));
								return true;
							}
							guichannel.open(j, j.getName());
						}else {
							guichannel.open(j, j.getName());
							return true;
						}
					}else {
						Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
					}
					return true;
				}
				
				if (args[0].equalsIgnoreCase("info")) {
					Utils.sendColorMessage(j, "&5-=-=-=-=-=[&b" + plugin.name + "&5]=-=-=-=-=-=-");
					Utils.sendColorMessage(j, "&5> &3Name: &b" + plugin.name);
					Utils.sendColorMessage(j, "&5> &3Author: &6jonagamerpro1234");
					Utils.sendColorMessage(j, "&5> &3Version: &6" + plugin.version);
					Utils.sendColorMessage(j, "&5> &3Update: &a" + plugin.latestversion);
					Utils.sendColorMessage(j, "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
					return true;
				}
				
					if (config.getString("Settings.Use-Default-Prefix").equals("true")) {
					Utils.sendColorMessage(j,
							Utils.getPrefixPlayer() + " " + config.getString("AdvancedChat.Error-Args"));
					} else if (config.getString("Settings.Use-Default-Prefix").equals("false")) {
						Utils.sendColorMessage(j,
							config.getString("Settings.Prefix") + " " + config.getString("AdvancedChat.Error-Args"));
				}
				return true;
			}
		} else {
			Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
			return true;
		}
		if (config.getString("Settings.Use-Default-Prefix").equals("true")) {
			Utils.sendColorMessage(j, Utils.getPrefixPlayer() + " " + config.getString("AdvancedChat.Help-Cmd"));
		} else if (config.getString("Settings.Use-Default-Prefix").equals("false")) {
			Utils.sendColorMessage(j,
					config.getString("Settings.Prefix") + " " + config.getString("AdvancedChat.Help-Cmd"));
		}
		return true;
		
	}

	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> listOptions = new ArrayList<>();
		String lastArgs = args.length != 0 ? args[args.length - 1] : "";
		if (!(sender instanceof Player)) {
			switch (args.length) {
			case 0:
			case 1:
				listOptions.add("help");
				listOptions.add("reload");
				listOptions.add("info");
				listOptions.add("color");
				listOptions.add("player");
				listOptions.add("channel");
				break;
			case 2:
				if(args[0].equalsIgnoreCase("color") || args[0].equalsIgnoreCase("player") || args[0].equalsIgnoreCase("channel")) {
					for(Player p : Bukkit.getOnlinePlayers()) {
						listOptions.add(p.getName());
					}
				}
				break;
			case 3:
				if(args[0].equalsIgnoreCase("color") || args[0].equalsIgnoreCase("channel")) {
					listOptions.add("set");
				}
				break;
			case 4:
				if(args[0].equalsIgnoreCase("color")) {
					listOptions.add("black");
					listOptions.add("white");
					listOptions.add("dark_gray");
					listOptions.add("gray");
					listOptions.add("dark_purple");
					listOptions.add("light_purple");
					listOptions.add("dark_aqua");
					listOptions.add("aqua");
					listOptions.add("gold");
					listOptions.add("yellow");
					listOptions.add("green");
					listOptions.add("dark_green");
					listOptions.add("blue");
					listOptions.add("dark_blue");
					listOptions.add("red");
					listOptions.add("dark_red");
				}
				
				if(args[0].equalsIgnoreCase("channel")){
					listOptions.add("global");
					listOptions.add("world");
					listOptions.add("local");
					listOptions.add("staff");
				}
				break;
			}
			return Utils.setLimitTab(listOptions, lastArgs);
		}
		Player j = (Player) sender;
		if ((j.isOp()) || (j.hasPermission("AdvancedChat.Tab"))) {
			switch (args.length) {
			case 0:
			case 1:
				listOptions.add("help");
				listOptions.add("reload");
				listOptions.add("info");
				listOptions.add("color");
				listOptions.add("player");
				listOptions.add("channel");
				break;
			case 2:
				if(args[0].equalsIgnoreCase("color") || args[0].equalsIgnoreCase("player") || args[0].equalsIgnoreCase("channel")) {
					for(Player p : Bukkit.getOnlinePlayers()) {
						listOptions.add(p.getName());
					}
				}
				break;
			case 3:
				if(args[0].equalsIgnoreCase("color") || args[0].equalsIgnoreCase("channel")) {
					listOptions.add("set");
				}
				break;
			case 4:
				if(args[0].equalsIgnoreCase("color")) {
					listOptions.add("black");
					listOptions.add("white");
					listOptions.add("dark_gray");
					listOptions.add("gray");
					listOptions.add("dark_purple");
					listOptions.add("light_purple");
					listOptions.add("dark_aqua");
					listOptions.add("aqua");
					listOptions.add("gold");
					listOptions.add("yellow");
					listOptions.add("green");
					listOptions.add("dark_green");
					listOptions.add("blue");
					listOptions.add("dark_blue");
					listOptions.add("red");
					listOptions.add("dark_red");
				}
				
				if(args[0].equalsIgnoreCase("channel")){
					listOptions.add("global");
					listOptions.add("world");
					listOptions.add("local");
					listOptions.add("staff");
				}
				break;
			}			
		}
		return Utils.setLimitTab(listOptions, lastArgs);
	}

}
