package jss.advancedchat.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLGetter {
	
    public boolean exist(MySQL sql,String uuid) {
        try {

            PreparedStatement statement = sql.getConnection().prepareStatement("SELECT * FROM AdvancedChat_Players WHERE (UUID=?)");
            statement.setString(1, uuid);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createPlayer(MySQL sql, String name, String uuid) {
        try {

            if (!exist(sql,uuid)) {
                PreparedStatement statement = sql.getConnection().prepareStatement("INSERT INTO AdvancedChat_Players VALUE (?,?,?,?)");
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


    public String getColor(MySQL sql ,String uuid) {
        try {

            PreparedStatement statement = sql.getConnection().prepareStatement("SELECT * FROM AdvancedChat_Players WHERE (UUID=?)");
            statement.setString(1, uuid);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String color = resultSet.getString("COLOR");
                return color;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isMute(MySQL sql, String uuid) {
        try {

            PreparedStatement statement = sql.getConnection().prepareStatement("SELECT * FROM AdvancedChat_Players WHERE (UUID=?)");
            statement.setString(1, uuid);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                boolean mute = resultSet.getBoolean("MUTE");
                return mute;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setColor(MySQL sql, String uuid, String color) {
        try {
            PreparedStatement statement = sql.getConnection().prepareStatement("UPDATE AdvancedChat_Players SET COLOR=? WHERE (UUID=?)");
            statement.setString(1, color);
            statement.setString(2, uuid);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setMute(MySQL sql, String name, String uuid, boolean mute) {
        try {
            PreparedStatement statement = sql.getConnection().prepareStatement("UPDATE AdvancedChat_Players SET MUTE=? WHERE (UUID=?)");
            statement.setBoolean(1, mute);
            statement.setString(2, uuid);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
