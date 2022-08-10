package jss.advancedchat.chat;

import java.util.List;
import jss.advancedchat.utils.Utils;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import org.bukkit.entity.Player;

@SuppressWarnings("deprecation")
public class Json {

	private Player player;
	private String text;
	private BaseComponent[] hoverText;
	private String suggestCommand;
	private String executeCommand;
	private String openURL;
	private String extraText;

	public Json(Player player, String text) {
		this.player = player;
		this.text = text;
		this.hoverText = null;
	}

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

	public Json setExtraText(String textEx) {
		this.extraText = textEx;
		return this;
	}

	public String getExtraText() {
		return this.extraText;
	}

	public Json setHover(List<String> hover) {
		this.hoverText = new BaseComponent[hover.size()];

		for (int i = 0; i < hover.size(); ++i) {
			TextComponent component = new TextComponent();
			if (i == hover.size() - 1) {
				component.setText(Utils.color(Utils.getVar(this.player, (String) hover.get(i))));
			} else {
				component.setText(Utils.color(Utils.getVar(this.player, (String) hover.get(i)) + "\n"));
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

	public void send() {
		TextComponent component = new TextComponent(TextComponent.fromLegacyText(this.getText()));
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

		this.player.spigot().sendMessage(component);
	}

	public void sendToAll() {
		TextComponent component = new TextComponent(TextComponent.fromLegacyText(this.getText()));
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

		Utils.sendAllPlayerBaseComponent(component);
	}

	public void sendDouble() {
		TextComponent component = new TextComponent(TextComponent.fromLegacyText(this.getText()));
		TextComponent component2 = new TextComponent(TextComponent.fromLegacyText(this.getExtraText()));
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

		this.player.spigot().sendMessage(new BaseComponent[] { component, component2 });
	}

	public void sendDoubleToAll() {
		TextComponent component = new TextComponent(TextComponent.fromLegacyText(this.getText()));
		TextComponent component2 = new TextComponent(TextComponent.fromLegacyText(this.getExtraText()));
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

		Utils.sendAllPlayerBaseComponent(component, component2);
	}
}