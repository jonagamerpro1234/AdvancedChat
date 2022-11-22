package jss.advancedchat.utils;

public class Logger {

    public static void info(String text){
        System.out.println(Utils.getPrefix(true) + "- [INFO]: " + text);
    }

    public static void outline(String text){
        System.out.println(Utils.getPrefix(true) + "- [OUTLINE]: " + text);
    }

    public static void success(String text){
        System.out.println(Utils.getPrefix(true) + "- [SUCCESS]: " + text);
    }

    public static void warning(String text){
        System.out.println(Utils.getPrefix(true) + "- [WARNING]: " + text);
    }

    public static void error(String text){
        System.out.println(Utils.getPrefix(true) + "- [ERROR]: " + text);
    }

}
