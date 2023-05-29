package jss.advancedchat.storage;

public class PlayerData {

    private final String name;
    private String group;
    private String chatType;
    private String channel;
    private int range;
    private String color;
    private String firstGradient, secondGradient;
    private String specialCodes;
    private boolean mute;
    private int timeMuted;
    private boolean lowMode;
    private boolean chat;
    private boolean privateMessage;

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

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFirstGradient() {
        return firstGradient;
    }

    public void setFirstGradient(String firstGradient) {
        this.firstGradient = firstGradient;
    }

    public String getSecondGradient() {
        return secondGradient;
    }

    public void setSecondGradient(String secondGradient) {
        this.secondGradient = secondGradient;
    }

    public String getSpecialCodes() {
        return specialCodes;
    }

    public void setSpecialCodes(String specialCodes) {
        this.specialCodes = specialCodes;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public int getTimeMuted() {
        return timeMuted;
    }

    public void setTimeMuted(int timeMuted) {
        this.timeMuted = timeMuted;
    }

    public boolean isLowMode() {
        return lowMode;
    }

    public void setLowMode(boolean lowMode) {
        this.lowMode = lowMode;
    }

    public boolean isChat() {
        return chat;
    }

    public void setChat(boolean chat) {
        this.chat = chat;
    }

    public boolean isPrivateMessage() {
        return privateMessage;
    }

    public void setPrivateMessage(boolean privateMessage) {
        this.privateMessage = privateMessage;
    }
}
