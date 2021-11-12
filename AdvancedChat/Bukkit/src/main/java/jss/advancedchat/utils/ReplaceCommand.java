package jss.advancedchat.utils;

import java.util.List;

public class ReplaceCommand {
	
	private String commandName;
	private String replaceCommand;
	private List<String> message;
	private Type type;
	
	public String getCommandName() {
		return commandName;
	}
	
	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}
	
	public String getReplaceCommand() {
		return replaceCommand;
	}
	
	public void setReplaceCommand(String replaceCommand) {
		this.replaceCommand = replaceCommand;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public List<String> getMessage() {
		return message;
	}
	
	public void setMessage(List<String> message) {
		this.message = message;
	} 
	
	
}
