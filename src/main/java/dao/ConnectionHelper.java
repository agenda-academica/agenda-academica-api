package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import config.Config;

public class ConnectionHelper
{
	private ConnectionHelper() {}
	
	public static Connection getConnection() throws SQLException {
		Properties properties = Config.getProperties();
		
        try {
			Class.forName(properties.getProperty("database.driverClass"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        return DriverManager.getConnection(
            String.format(
                "%s://%s:%s/%s",
                properties.getProperty("database.driver"),
                properties.getProperty("database.host"),
                properties.getProperty("database.port"),
                properties.getProperty("database.database")
            ),
            properties.getProperty("database.username"),
            properties.getProperty("database.password")
        );
	}
	
	public static void close(Connection connection)
	{
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}