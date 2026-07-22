package jss.advancedchat.api;

import jss.advancedchat.api.builder.MessageFactory;
import jss.advancedchat.api.service.chat.ChatService;

/**
 * Entry point of the AdvancedChat API.
 * <p>
 * Provides access to all public services exposed by AdvancedChat.
 *
 * @author SrJss (Jonagamerpro1234)
 * @since 2.0.0-alpha.1
 */
public interface AdvancedChat {

    //Chat Services
    ChatService getChatService();

    MessageFactory getMessageFactory();

}
