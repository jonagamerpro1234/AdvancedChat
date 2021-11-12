package jss.advancedchat.bungee.utils;

import jss.advancedchat.bungee.AdvancedChatBungee;

public class Logger {

    private AdvancedChatBungee plugin;
    private EventUtils eventsUtils = new EventUtils(plugin);

    public Logger(AdvancedChatBungee plugin) {
        this.plugin = plugin;
    }
    
    public static void error(String msg) {
    	Utils.sendBungeeColorMessageConsole(EventUtils.getStaticConsoleSender(), ERRORPrefix() + " " + msg);
    }

    public static void warning(String msg) {
    	Utils.sendBungeeColorMessageConsole(EventUtils.getStaticConsoleSender(), WARNINGPrefix() + " " + msg);
    }
    
    public static void info(String msg) {
    	Utils.sendBungeeColorMessageConsole(EventUtils.getStaticConsoleSender(), INFOPrefix() + " " + msg);
    }
    
    public static void outLine(String msg) {
    	Utils.sendBungeeColorMessageConsole(EventUtils.getStaticConsoleSender(), OUTLINEPrefix() + " " + msg);
    }
    
    public static void success(String msg) {
    	Utils.sendBungeeColorMessageConsole(EventUtils.getStaticConsoleSender(), SUCCESSPrefix() + " " + msg);
    }
    
    public static void debug(String msg) {
    	Utils.sendBungeeColorMessageConsole(EventUtils.getStaticConsoleSender(), DEBUGPrefix() + " " + msg);
    }
    
    public static void defaultMessage(String msg) {
    	Utils.sendBungeeColorMessageConsole(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + msg);
    }
    
    
    public void Log(Level level, Object object) {
        if (object == null) {
            return;
        }
        switch (level) {
            case ERROR:
                Utils.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&cERROR&e]&7" + " " + object);
                break;
            case WARNING:
                Utils.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&dWARNING&e]&7" + " " + object);
                break;
            case INFO:
                Utils.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&9INFO&e]&7" + " " + object);
                break;
            case OUTLINE:
                Utils.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&bOUTLINE&e]&7" + " " + object);
                break;
            case SUCCESS:
                Utils.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&aSUCCESS&e]&7" + " " + object);
                break;
            case DEBUG:
                Utils.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&dDEBUG&e]&7" + " " + object);
                break;
        }
    }

    public void Log(Level level, String msg) {
        if (msg == null) {
            return;
        }
        switch (level) {
            case ERROR:
                Utils.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&cERROR&e]&7" + " " + msg);
                break;
            case WARNING:
                Utils.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&dWARNING&e]&7" + " " + msg);
                break;
            case INFO:
                Utils.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&9INFO&e]&7" + " " + msg);
                break;
            case OUTLINE:
                Utils.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&bOUTLINE&e]&7" + " " + msg);
                break;
            case SUCCESS:
                Utils.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&aSUCCESS&e]&7" + " " + msg);
                break;
            case DEBUG:
                Utils.sendBungeeColorMessageConsole(eventsUtils.getConsoleSender(), "&e[&dDEBUG&e]&7" + " " + msg);
                break;
        }

    }
    
    private static String ERRORPrefix() {
    	return Utils.color("&e[&cERROR&e]&7");
    }
    private static String WARNINGPrefix() {
    	return Utils.color("&e[&dWARNING&e]&7");
    }
    private static String INFOPrefix() {
    	return Utils.color("&e[&9INFO&e]&7");
    }
    private static String OUTLINEPrefix() {
    	return Utils.color("&e[&bOUTLINE&e]&7");
    }
    private static String SUCCESSPrefix() {
    	return Utils.color("&e[&aSUCCESS&e]&7");
    }
    private static String DEBUGPrefix() {
    	return Utils.color("&e[&dDEBUG&e]&7");
    }
    
    public enum Level {
        ERROR, WARNING, INFO, SUCCESS, OUTLINE, DEBUG
    }

}
