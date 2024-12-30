package helpers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
public class NavigationHelper {

    public static void navigateBack(WebDriver driver) {
        log.info("Navigate back");
        driver.navigate().back();
        WaitHelps.getLongWait();
    }
    public static void refreshPage(WebDriver driver) {
        log.info("Refreshing page");
        driver.navigate().refresh();
    }

}
