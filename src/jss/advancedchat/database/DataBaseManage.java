package jss.advancedchat.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseManage {

    public static boolean existPlayer(Connection connection, String uuid) {
        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM players WHERE (UUID=?)");
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

    public static void createPlayer(Connection connection, String name, String uuid) {
        try {

            if (!existPlayer(connection, uuid)) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO players VALUE (?,?,?,?)");
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


    public static String getColor(Connection connection, String uuid) {
        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM players WHERE (UUID=?)");
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

    public static boolean isMute(Connection connection, String uuid) {
        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM players WHERE (UUID=?)");
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

    public static void setColor(Connection connection, String uuid, String color) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE players SET COLOR=? WHERE (UUID=?)");
            statement.setString(1, color);
            statement.setString(2, uuid);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setMute(Connection connection, String name, String uuid, boolean mute) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE players SET MUTE=? WHERE (UUID=?)");
            statement.setBoolean(1, mute);
            statement.setString(2, uuid);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
