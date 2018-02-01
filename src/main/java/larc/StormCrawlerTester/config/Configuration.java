package larc.StormCrawlerTester.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Configuration {

	public static String CONFIG_FILE = "conf/config.properties";
	private static Properties prop;
	
	public static void initConfig(String configFile){
		try {
			prop = new Properties();
			prop.load(new FileInputStream(new File(configFile)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initConfig(InputStream configFile){
		try {
			prop = new Properties();
			prop.load(configFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getParameter(String key){
		if(prop == null){
			initConfig(Configuration.class.getClassLoader().getResourceAsStream(Constant.CONFIG_FILE));
		}
		if(prop.getProperty(key) != null){
			return prop.getProperty(key).trim();
		}
		return null;
	}

}
