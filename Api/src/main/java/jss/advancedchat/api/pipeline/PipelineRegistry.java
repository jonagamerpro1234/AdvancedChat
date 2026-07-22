package jss.advancedchat.api.pipeline;

import jss.advancedchat.api.pipeline.processor.MessageProcessor;

import java.util.Collection;

public interface PipelineRegistry {

    void register(MessageProcessor processor);

    void unregister(MessageProcessor processor);

    Collection<MessageProcessor> getProcessors();

}