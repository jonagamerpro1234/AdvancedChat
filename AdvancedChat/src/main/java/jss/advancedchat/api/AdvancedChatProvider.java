package jss.advancedchat.api;

import org.jetbrains.annotations.ApiStatus.Internal;

import jss.advancedchat.api.exceptios.NotApiLoadedException;

public final class AdvancedChatProvider {
	
	static AdvancedChatApi instance = null;
	
	public static AdvancedChatApi get() {
		AdvancedChatApi instance = AdvancedChatProvider.instance;
		if(instance == null) {
			throw new NotApiLoadedException();
		}
		return instance;
	}
	
	@Internal
	static void register(AdvancedChatApi instance) {
		AdvancedChatProvider.instance = instance;
	}
	
	@Internal
	static void unregister() {
		AdvancedChatProvider.instance = null;
	}
	
	@Internal
	private AdvancedChatProvider() {
		throw new UnsupportedOperationException("This class cannot be instantiated.");
	}
}
