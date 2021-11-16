package jss.advancedchat.api.exceptios;


public final class NotApiLoadedException extends IllegalStateException{
	
	private static final long serialVersionUID = -7108701855219661843L;
	
	private static final String message =
			"The AdvancedChat API isn't loaded yet\n" +
			"a > the advancedchat plugin is not installed or it failed to enable\n" +
			"b > the plugin in the stacktrace does not declare a dependency on AdvancedChat\n" +
			"c > the plugin in the stacktrace is retrieving the API before the plugin 'enable' phase";
	
	public NotApiLoadedException() {
		super(message);
	}
	
}
