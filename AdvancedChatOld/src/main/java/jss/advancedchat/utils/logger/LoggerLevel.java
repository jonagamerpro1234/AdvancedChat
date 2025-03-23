package jss.advancedchat.utils.logger;

import jss.advancedchat.files.utils.Settings;

public enum LoggerLevel {
    INFO(Settings.logger_prefix_info),
    WARNING(Settings.logger_prefix_warning),
    SUCCESS(Settings.logger_prefix_success),
    ERROR(Settings.logger_prefix_error),
    DEBUG(Settings.logger_prefix_debug),
    OUTLINE(Settings.logger_prefix_outline),
    CHAT(Settings.logger_prefix_chat);

    private final String prefix;

    LoggerLevel(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
