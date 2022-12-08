package jss.advancedchat.chat;

import jss.advancedchat.utils.Utils;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.List;

public class MessageBuilder {
  private final Player player;

  private String text;

  private BaseComponent[] hoverText;

  private String suggestCommand;

  private String executeCommand;

  private String openURL;

  private final String extraText;

  public MessageBuilder(Player player, String text, String textEx) {
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

  public MessageBuilder setText(String text) {
    this.text = text;
    return this;
  }

  public String getExtraText() {
    return this.extraText;
  }

  public String getFormatFinal() {
    return Utils.color(Utils.getVar(this.player, this.text + this.extraText));
  }

  public MessageBuilder setHover(List<String> hover) {
    this.hoverText = new BaseComponent[hover.size()];
    for (int i = 0; i < hover.size(); i++) {
      TextComponent component = new TextComponent();
      if (i == hover.size() - 1) {
        component.setText(Utils.color(Utils.getVar(this.player, hover.get(i))));
      } else {
        component.setText(Utils.color(Utils.getVar(this.player, hover.get(i)) + "\n"));
      }
      this.hoverText[i] = component;
    }
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

  public void sendToAll() {
    TextComponent component = new TextComponent(TextComponent.fromLegacyText(getText()));
    TextComponent component2 = new TextComponent(TextComponent.fromLegacyText(getExtraText()));
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
}
