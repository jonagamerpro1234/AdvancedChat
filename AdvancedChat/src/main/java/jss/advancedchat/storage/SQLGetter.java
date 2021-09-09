package jss.advancedchat.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Setter;

public class SQLGetter {
	
	@Setter
	private MySQL mySQL;
	
    public boolean exists(String uuid) {
        try {
            PreparedStatement statement = mySQL.getConnection().prepareStatement("SELECT * FROM `advancedchat_players` WHERE (UUID=?)");
            statement.setString(1, uuid);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
            	return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createPlayer(String name, String uuid) {
        try {
            if (!this.exists(uuid)) {
                PreparedStatement statement = mySQL.getConnection().prepareStatement("INSERT INTO `advancedchat_players` VALUE (?,?,?,?)");
                statement.setString(1, name);
                statement.setString(2, uuid);
                statement.setBoolean(3, false);
                statement.setString(4, "white");
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String getColor(String uuid) {
        try {
            PreparedStatement statement = mySQL.getConnection().prepareStatement("SELECT * FROM `advancedchat_players` WHERE (UUID=?)");
            statement.setString(1, uuid);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String color = resultSet.getString("Color");
                return color;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean getMuted(String uuid) {
        try {
            PreparedStatement statement = mySQL.getConnection().prepareStatement("SELECT * FROM `advancedchat_players` WHERE (UUID=?)");
            statement.setString(1, uuid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int muted = resultSet.getInt("Mute");
                return muted == 1 ? true : false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setColor(String uuid, String color) {
        try {
            PreparedStatement statement = mySQL.getConnection().prepareStatement("UPDATE `advancedchat_players` SET Color=? WHERE (UUID=?)");
            statement.setString(1, color);
            statement.setString(2, uuid);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setMuted(String name, String uuid, boolean mute) {
        try {
            PreparedStatement statement = mySQL.getConnection().prepareStatement("UPDATE `advancedchat_players` SET Mute=? WHERE (UUID=?)");
            statement.setBoolean(1, mute);
            statement.setString(2, uuid);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}