package jss.advancedchat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.ConfigFile;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ClearChatCmd implements CommandExecutor{

	private AdvancedChat plugin;
	private EventUtils eventsUtils = new EventUtils(plugin);
	
	public ClearChatCmd(AdvancedChat plugin) {
		this.plugin = plugin;
		plugin.getCommand("ClearChat").setExecutor(this);
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		ConfigFile configFile = plugin.getConfigfile();
		FileConfiguration config = configFile.getConfig();
		if(!(sender instanceof Player)) {			
			eventsUtils.getClearChatAction(null, "server");
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
		eventsUtils.getClearChatAction(j, "player");
		return true;
	}	
	
}
