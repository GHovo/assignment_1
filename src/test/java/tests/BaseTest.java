package tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static configs.ConfigurationReader.getBrowserURL;
import static driver.DriverManager.*;
import static helpers.WaitHelps.getWait;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp(){
        initDriver();
        driver = getDriver();
        openMainPage();
    }

    protected void setResolution(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    private void openMainPage(){
        driver.get(getBrowserURL());
        getWait().waitForPageLoadComplete(getDriver(),30);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        quitDriver();
    }
}
