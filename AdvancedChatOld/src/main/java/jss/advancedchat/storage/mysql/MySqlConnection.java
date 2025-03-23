package jss.advancedchat.storage.mysql;

import jss.advancedchat.utils.logger.Logger;
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

            // Imprimir información de conexión para depuración
            Logger.info("Connecting to MySQL database: " + host + ":" + port + "/" + database);

            // Crear la conexión
            connection = new DataSources(host, port, database, username, password);

            // Verificar la conexión inicial
            connection.getHikariDataSource().getConnection();
            MySql.createTable();

            Logger.success("&aSuccessfully connected to the Database.");
        } catch (SQLException e) {
            Logger.error("&cError while connecting to the Database: " + e.getMessage());
            e.printStackTrace();  // Imprimir el stack trace para más detalles
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        try {
            return connection.getHikariDataSource().getConnection();
        } catch (SQLException ex) {
            Logger.error("&cError while getting a connection to the Database: " + ex.getMessage());
            ex.printStackTrace();  // Imprimir el stack trace para más detalles
            return null;
        }
    }

    public void close() {
        if (connection != null && connection.getHikariDataSource() != null) {
            connection.getHikariDataSource().close();
            Logger.success("&aDatabase connection closed successfully.");
        } else {
            Logger.error("&cNo connection to close.");
        }
    }
}
