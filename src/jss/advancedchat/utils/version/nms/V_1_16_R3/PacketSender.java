package jss.advancedchat.utils.version.nms.V_1_16_R3;

import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import jss.advancedchat.utils.version.nms.IPacketSender;
import net.minecraft.server.v1_16_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_16_R3.ChatMessageType;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.PacketPlayOutChat;

public class PacketSender extends IPacketSender{

	@Override
	protected void sendPlayOutChat(Player player, String jsonText, boolean dummy) {
		IChatBaseComponent baseComponent = ChatSerializer.a(jsonText);
		PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(baseComponent, ChatMessageType.CHAT, player.getUniqueId());
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutChat);
	}
}
