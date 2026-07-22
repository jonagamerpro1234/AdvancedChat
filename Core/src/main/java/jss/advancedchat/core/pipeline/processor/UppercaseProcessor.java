package jss.advancedchat.core.pipeline.processor;

import jss.advancedchat.api.pipeline.context.MessageContext;
import jss.advancedchat.api.pipeline.processor.MessageProcessor;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class UppercaseProcessor implements MessageProcessor {

    @Override
    public void process(@NotNull MessageContext context) {
        Objects.requireNonNull(context, "context");

        context.setContent(
                context.getContent().toUpperCase()
        );
    }

}