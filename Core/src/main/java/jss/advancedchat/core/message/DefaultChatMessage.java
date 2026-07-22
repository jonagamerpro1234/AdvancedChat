package jss.advancedchat.core.message;

import jss.advancedchat.api.model.chat.Chat;
import jss.advancedchat.api.model.message.ChatMessage;
import jss.advancedchat.api.model.player.ChatPlayer;

import java.util.Objects;

/**
 * Default implementation of {@link ChatMessage}.
 *
 * <p>This implementation is immutable and represents the
 * standard message model used by the Core module.</p>
 *
 * @author SrJss (Jonagamerpro1234)
 * @since 2.0.0-alpha.1
 */
public final class DefaultChatMessage implements ChatMessage {

    private final String content;
    private final ChatPlayer sender;
    private final Chat chat;

    public DefaultChatMessage(String content, ChatPlayer sender, Chat chat) {
        this.content = Objects.requireNonNull(content, "Message content cannot be null.");
        this.sender = Objects.requireNonNull(sender, "Message sender cannot be null.");
        this.chat = Objects.requireNonNull(chat, "Chat cannot be null.");
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public ChatPlayer getSender() {
        return sender;
    }

    @Override
    public Chat getChat() {
        return chat;
    }
}