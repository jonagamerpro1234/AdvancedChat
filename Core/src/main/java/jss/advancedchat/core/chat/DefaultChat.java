package jss.advancedchat.core.chat;

import java.util.Objects;

import jss.advancedchat.api.model.chat.Chat;

/**
 * Default implementation of {@link Chat}.
 *
 * <p>This implementation is immutable and represents
 * the standard chat model used by the Core module.</p>
 *
 * @author SrJss (Jonagamerpro1234)
 * @since 2.0.0-alpha.1
 */
public final class DefaultChat implements Chat {

    private final String id;
    private final String name;
    private final boolean enabled;

    /**
     * Creates a new chat.
     *
     * @param id the unique chat identifier.
     * @param name the chat display name.
     * @param enabled whether the chat is enabled.
     */
    public DefaultChat(String id, String name, boolean enabled) {
        this.id = Objects.requireNonNull(id, "Chat id cannot be null.");
        this.name = Objects.requireNonNull(name, "Chat name cannot be null.");
        this.enabled = enabled;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}