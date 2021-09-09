package jss.advancedchat.utils;

import java.sql.SQLException;

import jss.advancedchat.storage.MySQL;
import jss.advancedchat.storage.SQLGetter;

public class Methods {
	
	private static SQLGetter sqlGetter;
	
	public static boolean initMySQL() {
		
		MySQL mySQL = new MySQL(Settings.mysql_host, Settings.mysql_port, Settings.mysql_database, Settings.mysql_options, Settings.mysql_user, Settings.mysql_password);
		if(Settings.mysql_use) {
			try {
				mySQL.openConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sqlGetter.setMySQL(mySQL);
			return true;
		}else {
			Logger.warning("No connected MySql!");
			return false;
		}
	}
	
}
