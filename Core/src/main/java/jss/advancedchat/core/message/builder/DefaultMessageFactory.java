package jss.advancedchat.core.message.builder;

import jss.advancedchat.api.builder.ChatMessageBuilder;
import jss.advancedchat.api.builder.MessageFactory;
import jss.advancedchat.api.model.message.ChatMessage;

public class DefaultMessageFactory implements MessageFactory {


    @Override
    public ChatMessageBuilder builder() {
        return new DefaultChatMessageBuilder();
    }
}
