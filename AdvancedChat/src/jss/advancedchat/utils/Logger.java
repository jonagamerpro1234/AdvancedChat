package jss.advancedchat.utils;

import jss.advancedchat.AdvancedChat;

public class Logger {

    private AdvancedChat plugin;
    private EventUtils eventsUtils = new EventUtils(plugin);

    public Logger(AdvancedChat plugin) {
        super();
        this.plugin = plugin;
    }
    
    public static void Error(String msg) {
    	Utils.sendColorConsoleMessage(EventUtils.getStaticConsoleSender(), ERRORPrefix() + " " + msg);
    }

    public static void Warning(String msg) {
    	Utils.sendColorConsoleMessage(EventUtils.getStaticConsoleSender(), WARNINGPrefix() + " " + msg);
    }
    
    public static void Info(String msg) {
    	Utils.sendColorConsoleMessage(EventUtils.getStaticConsoleSender(), INFOPrefix() + " " + msg);
    }
    
    public static void OutLine(String msg) {
    	Utils.sendColorConsoleMessage(EventUtils.getStaticConsoleSender(), OUTLINEPrefix() + " " + msg);
    }
    
    public static void Succerss(String msg) {
    	Utils.sendColorConsoleMessage(EventUtils.getStaticConsoleSender(), SUCCESSPrefix() + " " + msg);
    }
    
    public static void Debug(String msg) {
    	Utils.sendColorConsoleMessage(EventUtils.getStaticConsoleSender(), DEBUGPrefix() + " " + msg);
    }
    
    public static void Default(String msg) {
    	Utils.sendColorConsoleMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + msg);
    }
    
    
    public void Log(Level level, Object object) {
        if (object == null) {
            return;
        }
        switch (level) {
            case ERROR:
                Utils.sendColorConsoleMessage(eventsUtils.getConsoleSender(), "&e[&cERROR&e]&7" + " " + object);
                break;
            case WARNING:
                Utils.sendColorConsoleMessage(eventsUtils.getConsoleSender(), "&e[&dWARNING&e]&7" + " " + object);
                break;
            case INFO:
                Utils.sendColorConsoleMessage(eventsUtils.getConsoleSender(), "&e[&9INFO&e]&7" + " " + object);
                break;
            case OUTLINE:
                Utils.sendColorConsoleMessage(eventsUtils.getConsoleSender(), "&e[&bOUTLINE&e]&7" + " " + object);
                break;
            case SUCCESS:
                Utils.sendColorConsoleMessage(eventsUtils.getConsoleSender(), "&e[&aSUCCESS&e]&7" + " " + object);
                break;
            case DEBUG:
                Utils.sendColorConsoleMessage(eventsUtils.getConsoleSender(), "&e[&dDEBUG&e]&7" + " " + object);
                break;
        }
    }

    public void Log(Level level, String msg) {
        if (msg == null) {
            return;
        }
        switch (level) {
            case ERROR:
                Utils.sendColorConsoleMessage(eventsUtils.getConsoleSender(), "&e[&cERROR&e]&7" + " " + msg);
                break;
            case WARNING:
                Utils.sendColorConsoleMessage(eventsUtils.getConsoleSender(), "&e[&dWARNING&e]&7" + " " + msg);
                break;
            case INFO:
                Utils.sendColorConsoleMessage(eventsUtils.getConsoleSender(), "&e[&9INFO&e]&7" + " " + msg);
                break;
            case OUTLINE:
                Utils.sendColorConsoleMessage(eventsUtils.getConsoleSender(), "&e[&bOUTLINE&e]&7" + " " + msg);
                break;
            case SUCCESS:
                Utils.sendColorConsoleMessage(eventsUtils.getConsoleSender(), "&e[&aSUCCESS&e]&7" + " " + msg);
                break;
            case DEBUG:
                Utils.sendColorConsoleMessage(eventsUtils.getConsoleSender(), "&e[&dDEBUG&e]&7" + " " + msg);
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
