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
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), ERRORPrefix() + " " + msg);
    }

    public static void Warning(String msg) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), WARNINGPrefix() + " " + msg);
    }
    
    public static void Info(String msg) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), INFOPrefix() + " " + msg);
    }
    
    public static void OutLine(String msg) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), OUTLINEPrefix() + " " + msg);
    }
    
    public static void Succerss(String msg) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), SUCCESSPrefix() + " " + msg);
    }
    
    public static void Debug(String msg) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), DEBUGPrefix() + " " + msg);
    }
    
    public static void Default(String msg) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + msg);
    }
    
    
    public void Log(Level level, Object object) {
        if (object == null) {
            return;
        }
        switch (level) {
            case ERROR:
                Utils.sendColorMessage(eventsUtils.getConsoleSender(), "&e[&cERROR&e]&7" + " " + object);
                break;
            case WARNING:
                Utils.sendColorMessage(eventsUtils.getConsoleSender(), "&e[&dWARNING&e]&7" + " " + object);
                break;
            case INFO:
                Utils.sendColorMessage(eventsUtils.getConsoleSender(), "&e[&9INFO&e]&7" + " " + object);
                break;
            case OUTLINE:
                Utils.sendColorMessage(eventsUtils.getConsoleSender(), "&e[&bOUTLINE&e]&7" + " " + object);
                break;
            case SUCCESS:
                Utils.sendColorMessage(eventsUtils.getConsoleSender(), "&e[&aSUCCESS&e]&7" + " " + object);
                break;
            case DEBUG:
                Utils.sendColorMessage(eventsUtils.getConsoleSender(), "&e[&dDEBUG&e]&7" + " " + object);
                break;
        }
    }

    public void Log(Level level, String msg) {
        if (msg == null) {
            return;
        }
        switch (level) {
            case ERROR:
                Utils.sendColorMessage(eventsUtils.getConsoleSender(), "&e[&cERROR&e]&7" + " " + msg);
                break;
            case WARNING:
                Utils.sendColorMessage(eventsUtils.getConsoleSender(), "&e[&dWARNING&e]&7" + " " + msg);
                break;
            case INFO:
                Utils.sendColorMessage(eventsUtils.getConsoleSender(), "&e[&9INFO&e]&7" + " " + msg);
                break;
            case OUTLINE:
                Utils.sendColorMessage(eventsUtils.getConsoleSender(), "&e[&bOUTLINE&e]&7" + " " + msg);
                break;
            case SUCCESS:
                Utils.sendColorMessage(eventsUtils.getConsoleSender(), "&e[&aSUCCESS&e]&7" + " " + msg);
                break;
            case DEBUG:
                Utils.sendColorMessage(eventsUtils.getConsoleSender(), "&e[&dDEBUG&e]&7" + " " + msg);
                break;
        }

    }
    
    private static String ERRORPrefix() {
    	return Utils.hexcolor("&e[&cERROR&e]&7");
    }
    private static String WARNINGPrefix() {
    	return Utils.hexcolor("&e[&dWARNING&e]&7");
    }
    private static String INFOPrefix() {
    	return Utils.hexcolor("&e[&9INFO&e]&7");
    }
    private static String OUTLINEPrefix() {
    	return Utils.hexcolor("&e[&bOUTLINE&e]&7");
    }
    private static String SUCCESSPrefix() {
    	return Utils.hexcolor("&e[&aSUCCESS&e]&7");
    }
    private static String DEBUGPrefix() {
    	return Utils.hexcolor("&e[&dDEBUG&e]&7");
    }
    
    public enum Level {
        ERROR, WARNING, INFO, SUCCESS, OUTLINE, DEBUG
    }

}
