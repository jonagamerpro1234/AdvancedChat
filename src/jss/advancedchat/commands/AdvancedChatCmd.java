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
import jss.advancedchat.ColorFile;
import jss.advancedchat.ConfigFile;
import jss.advancedchat.PlayerDataFile;
import jss.advancedchat.PlayerGuiFile;
import jss.advancedchat.gui.GuiColor;
import jss.advancedchat.gui.GuiPlayer;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.UpdateSettings;
import jss.advancedchat.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class AdvancedChatCmd implements CommandExecutor , TabCompleter{
	
	private AdvancedChat plugin;
	private EventUtils eventUtils = new EventUtils(plugin);
	
	public AdvancedChatCmd(AdvancedChat plugin) {
		this.plugin = plugin;
		plugin.getCommand("AdvancedChat").setExecutor(this);
		plugin.getCommand("AdvancedChat").setTabCompleter(this);
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		ConfigFile configFile = plugin.getConfigFile();
		PlayerDataFile playerDataFile = plugin.getPlayerDataFile();
		ColorFile colorFile = plugin.getColorFile();
		PlayerGuiFile playerGuiFile = plugin.getPlayerGuiFile();
		FileConfiguration config = configFile.getConfig();
		if(!(sender instanceof Player)) {
			if(args.length >= 1) {
				if(args[0].equalsIgnoreCase("info")) {
					Utils.sendColorMessage(eventUtils.getConsoleSender(), "&5-=-=-=-=-=[&b"+plugin.name+"&5]=-=-=-=-=-=-");
					Utils.sendColorMessage(eventUtils.getConsoleSender(), "&5> &3Name: &b"+plugin.name);
					Utils.sendColorMessage(eventUtils.getConsoleSender(), "&5> &3Author: &6jonagamerpro1234");
					Utils.sendColorMessage(eventUtils.getConsoleSender(), "&5> &3Version: &6"+plugin.version);
					Utils.sendColorMessage(eventUtils.getConsoleSender(), "&5> &3Update: &a" + plugin.latestversion);
					Utils.sendColorMessage(eventUtils.getConsoleSender(), "&5> &6Spigot: &a"+UpdateSettings.URL_PLUGIN_SPIGOT);
					Utils.sendColorMessage(eventUtils.getConsoleSender(), "&5> &dSongoda: &a"+UpdateSettings.URL_PLUGIN_SONGODA);
					Utils.sendColorMessage(eventUtils.getConsoleSender(), "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
				}else if(args[0].equalsIgnoreCase("help")) {
					List<String> help = config.getStringList("AdvancedChat.Help-Msg");
					Utils.sendColorMessage(eventUtils.getConsoleSender(), "&5-=-=-=-=-=-=-=-=-=-=-=&6[&d"+plugin.name+"&6]&5=-=-=-=-=-=-=-=-=-=-=-");
					for(int i = 0; i < help.size(); i++) {
						String text = (String) help.get(i);
						Utils.sendColorMessage(eventUtils.getConsoleSender(), text);
					}					
					Utils.sendColorMessage(eventUtils.getConsoleSender(), "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
				}else if(args[0].equalsIgnoreCase("reload")) {
					configFile.reloadConfig();
					playerDataFile.reloadConfig();
					colorFile.reloadConfig();
					playerGuiFile.reloadConfig();
					if(config.getString("Settings.Use-Default-Prefix").equals("true")) {
						Utils.sendColorMessage(eventUtils.getConsoleSender(), Utils.getPrefixPlayer() + " " + config.getString("AdvancedChat.Reload"));
					}else if(config.getString("Settings.Use-Default-Prefix").equals("false")) {
						Utils.sendColorMessage(eventUtils.getConsoleSender(), config.getString("Settings.Prefix") + " " + config.getString("AdvancedChat.Reload"));
					}
				}else {
					if(config.getString("Settings.Use-Default-Prefix").equals("true")) {
						Utils.sendColorMessage(eventUtils.getConsoleSender(), Utils.getPrefixPlayer() + " " + config.getString("AdvancedChat.Error-Args"));
					}else if(config.getString("Settings.Use-Default-Prefix").equals("false")) {
						Utils.sendColorMessage(eventUtils.getConsoleSender(), config.getString("Settings.Prefix") + " " + config.getString("AdvancedChat.Error-Args"));
					}
				}
				return true;
			}
			if(config.getString("Settings.Use-Default-Prefix").equals("true")) {
				Utils.sendColorMessage(eventUtils.getConsoleSender(), Utils.getPrefixPlayer() + " " + config.getString("AdvancedChat.Help-Cmd"));
			}else if(config.getString("Settings.Use-Default-Prefix").equals("false")) {
				Utils.sendColorMessage(eventUtils.getConsoleSender(), config.getString("Settings.Prefix") + " " + config.getString("AdvancedChat.Help-Cmd"));
			}
			return false;
		}
		Player j = (Player) sender;		
		if(args.length >= 1) {
			if(args[0].equalsIgnoreCase("info")) {
				Utils.sendColorMessage(j, "&5-=-=-=-=-=[&b"+plugin.name+"&5]=-=-=-=-=-=-");
				Utils.sendColorMessage(j, "&5> &3Name: &b"+plugin.name);
				Utils.sendColorMessage(j, "&5> &3Author: &6jonagamerpro1234");
				Utils.sendColorMessage(j, "&5> &3Version: &6"+plugin.version);
				Utils.sendColorMessage(j, "&5> &3Update: &a" + plugin.latestversion);
				Utils.sendColorMessage(j, "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
				return true;
			}
			if(args[0].equalsIgnoreCase("help")) {
				if((j.isOp()) || (j.hasPermission("AdvancedChat.Help"))) {
					List<String> help = config.getStringList("AdvancedChat.Help-Msg");
					Utils.sendColorMessage(j, "&5-=-=-=-=-=-=-=-=-=-=-=&6[&d"+plugin.name+"&6]&5=-=-=-=-=-=-=-=-=-=-=-");
					for(int i = 0; i < help.size(); i++) {
						String text = (String) help.get(i);
						Utils.sendColorMessage(j, text);
					}					
					Utils.sendColorMessage(j, "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
				}else {
					TextComponent msg = new TextComponent();
					msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
					msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
					j.spigot().sendMessage(msg);
				}
				return true;
			}
			if(args[0].equalsIgnoreCase("reload")) {
				if((j.isOp()) || (j.hasPermission("AdvancedChat.Reload"))) {
					configFile.reloadConfig();
					playerDataFile.reloadConfig();
					colorFile.reloadConfig();
					playerGuiFile.reloadConfig();
					if(config.getString("Settings.Use-Default-Prefix").equals("true")) {
						Utils.sendColorMessage(j, Utils.getPrefixPlayer() + " " + config.getString("AdvancedChat.Reload"));
					}else if(config.getString("Settings.Use-Default-Prefix").equals("false")) {
						Utils.sendColorMessage(j, config.getString("Settings.Prefix") + " " + config.getString("AdvancedChat.Reload"));
					}
				}else {
					TextComponent msg = new TextComponent();
					msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
					msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
					j.spigot().sendMessage(msg);	
				}
				return true;
			}
			
			if(args[0].equalsIgnoreCase("color")) {
				if((j.isOp()) || (j.hasPermission("AdvancedChat.Gui.Color"))) {
					GuiColor guiColor = new GuiColor(plugin);
					if(args.length >= 2) {
						
						String name = args[1];
						
						Player p = Bukkit.getPlayer(name);
						if(p == null) {
							Utils.sendColorMessage(j, config.getString("AdvancedChat.No-Online-Player"));
							return true;
						}
						guiColor.openGuiColor(j,p.getName());
						//Utils.sendColorMessage(j, "&6open player gui " + p.getName());
	
					}else {
						guiColor.openGuiColor(j,j.getName());
						//Utils.sendColorMessage(j, "&6open player gui");
						return true;
					}
				}else {
					TextComponent msg = new TextComponent();
					msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
					msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
					j.spigot().sendMessage(msg);	
				} 
				return true;
			}
			if(args[0].equalsIgnoreCase("player")) {
				if((j.isOp()) || (j.hasPermission("AdvancedChat.Gui.Player"))) {
					GuiPlayer guiPlayer = new GuiPlayer(plugin);
					if(args.length >= 2) {
						
						String name = args[1];
						
						Player p = Bukkit.getPlayer(name);
						if(p == null) {
							Utils.sendColorMessage(j, config.getString("AdvancedChat.No-Online-Player"));
							return true;
						}
						guiPlayer.openPlayerGui(j,p.getName());
						//Utils.sendColorMessage(j, "&6open player gui " + p.getName());
	
					}else {
						guiPlayer.openPlayerGui(j,j.getName());
						//Utils.sendColorMessage(j, "&6open player gui");
						return true;
					}
				}else {
					TextComponent msg = new TextComponent();
					msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
					msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
					j.spigot().sendMessage(msg);	
				}
				return true;
			}
			
			// test section //
			
			/*if(args[0].equalsIgnoreCase("players")) {
				if((j.isOp()) || (j.hasPermission("AdvancedChat.Gui.Player.List"))) {
					GuiPlayer guiPlayer = new GuiPlayer(plugin);
					guiPlayer.openPlayerListGui(j, 1);
					Utils.sendColorMessage(j, "&bopen player list gui");
				}else {
					TextComponent msg = new TextComponent();
					msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
					msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
					j.spigot().sendMessage(msg);	
				}
				return true;
			}*/
	
			//------------------
			if(config.getString("Settings.Use-Default-Prefix").equals("true")) {
				Utils.sendColorMessage(j, Utils.getPrefixPlayer() + " " + config.getString("AdvancedChat.Error-Args"));
			}else if(config.getString("Settings.Use-Default-Prefix").equals("false")) {
				Utils.sendColorMessage(j, config.getString("Settings.Prefix") + " " + config.getString("AdvancedChat.Error-Args"));
			}
			return true;
		}
		if(config.getString("Settings.Use-Default-Prefix").equals("true")) {
			Utils.sendColorMessage(j, Utils.getPrefixPlayer() + " " + config.getString("AdvancedChat.Help-Cmd"));
		}else if(config.getString("Settings.Use-Default-Prefix").equals("false")) {
			Utils.sendColorMessage(j, config.getString("Settings.Prefix") + " " + config.getString("AdvancedChat.Help-Cmd"));
		}
		return true;
	}
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			List<String> listOptions = new ArrayList<>();
			String lastArgs = args.length !=0 ? args[args.length - 1] : "";
			
			switch(args.length) {
			
			case 0:
			case 1:
				listOptions.add("help");
				listOptions.add("reload");
				listOptions.add("info");
				listOptions.add("color");
				listOptions.add("player");
				break;
			}
			return Utils.setLimitTab(listOptions, lastArgs);
			
		}
		List<String> listOptions = new ArrayList<>();
		String lastArgs = args.length !=0 ? args[args.length - 1] : "";
		Player j = (Player) sender;
		
		switch(args.length) {
		
		case 0:
		case 1:
			if((j.isOp()) || (j.hasPermission("AdvancedChat.Tab"))) return null;
			listOptions.add("help");
			listOptions.add("reload");
			listOptions.add("info");
			listOptions.add("color");
			listOptions.add("player");
			break;
		}
		return Utils.setLimitTab(listOptions, lastArgs);
	}
	
}
