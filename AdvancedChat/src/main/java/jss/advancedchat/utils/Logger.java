package jss.advancedchat.utils;

public class Logger {
    
    public static void warning(String msg) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix(true) + " -> &e[&dWARNING&e]&7" + " " + msg);
    }
    
    public static void success(String msg) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix(true) + " -> &e[&aSUCCESS&e]&7" + " " + msg);
    }
    
    public static void error(String msg) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix(true) + " -> &e[&cERROR&e]&7" + " " + msg);
    }
    
    public static void debug(String msg) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix(true) + " -> &e&e[&dDEBUG&e]&7" + " " + msg);
    }
    
    public static void info(String msg) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix(true) + " -> &e[&9INFO&e]&7" + " " + msg);
    }
    
    public static void outline(String msg) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix(true) + " -> &e[&bOUTLINE&e]&7" + " " + msg);
    }
    
    public static void defaultMessage(String msg) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix(true) + msg);
    }
    
    public static void warning(Object object) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix(true) + " -> &e[&dWARNING&e]&7" + " " + object);
    }
    
    public static void success(Object object) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix(true) + " -> &e[&aSUCCESS&e]&7" + " " + object);
    }
    
    public static void error(Object object) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix(true) + " -> &e[&cERROR&e]&7" + " " + object);
    }
    
    public static void debug(Object object) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix(true) + " -> &e&e[&dDEBUG&e]&7" + " " + object);
    }
    
    public static void info(Object object) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix(true) + " -> &e[&9INFO&e]&7" + " " + object);
    }
    
    public static void outline(Object object) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix(true) + " -> &e[&bOUTLINE&e]&7" + " " + object);
    }
	
    public static void defaultMessage(Object object) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix(true) + object);
    }

}
