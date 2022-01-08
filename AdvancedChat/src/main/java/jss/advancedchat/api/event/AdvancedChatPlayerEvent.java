package jss.advancedchat.api.event;

import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.HoverEvent.Action;

public class AdvancedChatPlayerEvent extends Event implements Cancellable {
	
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
	private Player player;
	private String text;
	private BaseComponent[] hoverText;
	private String suggestCommand;
	private String executeCommand;
	private String openURL;
	private String extraText;
	private AsyncPlayerChatEvent asyncPlayerChatEvent;

	public AdvancedChatPlayerEvent(Player player, String text, String extraText) {
		this.player = player;
		this.text = text;
		this.hoverText = null;
	}

	public Player getPlayer() {
		return this.player;
	}
	
	public AsyncPlayerChatEvent getPlayerChatEvent() {
		return asyncPlayerChatEvent;
	}
	
	/**
	 * get the defined message
	 * @param text
	 * @return message
	 */
	public String getText() {
		return this.text;
	}
	

	public AdvancedChatPlayerEvent setText(String text) {
		this.text = text;
		return this;
	}
	
	public AdvancedChatPlayerEvent setExtraText(String textEx) {
		this.extraText = textEx;
		return this;
	}
	
	/**
	 * get the defined message
	 * @return message
	 */
	public String getExtraText() {
		return this.extraText;
	}
	
	/**
	 * Add the hover component
	 * @param executeCommand
	 * @return AdvancedChatPlayerEvent
	 */
	public AdvancedChatPlayerEvent setHover(List<String> hover) {
		this.hoverText = new BaseComponent[hover.size()];

		for (int i = 0; i < hover.size(); ++i) {
			TextComponent component = new TextComponent();
			if (i == hover.size() - 1) {
				component.setText((String) hover.get(i));
			} else {
				component.setText((String) hover.get(i) + "\n");
			}

			this.hoverText[i] = component;
		}
		return this;
	}
	
	/**
	 * Add the suggest_command component
	 * @param suggestCommand
	 * @return AdvancedChatPlayerEvent
	 */
	public AdvancedChatPlayerEvent setSuggestCommand(String suggestCommand) {
		this.suggestCommand = suggestCommand;
		return this;
	}
	
	/**
	 * Add the run_command component
	 * @param executeCommand
	 * @return AdvancedChatPlayerEvent
	 */
	public AdvancedChatPlayerEvent setExecuteCommand(String executeCommand) {
		this.executeCommand = executeCommand;
		return this;
	}
	
	/**
	 * Add the open_url component
	 * 
	 * @param url
	 * @return AdvancedChatPlayerEvent
	 */
	public AdvancedChatPlayerEvent setOpenURL(String url) {
		this.openURL = url;
		return this;
	}
	
	/**
	 * Send the created message
	 */
	@SuppressWarnings("deprecation")
	public void send() {
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

		this.sendAllPlayerBaseComponent(component, component2);
	}
	
	private void sendAllPlayerBaseComponent(BaseComponent... baseComponents) {
		try{
			Iterator<?> a = Bukkit.getOnlinePlayers().iterator();
			
			while(a.hasNext()) {
				Player p = (Player) a.next();
				p.spigot().sendMessage(baseComponents);
			}
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}
	
    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
	
}
