package jss.advancedchat.manager;

import java.util.List;

import org.bukkit.entity.Player;

import jss.advancedchat.chat.Json;
import jss.advancedchat.utils.Util;

public class GroupHelper {
	
	private GroupManager groupManager = new GroupManager();
	private String group = "";
	private String format;
	private boolean click;
	private boolean hover;
	private String click_mode;
	private String cmd_action;
	private String url_action;
	private String suggest_action;
	private List<String> hovertext;
	private Json json;
	
	public static GroupHelper get() {
		return new GroupHelper();
	}
	
	public GroupHelper setGroup(String group) {
		this.group = group;
		return this;
	}
	
	public void sendGroup(Player player, String message) {
		this.getGroupOptions(group);
		json = new Json(player, Util.getVar(player, format), message);
		this.buildMessage();
	}
	
	private void getGroupOptions(String group) {
		format = groupManager.getFormat(group);
		click = groupManager.isClick(group);
		hover = groupManager.isHover(group);
		click_mode = groupManager.getClickMode(group);
		cmd_action = groupManager.getClickCommand(group);
		url_action = groupManager.getClickUrl(group);
		suggest_action = groupManager.getClickSuggestCommand(group);
		hovertext = groupManager.getHover(group);
	}
	
	private void buildMessage() {
		if (hover) {
			if (click) {
				if (click_mode.equals("command")) {
					json.setHover(hovertext).setExecuteCommand(cmd_action).sendDoubleToAll();
				} else if (click_mode.equals("url")) {
					json.setHover(hovertext).setOpenURL(url_action).sendDoubleToAll();
				} else if (click_mode.equals("suggest")) {
					json.setHover(hovertext).setSuggestCommand(suggest_action).sendDoubleToAll();
				}
			} else {
				json.setHover(hovertext).sendDoubleToAll();
			}
		} else {
			if (click) {
				if (click_mode.equals("command")) {
					json.setExecuteCommand(cmd_action).sendDoubleToAll();
				} else if (click_mode.equals("url")) {
					json.setOpenURL(url_action).sendDoubleToAll();
				} else if (click_mode.equals("suggest")) {
					json.setSuggestCommand(suggest_action).sendDoubleToAll();
				}
			} else {
				json.sendDoubleToAll();
			}
		}
	}	
}
