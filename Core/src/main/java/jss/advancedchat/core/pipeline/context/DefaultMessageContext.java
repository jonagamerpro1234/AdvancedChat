package jss.advancedchat.core.pipeline.context;

import jss.advancedchat.api.model.message.ChatMessage;
import jss.advancedchat.api.pipeline.context.MessageContext;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class DefaultMessageContext implements MessageContext {

    private final ChatMessage message;
    private String content;
    private boolean cancelled;

    public DefaultMessageContext(@NotNull ChatMessage message) {
        this.message = Objects.requireNonNull(message, "message");
        this.content = message.getContent();
        this.cancelled = false;
    }

    @Override
    public @NotNull ChatMessage getMessage() {
        return message;
    }

    @Override
    public @NotNull String getContent() {
        return content;
    }

    @Override
    public void setContent(@NotNull String content) {
        this.content = Objects.requireNonNull(content, "content");
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
