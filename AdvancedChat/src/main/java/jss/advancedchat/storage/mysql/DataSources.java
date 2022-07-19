package jss.advancedchat.storage.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSources {

    private HikariDataSource hikariDataSource;

    public DataSources(String ip, int port, String database, String username, String password){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + ip + ":" + port + "/" + database);
        config.setUsername(username);
        config.setPassword(password);
        config.addDataSourceProperty("autoReconnect", "true");
        config.addDataSourceProperty("leakDetectionThreshold", "true");
        config.addDataSourceProperty("verifyServerCertificate", "false");
        config.addDataSourceProperty("useSSL", "false");
        config.setConnectionTimeout(5000);
        hikariDataSource = new HikariDataSource(config);
    }

    public HikariDataSource getHikariDataSource(){
        return this.hikariDataSource;
    }

    public void disable(){
        if (hikariDataSource != null) {
            hikariDataSource.close();
        }
    }

}
