package configs;

import constants.common.BaseConstants;
import constants.common.BrowserTypes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static constants.common.BaseConstants.BROWSER;
import static constants.common.BaseConstants.CONFIGURATION_PROPERTIES_PATH;
import static constants.common.BrowserTypes.CHROME;

public class Configuration {
    public static BrowserTypes BROWSER_TYPE;
    public static String BASE_URL;
    public static final Properties BASE_URL_PROPERTIES = readFile( CONFIGURATION_PROPERTIES_PATH) ;

    static {
        BROWSER_TYPE = BrowserTypes.parse(System.getProperty(BROWSER, CHROME.getType()));
        BASE_URL = getProperty(BaseConstants.BASE_URL_KEY,BASE_URL_PROPERTIES);
    }


    public static BrowserTypes getBrowserType() {
        return BROWSER_TYPE;
    }

    public static String getBrowserURL() {
        return BASE_URL;
    }

    public static String getProperty(String key, Properties properties){
        return  properties.getProperty(key);
    }

    public static Properties readFile(final String path) {
        Properties properties = new Properties();
        try (FileInputStream file = new FileInputStream(path)) {
            properties.load(file);
        } catch (IOException e) {
            e.getStackTrace();
        }
        return properties;
    }
}
