package jss.advancedchat.api;

import java.util.Objects;

/**
 * Provides global access to the AdvancedChat API.
 * <p>
 * The provider is initialized during the platform startup
 * and exposes the current API implementation.
 *
 * @author ...
 * @since 2.0.0-alpha.1
 */
public final class AdvancedChatProvider {

    private static volatile AdvancedChat instance;

    /**
     * Utility class.
     */
    private AdvancedChatProvider() {}

    /**
     * Returns the active AdvancedChat API implementation.
     *
     * @return active API implementation
     * @throws IllegalStateException if the API has not been initialized
     */
    public static AdvancedChat get() {
        if (instance == null) {
            throw new IllegalStateException("AdvancedChat API is not initialized.");
        }

        return instance;
    }

    /**
     * Registers the AdvancedChat API implementation.
     *
     * <p>This method is intended to be called once during
     * the platform initialization.</p>
     *
     * @param advancedChat the API implementation
     *
     * @throws NullPointerException if {@code advancedChat} is {@code null}
     * @throws IllegalStateException if the API has already been registered
     */
    public static void register(AdvancedChat advancedChat) {

        Objects.requireNonNull(advancedChat, "AdvancedChat instance cannot be null.");

        if (instance != null) {
            throw new IllegalStateException("AdvancedChat API is already initialized.");
        }

        instance = advancedChat;
    }

    /**
     * Unregisters the current API implementation.
     *
     * <p>This method should only be used by the platform during
     * the plugin shutdown process.</p>
     *
     * @throws IllegalStateException if the API has not been initialized
     */
    public static void unregister() {
        if (instance == null) {
            throw new IllegalStateException("AdvancedChat API is not initialized.");
        }
        instance = null;
    }

    /**
     * Returns whether the API has been registered.
     *
     * @return {@code true} if the API is available, otherwise {@code false}
     */
    public static boolean isRegistered() {
        return instance != null;
    }

}
