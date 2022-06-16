package jss.advancedchat;

import java.lang.reflect.Method;

import jss.advancedchat.api.AdvancedChatApiT;
import jss.advancedchat.api.AdvancedChatProvider;
import jss.advancedchat.utils.Logger;

public class ApiRegistrationUtil {

	private static final Method REGISTER;
	private static final Method UNREGISTER;

	static {
		try {

			REGISTER = AdvancedChatProvider.class.getDeclaredMethod("register", AdvancedChatApiT.class);
			REGISTER.setAccessible(true);

			UNREGISTER = AdvancedChatProvider.class.getDeclaredMethod("unregister");
			UNREGISTER.setAccessible(true);

		} catch (NoSuchMethodException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static void regsiterProvider(AdvancedChatApiT apiT) {
		try {
			Logger.debug("&bRegister Api");
			REGISTER.invoke(null, apiT);
		} catch (Exception e) {
			Logger.debug("&eError register Api");
			e.printStackTrace();
		}
	}

	public static void unregsiterProvider() {
		try {
			Logger.debug("&bUnregister Api");
			UNREGISTER.invoke(null);
		} catch (Exception e) {
			Logger.debug("&eError unregister Api");
			e.printStackTrace();
		}
	}
	
}
