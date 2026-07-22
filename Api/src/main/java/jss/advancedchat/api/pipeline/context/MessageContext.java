package jss.advancedchat.api.pipeline.context;

import jss.advancedchat.api.model.message.ChatMessage;
import org.jetbrains.annotations.NotNull;

public interface MessageContext {

    ChatMessage getMessage();

    String getContent();

    void setContent(@NotNull String content);

    boolean isCancelled();

    void setCancelled(boolean cancelled);

}
