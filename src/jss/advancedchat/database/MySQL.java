package jss.advancedchat.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jss.advancedchat.utils.Logger;

public class MySQL {

	private Connection connection;
	
	public void connect(String host, int port, String database, String user, String pass, boolean ssl) throws ClassNotFoundException, SQLException {
        try {
            synchronized (this) {
                if (connection != null && !connection.isClosed()) {
                    Logger.Error("Error al conectar con MySQL");
                    return;
                }
                Class.forName("com.mysql.jdbc.Driver");
                this.connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=" + ssl, user, pass);
                queryUpdate("CREATE TABLE IF NOT EXISTS `AdvancedChat_Players` (`Name` VARCHAR(100), `UUID` VARCHAR(100), `Mute` BOOLEAN, `COLOR` VARCHAR(10))");
            }	
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	public void queryUpdate(String query) {
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
	
	public static void closeResources(ResultSet rs, PreparedStatement st) {
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
		try{
			return connection != null || connection.isValid(100);
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		
		return false;
	}
	
	public void disconnect() {
		try {
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return this.connection;
	}
}
