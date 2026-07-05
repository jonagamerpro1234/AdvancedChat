package jss.advancedchat.api.model.message;

import jss.advancedchat.api.model.chat.Chat;
import jss.advancedchat.api.model.player.ChatPlayer;

/**
 * Represents an immutable message travelling through the
 * AdvancedChat communication pipeline.
 *
 * <p>Implementations of this interface should be immutable.
 * Any modification to a message should result in a new instance
 * rather than altering the existing one.</p>
 */
public interface ChatMessage {

    String getContent();

    ChatPlayer getSender();

    Chat getChat();

}
