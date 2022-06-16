package jss.advancedchat.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;

import jss.advancedchat.AdvancedChat;

public class MySQL {
	
	private static AdvancedChat plugin = AdvancedChat.get();
	private static Connection connection = plugin.getConnection();
	private PreparedStatement statement; 
	
	public void getPlayerFromStorage(String name) {
		
	}
	
	
	/*
	
	public MySQL(AdvancedChat plugin) {
		this.plugin = plugin;
	}
	
	private Connection connection = plugin.getConnection();
	private  statement;
	
	public PlayerData getPlayerData(Player player) {
		return new PlayerData(connection, player);
	}
	
	public String getName(Player player) {
		try {
			statement = connection.prepareStatement("SELECT * FROM `" + Settings.mysql_table + "` WHERE (player_name=?)");
			statement.setString(1, player.getName());
			
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				String player_color = resultSet.getString("PLAYER_NAME");
				return player_color; 
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getColor(Player player) {
		try {
			statement = connection.prepareStatement("SELECT * FROM `" + "" + "` WHERE (UUID=?)");
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getColor0(String uuid) {
		try {
			statement = connection.prepareStatement("SELECT * FROM `" + Settings.mysql_table + "` WHERE (UUID=?)");
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
	
	public String getFirstGradient(String uuid) {
		try {
			statement = connection.prepareStatement("SELECT * FROM `" + Settings.mysql_table + "` WHERE (UUID=?)");
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
	
	public String getSecondGradient(String uuid) {
		try {
			statement = connection.prepareStatement("SELECT * FROM `" + Settings.mysql_table + "` WHERE (UUID=?)");
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
	
	public boolean isMute(String uuid) {
		try {
			statement = connection.prepareStatement("SELECT * FROM `" + Settings.mysql_table + "` WHERE (UUID=?)");
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
	
	public void setColor(Player uuid, String color_name) {
		try {
			statement = connection.prepareStatement("UPDATE `" + Settings.mysql_table + "` SET COLOR_NAME WHERE (UUID=?)");
			statement.setString(1, color_name);
			statement.setString(2, uuid.getUniqueId().toString());
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void setGradientFirst(Player player, String color) {
		try {
			statement = connection.prepareStatement("UPDATE `" + Settings.mysql_table + "` SET GRADIENT_COLOR_ONE WHERE (UUID=?)");
			statement.setString(1, color);
			statement.setString(2, player.getUniqueId().toString());
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void setGradientSecond(Player player, String color) {
		try {
			statement = connection.prepareStatement("UPDATE `" + Settings.mysql_table + "` SET GRADIENT_COLOR_TWO WHERE (UUID=?)");
			statement.setString(1, color);
			statement.setString(2, player.getUniqueId().toString());
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setMute(String uuid, boolean mute) {
		try {
			statement = connection.prepareStatement("UPDATE `" + Settings.mysql_table + "` SET IS_MUTE WHERE (UUID=?)");
			statement.setBoolean(1, mute);
			statement.setString(2, uuid);
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createTable() {
		try{
			statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `" + "advancedchat_user_data" + "` (`UUID` VARCHAR(200), `PLAYER_NAME` VARCHAR(100), `COLOR` VARCHAR(16), `FIRST_GRADIENT` VARCHAR(20), `SECOND_GRADIENT` VARCHAR(20), `RAINBOW` VARCHAR(20), `SPECIAL_COLOR_CODES` VARCHAR(20))");
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
		
	public void createSettingsTable() {
		try{
			statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `" + "advancedchat_user_settings" + "` (`UUID` VARCHAR(200), `PLAYER_NAME` VARCHAR(100), `IS_MUTE` BOOLEAN, `IS_LOW_MODE` BOOLEAN , `IS_CHAT` BOOLEAN, `IS_MENTION` BOOLEAN, `IS_MSG` BOOLEAN)");
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createDataPlayer(Player player, String group, String color, String first_gradient, String second_gradient, String rainbow, String specialcodes) {
		try {
			if(!existsPlayer(TableType.Data,player.getName())) {
				statement = connection.prepareStatement("INSERT INTO `" + Settings.mysql_table + "` VALUE (?,?,?,?,?,?,?,?)");
				statement.setString(1, player.getUniqueId().toString());
				statement.setString(2, player.getName());
				statement.setString(3, group);
				statement.setString(4, color);
				statement.setString(5, first_gradient);
				statement.setString(6, second_gradient);
				statement.setString(7, rainbow);
				statement.setString(8, specialcodes);
				statement.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createSettingsPlayer(Player player, boolean ismute, boolean islowmode, boolean ischat, boolean ismention, boolean ismsg) {
		try {
			if(!existsPlayer(TableType.Settings,player.getName())) {
				statement = connection.prepareStatement("INSERT INTO `" + Settings.mysql_table + "` VALUE (?,?,?,?,?,?,?)");
				statement.setString(1, player.getUniqueId().toString());
				statement.setString(2, player.getName());
				statement.setBoolean(3, ismsg);
				statement.setBoolean(4, islowmode);
				statement.setBoolean(5, ischat);
				statement.setBoolean(6, ismention);
				statement.setBoolean(7, ismsg);
				statement.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean existsPlayer(TableType type ,String uuid) {
		try {
			statement = plugin.getConnection().prepareStatement("SELECT * FROM `" + type + "` WHERE (UUID=?)");
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
	}*/
}