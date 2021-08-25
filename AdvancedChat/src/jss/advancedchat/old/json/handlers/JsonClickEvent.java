package jss.advancedchat.old.json.handlers;

public class JsonClickEvent {
	
	private ClickAction action;
	private String arg0;
	
	public JsonClickEvent(ClickAction action, String arg0) {
		super();
		this.action = action;
		this.arg0 = arg0;
	}

	public ClickAction getAction() {
		return action;
	}

	public String getValue() {
		return arg0;
	}
}
