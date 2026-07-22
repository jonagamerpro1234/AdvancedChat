package jss.advancedchat.core.pipeline;

import jss.advancedchat.api.pipeline.PipelineRegistry;
import jss.advancedchat.api.pipeline.processor.MessageProcessor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class DefaultPipelineRegistry implements PipelineRegistry {

    private final List<MessageProcessor> processors;

    public DefaultPipelineRegistry() {
        this.processors = new ArrayList<>();
    }

    @Override
    public void register(@NotNull MessageProcessor processor) {
        Objects.requireNonNull(processor, "processor");

        if (processors.contains(processor)) {
            throw new IllegalArgumentException(
                    "Processor is already registered."
            );
        }

        processors.add(processor);
    }

    @Override
    public void unregister(@NotNull MessageProcessor processor) {
        Objects.requireNonNull(processor, "processor");
        processors.remove(processor);
    }

    @Contract(pure = true)
    @Override
    public @NotNull @UnmodifiableView Collection<MessageProcessor> getProcessors() {
        return Collections.unmodifiableList(processors);
    }
}