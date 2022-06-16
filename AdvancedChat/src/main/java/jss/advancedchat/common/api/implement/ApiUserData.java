package jss.advancedchat.common.api.implement;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.api.PlayerData;
import jss.advancedchat.api.UserData;

public class ApiUserData extends ApiAbstractManager<PlayerData, jss.advancedchat.api.PlayerData, UserData> implements UserData{

	public ApiUserData(AdvancedChat plugin, UserData handle) {
		super(plugin, handle);
	}

	@Override
	public PlayerData getUser(String user) {
		return null;
	}

	@Override
	protected PlayerData proxy(PlayerData internal) {
		return null;
	}

	
	
}
