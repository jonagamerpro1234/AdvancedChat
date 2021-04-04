package jss.advancedchat.utils.version.nms.v1_8_R3;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import jss.advancedchat.utils.version.nms.IPacketSender;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class PacketSender extends IPacketSender{

	@Override
	protected void sendPlayOutChat(Player player, String jsonText, boolean dummy) {
		IChatBaseComponent baseComponent = ChatSerializer.a(jsonText);
		PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(baseComponent);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutChat);
	}
}
