package jss.advancedchat.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Logger.Level;

public class ConnectionMySQL {
	
	private AdvancedChat plugin;
	private Connection connection;
	private String host;
	private int port;
	private String user;
	private String password;
	private String database;
	private Logger logger = new Logger(plugin);

	public ConnectionMySQL(AdvancedChat plugin, String host, int port, String user, String password, String database) {
		this.plugin = plugin;
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
		this.database = database;

		try {
			
			synchronized (this) {
				if(connection != null && !connection.isClosed()) {
					logger.Log(Level.ERROR, "Error al conectar con MySQL");
					return;
				}
				Class.forName("com.mysql.jdbc.Driver");
				this.connection = DriverManager.getConnection("jdbc:mysql://"+this.host+":"+this.port+"/"+this.database,this.user,this.password);
				logger.Log(Level.SUCCESS, "Se a conectado con MySQL Correctamente");
			
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public Connection getConnection() {
		return connection;
	}

	
}
