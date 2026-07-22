package jss.advancedchat.api.pipeline.processor;

import jss.advancedchat.api.pipeline.context.MessageContext;

public interface MessageProcessor {

    void process(MessageContext context);

}
