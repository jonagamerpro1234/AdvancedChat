package jss.advancedchat.api.pipeline;


import jss.advancedchat.api.model.message.ChatMessage;
import jss.advancedchat.api.pipeline.context.MessageContext;


public interface MessagePipeline {

    MessageContext process(ChatMessage message);

}
