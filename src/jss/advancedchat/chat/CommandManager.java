package jss.advancedchat.chat;

import java.util.List;

public class CommandManager {
	
	private String command;
	private String value;
	private List<String> commandlist;
	
	public String getCommand() {
		return command;
	}
	
	public void setCommand(String command) {
		this.command = command;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public List<String> getCommandlist() {
		return commandlist;
	}

	public void setCommandlist(List<String> commandlist) {
		this.commandlist = commandlist;
	}
	
}
