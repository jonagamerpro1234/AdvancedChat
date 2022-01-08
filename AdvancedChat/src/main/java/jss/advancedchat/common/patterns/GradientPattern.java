package jss.advancedchat.common.patterns;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jss.advancedchat.common.utils.IridiumColorAPI;

public class GradientPattern implements IPattern {

    Pattern pattern = Pattern.compile("<#:([0-9A-Fa-f]{6})>(.*?)</#:([0-9A-Fa-f]{6})>");

    public String process(String string) {
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String start = matcher.group(1);
            String end = matcher.group(3);
            String content = matcher.group(2);
            string = string.replace(matcher.group(), IridiumColorAPI.color(content, new Color(Integer.parseInt(start, 16)), new Color(Integer.parseInt(end, 16))));
        }
        return string;
    }

}
