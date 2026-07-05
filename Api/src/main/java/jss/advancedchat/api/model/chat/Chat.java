package jss.advancedchat.api.model.chat;

/**
 * Represents a communication channel within AdvancedChat.
 *
 * <p>A chat is responsible for processing and delivering messages
 * according to its configuration.</p>
 *
 * <p>This interface represents the core communication abstraction
 * of the domain and intentionally exposes only the minimum set of
 * properties required to identify and manage a chat.</p>
 *
 * @author SrJss (Jonagamerpro1234)
 * @since 2.0.0-alpha.1
 */
public interface Chat {


    String getId();

    String getName();

    boolean isEnabled();

}
