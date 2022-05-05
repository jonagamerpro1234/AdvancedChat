package jss.advancedchat.storage;

public enum TableType {
	Data("advancedchat_user_data"),Format("advancedchat_user_formats"),Settings("advancedchat_user_settings");
	
	private String type;

	private TableType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
