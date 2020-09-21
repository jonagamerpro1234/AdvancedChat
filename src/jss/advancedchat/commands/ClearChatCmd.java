package jss.advancedchat.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.EventsUtils;
import jss.advancedchat.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ClearChatCmd implements CommandExecutor{

	private AdvancedChat plugin;
	private CommandSender c = Bukkit.getConsoleSender();
	
	public ClearChatCmd(AdvancedChat plugin) {
		this.plugin = plugin;
		plugin.getCommand("ClearChat").setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration config = plugin.getConfig();
		if(!(sender instanceof Player)) {
			Utils.sendColorMessage(c,  Utils.getPrefixConsole() +" "+ config.getString("AdvancedChat.Error-Console"));
			return false;
		}
		Player j = (Player) sender;
		
		if(!(j.hasPermission("AdvancedChat.Commands.ClearChat")) || !(j.isOp())) {
			TextComponent msg = new TextComponent();
			msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
			msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
			j.spigot().sendMessage(msg);
			return true;
		}
		EventsUtils.getAutoClearAction(plugin,j);
		return true;
	}	
	
}
