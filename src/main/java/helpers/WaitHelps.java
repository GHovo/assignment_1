package helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static driver.DriverManager.getDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class WaitHelps {
    private static final int SHORT_TIMEOUT = 10;
    private static final int TIMEOUT = 30;
    private static final int LONG_TIMEOUT = 900;
    private WebDriverWait wait;

    // Constructor to initialize WebDriverWait
    public WaitHelps(WebDriver driver, int timeout) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public static WaitHelps getShortWait() {
        return new WaitHelps(getDriver(), SHORT_TIMEOUT);
    }

    public static WaitHelps getWait() {
        return new WaitHelps(getDriver(), TIMEOUT);
    }

    public static WaitHelps getLongWait() {
        return new WaitHelps(getDriver(), LONG_TIMEOUT);
    }

//    public void waitUntilElementToBeVisible(WebElement element) {
//        wait.until(visibilityOf(element));
//    }

    public void waitUntilElementToBeClickable(WebElement element) {
        wait.until(elementToBeClickable(element));
    }

//    public WebElement waitUntilElementToBeVisible(WebElement element) {
//
//        return waitUntilElementToBeVisibleWithRetry(element, 15);
//    }

    public void waitUntil(ExpectedCondition<Boolean> condition) {
        wait.until(condition);
    }

    public void waitForPageLoadComplete(WebDriver driver, int timeout) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(driver1 -> String.valueOf(((JavascriptExecutor) driver1)
                .executeScript("return document.readyState")).equals("complete"));
    }

    // New method to wait until an element is visible with retry handling
    public WebElement waitUntilElementToBeVisible(WebElement element) {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        try {
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element not visible after 15 seconds: " + element, e);
        }
    }
}