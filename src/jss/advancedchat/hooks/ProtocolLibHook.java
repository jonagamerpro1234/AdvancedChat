package jss.advancedchat.hooks;

import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

import jss.advancedchat.AdvancedChat;

public class ProtocolLibHook {
	
	private ProtocolManager manager = ProtocolLibrary.getProtocolManager();
	
	void InitPacketListening() {
		manager.addPacketListener(new PacketAdapter(AdvancedChat.getPlugin(), PacketType.Play.Client.TAB_COMPLETE) {
			@Override
			public void onPacketReceiving(PacketEvent e) {
				@SuppressWarnings("unused")
				Player j = e.getPlayer();
				
				String msg = e.getPacket().getStrings().read(0).trim();
				if(!msg.startsWith("/") || msg.contains(" ")) {
					return;
				}
				if(msg.length() > 0) {
					e.setCancelled(true);
				}
			}
		});
	}
}
