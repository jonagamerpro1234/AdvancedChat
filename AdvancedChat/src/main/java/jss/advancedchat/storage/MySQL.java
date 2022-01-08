package jss.advancedchat.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;

public class MySQL {
	
	public static boolean isEnabled() {
		if(Settings.mysql_use) {
			return true;
		}
		return false;
	}

	public static void createTable(AdvancedChat plugin) {
		try(Connection connection = plugin.getConnection()){
			PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `" + Settings.mysql_table + "` (`UUID` VARCHAR(200), `PLAYER_NAME` VARCHAR(100), `COLOR_NAME` VARCHAR(16), `GRADIENT_COLOR_ONE` VARCHAR(20), `GRADIENT_COLOR_TWO` VARCHAR(20), `IS_MUTE` BOOLEAN)");
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createPlayer(AdvancedChat plugin, final String uuid, final String name, String color, String gradient_one, String gradient_two, boolean ismute) {
		try(Connection connection = plugin.getConnection()) {
			if(!existsPlayer(plugin, uuid)) {
				PreparedStatement statement = connection.prepareStatement("INSERT INTO `" + Settings.mysql_table + "` VALUE (?,?,?,?,?,?)");
				statement.setString(1, uuid);
				statement.setString(2, name);
				statement.setString(3, color);
				statement.setString(4, gradient_one);
				statement.setString(5, gradient_two);
				statement.setBoolean(6, ismute);
				statement.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean existsPlayer(AdvancedChat plugin, String uuid) {
		try {
			PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM `" + Settings.mysql_table + "` WHERE (UUID=?)");
			statement.setString(1, uuid);
			
			ResultSet resultSet = statement.executeQuery();
			Logger.debug("&aExists player in database");
			while(resultSet.next()) {
				return true;
			}
		}catch(SQLException e) {
			Logger.debug("&eNo exists player in database");	
		}
		return false;
	}
	
	public static String getColor(AdvancedChat plugin, String uuid) {
		try (Connection connection = plugin.getConnection()){
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM `" + Settings.mysql_table + "` WHERE (UUID=?)");
			statement.setString(1, uuid);
			
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				String player_color = resultSet.getString("COLOR_NAME");
				return player_color; 
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getFirstGradient(AdvancedChat plugin, String uuid) {
		try (Connection connection = plugin.getConnection()){
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM `" + Settings.mysql_table + "` WHERE (UUID=?)");
			statement.setString(1, uuid);
			
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				String player_color = resultSet.getString("GRADIENT_COLOR_ONE");
				return player_color; 
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getSecondGradient(AdvancedChat plugin, String uuid) {
		try (Connection connection = plugin.getConnection()){
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM `" + Settings.mysql_table + "` WHERE (UUID=?)");
			statement.setString(1, uuid);
			
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				String player_color = resultSet.getString("GRADIENT_COLOR_TWO");
				return player_color; 
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean isMute(AdvancedChat plugin, String uuid) {
		try (Connection connection = plugin.getConnection()){
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM `" + Settings.mysql_table + "` WHERE (UUID=?)");
			statement.setString(1, uuid);
			
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				int player_mute = resultSet.getInt("IS_MUTE");
				return player_mute == 1 ? true : false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void setColor(AdvancedChat plugin, String uuid, String color_name) {
		try(Connection connection = plugin.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("UPDATE `" + Settings.mysql_table + "` SET COLOR_NAME WHERE (UUID=?)");
			statement.setString(1, color_name);
			statement.setString(2, uuid);
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static void setGradientOne(AdvancedChat plugin, String uuid, String color) {
		try(Connection connection = plugin.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("UPDATE `" + Settings.mysql_table + "` SET GRADIENT_COLOR_ONE WHERE (UUID=?)");
			statement.setString(1, color);
			statement.setString(2, uuid);
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static void setGradientTwo(AdvancedChat plugin, String uuid, String color) {
		try(Connection connection = plugin.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("UPDATE `" + Settings.mysql_table + "` SET GRADIENT_COLOR_TWO WHERE (UUID=?)");
			statement.setString(1, color);
			statement.setString(2, uuid);
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setMute(AdvancedChat plugin, String uuid, boolean mute) {
		try(Connection connection = plugin.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("UPDATE `" + Settings.mysql_table + "` SET IS_MUTE WHERE (UUID=?)");
			statement.setBoolean(1, mute);
			statement.setString(2, uuid);
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}