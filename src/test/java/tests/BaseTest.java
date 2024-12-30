package tests;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static configs.ConfigurationReader.getBrowserURL;
import static driver.DriverManager.*;
import static helpers.WaitHelps.getWait;
@Log4j2
public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUpBase(){
        log.info("Initializing Base setup...");
        initDriver();
        driver = getDriver();
        openMainPage();
    }

    protected void setResolution(int width, int height) {
        log.info("Set resolution(width:{}, height:{})",width,height);
        driver.manage().window().setSize(new Dimension(width, height));
    }

    private void openMainPage(){
        driver.get(getBrowserURL());
        getWait().waitForPageLoadComplete(getDriver(),30);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        log.info("Closing driver...");
        quitDriver();
    }
}
