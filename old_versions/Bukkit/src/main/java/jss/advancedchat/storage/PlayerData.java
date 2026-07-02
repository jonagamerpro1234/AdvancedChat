package jss.advancedchat.storage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerData {

    private final String name;
    private final String group;
    private final String chatType;
    private final String channel;
    private final int range;
    private final String color;
    private final String firstGradient, secondGradient;
    private final String specialCodes;
    private final boolean mute;
    private final int timeMuted;
    private final boolean lowMode;
    private final boolean chat;
    private final boolean privateMessage;

    public PlayerData(String name, String group, String chatType, String channel, int range, String color, String firstGradient, String secondGradient, String specialCodes, boolean mute, int timeMuted, boolean lowMode, boolean chat, boolean privateMessage) {
        this.name = name;
        this.group = group;
        this.chatType = chatType;
        this.channel = channel;
        this.range = range;
        this.color = color;
        this.firstGradient = firstGradient;
        this.secondGradient = secondGradient;
        this.specialCodes = specialCodes;
        this.mute = mute;
        this.timeMuted = timeMuted;
        this.lowMode = lowMode;
        this.chat = chat;
        this.privateMessage = privateMessage;
    }

}
