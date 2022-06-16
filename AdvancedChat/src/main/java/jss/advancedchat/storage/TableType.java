package jss.advancedchat.storage;

public enum TableType {
	Data("advancedchat_user_data"),Settings("advancedchat_user_settings");
	
	private String type;

	private TableType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
