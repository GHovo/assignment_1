package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import static configs.ConfigurationReader.getBrowserURL;
import static driver.DriverManager.getDriver;
import static driver.DriverManager.initDriver;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    @Parameters("resolution")
    public void setup(String resolution){
        initDriver(resolution);
        driver = getDriver();
        openMainPage();
    }

    private void openMainPage(){
        driver.get(getBrowserURL());
       // getWait().waitForPageLoadComplete(getDriver(),30);
    }
}
