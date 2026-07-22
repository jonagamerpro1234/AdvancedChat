package jss.advancedchat.api.builder;

import jss.advancedchat.api.model.chat.Chat;
import jss.advancedchat.api.model.message.ChatMessage;
import jss.advancedchat.api.model.player.ChatPlayer;

public interface ChatMessageBuilder {

    ChatMessageBuilder content(String content);

    ChatMessageBuilder sender(ChatPlayer sender);

    ChatMessageBuilder chat(Chat chat);

    ChatMessage build();

}