package driver;

import configs.ConfigurationReader;
import constants.common.BrowserTypes;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Optional;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

     private DriverManager(){}

    public static WebDriver getDriver() {
         return  driverThreadLocal.get();
    }

    public static void initDriver(String resolution) {
         if (driverThreadLocal.get() == null) {
             WebDriver driver = createDriver(ConfigurationReader.getBrowserType(),resolution);
             driverThreadLocal.set(driver);
             maximizeWindow(driver);
         }
    }

    private static WebDriver createDriver(BrowserTypes browserType, String resolution) {
        return switch (browserType) {

            case CHROME -> getChromeDriver(resolution);

            case FIREFOX -> getFirefoxDriver();
            default -> throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        };
    }

    public static void quitDriver() {
        Optional.ofNullable(driverThreadLocal.get()).ifPresent(driver -> {
            driver.quit();
            driverThreadLocal.remove();
        });}

    private static WebDriver getChromeDriver(String resolution) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=" + resolution);
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
    private static void maximizeWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }
}
