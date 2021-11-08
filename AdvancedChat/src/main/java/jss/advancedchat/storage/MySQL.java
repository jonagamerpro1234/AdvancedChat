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
		}else {
			return false;
		}
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

	
}