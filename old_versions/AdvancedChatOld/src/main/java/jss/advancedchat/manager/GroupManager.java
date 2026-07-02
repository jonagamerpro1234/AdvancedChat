package jss.advancedchat.manager;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.utils.Settings;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GroupManager {

    private final AdvancedChat plugin = AdvancedChat.get();
    private final FileConfiguration config = plugin.getGroupFile().getConfig();

    public static GroupManager get() {
        return new GroupManager();
    }

    public boolean isGroupType() {
        return Settings.chatformat_chattype.equalsIgnoreCase("group");
    }

    public Set<String> getGroupList() {
        return config.getKeys(false);
    }

    public boolean existsGroup(String group) {
        return config.contains(group);
    }

    public String getGroup(String group) {
        if (existsGroup(group)) {
            return config.getString(group);
        }
        return null;
    }

    public String getFormat(String group) {
        if (existsGroup(group)) {
            return config.getString(group + ".Format");
        }
        return null;
    }

    public boolean isHover(String group) {
        boolean isHover = false;
        if (existsGroup(group)) {
            isHover = config.getBoolean(group + ".HoverEvent.Enabled");
        }
        return isHover;
    }

    public List<String> getHover(String group) {
        if (existsGroup(group)) {
            return config.getStringList(group + ".HoverEvent.Hover");
        }
        return new ArrayList<>();
    }

    public boolean isClick(String group) {
        boolean isclick = false;
        if (existsGroup(group)) {
            isclick = config.getBoolean(group + ".ClickEvent.Enabled");
        }
        return isclick;
    }

    public String getClickMode(String group) {
        if (existsGroup(group)) {
            return config.getString(group + ".ClickEvent.Mode");
        }
        return null;
    }

    public String getClickCommand(String group) {
        if (existsGroup(group)) {
            return config.getString(group + ".ClickEvent.Actions.Command");
        }
        return null;
    }

    public String getClickUrl(String group) {
        if (existsGroup(group)) {
            return config.getString(group + ".ClickEvent.Actions.Url");
        }
        return null;
    }

    public String getClickSuggestCommand(String group) {
        if (existsGroup(group)) {
            return config.getString(group + ".ClickEvent.Actions.Suggest-Command");
        }
        return null;
    }

    public String getGroupColor(String group) {
        if (existsGroup(group)) {
            return config.getString(group + ".Default-Color-Message");
        }
        return null;
    }
}
