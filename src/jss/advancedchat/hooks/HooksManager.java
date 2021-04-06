package jss.advancedchat.hooks;

import jss.advancedchat.utils.Utils;

public class HooksManager {

	private static ProtocolLibHook protocolLib;
	
	public HooksManager() {}
	
	public static void loadDependencies() {
		if(Utils.doesPluginExist("ProtocolLib", "[ProtocolLib> Load Packet Features")) {
			protocolLib = new ProtocolLibHook();
		}
	}
	
	public static boolean isLoadProtocolLib() {
		return protocolLib != null;
	}
	
	public static void InitPacketListening() {
		if(isLoadProtocolLib()) {
			protocolLib.InitPacketListening();
		}
	}
	
}
