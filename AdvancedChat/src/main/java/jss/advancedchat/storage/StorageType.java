package jss.advancedchat.storage;

public enum StorageType {
	YAML("yml"),MYSQL("mysql");
	
	private String name;

	private StorageType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
