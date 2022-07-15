package jss.advancedchat.manager;

import java.util.Random;

import org.bukkit.entity.Player;

import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Util;

public class ColorManager {
	
    private final String[] ColorCodes = new String[] {"&0", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9", "&b", "&c", "&a"};
	private final String[] HexColorCodes = new String[] {"#000000","#0000AA","#00AA00","#00AAAA","#AA0000","#AA00AA","#FFAA00","#AAAAAA","#555555","#5555FF","#55FF55","#55FFFF","#FF5555","#FF55FF","#FFFF55","#FFFFFF"};
		
	public static ColorManager get() {
		return new ColorManager();
	}
	
	public String addFormat(Player player, String text) {
		PlayerManager playerManager = new PlayerManager(player);
		
		if(playerManager.isColor() && !playerManager.isRainbow() && !playerManager.isGradient() && !playerManager.isRandom()) {
			Logger.debug("Color enable");
			return convertColor(player, playerManager.getColor(), converSpecialColor(playerManager.getSpecialColor(), text));
		}
		
		if(playerManager.isGradient() && !playerManager.isRainbow() &&  !playerManager.isColor() && !playerManager.isRandom() && !playerManager.isSpecialCodes()) {
			Logger.debug("Gradient enable");
			return setGradient(player, text);			
		}
		
		if(playerManager.isRandom() && !playerManager.isRainbow() &&  !playerManager.isColor() && !playerManager.isGradient() && !playerManager.isSpecialCodes()) {
			Logger.debug("Random enable");
			return convertRandomColor(text);
		}
		
		if(playerManager.isRainbow() &&  !playerManager.isColor() && !playerManager.isGradient() && !playerManager.isRandom() && !playerManager.isSpecialCodes()) {
			Logger.debug("Rainbow enable");
			return setRainbow(playerManager.getRainbow(), text);
		}
		
		return text;	
	}
	
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
        return null;
    }
    
    public String convertRandomColor(String text) {
            StringBuffer stringBuffer = new StringBuffer();

            for (char c : text.toCharArray()) {
                stringBuffer.append(setColorRandom() + c);
            }
            return stringBuffer.toString();
    }
    
    public String setRainbow(String temp, String text) {
        if(temp.equalsIgnoreCase("rainbow_1")) {
        	return Util.color("<RAINBOW:1>" + text + "</RAINBOW>");
        }
        if(temp.equalsIgnoreCase("rainbow_2")) {
        	return Util.color("<RAINBOW:5>" + text + "</RAINBOW>");
        }
        
        if(temp.equalsIgnoreCase("rainbow_3")) {
        	return Util.color("<RAINBOW:10>" + text + "</RAINBOW>");
        }
        
        if(temp.equalsIgnoreCase("rainbow_4")) {
        	return Util.color("<RAINBOW:15>" + text + "</RAINBOW>");
        }
        
        if(temp.equalsIgnoreCase("rainbow_5")) {
        	return Util.color("<RAINBOW:20>" + text + "</RAINBOW>");
        }
        if(temp.equalsIgnoreCase("rainbow_6")) {
        	return Util.color("<RAINBOW:25>" + text + "</RAINBOW>");
        }
        
        if(temp.equalsIgnoreCase("rainbow_7")) {
        	return Util.color("<RAINBOW:30>" + text + "</RAINBOW>");
        }
        
        if(temp.equalsIgnoreCase("rainbow_8")) {
        	return Util.color("<RAINBOW:35>" + text + "</RAINBOW>");
        }

        return null;
    }
    
    public String convertHexColor(String color) {
        String temp = color;
        if (temp.equalsIgnoreCase("dark_red")) {
            return "AA0000";
        }
        if (temp.equalsIgnoreCase("red")) {
            return "FF5555";
        }
        if (temp.equalsIgnoreCase("dark_blue")) {
            return "0000AA";
        }
        if (temp.equalsIgnoreCase("blue")) {
            return "5555FF";
        }
        if (temp.equalsIgnoreCase("dark_Green")) {
            return "00AA00";
        }
        if (temp.equalsIgnoreCase("green")) {
            return "55FF55";
        }
        if (temp.equalsIgnoreCase("yellow")) {
            return "FFFF55";
        }
        if (temp.equalsIgnoreCase("gold")) {
            return "FFAA00";
        }
        if (temp.equalsIgnoreCase("dark_aqua")) {
            return "00AAAA";
        }
        if (temp.equalsIgnoreCase("aqua")) {
            return "55FFFF";
        }
        if (temp.equalsIgnoreCase("light_purple")) {
            return "FF55FF";
        }
        if (temp.equalsIgnoreCase("dark_purple")) {
            return "AA00AA";
        }
        if (temp.equalsIgnoreCase("gray")) {
            return "AAAAAA";
        }
        if (temp.equalsIgnoreCase("dark_gray")) {
            return "555555";
        }
        if (temp.equalsIgnoreCase("white")) {
            return "FFFFFF";
        }
        if (temp.equalsIgnoreCase("black")) {
            return "000000";
        }
        return null;
    }
    
    public String converSpecialColor(String specialcolor, String text) {
    	String temp = specialcolor;
    	if (temp.equalsIgnoreCase("bold")) {
            return "&l" + text;
        }
        if (temp.equalsIgnoreCase("strikethrough")) {
            return "&m" + text;
        }
        if (temp.equalsIgnoreCase("underline")) {
            return "&n" + text;
        }
        if (temp.equalsIgnoreCase("italic")) {
            return "&o" + text;
        }
        if (temp.equalsIgnoreCase("magic")) {
            return "&k" + text;
        }
        if (temp.equalsIgnoreCase("none")) {
            return "" + text;
        }
        return null;
    }
    
    public String converSpecialColor(String specialcolor) {
    	String temp = specialcolor;
    	if (temp.equalsIgnoreCase("bold")) {
            return "&l";
        }
        if (temp.equalsIgnoreCase("strikethrough")) {
            return "&m";
        }
        if (temp.equalsIgnoreCase("underline")) {
            return "&n";
        }
        if (temp.equalsIgnoreCase("italic")) {
            return "&o";
        }
        if (temp.equalsIgnoreCase("magic")) {
            return "&k";
        }
        if (temp.equalsIgnoreCase("none")) {
            return "";
        }
        return null;
    }
    
    public String setGradient(Player player, String text){
    	PlayerManager playerManager = new PlayerManager(player);
    	return Util.color("<GRADIENT:" + playerManager.getFirstGradient() + ">" + text + "</GRADIENT:" + playerManager.getSecondGradient() + ">");
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
