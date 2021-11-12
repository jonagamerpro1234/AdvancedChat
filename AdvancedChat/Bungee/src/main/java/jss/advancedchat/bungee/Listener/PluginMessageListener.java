package jss.advancedchat.bungee.Listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PluginMessageListener implements Listener{

	@EventHandler
	public void onPluginMessage(PluginMessageEvent e) {
		
		if(!e.getTag().equalsIgnoreCase("ac:messagechannel")) return;
		
		ByteArrayDataInput in = ByteStreams.newDataInput(e.getData());
		in.readUTF();
		
		
	}
}
