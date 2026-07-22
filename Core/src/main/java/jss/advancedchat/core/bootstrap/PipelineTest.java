package jss.advancedchat.core.bootstrap;

import jss.advancedchat.api.builder.ChatMessageBuilder;
import jss.advancedchat.api.model.chat.Chat;
import jss.advancedchat.api.model.message.ChatMessage;
import jss.advancedchat.api.model.player.ChatPlayer;
import jss.advancedchat.api.pipeline.MessagePipeline;
import jss.advancedchat.api.pipeline.PipelineRegistry;
import jss.advancedchat.api.pipeline.context.MessageContext;
import jss.advancedchat.core.chat.DefaultChat;
import jss.advancedchat.core.message.builder.DefaultMessageFactory;
import jss.advancedchat.core.pipeline.DefaultMessagePipeline;
import jss.advancedchat.core.pipeline.DefaultPipelineRegistry;
import jss.advancedchat.core.pipeline.processor.UppercaseProcessor;
import jss.advancedchat.core.player.DefaultChatPlayer;

import java.util.UUID;

public final class PipelineTest {

    public static void main(String[] args) {

        PipelineRegistry registry = new DefaultPipelineRegistry();
        registry.register(new UppercaseProcessor());

        MessagePipeline pipeline = new DefaultMessagePipeline(registry);

        ChatMessageBuilder builder = new DefaultMessageFactory().builder();

        ChatPlayer player = new DefaultChatPlayer(UUID.randomUUID(),"TestPlayer");
        Chat chat = new DefaultChat("1","global",true);
        ChatMessage message = builder.content("test").sender(player).chat(chat).build();

        MessageContext context = pipeline.process(message);

        System.out.println("Origin : " + message.getContent());
        System.out.println("Result: " + context.getContent());
    }
}