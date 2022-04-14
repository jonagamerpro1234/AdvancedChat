package jss.advancedchat.common;

import java.util.HashMap;

import jss.advancedchat.common.interfaces.IHook;

public abstract class HookLoader {

	private HashMap<String, IHook> iHooks = new HashMap<>(); 
	
	public void loadRegisteredHook() {
		this.iHooks.forEach( (s , h) -> {
			initHooks(this.getRegisteredHook(s));
		});
	}
	
	public IHook getRegisteredHook(String id) {
		if(this.isRegisteredHook(id)){
			return this.iHooks.get(id);
		}
		return null;
	}
	
	public void registeredHook(String id, IHook hooks) {
		if(this.isRegisteredHook(id)) {
			iHooks.put(id, hooks);
		}
	}
	
	public boolean isRegisteredHook(String id) {
		if(this.iHooks.get(id) != null && this.iHooks.containsKey(id)) {
			return false;
		}
		return true;
	}
	
	public void initHooks(IHook... hooks) {
		for(IHook hook : hooks) {
			hook.setup();
		}
	}
	
}
