package jss.advancedchat.common.api.implement;

import jss.advancedchat.AdvancedChat;

public abstract class ApiAbstractManager<I, E, H>  {

	protected final AdvancedChat plugin;
	protected final H handle;
	
	public ApiAbstractManager(AdvancedChat plugin, H handle) {
		this.plugin = plugin;
		this.handle = handle;
	}
	
	protected abstract E proxy(I internal);
	
}
