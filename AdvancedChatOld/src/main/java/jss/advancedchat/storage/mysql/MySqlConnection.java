package jss.advancedchat.storage.mysql;

import jss.advancedchat.utils.Logger;
import jss.advancedchat.files.utils.Settings;

import java.sql.Connection;
import java.sql.SQLException;

public class MySqlConnection {

    private DataSources connection;

    public void setup() {
        try {
            String host = Settings.mysql_host;
            int port = Settings.mysql_port;
            String database = Settings.mysql_database;
            String username = Settings.mysql_username;
            String password = Settings.mysql_password;
            connection = new DataSources(host, port, database, username, password);
            connection.getHikariDataSource().getConnection();
            MySql.createTable();
            Logger.success("&aSuccessfully connected to the Database.");
        } catch (SQLException e) {
            Logger.error("&cError while connecting to the Database.");
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        try {
            return connection.getHikariDataSource().getConnection();
        } catch (SQLException ex) {
            Logger.error("&cError while connecting to the Database");
            return null;
        }
    }

}
