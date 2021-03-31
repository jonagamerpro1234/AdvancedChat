package jss.advancedchat.utils.version.nms.V_1_9_R2;

import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import jss.advancedchat.utils.version.nms.IPacketSender;
import net.minecraft.server.v1_9_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_9_R2.IChatBaseComponent;
import net.minecraft.server.v1_9_R2.PacketPlayOutChat;

public class PacketSender extends IPacketSender{

	@Override
	protected void sendPlayOutChat(Player player, String jsonText, boolean dummy) {
		IChatBaseComponent baseComponent = ChatSerializer.a(jsonText);
		PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(baseComponent);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutChat);
	}
}
