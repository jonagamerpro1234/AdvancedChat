package jss.advancedchat.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MySQL {

	private final String host;
	private final String port;
	private final String database;
	private final String username;
	private final String  password;
	private final String options;
	
	private Connection connection;
	private boolean isconnected = false;
	
	
	/*public MySQL(String host, String port, String database, String username, String password, String options) {
		this.host = host;
		this.port = port;
		this.database = database;
		this.username = username;
		this.password = password;
		this.options = options;
	}*/
	
	public void openConnection() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			
			this.connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?" + options, username, password);
			
		}
	}
	
	//advancedchat_players
	public void connect(String host, int port, String database, String user, String pass, boolean ssl) {
        try {
            synchronized (this) {
                if (connection != null && !connection.isClosed()) {
                    Logger.error("Error al conectar con MySQL");
                    return;
                }
                Class.forName("com.mysql.jdbc.Driver");
                this.connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=" + ssl + "&autoReconnect=true", user, pass);
                this.isconnected = true;
                
            }	
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	public void createTable() {
		updateQuery("CREATE TABLE IF NOT EXISTS `" + Settings.mysql_table + "` (`Name` VARCHAR(100), `UUID` VARCHAR(100), `Mute` BOOLEAN, `Color` VARCHAR(10), `Channel` VARCHAR(16))");
	}
	
	public void updateQuery(String query) {
		Connection con = connection;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			closeResources(null, preparedStatement);
		}
	}	
	
	public void disconnected() {
		try {
			if(!isConnected()) {
				this.connection.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String query) {
		if(!isConnected()) {
			return null;
		}else {
			try {
				this.connection.createStatement().executeQuery(query);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void closeResources(ResultSet rs, PreparedStatement st) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
	public boolean isConnected() {
		return this.isconnected;
	}
	
	public Connection getConnection() {
		return this.connection;
	}
}