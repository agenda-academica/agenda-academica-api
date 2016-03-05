package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import dao.ConnectionHelper;

public class Config {
	private static Properties properties = null;
	
	private Config() {}
	
	public static Properties getProperties() {
		if (Config.properties != null) {
			return Config.properties;
		}
		
		try {
			Properties properties = new Properties();
			InputStream inputStream = ConnectionHelper.class.getResourceAsStream(
				"/agenda-academica-api.properties"
			);
			properties.load(inputStream);
			inputStream.close();
			Config.properties = properties;
		} catch (IOException e) {
			e.printStackTrace(); 
		}
		return Config.properties;
	}
}
