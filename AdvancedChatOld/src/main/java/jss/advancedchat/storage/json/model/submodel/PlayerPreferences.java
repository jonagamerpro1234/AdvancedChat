package jss.advancedchat.storage.json.model.submodel;

public class PlayerPreferences {

    private String chatType;
    private String language;
    private boolean hideMention;
    private boolean soundNotifications;
    private final ChatMessage chatMessage;
    private final MuteSettings muteSettings;
    private final OtherSettings otherSettings;
    private final DiscordSettings discordSettings;

    public PlayerPreferences(){
        this.chatType = "color";
        this.language = "en_US";
        this.hideMention = false;
        this.soundNotifications = true;
        this.chatMessage = new ChatMessage();
        this.muteSettings = new MuteSettings();
        this.otherSettings = new OtherSettings();
        this.discordSettings = new DiscordSettings();
    }

    public ChatMessage getChatMessage() {
        return chatMessage;
    }

    public MuteSettings getMuteSettings() {
        return muteSettings;
    }

    public OtherSettings getOtherSettings() {
        return otherSettings;
    }

    public DiscordSettings getDiscordSettings() {
        return discordSettings;
    }

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isHideMention() {
        return hideMention;
    }

    public void setHideMention(boolean hideMention) {
        this.hideMention = hideMention;
    }

    public boolean isSoundNotifications() {
        return soundNotifications;
    }

    public void setSoundNotifications(boolean soundNotifications) {
        this.soundNotifications = soundNotifications;
    }

    public static class ChatMessage{
        private String color;
        private String rainbow;
        private String specialColorCodes;

        public ChatMessage() {
            this.color = "#FFFFFF";
            this.rainbow = "rainbow_1";
            this.specialColorCodes = "none";
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getRainbow() {
            return rainbow;
        }

        public void setRainbow(String rainbow) {
            this.rainbow = rainbow;
        }

        public String getSpecialColorCodes() {
            return specialColorCodes;
        }

        public void setSpecialColorCodes(String specialColorCodes) {
            this.specialColorCodes = specialColorCodes;
        }
    }

    public static class MuteSettings{
        private boolean isMute;
        private boolean isTempMute;
        private int TimeTempMute;
        private String reason;

        public MuteSettings() {
            this.isMute = false;
            this.isTempMute = false;
            this.TimeTempMute = 0;
            this.reason = "";
        }

        public boolean isMute() {
            return isMute;
        }

        public void setMute(boolean mute) {
            isMute = mute;
        }

        public boolean isTempMute() {
            return isTempMute;
        }

        public void setTempMute(boolean tempMute) {
            isTempMute = tempMute;
        }

        public int getTimeTempMute() {
            return TimeTempMute;
        }

        public void setTimeTempMute(int timeTempMute) {
            TimeTempMute = timeTempMute;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }

    public static class OtherSettings{
        private boolean isChat;
        private boolean isMention;
        private boolean isMsg;
        private boolean isLowMode;

        public OtherSettings() {
            this.isChat = true;
            this.isMention = true;
            this.isMsg = true;
            this.isLowMode = false;
        }

        public boolean isChat() {
            return isChat;
        }

        public void setChat(boolean chat) {
            isChat = chat;
        }

        public boolean isMention() {
            return isMention;
        }

        public void setMention(boolean mention) {
            isMention = mention;
        }

        public boolean isMsg() {
            return isMsg;
        }

        public void setMsg(boolean msg) {
            isMsg = msg;
        }

        public boolean isLowMode() {
            return isLowMode;
        }

        public void setLowMode(boolean lowMode) {
            isLowMode = lowMode;
        }
    }

    public static class DiscordSettings{
        private boolean isLinked;
        private String discordId;
        private boolean SyncNickName;
        private boolean ReceiveMessages;

        public DiscordSettings() {
            this.isLinked = false;
            this.discordId = "";
            this.SyncNickName = false;
            this.ReceiveMessages = true;
        }

        public boolean isLinked() {
            return isLinked;
        }

        public void setLinked(boolean linked) {
            isLinked = linked;
        }

        public String getDiscordId() {
            return discordId;
        }

        public void setDiscordId(String discordId) {
            this.discordId = discordId;
        }

        public boolean isSyncNickName() {
            return SyncNickName;
        }

        public void setSyncNickName(boolean syncNickName) {
            SyncNickName = syncNickName;
        }

        public boolean isReceiveMessages() {
            return ReceiveMessages;
        }

        public void setReceiveMessages(boolean receiveMessages) {
            ReceiveMessages = receiveMessages;
        }
    }

}
