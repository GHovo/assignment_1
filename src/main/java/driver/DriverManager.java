package driver;

import configs.ConfigurationReader;
import constants.common.BrowserTypes;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Optional;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager(){}

    public static WebDriver getDriver() {
        return  driverThreadLocal.get();
    }

    public static void initDriver() {
        if (driverThreadLocal.get() == null) {
            WebDriver driver = createDriver(ConfigurationReader.getBrowserType());
            driverThreadLocal.set(driver);
        }
    }

    private static WebDriver createDriver(BrowserTypes browserType) {
        return switch (browserType) {

            case CHROME -> getChromeDriver();

            case FIREFOX -> getFirefoxDriver();
            default -> throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        };
    }

    private static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
    public static void quitDriver() {
        Optional.ofNullable(driverThreadLocal.get()).ifPresent(driver -> {
            driver.quit();
            driverThreadLocal.remove();
        });
    }
}
