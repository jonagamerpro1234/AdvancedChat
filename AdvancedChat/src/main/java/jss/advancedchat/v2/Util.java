package jss.advancedchat.v2;

import jss.advancedchat.AdvancedChat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Util {
	
	private static final AdvancedChat plugin = AdvancedChat.get();
	@SuppressWarnings("unused")
	public static void sendMessage(CommandSender sender , String text) {
		TextComponent component = Component.text(text);
		plugin.getAdventure().sender(sender).sendMessage(component);
	}
	
	public static void sendMessage(Player sender , String text) {
		TextComponent component = Component.text(text);
		plugin.getAdventure().player(sender).sendMessage(component);		
	}
}
