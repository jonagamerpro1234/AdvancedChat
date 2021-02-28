package jss.advancedchat.utils;

import org.bukkit.entity.Player;

public class Permission {

	
	private String name;

	private Permission(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public boolean hasPermission(Player p, Permission permissionName) {
		return p.hasPermission(permissionName.getName());
	}
	
	
}
