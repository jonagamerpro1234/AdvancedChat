package jss.advancedchat.utils.interfaces;

public interface Hook {

    String name();
    void setup();
    boolean isEnabled();
}
