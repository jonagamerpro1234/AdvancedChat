package jss.advancedchat.common.utils;

public enum Platform {
	BungeeCord("BungeeCord"),Spigot("Spigot");
	
	private String platform;
	
	private Platform(String platform) {
		this.platform = platform;
	}
	
	public String getname() {
		return platform;
	}
}
