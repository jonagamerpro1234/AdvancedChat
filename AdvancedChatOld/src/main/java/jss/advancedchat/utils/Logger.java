package jss.advancedchat.utils;

import jss.advancedchat.files.utils.Settings;

public class Logger {

    public static void warning(String msg) {
        Util.sendColorMessage(EventUtils.getConsoleSender(), Util.getPrefixVar(Settings.logger_prefix_warning) + " " + msg);
    }

    public static void success(String msg) {
        Util.sendColorMessage(EventUtils.getConsoleSender(), Util.getPrefixVar(Settings.logger_prefix_success) + " " + msg);
    }

    public static void error(String msg) {
        Util.sendColorMessage(EventUtils.getConsoleSender(), Util.getPrefixVar(Settings.logger_prefix_error) + " " + msg);
    }

    public static void debug(String msg) {
        Util.sendColorMessage(EventUtils.getConsoleSender(), Util.getPrefixVar(Settings.logger_prefix_debug) + " " + msg);
    }

    public static void info(String msg) {
        Util.sendColorMessage(EventUtils.getConsoleSender(), Util.getPrefixVar(Settings.logger_prefix_info) + " " + msg);
    }

    public static void outline(String msg) {
        Util.sendColorMessage(EventUtils.getConsoleSender(), Util.getPrefixVar(Settings.logger_prefix_outline) + " " + msg);
    }

    public static void chat(String msg) {
        Util.sendColorMessage(EventUtils.getConsoleSender(), Util.getPrefixVar(Settings.logger_prefix_chat) + " " + msg);
    }

    public static void defaultMessage(String msg) {
        Util.sendColorMessage(EventUtils.getConsoleSender(), Util.getPrefix(true) + msg);
    }

}
