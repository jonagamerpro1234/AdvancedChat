package jss.advancedchat.manager;

import java.util.Random;

public class ColorManager {

    private String[] ColorCodes = new String[] {"&0", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9", "&b", "&c", "&a"};
	private String[] HexColorCodes = new String[] {"#000000","#0000AA","#00AA00","#00AAAA","#AA0000","#AA00AA","#FFAA00","#AAAAAA","#555555","#5555FF","#55FF55","#55FFFF","#FF5555","#FF55FF","#FFFF55","#FFFFFF"};
	
    public String convertColor(String color, String text) {
        String temp = color;
        if (temp.equalsIgnoreCase("Dark_Red")) {
            return "&4" + text;
        }
        if (temp.equalsIgnoreCase("Red")) {
            return "&c" + text;
        }
        if (temp.equalsIgnoreCase("Dark_Blue")) {
            return "&1" + text;
        }
        if (temp.equalsIgnoreCase("Blue")) {
            return "&9" + text;
        }
        if (temp.equalsIgnoreCase("Dark_Green")) {
            return "&2" + text;
        }
        if (temp.equalsIgnoreCase("Green")) {
            return "&a" + text;
        }
        if (temp.equalsIgnoreCase("Yellow")) {
            return "&e" + text;
        }
        if (temp.equalsIgnoreCase("Gold")) {
            return "&6" + text;
        }
        if (temp.equalsIgnoreCase("Dark_Aqua")) {
            return "&3" + text;
        }
        if (temp.equalsIgnoreCase("Aqua")) {
            return "&b" + text;
        }
        if (temp.equalsIgnoreCase("Light_Purple")) {
            return "&d" + text;
        }
        if (temp.equalsIgnoreCase("Dark_Purple")) {
            return "&5" + text;
        }
        if (temp.equalsIgnoreCase("Gray")) {
            return "&7" + text;
        }
        if (temp.equalsIgnoreCase("Dark_Gray")) {
            return "&8" + text;
        }
        if (temp.equalsIgnoreCase("White")) {
            return "&f" + text;
        }
        if (temp.equalsIgnoreCase("Black")) {
            return "&0" + text;
        }
        if (temp.equalsIgnoreCase("RainBow")) {
            StringBuffer stringBuffer = new StringBuffer();

            for (char c : text.toCharArray()) {
                stringBuffer.append(setColorRandom() + c);
                /*if(plugin.uselegacyversion = true) {
                	stringBuffer.append(setHexColorRandom() + c);
                }*/
            }
            return stringBuffer.toString();
        }
        return null;
    }

    public String setColorRandom() {
        Random r = new Random();
        return ColorCodes[r.nextInt((this.ColorCodes.length))];
    }
    
    public String setHexColorRandom() {
        Random r = new Random();
        return this.HexColorCodes[r.nextInt((this.HexColorCodes.length))];
    }
	
}
