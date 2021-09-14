package jss.advancedchat.utils.commands;

public enum Actions {

	MESSAGE("message"),
	EXECUTE("execute");
	
	private String name;
	
	private Actions(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
