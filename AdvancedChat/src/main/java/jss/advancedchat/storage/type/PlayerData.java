package jss.advancedchat.storage.type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;


import jss.advancedchat.storage.TableType;
import jss.advancedchat.utils.Logger;

public class PlayerData {

	private Connection connection;
	private PreparedStatement statement;
	private Player player;
	private ResultSet resultSet;
	
	public PlayerData(Connection connection, Player player) {
		this.connection = connection;
		this.player = player;
	}

	public String getName(TableType tableType) {
		if(tableType == null) Logger.error("&cCould not find table");

		try {
			statement = connection.prepareStatement("SELECT * FROM `" + tableType + "` WHERE (PLAYER_NAME=?)");
			statement.setString(1, player.getName());
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getString("PLAYER_NAME");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Data getData(){
		return new Data();
	}
	
	public class Data{
		
		public String getGroup() {
			try {
				statement = connection.prepareStatement("SELECT * FROM `" + TableType.Data + "` WHERE (PLAYER_NAME=?)");
				statement.setString(1, player.getName());
				resultSet = statement.executeQuery();
				if(resultSet.next()) {
					return resultSet.getString("PLAYER_GROUP");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public String getColor() {
			try {
				statement = connection.prepareStatement("SELECT * FROM `" + TableType.Data + "` WHERE (PLAYER_NAME=?)");
				statement.setString(1, player.getName());
				resultSet = statement.executeQuery();
				if(resultSet.next()) {
					return resultSet.getString("COLOR_NAME");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public String getRainbow() {
			try {
				statement = connection.prepareStatement("SELECT * FROM `" + TableType.Data + "` WHERE (PLAYER_NAME=?)");
				statement.setString(1, player.getName());
				resultSet = statement.executeQuery();
				if(resultSet.next()) {
					return resultSet.getString("RAINBOW");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public String getFirstGradient() {
			try {
				statement = connection.prepareStatement("SELECT * FROM `" + TableType.Data + "` WHERE (PLAYER_NAME=?)");
				statement.setString(1, player.getName());
				resultSet = statement.executeQuery();
				if(resultSet.next()) {
					return resultSet.getString("FIRST_GRADIENT");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
}
