package jss.advancedchat.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.inventory.GuiChannel;
import jss.advancedchat.inventory.GuiColor;
import jss.advancedchat.inventory.GuiGradient;
import jss.advancedchat.inventory.GuiPlayer;
import jss.advancedchat.inventory.GuiSettings;
import jss.advancedchat.inventory.GuiTest;
import jss.advancedchat.manager.ColorManager;
import jss.advancedchat.manager.GroupManager;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.MySQL;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;
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
		String prefixServer;
		String prefix;
		
		if(Settings.boolean_use_default_prefix) {
			prefixServer = Utils.getPrefix();
			prefix = Utils.getPrefixPlayer();
		}else {
			prefixServer = " " + Settings.message_prefix_custom;
			prefix = " " + Settings.message_prefix_custom;
		}
			
		if (!(sender instanceof Player)) {
			if (args.length >= 1) {
				if (args[0].equalsIgnoreCase("info")) {
					Utils.getInfoPlugin(sender, plugin.name, plugin.version, plugin.latestversion);
				} else if (args[0].equalsIgnoreCase("help")) {
					Utils.sendColorMessage(eventUtils.getConsoleSender(), "&5-=-=-=-=-=-=-=-=-=-=-=&6[&d" + plugin.name + "&6]&5=-=-=-=-=-=-=-=-=-=-=-");
					Settings.list_message_help.forEach((text) -> Utils.sendColorMessage(eventUtils.getConsoleSender(), text));
					Utils.sendColorMessage(eventUtils.getConsoleSender(), "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
				} else if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
					plugin.reloadAllFiles();
					Utils.sendColorMessage(sender, prefixServer + Settings.message_Reload);
				} else {
					Utils.sendColorMessage(sender, prefixServer + Settings.message_Error_Args);
				}
				return true;
			}
			Utils.sendColorMessage(sender, prefixServer + Settings.message_Help_Cmd);
			return false;
		}

		Player j = (Player) sender;
		if ((j.isOp()) || (j.hasPermission("AdvancedChat.Admin"))) {
			if (args.length >= 1) {
				
				if (args[0].equalsIgnoreCase("help")) {
					if ((j.isOp()) || (j.hasPermission("AdvancedChat.Command.Help"))) {
						Utils.sendColorMessage(j, "&5-=-=-=-=-=-=-=-=-=-=-=&6[&d" + plugin.name + "&6]&5=-=-=-=-=-=-=-=-=-=-=-");
						Settings.list_message_help.forEach( (text) -> Utils.sendColorMessage(j, text));
						Utils.sendColorMessage(j, "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
					} else {
						Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
					if ((j.isOp()) || (j.hasPermission("AdvancedChat.Command.Reload"))) {
						plugin.reloadAllFiles();
						Utils.sendColorMessage(j, prefix + Settings.message_Reload);
					} else {
						Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("color")) {
					if ((j.isOp()) || (j.hasPermission("AdvancedChat.Command.Color"))) {
						GuiColor guiColor = new GuiColor();

						if (args.length >= 2) {
							
							String playername = args[1];
							Player target = Bukkit.getPlayer(playername);
							
							if (target == null) Utils.sendColorMessage(j, Settings.message_No_Online_Player);
							
							PlayerManager playerManager = new PlayerManager(target);
							
							if (args.length >= 3) {
								if (args[2].equalsIgnoreCase("set")) {

									String color = args[3];

									if (color == null) Logger.debug("&6Please select a color"); 

									if (Settings.mysql_use) {
										MySQL.setColor(plugin, target, color);
									} else {
										playerManager.setColor(color);
									}
								}
								return true;
							}
							guiColor.open(j, playername);
							return true;
						}
						guiColor.open(j, j.getName());
					} else {
						Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
					}
					return true;
				}
				
				if(args[0].equalsIgnoreCase("gradient")) {
					if((j.isOp()) || (j.hasPermission("AdvancedChat.Command.Gradient"))) {
						GuiGradient guiGradient = new GuiGradient();
						
						if(args.length >= 2) {
							
							String playerName = args[1];
							
							if (playerName == null) Utils.sendColorMessage(j, "&cPlease use the name of the player you want to set the color");
							
							Player target = Bukkit.getPlayer(playerName);
							
							if (target == null) Utils.sendColorMessage(j, Settings.message_No_Online_Player);
							
							PlayerManager playerManager = new PlayerManager(target);
							
							if(args.length >= 3) {
								
								if(args[2].equalsIgnoreCase("set")) {
									
									String hex = args[3];
									
									if (hex == null) Logger.debug("&6Please select a hex color"); 
									
									if(args.length >= 3) {
										if(args[4].equalsIgnoreCase("first")) {	
											if(Settings.mysql_use) {
												MySQL.setGradientFirst(plugin, target, hex);
											}else {
												playerManager.setFirstGradient(ColorManager.get().convertHexColor(hex));
											}
											return true;
										}
										if(args[4].equalsIgnoreCase("second")) {
											if(Settings.mysql_use) {
												MySQL.setGradientSecond(plugin, target, hex);
											}else {
												playerManager.setSecondGradient(ColorManager.get().convertHexColor(hex));
											}
											return true;
										}
									}
								}
								return true;
							}
							guiGradient.open(j, playerName);
							return true;
						}
						guiGradient.open(j, j.getName());
					} else {
						Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
					}
					return true;
				}
				
				if(args[0].equalsIgnoreCase("settings")) {
					if((j.isOp()) || (j.hasPermission("AdvancedChat.Command.Settings"))) {
						GuiSettings guiSettings = new GuiSettings();
						guiSettings.open(j);
					} else {
						Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
					}
					return true;
				}

				if (args[0].equalsIgnoreCase("player")) {
					if ((j.isOp()) || (j.hasPermission("AdvancedChat.Command.Player"))) {
						GuiPlayer guiPlayer = new GuiPlayer();
						if (args.length >= 2) {

							String playerName = args[1];
							
							if(playerName == null) Logger.debug("&6Select the player for open inventory");
							
							Player target = Bukkit.getPlayer(playerName);
							
							if (target == null) Utils.sendColorMessage(j, Settings.message_No_Online_Player);
							
							guiPlayer.open(j, playerName);
						} else {
							guiPlayer.open(j, j.getName());
							return true;
						}
					} else {
						Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
					}
					return true;
				}
				
				if (args[0].equalsIgnoreCase("open")) {
					GuiTest guiTest = new GuiTest(plugin);
					guiTest.open(j);
					return true;
				}

				if (args[0].equalsIgnoreCase("channel")) {
					if ((j.isOp()) || (j.hasPermission("AdvancedChat.Command.Gui.Channel"))) {
						GuiChannel guichannel = new GuiChannel(plugin);

						if (args.length >= 2) {

							String name = args[1];

							Player p = Bukkit.getPlayer(name);

							if (p == null) {
								Utils.sendColorMessage(j, Settings.message_No_Online_Player);
								return true;
							}
							
							guichannel.open(j, j.getName());
						} else {
							guichannel.open(j, j.getName());
							return true;
						}
					} else {
						Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
					}
					return true;
				}

				if (args[0].equalsIgnoreCase("info")) {
					Utils.getInfoPlugin(j, plugin.name, plugin.version, plugin.latestversion);
					return true;
				}
				
				Utils.sendColorMessage(j, prefix + Settings.message_Error_Args);
				return true;
			}
		} else {
			Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
			return true;
		}
		Utils.sendColorMessage(j, prefix + Settings.message_Help_Cmd);
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
				if (args[0].equalsIgnoreCase("color") || args[0].equalsIgnoreCase("player")
						|| args[0].equalsIgnoreCase("channel")) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						listOptions.add(p.getName());
					}
				}
				break;
			case 3:
				if (args[0].equalsIgnoreCase("color") || args[0].equalsIgnoreCase("channel")) {
					listOptions.add("set");
				}
				break;
			case 4:
				if (args[0].equalsIgnoreCase("color")) {
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

				if (args[0].equalsIgnoreCase("channel")) {
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
		if ((j.isOp()) || (j.hasPermission("AdvancedChat.Command.TabComplete"))) {
			switch (args.length) {
			case 0:
			case 1:
				listOptions.add("help");
				listOptions.add("reload");
				listOptions.add("info");
				listOptions.add("color");
				listOptions.add("player");
				listOptions.add("gradient");
				listOptions.add("channel");
				listOptions.add("settings");
				break;
			case 2:
				if (args[0].equalsIgnoreCase("color") || args[0].equalsIgnoreCase("player") || args[0].equalsIgnoreCase("channel") || args[0].equalsIgnoreCase("gradient")) {
					Bukkit.getOnlinePlayers().forEach( (p) -> listOptions.add(p.getName()));
				}
				if(args[0].equalsIgnoreCase("settings")) {
					listOptions.add("low-mode");
					listOptions.add("group");
										
				}
				break;
			case 3:
				if (args[0].equalsIgnoreCase("color") || args[0].equalsIgnoreCase("channel") || args[0].equalsIgnoreCase("gradient")) {
					listOptions.add("set");
				}
				if(args[0].equalsIgnoreCase("settings")) {
					listOptions.add("set");
					listOptions.add("true");
					listOptions.add("false");
				}
				break;
			case 4:
				if (args[0].equalsIgnoreCase("color")) {		
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
				if (args[0].equalsIgnoreCase("channel")) {
					listOptions.add("global");
					listOptions.add("world");
					listOptions.add("local");
					listOptions.add("staff");
				}
				if(args[0].equalsIgnoreCase("gradient")) {
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
				if(args[0].equalsIgnoreCase("settings")) {
					for(String group : GroupManager.get().getGroupList()) {
						listOptions.add(group);
					}
				}
				break;
			case 5:
				if(args[0].equalsIgnoreCase("gradient")) {
					listOptions.add("first");
					listOptions.add("second");	
				}
				break;
			}
		}
		return Utils.setLimitTab(listOptions, lastArgs);
	}
	
}
