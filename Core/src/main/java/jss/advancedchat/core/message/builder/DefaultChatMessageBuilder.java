package jss.advancedchat.core.message.builder;

import jss.advancedchat.api.builder.ChatMessageBuilder;
import jss.advancedchat.api.model.chat.Chat;
import jss.advancedchat.api.model.message.ChatMessage;
import jss.advancedchat.api.model.player.ChatPlayer;
import jss.advancedchat.core.message.DefaultChatMessage;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class DefaultChatMessageBuilder implements ChatMessageBuilder {

    private String content;
    private ChatPlayer sender;
    private Chat chat;

    @Override
    public ChatMessageBuilder content(String content) {
        this.content = Objects.requireNonNull(content, "content");
        return this;
    }

    @Override
    public ChatMessageBuilder sender(ChatPlayer sender) {
        this.sender = Objects.requireNonNull(sender, "sender");
        return this;
    }

    @Override
    public ChatMessageBuilder chat(Chat chat) {
        this.chat = Objects.requireNonNull(chat, "chat");
        return this;
    }

    @Contract(" -> new")
    @Override
    public @NotNull ChatMessage build() {

        if (content == null) {
            throw new IllegalStateException("Message content has not been set.");
        }

        if (sender == null) {
            throw new IllegalStateException("Message sender has not been set.");
        }

        if (chat == null) {
            throw new IllegalStateException("Message chat has not been set.");
        }

        return new DefaultChatMessage(content, sender, chat);
    }
}
