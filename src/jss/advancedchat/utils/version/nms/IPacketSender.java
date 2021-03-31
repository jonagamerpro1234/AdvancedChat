package jss.advancedchat.utils.version.nms;

import org.bukkit.entity.Player;

public abstract class IPacketSender {

	public final void sendPlayOutChat(Player player, String jsonText) {
		this.sendPlayOutChat(player, jsonText, true);
	}
	
	protected abstract void sendPlayOutChat(Player player, String jsonText, boolean dummy);
}
