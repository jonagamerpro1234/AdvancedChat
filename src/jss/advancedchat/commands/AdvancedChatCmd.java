package jss.advancedchat.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class AdvancedChatCmd implements CommandExecutor{
	
	private AdvancedChat plugin;
	private CommandSender c = Bukkit.getConsoleSender();
	
	public AdvancedChatCmd(AdvancedChat plugin) {
		this.plugin = plugin;
		plugin.getCommand("AdvancedChat").setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration config = plugin.getConfig();
		if(!(sender instanceof Player)) {
			Utils.sendColorMessage(c,  Utils.getPrefixConsole() +" "+ config.getString("AdvancedChat.Error-Console"));
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
				if(!(j.hasPermission("AdvancedChat.Commands.Help")) || !(j.isOp())) {
					TextComponent msg = new TextComponent();
					msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
					msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
					j.spigot().sendMessage(msg);
					return true;
				}
				
				List<String> help = config.getStringList("AdvancedChat.Help-Msg");
				Utils.sendColorMessage(j, "&5-=-=-=-=-=-=-=-=-=-=-=&6[&d"+plugin.name+"&6]&5=-=-=-=-=-=-=-=-=-=-=-");
				for(int i = 0; i < help.size(); i++) {
					String text = (String) help.get(i);
					Utils.sendColorMessage(j, text);
				}					
				Utils.sendColorMessage(j, "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
				return true;
			}
			if(args[0].equalsIgnoreCase("reload")) {
				if(!(j.hasPermission("AdvancedChat.Commands.Reload")) || !(j.isOp())) {
					TextComponent msg = new TextComponent();
					msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
					msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
					j.spigot().sendMessage(msg);
					return true;
				}	
				plugin.reloadConfig();
				if(config.getString("Settings.Use-Default-Prefix").equals("true")) {
					Utils.sendColorMessage(j, Utils.getPrefixPlayer() + " " + config.getString("AdvancedChat.Reload"));
				}else if(config.getString("Settings.Use-Default-Prefix").equals("false")) {
					Utils.sendColorMessage(j, config.getString("Settings.Prefix") + " " + config.getString("AdvancedChat.Reload"));
				}
				return true;
			}
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
	
}
