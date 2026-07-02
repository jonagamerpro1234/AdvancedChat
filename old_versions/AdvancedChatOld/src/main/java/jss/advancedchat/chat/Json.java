package jss.advancedchat.chat;

import jss.advancedchat.utils.Util;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("deprecation")
public class Json {
    private final Player player;
    private final String extraText;
    private String text;
    private BaseComponent[] hoverText;
    private String suggestCommand;
    private String executeCommand;
    private String openURL;

    public Json(Player player, String text, String textEx) {
        this.player = player;
        this.text = text;
        this.extraText = textEx;
        this.hoverText = null;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getText() {
        return this.text;
    }

    public Json setText(String text) {
        this.text = text;
        return this;
    }

    public String getExtraText() {
        return this.extraText;
    }

    public Json setHover(@NotNull List<String> hover) {
        this.hoverText = new BaseComponent[hover.size()];

        for (int i = 0; i < hover.size(); ++i) {
            TextComponent component = new TextComponent();
            if (i == hover.size() - 1) {
                component.setText(Util.color(Util.getVar(this.player, hover.get(i))));
            } else {
                component.setText(Util.color(Util.getVar(this.player, hover.get(i)) + "\n"));
            }

            this.hoverText[i] = component;
        }
        return this;
    }

    public Json setSuggestCommand(String suggestCommand) {
        this.suggestCommand = suggestCommand;
        return this;
    }

    public Json setExecuteCommand(String executeCommand) {
        this.executeCommand = executeCommand;
        return this;
    }

    public Json setOpenURL(String url) {
        this.openURL = url;
        return this;
    }

    public String getFormat() {
        return Util.color(Util.getVar(player, this.text + this.extraText));
    }

    public void sendDoubleToAll() {
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(Util.color(this.getText())));
        TextComponent component2 = new TextComponent(TextComponent.fromLegacyText(Util.color(this.getExtraText())));
        if (this.hoverText != null) {
            component.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, this.hoverText));
        }

        if (this.executeCommand != null) {
            component.setClickEvent(
                    new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, this.executeCommand));
        }

        if (this.suggestCommand != null) {
            component.setClickEvent(
                    new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, this.suggestCommand));
        }

        if (this.openURL != null) {
            component.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.OPEN_URL, this.openURL));
        }

        Util.sendAllBaseComponent(component, component2);
    }
}