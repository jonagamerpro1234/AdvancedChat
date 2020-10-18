package jss.advancedchat.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class ChatUtils {

	private final static Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
	
	private static String format(String msg) {
		if(Bukkit.getVersion().contains("1.16")) {
			Matcher match = pattern.matcher(msg);
			while(match.find()){
				String color = msg.substring(match.start(), match.end());
				msg = msg.replace(color, ChatColor.valueOf(color) + "");
				match = pattern.matcher(msg);
			}
		}
		
		return Utils.color(msg);
	}
	
	public static String hexcolor(String msg) {
		return format(msg);
	}
	
	public static void sendHexColorMessage(Player player, String msg) {
		player.sendMessage(hexcolor(msg));
	}
	
	public static void sendHexColorMessage(CommandSender sender, String msg) {
		sender.sendMessage(hexcolor(msg));
	}
}
