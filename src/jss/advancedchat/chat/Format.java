package jss.advancedchat.chat;

import java.util.regex.Pattern;

public class Format {

	private static Pattern COLOR_PATTERN = Pattern.compile("(?i)&([0-9A-F])");
	private static Pattern MAGIC_PATTERN = Pattern.compile("(?i)&([K])");
	private static Pattern BOLD_PATTERN = Pattern.compile("(?i)&([L])");
	private static Pattern STRIKETHROUGH_PATTERN = Pattern.compile("(?i)&([M])");
	private static Pattern UNDERLINE_PATTERN = Pattern.compile("(?i)&([N])");
	private static Pattern ITALIC_PATTERN = Pattern.compile("(?i)&([O])");
	private static Pattern RESET_PATTERN = Pattern.compile("(?i)&([R])");

	public static String formatStringColor(String string) {
		string = COLOR_PATTERN.matcher(string).replaceAll("§");
		string = string.replaceAll("%", "\\%");
		return string;
	}

	public static String formatStringMagic(String string) {
		string = MAGIC_PATTERN.matcher(string).replaceAll("§");
		return string;
	}

	public static String formatStringBold(String string) {
		string = BOLD_PATTERN.matcher(string).replaceAll("§");
		return string;
	}

	public static String formatStringStrikethrough(String string) {
		string = STRIKETHROUGH_PATTERN.matcher(string).replaceAll("§");
		return string;
	}

	public static String formatStringUnderline(String string) {
		string = UNDERLINE_PATTERN.matcher(string).replaceAll("§");
		return string;
	}

	public static String formatStringItalic(String string) {
		string = ITALIC_PATTERN.matcher(string).replaceAll("§");
		return string;
	}

	public static String formatStringReset(String string) {
		string = RESET_PATTERN.matcher(string).replaceAll("§");
		return string;
	}

	public static String formatString(String string) {
		string = MAGIC_PATTERN.matcher(string).replaceAll("§");
		string = BOLD_PATTERN.matcher(string).replaceAll("§");
		string = STRIKETHROUGH_PATTERN.matcher(string).replaceAll("§");
		string = UNDERLINE_PATTERN.matcher(string).replaceAll("§");
		string = ITALIC_PATTERN.matcher(string).replaceAll("§");
		string = RESET_PATTERN.matcher(string).replaceAll("§");
		string = string.replaceAll("%", "\\%");
		return string;
	}

	public static String formatStringAll(String string) {
		string = COLOR_PATTERN.matcher(string).replaceAll("§");
		string = MAGIC_PATTERN.matcher(string).replaceAll("§");
		string = BOLD_PATTERN.matcher(string).replaceAll("§");
		string = STRIKETHROUGH_PATTERN.matcher(string).replaceAll("§");
		string = UNDERLINE_PATTERN.matcher(string).replaceAll("§");
		string = ITALIC_PATTERN.matcher(string).replaceAll("§");
		string = RESET_PATTERN.matcher(string).replaceAll("§");
		string = string.replaceAll("%", "\\%");
		return string;
	}
}
