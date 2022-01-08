package jss.advancedchat.utils;

public class Logger {

	public Logger() {}

    public void Log(Level level, Object object) {
        if (object == null) {
            return;
        }
        switch (level) {
            case ERROR:
                error(object);
                break;
            case WARNING:
            	warning(object);
                break;
            case INFO:
                info(object);
                break;
            case OUTLINE:
                outline(object);
                break;
            case SUCCESS:
                success(object);
                break;
            case DEBUG:
            	debug(object);
                break;
        }
    }

    public void Log(Level level, String msg) {
        if (msg == null) {
            return;
        }
        switch (level) {
            case ERROR:
                error(msg);
                break;
            case WARNING:
                warning(msg);
                break;
            case INFO:
                info(msg);
                break;
            case OUTLINE:
                outline(msg);
                break;
            case SUCCESS:
                success(msg);
                break;
            case DEBUG:
            	debug(msg);
                break;
        }

    }
    
    public static void warning(String msg) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + " -> &e[&dWARNING&e]&7" + " " + msg);
    }
    
    public static void success(String msg) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + " -> &e[&aSUCCESS&e]&7" + " " + msg);
    }
    
    public static void error(String msg) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + " -> &e[&cERROR&e]&7" + " " + msg);
    }
    
    public static void debug(String msg) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + " -> &e&e[&dDEBUG&e]&7" + " " + msg);
    }
    
    public static void info(String msg) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + " -> &e[&9INFO&e]&7" + " " + msg);
    }
    
    public static void outline(String msg) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + " -> &e[&bOUTLINE&e]&7" + " " + msg);
    }
    
    public static void defaultMessage(String msg) {
    	Utils.sendColorConsoleMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + msg);
    }
    
    public static void warning(Object object) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + " -> &e[&dWARNING&e]&7" + " " + object);
    }
    
    public static void success(Object object) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + " -> &e[&aSUCCESS&e]&7" + " " + object);
    }
    
    public static void error(Object object) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + " -> &e[&cERROR&e]&7" + " " + object);
    }
    
    public static void debug(Object object) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + " -> &e&e[&dDEBUG&e]&7" + " " + object);
    }
    
    public static void info(Object object) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + " -> &e[&9INFO&e]&7" + " " + object);
    }
    
    public static void outline(Object object) {
    	Utils.sendColorMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + " -> &e[&bOUTLINE&e]&7" + " " + object);
    }
	
    public static void defaultMessage(Object object) {
    	Utils.sendColorConsoleMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + object);
    }
    
	public enum Level{
		ERROR, WARNING, INFO, SUCCESS, OUTLINE, DEBUG;
	}

}
