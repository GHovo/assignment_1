package helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static configs.DriverManager.getDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class WaitHelps {
    private static final int TIMEOUT = 30;

    private static final int DEFAULT_POLLING_INTERVAL = 100;

    private static WebDriverWait wait;


    public static WaitHelps getWait() {
        return getCustomWait();
    }

    private static WaitHelps getCustomWait() {
        WaitHelps waitUtils = new WaitHelps();
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WaitHelps.TIMEOUT), Duration.ofMillis(DEFAULT_POLLING_INTERVAL));
        return waitUtils;
    }

    public void waitUntilElementToBeVisible(WebElement element) {
        wait.until(visibilityOf(element));
    }

    public void waitForPageLoadComplete() {
        wait.until((driver) -> String.valueOf(((JavascriptExecutor)driver)
                .executeScript("return document.readyState")).equals("complete"));
    }

}
