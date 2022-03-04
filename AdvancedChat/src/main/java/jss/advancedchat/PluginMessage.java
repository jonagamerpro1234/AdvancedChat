package jss.advancedchat;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

public class PluginMessage implements PluginMessageListener {

	public void onPluginMessageReceived(String channel, Player player, byte[] message) {

		if(!channel.equalsIgnoreCase("advancedchat")) return;
		
		ByteArrayDataInput input = ByteStreams.newDataInput(message);
		String subChannel = input.readUTF();
		
		
		if(subChannel.equals("")) return;
		
	}

}
