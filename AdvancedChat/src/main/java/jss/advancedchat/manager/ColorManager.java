package jss.advancedchat.manager;

import java.util.Random;

import org.bukkit.entity.Player;

import jss.advancedchat.utils.Utils;

public class ColorManager {

    private String[] ColorCodes = new String[] {"&0", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9", "&b", "&c", "&a"};
	private String[] HexColorCodes = new String[] {"#000000","#0000AA","#00AA00","#00AAAA","#AA0000","#AA00AA","#FFAA00","#AAAAAA","#555555","#5555FF","#55FF55","#55FFFF","#FF5555","#FF55FF","#FFFF55","#FFFFFF"};
	
    public String convertColor(Player player, String color, String text) {
        String temp = color;
        if (temp.equalsIgnoreCase("dark_red")) {
            return "&4" + text;
        }
        if (temp.equalsIgnoreCase("red")) {
            return "&c" + text;
        }
        if (temp.equalsIgnoreCase("dark_blue")) {
            return "&1" + text;
        }
        if (temp.equalsIgnoreCase("blue")) {
            return "&9" + text;
        }
        if (temp.equalsIgnoreCase("dark_Green")) {
            return "&2" + text;
        }
        if (temp.equalsIgnoreCase("green")) {
            return "&a" + text;
        }
        if (temp.equalsIgnoreCase("yellow")) {
            return "&e" + text;
        }
        if (temp.equalsIgnoreCase("gold")) {
            return "&6" + text;
        }
        if (temp.equalsIgnoreCase("dark_aqua")) {
            return "&3" + text;
        }
        if (temp.equalsIgnoreCase("aqua")) {
            return "&b" + text;
        }
        if (temp.equalsIgnoreCase("light_purple")) {
            return "&d" + text;
        }
        if (temp.equalsIgnoreCase("dark_purple")) {
            return "&5" + text;
        }
        if (temp.equalsIgnoreCase("gray")) {
            return "&7" + text;
        }
        if (temp.equalsIgnoreCase("dark_gray")) {
            return "&8" + text;
        }
        if (temp.equalsIgnoreCase("white")) {
            return "&f" + text;
        }
        if (temp.equalsIgnoreCase("black")) {
            return "&0" + text;
        }
        if (temp.equalsIgnoreCase("random")) {
            StringBuffer stringBuffer = new StringBuffer();

            for (char c : text.toCharArray()) {
                stringBuffer.append(setColorRandom() + c);
            }
            return stringBuffer.toString();
        }
        
        if(temp.equalsIgnoreCase("rainbow1")) {
        	return Utils.color("<RAINBOW:1>" + text + "</RAINBOW>");
        }
        
        if(temp.equalsIgnoreCase("rainbow2")) {
        	return Utils.color("<RAINBOW:5>" + text + "</RAINBOW>");
        }
        
        if(temp.equalsIgnoreCase("rainbow3")) {
        	return Utils.color("<RAINBOW:10>" + text + "</RAINBOW>");
        }
        
        if(temp.equalsIgnoreCase("rainbow4")) {
        	return Utils.color("<RAINBOW:15>" + text + "</RAINBOW>");
        }
        
        if(temp.equalsIgnoreCase("rainbow5")) {
        	return Utils.color("<RAINBOW:20>" + text + "</RAINBOW>");
        }
        
        if(temp.equalsIgnoreCase("gradient")) {
        	return setGradient(player, text);
        }
        return null;
    }
    
    public String setGradient(Player player, String text){
    	PlayerManager playerManager = new PlayerManager(player);
    	return Utils.color("<GRADIENT:" + playerManager.getFirstGradient(player) + ">" + text + "</GRADIENT:" + playerManager.getSecondGradient(player) + ">");
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
