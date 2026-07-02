package jss.advancedchat.chat;

import jss.advancedchat.utils.Utils;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Allows the creation of personalized messages which are sent to all players
 */
public class MessageBuilder {

    private final Player player;
    private final String message;
    private final String extraMessage;
    private BaseComponent[] hoverText;
    private String suggestCommand;
    private String executeCommand;
    private String openURL;

    public MessageBuilder(Player player, String message, String extraMessage) {
        this.player = player;
        this.message = message;
        this.extraMessage = extraMessage;
        this.hoverText = null;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getMessage() {
        return this.message;
    }

    public String getExtraMessage() {
        return this.extraMessage;
    }

    public String getFormatFinal() {
        return Utils.color(Utils.getVar(this.player, this.message + this.extraMessage));
    }

    public MessageBuilder setHover(@NotNull List<String> hover) {
        this.hoverText = toBaseComponents(hover);
        /* new BaseComponent[hover.size()];
        for (int i = 0; i < hover.size(); i++) {
            TextComponent component = new TextComponent();
            if (i == hover.size() - 1) {
                component.setText(Utils.color(Utils.getVar(this.player, hover.get(i))));
            } else {
                component.setText(Utils.color(Utils.getVar(this.player, hover.get(i)) + "\n"));
            }
            this.hoverText[i] = component;
        }*/
        return this;
    }

    public MessageBuilder setSuggestCommand(String suggestCommand) {
        this.suggestCommand = suggestCommand;
        return this;
    }

    public MessageBuilder setExecuteCommand(String executeCommand) {
        this.executeCommand = executeCommand;
        return this;
    }

    public MessageBuilder setOpenURL(String url) {
        this.openURL = url;
        return this;
    }

    public void sendToAllOld() {
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(this.message));
        TextComponent component2 = new TextComponent(TextComponent.fromLegacyText(this.extraMessage));
        if (this.hoverText != null)
            component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, this.hoverText));
        if (this.executeCommand != null)
            component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, this.executeCommand));
        if (this.suggestCommand != null)
            component.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, this.suggestCommand));
        if (this.openURL != null)
            component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, this.openURL));
        Utils.sendAllPlayerBaseComponent(component, component2);
    }

    public void sendToAll() {
        TextComponent mainMessage = toTextComponent(this.message);
        TextComponent extraMessage = toTextComponent(this.extraMessage);

        if (this.hoverText != null) {
            mainMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, this.hoverText));
        }
        if (this.executeCommand != null) {
            mainMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, this.executeCommand));
        }
        if (this.suggestCommand != null) {
            mainMessage.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, this.suggestCommand));
        }
        if (this.openURL != null) {
            mainMessage.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, this.openURL));
        }
        Utils.sendAllPlayerBaseComponent(mainMessage, extraMessage);
    }

    @Contract("_ -> new")
    private @NotNull TextComponent toTextComponent(String message) {
        return new TextComponent(TextComponent.fromLegacyText(Utils.color(Utils.getVar(this.player, message))));
    }

    private BaseComponent @NotNull [] toBaseComponents(@NotNull List<String> hover){
        BaseComponent[] components = new BaseComponent[hover.size()];

        for (int i = 0; i < hover.size(); i++){

            TextComponent component = new TextComponent();

            if(i == hover.size() -1){
                component.setText(Utils.color(Utils.getVar(this.player, hover.get(i))));
            }else{
                component.setText(Utils.color(Utils.getVar(this.player, hover.get(i)) + "\n"));
            }

            components[i] = component;
        }

        return components;
    }

}
