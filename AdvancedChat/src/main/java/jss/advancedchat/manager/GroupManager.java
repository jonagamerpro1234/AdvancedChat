package jss.advancedchat.manager;

import java.util.List;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;

import jss.advancedchat.AdvancedChat;

public class GroupManager {

	private AdvancedChat plugin;
	
	public GroupManager(AdvancedChat plugin) {
		this.plugin = plugin;
	}
	
	private final FileConfiguration config = plugin.getGroupFile().getConfig();
	
	public Set<String> getGroups() {
		Set<String> sections = config.getKeys(false);
		return sections;
	}
	
	public String getGroup(String group) throws NullPointerException{
		return config.getString(group);
	}
	
	public String getFormat(String group) {
		return config.getString(group + ".Format");
	}
	
	public boolean isHover(String group) {
		return config.getString(group + ".HoverEvent.Enabled").equals("true");
	}
	
	public List<String> getHover(String group) {
		return config.getStringList(group + ".HoverEvent.Hover");
	}
	
	public boolean isClick(String group) {
		return config.getString(group + ".ClickEvent.Enabled").equals("true");
	}
	
	public String getClickMode(String group) {
		return config.getString(group + ".ClickEvent.Mode");
	}
	
	public String getClickCommand(String group) {
		return config.getString(group + ".ClickEvent.Actions.Command");
	}
	
	public String getClickUrl(String group) {
		return config.getString(group + ".ClickEvent.Actions.Url");
	}
	
	public String getClickSuggestCommand(String group) {
		return config.getString(group + ".ClickEvent.Actions.Suggest-Command");
	}
}
