package jss.advancedchat.api;

import javax.annotation.Nonnull;

import static org.jetbrains.annotations.ApiStatus.Internal;

public final class AdvancedChatProvider {
	
	private static AdvancedChatApiT instance = null;
	
	public static @Nonnull AdvancedChatApiT get() {
		AdvancedChatApiT instance = AdvancedChatProvider.instance;
		if(instance == null) {
			throw new NotLoadedException();
		}
		return instance;
	}
	
    @Internal
    static void register(AdvancedChatApiT instance) {
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
	

    private static final class NotLoadedException extends IllegalStateException {
		private static final long serialVersionUID = 2818988245527975199L;
		private static final String MESSAGE = "ANSI_AQUA" + "The AdvancedChat API isn't loaded yet!\n" +
                "This could be because:\n" +
                "  a) the AdvancedChat plugin is not installed or it failed to enable\n" +
                "  b) the plugin in the stacktrace does not declare a dependency on AdvancedChat\n" +
                "  c) the plugin in the stacktrace is retrieving the API before the plugin 'enable' phase\n" +
                "     (call the #get method in onEnable, not the constructor!)\n";

        NotLoadedException() {
            super(MESSAGE);
        }
    }
	
}
