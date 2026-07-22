package jss.advancedchat.core.pipeline;

import jss.advancedchat.api.model.message.ChatMessage;
import jss.advancedchat.api.pipeline.MessagePipeline;
import jss.advancedchat.api.pipeline.PipelineRegistry;
import jss.advancedchat.api.pipeline.context.MessageContext;
import jss.advancedchat.api.pipeline.processor.MessageProcessor;
import jss.advancedchat.core.pipeline.context.DefaultMessageContext;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class DefaultMessagePipeline implements MessagePipeline {

    private final PipelineRegistry registry;

    public DefaultMessagePipeline(@NotNull PipelineRegistry registry) {
        this.registry = Objects.requireNonNull(registry, "registry");
    }

    @Override
    public @NotNull MessageContext process(@NotNull ChatMessage message) {

        Objects.requireNonNull(message, "message");

        MessageContext context = new DefaultMessageContext(message);

        for (MessageProcessor processor : registry.getProcessors()) {

            if (context.isCancelled()) {
                break;
            }

            processor.process(context);
        }

        return context;
    }

}