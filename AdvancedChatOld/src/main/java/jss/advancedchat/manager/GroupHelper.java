package jss.advancedchat.manager;

import jss.advancedchat.chat.Json;
import jss.advancedchat.utils.Util;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GroupHelper {

    private final GroupManager groupManager = new GroupManager();
    private String group;
    private String format;
    private boolean click;
    private boolean hover;
    private String click_mode;
    private String cmd_action;
    private String url_action;
    private String suggest_action;
    private List<String> hoverText;
    private Json json;

    @Contract(" -> new")
    public static @NotNull GroupHelper get() {
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
        hoverText = groupManager.getHover(group);
    }

    private void buildMessage() {
        if (hover) {
            if (click) {
                switch (click_mode) {
                    case "command":
                        json.setHover(hoverText).setExecuteCommand(cmd_action).sendDoubleToAll();
                        break;
                    case "url":
                        json.setHover(hoverText).setOpenURL(url_action).sendDoubleToAll();
                        break;
                    case "suggest":
                        json.setHover(hoverText).setSuggestCommand(suggest_action).sendDoubleToAll();
                        break;
                }
            } else {
                json.setHover(hoverText).sendDoubleToAll();
            }
        } else {
            if (click) {
                switch (click_mode) {
                    case "command":
                        json.setExecuteCommand(cmd_action).sendDoubleToAll();
                        break;
                    case "url":
                        json.setOpenURL(url_action).sendDoubleToAll();
                        break;
                    case "suggest":
                        json.setSuggestCommand(suggest_action).sendDoubleToAll();
                        break;
                }
            } else {
                json.sendDoubleToAll();
            }
        }
    }
}
