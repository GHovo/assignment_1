package helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static driver.DriverManager.getDriver;

public class WaitHelps {
    private static final int TIMEOUT = 30;
    private static final int LONG_TIMEOUT = 90;

    public WaitHelps(WebDriver driver, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public static WaitHelps getWait() {
        return new WaitHelps(getDriver(), TIMEOUT);
    }

    public static void getLongWait() {
        new WaitHelps(getDriver(), LONG_TIMEOUT);
    }

    public WebElement waitUntilElementToBeClickable(WebElement element) {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element not visible after 15 seconds: " + element, e);
        }
    }

    public void waitForPageLoadComplete(WebDriver driver, int timeout) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(driver1 -> String.valueOf(((JavascriptExecutor) driver1)
                .executeScript("return document.readyState")).equals("complete"));
    }

    public WebElement waitUntilElementToBeVisible(WebElement element) {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        try {
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element not visible after 15 seconds: " + element, e);
        }
    }
}