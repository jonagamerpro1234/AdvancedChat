package jss.advancedchat.storage;

import java.sql.Connection;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;

public class MySQLConnection {
	
	private DataSource source;
	private String host;
	private String database;
	private String username;
	private String password;
	private int port;
	
	public void setup(AdvancedChat plugin) {
		try {
			host = Settings.mysql_host;
			port = Settings.mysql_port;
			database = Settings.mysql_database;
			username = Settings.mysql_user;
			password = Settings.mysql_password;
			source = new DataSource(host, port, database, username, password);
			source.getDataSource().getConnection();
			MySQL.createTable(plugin);
			Logger.success("&aSuccessfully connected to the database");
		} catch (Exception e) {
			Logger.warning("&cCould not connect to the database");
		}
	}

	public String getHost() {
		return host;
	}

	public String getPassword() {
		return password;
	}

	public int getPort() {
		return port;
	}

	public String getDatabase() {
		return database;
	}
	
	public Connection getConnetion() {
		try {
			return source.getDataSource().getConnection();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
