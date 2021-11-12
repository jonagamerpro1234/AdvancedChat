package jss.advancedchat.storage;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {
	
	private HikariDataSource dataSource;
	
	public DataSource(String host, int port, String database, String username, String password) {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database);
		config.setUsername(username);
		config.setPassword(password);
        config.addDataSourceProperty("autoReconnect", "true");
        config.addDataSourceProperty("leakDetectionThreshold", "true");
        config.addDataSourceProperty("verifyServerCertificate", "false");
        config.addDataSourceProperty("useSSL", "false");
        config.setConnectionTimeout(5000);
		dataSource = new HikariDataSource(config);
	}
	
	public HikariDataSource getDataSource() {
		return dataSource;
	}
	
	public void disconnect() {
		if(dataSource != null) {
			dataSource.close();
		}
	}
	
}
