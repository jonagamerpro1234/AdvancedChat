package jss.advancedchat.hooks;

import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Settings;

public class ProtocolLibHook {
	
	private final ProtocolManager manager = ProtocolLibrary.getProtocolManager();
	
	void InitPacketListening() {
		
		if(Settings.boolean_antitabcompleted) {
			manager.addPacketListener(new PacketAdapter(AdvancedChat.getPlugin(), PacketType.Play.Client.TAB_COMPLETE) {
				@Override
				public void onPacketReceiving(PacketEvent e) {
					Player j = e.getPlayer();
					
					if((j.isOp()) || (j.hasPermission("AdvancedChat.Chat.Bypass"))) return;
					
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
}
