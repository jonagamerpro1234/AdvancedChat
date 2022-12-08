package jss.advancedchat.utils;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.listeners.utils.EventUtils;

public class Logger {
  private final AdvancedChat plugin = AdvancedChat.get();

  private final EventUtils eventsUtils = new EventUtils(this.plugin);

  public static void error(String msg) {
    Utils.sendColorConsoleMessage(EventUtils.getStaticConsoleSender(), ERRORPrefix() + " " + msg);
  }

  public static void warning(String msg) {
    Utils.sendColorConsoleMessage(EventUtils.getStaticConsoleSender(), WARNINGPrefix() + " " + msg);
  }

  public static void info(String msg) {
    Utils.sendColorConsoleMessage(EventUtils.getStaticConsoleSender(), INFOPrefix() + " " + msg);
  }

  public static void outLine(String msg) {
    Utils.sendColorConsoleMessage(EventUtils.getStaticConsoleSender(), OUTLINEPrefix() + " " + msg);
  }

  public static void success(String msg) {
    Utils.sendColorConsoleMessage(EventUtils.getStaticConsoleSender(), SUCCESSPrefix() + " " + msg);
  }

  public static void debug(String msg) {
    Utils.sendColorConsoleMessage(EventUtils.getStaticConsoleSender(), DEBUGPrefix() + " " + msg);
  }

  public static void chat(String msg) {
    Utils.sendColorConsoleMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + "- [CHAT]: " + msg);
  }

  public static void defaultMessage(String msg) {
    Utils.sendColorConsoleMessage(EventUtils.getStaticConsoleSender(), Utils.getPrefix() + msg);
  }

  private static String ERRORPrefix() {
    return Utils.color(Utils.getPrefix() + "-> &e[&cERROR&e]&7");
  }

  private static String WARNINGPrefix() {
    return Utils.color(Utils.getPrefix() + "-> &e[&dWARNING&e]&7");
  }

  private static String INFOPrefix() {
    return Utils.color(Utils.getPrefix() + "-> &e[&9INFO&e]&7");
  }

  private static String OUTLINEPrefix() {
    return Utils.color(Utils.getPrefix() + "-> &e[&bOUTLINE&e]&7");
  }

  private static String SUCCESSPrefix() {
    return Utils.color(Utils.getPrefix() + "-> &e[&aSUCCESS&e]&7");
  }

  private static String DEBUGPrefix() {
    return Utils.color(Utils.getPrefix() + "-> &e[&dDEBUG&e]&7");
  }

  public void Log(Level level, String msg) {
    if (msg == null)
      return;
    switch (level) {
      case ERROR:
        Utils.sendColorMessage(this.eventsUtils.getConsoleSender(), Utils.getPrefix() + "-> &e[&cERROR&e]&7 " + msg);
        break;
      case WARNING:
        Utils.sendColorMessage(this.eventsUtils.getConsoleSender(), Utils.getPrefix() + "-> &e[&dWARNING&e]&7 " + msg);
        break;
      case INFO:
        Utils.sendColorMessage(this.eventsUtils.getConsoleSender(), Utils.getPrefix() + "-> &e[&9INFO&e]&7 " + msg);
        break;
      case OUTLINE:
        Utils.sendColorMessage(this.eventsUtils.getConsoleSender(), Utils.getPrefix() + "-> &e[&bOUTLINE&e]&7 " + msg);
        break;
      case SUCCESS:
        Utils.sendColorMessage(this.eventsUtils.getConsoleSender(), Utils.getPrefix() + "-> &e[&aSUCCESS&e]&7 " + msg);
        break;
      case DEBUG:
        Utils.sendColorMessage(this.eventsUtils.getConsoleSender(), Utils.getPrefix() + "-> &e[&dDEBUG&e]&7 " + msg);
        break;
    }
  }

  public enum Level {
    ERROR, WARNING, INFO, SUCCESS, OUTLINE, DEBUG
  }
}
