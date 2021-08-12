package jss.advancedchat.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import jss.advancedchat.json.handlers.JsonClickEvent;
import jss.advancedchat.json.handlers.JsonHoverEvent;

public abstract class JsonMessage {
	
	private boolean bold = false;
	private boolean italic = false;
	private boolean underlined = false;
	private boolean strikethrough = false;
	private boolean obfuscated = false;
	private String color = "reset";
	private JsonHoverEvent hoverEvent = null;
	private JsonClickEvent clickEvent = null;
	
	public boolean isBold() {
		return bold;
	}
	public JsonMessage setBold(boolean bold) {
		this.bold = bold;
		return this;
	}
	public boolean isItalic() {
		return italic;
	}
	public JsonMessage setItalic(boolean italic) {
		this.italic = italic;
		return this;
	}
	public boolean isUnderlined() {
		return underlined;
	}
	public JsonMessage setUnderlined(boolean underlined) {
		this.underlined = underlined;
		return this;
	}
	public boolean isStrikethrough() {
		return strikethrough;
	}
	public JsonMessage setStrikethrough(boolean strikethrough) {
		this.strikethrough = strikethrough;
		return this;
	}
	public boolean isObfuscated() {
		return obfuscated;
	}
	public JsonMessage setObfuscated(boolean obfuscated) {
		this.obfuscated = obfuscated;
		return this;
	}
	public String getColor() {
		return color;
	}
	public JsonMessage setColor(String color) {
		this.color = color;
		return this;
	}
	
	public JsonHoverEvent getHoverEvent() {
		return hoverEvent;
	}
	
	public JsonMessage setHoverEvent(JsonHoverEvent hoverEvent) {
		this.hoverEvent = hoverEvent;
		return this;
	}
	
	public JsonClickEvent getClickEvent() {
		return clickEvent;
	}
	
	public JsonMessage setClickEvent(JsonClickEvent clickEvent) {
		this.clickEvent = clickEvent;
		return this;
	}
	
	public String buildToString(Gson gson) {
		return gson.toJson(this);
	}
	
	public JsonElement buildToElement(Gson gson) {
		return gson.toJsonTree(this);
	}
	
}
