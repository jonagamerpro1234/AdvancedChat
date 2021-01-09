package jss.advancedchat.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.test.PlayerManager;
import jss.advancedchat.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

@SuppressWarnings("deprecation")
public class UnMuteCmd implements CommandExecutor {

	private AdvancedChat plugin;

	public UnMuteCmd(AdvancedChat plugin) {
		this.plugin = plugin;
		plugin.getCommand("UnMute").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration config = plugin.getConfigfile().getConfig();
		PlayerManager manager = new PlayerManager(plugin);
		
		String text = config.getString("AdvancedChat.Help-UnMute");
		String prefix = "";
		String prefixserver = "";
		
		if(config.getString("Settings.Use-Default-Prefix").equals("false")) {
			prefixserver = config.getString("Settings.Prefix");
		}else if(config.getString("Settings.Use-Default-Prefix").equals("true")) {
			prefixserver = Utils.getPrefixPlayer();
		}
		
		if(config.getString("Settings.Use-Default-Prefix").equals("false")) {
			prefix = config.getString("Settings.Prefix");
		}else if(config.getString("Settings.Use-Default-Prefix").equals("true")) {
			prefix = Utils.getPrefixPlayer();
		}
		
		
		
		if (!(sender instanceof Player)) {
			
			if(args.length >= 1) {
				Player p = Bukkit.getPlayer(args[0]);
				manager.setMute(p, false);
				Utils.sendColorMessage(sender, prefixserver + config.getString("AdvancedChat.UnMute-Player").replace("<name>", p.getName()));
				return true;
			}
			Utils.sendColorMessage(sender, Utils.getPrefix() + " " + text);
			return false;
		}
		Player j = (Player) sender;
		if(j.isOp() || j.hasPermission("AdvancedChat.UnMute")) {
			if(args.length >= 1) {
				text = Utils.getVar(text, j);
				Player p = Bukkit.getPlayer(args[0]);
				manager.setMute(p, false);
				Utils.sendColorMessage(j,  prefix + config.getString("AdvancedChat.UnMute-Player").replace("<name>", p.getName()));
				return true;
			}
		}else {
			TextComponent msg = new TextComponent();
			msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
			msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
			j.spigot().sendMessage(msg);
			return true;
		}

		Utils.sendColorMessage(j, Utils.getPrefix() + " " + text);
		return true;
	}

}
