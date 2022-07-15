package jss.advancedchat.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.inventory.GuiColor;
import jss.advancedchat.inventory.GuiError;
import jss.advancedchat.inventory.GuiGradient;
import jss.advancedchat.inventory.GuiPlayer;
import jss.advancedchat.inventory.GuiSettings;
import jss.advancedchat.manager.ColorManager;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Perms;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Util;
import org.jetbrains.annotations.NotNull;

public class AdvancedChatCmd implements CommandExecutor, TabCompleter {
	private final AdvancedChat plugin;
	@SuppressWarnings("ConstantConditions")
	public AdvancedChatCmd(AdvancedChat plugin) {
		this.plugin = plugin;
		plugin.getCommand("AdvancedChat").setExecutor(this);
		plugin.getCommand("AdvancedChat").setTabCompleter(this);
	}
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length >= 1) {
				if (args[0].equalsIgnoreCase("info")) {
					Util.getInfoPlugin(sender, plugin.name, plugin.version, plugin.latestversion);
					return true;
				}
				if (args[0].equalsIgnoreCase("help")) {
					sendHelp(sender);
				}
				if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
					plugin.reloadAllFiles();
					Util.sendColorMessage(sender, Util.getPrefix(false) + Settings.message_Reload);
				}
				Util.sendColorMessage(sender, Util.getPrefix(false) + Settings.message_Error_Args);
				return true;
			}
			Util.sendColorMessage(sender, Util.getPrefix(false) + Settings.message_Help_Cmd);
			return false;
		}

		Player j = (Player) sender;
		if (args.length >= 1) {

			if (args[0].equalsIgnoreCase("help")) {
				if(!j.isOp() || !j.hasPermission(Perms.ac_cmd_help)) Util.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
				sendHelp(j);
				return true;
			}
			
			if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
				if ((j.isOp()) || (j.hasPermission(Perms.ac_cmd_reload))) {
					plugin.reloadAllFiles();
					Util.sendColorMessage(j, Util.getPrefix(false) + Settings.message_Reload);
				} else {
					Util.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
				}
				return true;
			}
			
			if (args[0].equalsIgnoreCase("test1")) {
				jss.advancedchat.v2.Util.sendMessage(j, "&6Test adventure api");
				return true;
			}
			
			if (args[0].equalsIgnoreCase("color")) {
				if ((j.isOp()) || (j.hasPermission(Perms.ac_cmd_color))) {
					GuiColor guiColor = new GuiColor();

					if (args.length >= 2) {

						String playername = args[1];
						Player target = Bukkit.getPlayer(playername);

						if (target == null) Util.sendColorMessage(j, Settings.message_No_Online_Player);
						PlayerManager playerManager = new PlayerManager(target);

						if (args.length >= 3) {
							if (args[2].equalsIgnoreCase("set")) {

								String color = args[3];

								if (color == null)
									Util.sendColorMessage(j, "&6Please select a color");

								if (Settings.mysql) {
									//mySQL.setColor(target, color);
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
					Util.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
				}
				return true;
			}

			if (args[0].equalsIgnoreCase("test")) {
				sendTest(j);
				return true;
			}

			if (args[0].equalsIgnoreCase("gradient")) {
				if ((j.isOp()) || (j.hasPermission(Perms.ac_cmd_gradient))) {
					GuiGradient guiGradient = new GuiGradient();

					if (args.length >= 2) {

						String playerName = args[1];

						if (playerName == null)
							Util.sendColorMessage(j, "&cPlease use the name of the player you want to set the color");

						Player target = Bukkit.getPlayer(playerName);

						if (target == null)
							Util.sendColorMessage(j, Settings.message_No_Online_Player);

						PlayerManager playerManager = new PlayerManager(target);

						if (args.length >= 3) {

							if (args[2].equalsIgnoreCase("set")) {

								String hex = args[3];

								if (hex == null)
									Logger.debug("&6Please select a hex color");

								if (args[4].equalsIgnoreCase("first")) {
									if (Settings.mysql) {
									//	mySQL.setGradientFirst(target, hex);
									} else {
										playerManager.setFirstGradient(ColorManager.get().convertHexColor(hex));
									}
									return true;
								}
								if (args[4].equalsIgnoreCase("second")) {
									if (Settings.mysql) {
										//mySQL.setGradientSecond(target, hex);
									} else {
										playerManager.setSecondGradient(ColorManager.get().convertHexColor(hex));
									}
									return true;
								}
							}
							return true;
						}
						guiGradient.open(j, playerName);
						return true;
					}
					guiGradient.open(j, j.getName());
				} else {
					Util.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
				}
				return true;
			}

			if (args[0].equalsIgnoreCase("settings")) {
				if ((j.isOp()) || (j.hasPermission(Perms.ac_cmd_settings))) {
					GuiSettings guiSettings = new GuiSettings();

					if (args.length >= 2) {

						String playerName = args[1];

						if (playerName == null)
							Logger.debug("&6Select the player for open inventory");

						Player target = Bukkit.getPlayer(playerName);

						if (target == null)
							Util.sendColorMessage(j, Settings.message_No_Online_Player);

						if (args.length >= 3) {

							PlayerManager playerManager = new PlayerManager(target);

							if (args[2].equalsIgnoreCase("low-mode")) {

								if (args.length >= 4) {

									if (args[3].equalsIgnoreCase("true")) {
										playerManager.setLowMode(true);
										return true;
									}

									if (args[3].equalsIgnoreCase("false")) {
										playerManager.setLowMode(false);
										return true;
									}
								}
								return true;
							}

							if (args[2].equalsIgnoreCase("chat")) {
								
								if (args.length >= 4) {

									if (args[3].equalsIgnoreCase("true")) {
										playerManager.setLowMode(true);
										return true;
									}

									if (args[3].equalsIgnoreCase("false")) {
										playerManager.setLowMode(false);
										return true;
									}
								}
								return true;
							}

							Util.sendColorMessage(j, "please select the setting");
							return true;
						}

						guiSettings.open(j, target.getName());
						return true;
					}

					guiSettings.open(j, j.getName());
				} else {
					Util.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
				}
				return true;
			}

			if (args[0].equalsIgnoreCase("player")) {
				if ((j.isOp()) || (j.hasPermission(Perms.ac_cmd_player))) {
					GuiPlayer guiPlayer = new GuiPlayer();
					if (args.length >= 2) {

						String playerName = args[1];

						if (playerName == null)
							Logger.debug("&6Select the player for open inventory");

						Player target = Bukkit.getPlayer(playerName);

						if (target == null)
							Util.sendColorMessage(j, Settings.message_No_Online_Player);

						guiPlayer.open(j, playerName);
					} else {
						guiPlayer.open(j, j.getName());
						return true;
					}
				} else {
					Util.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
				}
				return true;
			}

			if (args[0].equalsIgnoreCase("info")) {
				Util.getInfoPlugin(j, plugin.name, plugin.version, plugin.latestversion);
				return true;
			}

			Util.sendColorMessage(j, Util.getPrefix(false) + Settings.message_Error_Args);
			return true;
		}
		Util.sendColorMessage(j, Util.getPrefix(false) + Settings.message_Help_Cmd);
		return true;
	}

	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		List<String> listOptions = new ArrayList<>();
		String lastArgs = args.length != 0 ? args[args.length - 1] : "";
		if (!(sender instanceof Player)) {
			switch (args.length) {
			case 0:
			case 1:
				listOptions.addAll(Arrays.asList("help", "reload", "info"));
				break;
			}
		} else {
			final Player j = (Player) sender;

			if (!j.isOp() || !j.hasPermission(Perms.ac_cmd_tabcomplete))
				return new ArrayList<>();

			switch (args.length) {
			case 0:
			case 1:
				listOptions.add("help");
				listOptions.add("reload");
				listOptions.add("info");
				listOptions.add("color");
				listOptions.add("player");
				listOptions.add("gradient");
				listOptions.add("settings");
				break;
			case 2:
				if (args[0].equalsIgnoreCase("color") || args[0].equalsIgnoreCase("player")
						|| args[0].equalsIgnoreCase("gradient")) {
					Bukkit.getOnlinePlayers().forEach((p) -> listOptions.add(p.getName()));
				}
				if (args[0].equalsIgnoreCase("settings")) {
					listOptions.add("low-mode");
					listOptions.add("group");

				}
				break;
			case 3:
				if (args[0].equalsIgnoreCase("color") || args[0].equalsIgnoreCase("gradient")) {
					listOptions.add("set");
				}
				if (args[0].equalsIgnoreCase("settings")) {
					listOptions.add("set");
					listOptions.add("true");
					listOptions.add("false");
				}
				break;
			case 4:
				if (args[0].equalsIgnoreCase("color") || args[0].equalsIgnoreCase("gradient")) {
					listOptions.addAll(Arrays.asList("black", "white", "dark_gray", "gray", "dark_purple",
							"light_purple", "dark_aqua", "aqua", "gold", "yellow", "green", "dark_green", "blue",
							"dark_blue", "red", "dark_red"));
				}
				break;
			case 5:
				if (args[0].equalsIgnoreCase("gradient")) {
					listOptions.add("first");
					listOptions.add("second");
				}
				break;
			}
		}

		return Util.setLimitTab(listOptions, lastArgs);
	}

	private void sendHelp(CommandSender sender) {
		Util.sendColorMessage(sender, "&5-=-=-=-=-=-=-=-=-=-=-=&6[&d" + plugin.name + "&6]&5=-=-=-=-=-=-=-=-=-=-=-");
		for (String msg : Settings.list_message_help) {
			Util.sendColorMessage(sender, msg);
		}
		Util.sendColorMessage(sender, "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
	}

	private void sendTest(Player j) {
		GuiError guiError = new GuiError();
		guiError.open(j);
	}

}
