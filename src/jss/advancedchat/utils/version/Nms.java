package jss.advancedchat.utils.version;

import org.bukkit.entity.Player;

import com.cryptomorin.xseries.ReflectionUtils;

public class Nms {

	// !working progress!
	public static void sendHoverChat(Player player, String hoverAction) {
		try {
			
			ReflectionUtils.sendPacket(player, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	
}
