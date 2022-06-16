package jss.advancedchat.common.api;

import org.bukkit.plugin.PluginLogger;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.api.AdvancedChatApiT;
import jss.advancedchat.api.AdvancedChatProvider;
import jss.advancedchat.api.UserData;

public class AdvancedChatApiProvider implements AdvancedChatApiT {

	private AdvancedChat plugin;
	// private final UserData userData;

	public AdvancedChatApiProvider(AdvancedChat plugin) {
		this.plugin = plugin;
		// userData = new ApiUserData(null, userData);
	}

	public void ensureApiWasLoadedByPlugin() {

		for (Class<?> apiClass : new Class[] { AdvancedChatApiT.class, AdvancedChatProvider.class }) {
			ClassLoader apiClassLoader = apiClass.getClassLoader();

			if (!apiClassLoader.equals(plugin.getClass().getClassLoader())) {
				String guilty = "unknown";
				try {
					//guilty = bootstrap.identifyClassLoader(apiClassLoader);
				} catch (Exception e) {
					// ignore
				}

				PluginLogger logger = (PluginLogger) this.plugin.getLogger();
				logger.warning("It seems that the AdvancedChat API has been (class)loaded by a plugin other than AdvancedChat!");
				logger.warning("The API was loaded by " + apiClassLoader + " (" + guilty + ") and the "
						+ "AdvancedChat plugin was loaded by " + plugin.toString() + ".");
				logger.warning("This indicates that the other plugin has incorrectly \"shaded\" the "
						+ "AdvancedChat API into its jar file. This can cause errors at runtime and should be fixed.");
				return;
			}
		}
	}

	public UserData geUserData() {
		return null;
	}

}
