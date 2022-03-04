package jss.advancedchat.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Settings;

public class GroupManager{

	private AdvancedChat plugin = AdvancedChat.get();
	private FileConfiguration config = plugin.getGroupFile().getConfig();
	
	public static GroupManager get() {
		return new GroupManager();
	}
	
	public boolean isGroup() {
		boolean isgroup = false;
		if(Settings.chatformat_chattype.equalsIgnoreCase("group")) {
			isgroup = true;
		}
		return isgroup;
	}
	
	public Set<String> getGroupList() {
		Set<String> sections = config.getKeys(false);
		return sections;
	}
	
	public boolean existsGroup(String group) {
		if(config.contains(group)) {
			return true;
		}
		return false;
	}
	
	public String getGroup(String group){
		if(existsGroup(group)) {
			return config.getString(group);
		}
		return null;
	}
	
	public String getFormat(String group) {
		if(existsGroup(group)) {
			return config.getString(group + ".Format");
		}
		return null;
	}
	
	public boolean isHover(String group) {
		boolean isHover = false;
		if(existsGroup(group)) {
			isHover = config.getString(group + ".HoverEvent.Enabled").equals("true");
		}
		return isHover;
	}
	
	public List<String> getHover(String group) {
		if(existsGroup(group)) {
			return config.getStringList(group + ".HoverEvent.Hover");
		}
		return new ArrayList<String>();
	}
	
	public boolean isClick(String group) {
		boolean isclick = false;
		if(existsGroup(group)) {
			isclick = config.getString(group + ".ClickEvent.Enabled").equals("true");
		}
		return isclick;
	}
	
	public String getClickMode(String group) {
		if(existsGroup(group)) {
			return config.getString(group + ".ClickEvent.Mode");
		}
		return null;
	}
	
	public String getClickCommand(String group) {
		if(existsGroup(group)) {
			return config.getString(group + ".ClickEvent.Actions.Command");
		}
		return null;
	}
	
	public String getClickUrl(String group) {
		if(existsGroup(group)) {
			return config.getString(group + ".ClickEvent.Actions.Url");
		}
		return null;
	}
	
	public String getClickSuggestCommand(String group) {
		if(existsGroup(group)) {
			return config.getString(group + ".ClickEvent.Actions.Suggest-Command");
		}
		return null;
	}
	
	public String getGroupColor(String group) {
		if(existsGroup(group)) {
			return config.getString(group + ".Default-Color-Message");
		}
		return null;
	}
}
