package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    private static WebDriverWait wait;


    public static WaitHelps getShortWait() {
        return getCustomWait(SHORT_TIMEOUT);
    }

    public static WaitHelps getWait() {
        return getCustomWait(TIMEOUT);
    }

    public static void getLongWait() {
        getCustomWait(LONG_TIMEOUT);
    }

    private static WaitHelps getCustomWait(int timeout) {
        WaitHelps waitUtils = new WaitHelps();
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(LONG_TIMEOUT));
        return waitUtils;
    }

    public void waitUntilElementToBeVisible(WebElement element) {
        wait.until(visibilityOf(element));
    }
    public void waitUntilElementToBeClickable(WebElement element) {
        wait.until(elementToBeClickable(element));
    }
    public WebElement waitUntilElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitUntil(ExpectedCondition<Boolean> condition) {
        wait.until(condition);
    }

    public void waitForPageLoadComplete(WebDriver driver, int timeout) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(LONG_TIMEOUT));
        wait.until((driver1) -> String.valueOf(((JavascriptExecutor)driver1)
                .executeScript("return document.readyState")).equals("complete"));
    }

}
