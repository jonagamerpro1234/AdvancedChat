package jss.advancedchat.bungee.utils;

import jss.advancedchat.bungee.AdvancedChatBungee;

public class LoggerBunge {

    private AdvancedChatBungee plugin;
    private EventUtils eventsUtils = new EventUtils(plugin);

    public LoggerBunge(AdvancedChatBungee plugin) {
        this.plugin = plugin;
    }
    
    public static void error(String msg) {
    	UtilsBunge.sendBungeeColorMessageConsole(EventUtils.getStaticConsoleSender(), ERRORPrefix() + " " + msg);
    }

    public static void warning(String msg) {
    	UtilsBunge.sendBungeeColorMessageConsole(EventUtils.getStaticConsoleSender(), WARNINGPrefix() + " " + msg);
    }
    
    public static void info(String msg) {
    	UtilsBunge.sendBungeeColorMessageConsole(EventUtils.getStaticConsoleSender(), INFOPrefix() + " " + msg);
    }
    
    public static void outLine(String msg) {
    	UtilsBunge.sendBungeeColorMessageConsole(EventUtils.getStaticConsoleSender(), OUTLINEPrefix() + " " + msg);
    }
    
    public static void success(String msg) {
    	UtilsBunge.sendBungeeColorMessageConsole(EventUtils.getStaticConsoleSender(), SUCCESSPrefix() + " " + msg);
    }
    
    public static void debug(String msg) {
    	UtilsBunge.sendBungeeColorMessageConsole(EventUtils.getStaticConsoleSender(), DEBUGPrefix() + " " + msg);
    }
    
    public static void defaultMessage(String msg) {
    	UtilsBunge.sendBungeeColorMessageConsole(EventUtils.getStaticConsoleSender(), UtilsBunge.getPrefix() + msg);
    }
    
    
    public void Log(Level level, Object object) {
        if (object == null) {
            return;
        }
        switch (level) {
            case ERROR:
                UtilsBunge.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&cERROR&e]&7" + " " + object);
                break;
            case WARNING:
                UtilsBunge.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&dWARNING&e]&7" + " " + object);
                break;
            case INFO:
                UtilsBunge.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&9INFO&e]&7" + " " + object);
                break;
            case OUTLINE:
                UtilsBunge.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&bOUTLINE&e]&7" + " " + object);
                break;
            case SUCCESS:
                UtilsBunge.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&aSUCCESS&e]&7" + " " + object);
                break;
            case DEBUG:
                UtilsBunge.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&dDEBUG&e]&7" + " " + object);
                break;
        }
    }

    public void Log(Level level, String msg) {
        if (msg == null) {
            return;
        }
        switch (level) {
            case ERROR:
                UtilsBunge.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&cERROR&e]&7" + " " + msg);
                break;
            case WARNING:
                UtilsBunge.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&dWARNING&e]&7" + " " + msg);
                break;
            case INFO:
                UtilsBunge.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&9INFO&e]&7" + " " + msg);
                break;
            case OUTLINE:
                UtilsBunge.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&bOUTLINE&e]&7" + " " + msg);
                break;
            case SUCCESS:
                UtilsBunge.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&aSUCCESS&e]&7" + " " + msg);
                break;
            case DEBUG:
                UtilsBunge.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&dDEBUG&e]&7" + " " + msg);
                break;
        }

    }
    
    private static String ERRORPrefix() {
    	return UtilsBunge.color("&e[&cERROR&e]&7");
    }
    private static String WARNINGPrefix() {
    	return UtilsBunge.color("&e[&dWARNING&e]&7");
    }
    private static String INFOPrefix() {
    	return UtilsBunge.color("&e[&9INFO&e]&7");
    }
    private static String OUTLINEPrefix() {
    	return UtilsBunge.color("&e[&bOUTLINE&e]&7");
    }
    private static String SUCCESSPrefix() {
    	return UtilsBunge.color("&e[&aSUCCESS&e]&7");
    }
    private static String DEBUGPrefix() {
    	return UtilsBunge.color("&e[&dDEBUG&e]&7");
    }
    
    public enum Level {
        ERROR, WARNING, INFO, SUCCESS, OUTLINE, DEBUG
    }

}
