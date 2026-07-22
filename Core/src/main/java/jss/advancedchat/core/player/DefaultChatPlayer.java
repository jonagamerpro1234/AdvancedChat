package jss.advancedchat.core.player;

import jss.advancedchat.api.model.player.ChatPlayer;

import java.util.Objects;
import java.util.UUID;

/**
 * Default implementation of {@link ChatPlayer}.
 *
 * <p>This implementation is immutable and represents
 * the standard player model used by the Core module.</p>
 *
 * @author SrJss (Jonagamerpro1234)
 * @since 2.0.0-alpha.1
 */
public final class DefaultChatPlayer implements ChatPlayer {

    private final UUID uniqueId;
    private final String name;

    public DefaultChatPlayer(UUID uniqueId, String name) {
        this.uniqueId = Objects.requireNonNull(uniqueId, "Player unique id cannot be null.");
        this.name = Objects.requireNonNull(name, "Player name cannot be null.");
    }

    @Override
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    public String getName() {
        return name;
    }


}
