package jss.advancedchat.api;

import jss.advancedchat.api.exceptios.NotApiLoadedException;

public final class AdvancedChatProvider {
	
	private static AdvancedChat instance = null;
	
	public static AdvancedChat get() {
		AdvancedChat instance = AdvancedChatProvider.instance;
		if(instance == null) {
			throw new NotApiLoadedException();
		}
		return instance;
	}
	
	static void register(AdvancedChat instance) {
		AdvancedChatProvider.instance = instance;
	}
	
	static void unregister() {
		AdvancedChatProvider.instance = null;
	}
	
	private AdvancedChatProvider() {
		throw new UnsupportedOperationException("This class cannot be instantiated.");
	}
}
