package helpers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class ElementHelper {
    public static void scrollToElement(WebElement element, WebDriver driver){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);

    }

    public static void navigateBack(WebDriver driver) {
        log.info("Get random item from list");
        driver.navigate().back();
        WaitHelps.getLongWait();
    }

    public static String getAttribute(WebElement element, String attribute) {
        if (element == null || attribute == null || attribute.isEmpty()) {
            throw new IllegalArgumentException("Element or attribute cannot be null or empty");
        }
        return element.getAttribute(attribute);
    }
    public static String splitStringByIndex(String input, String delimiter, int index) {
        if (input == null || delimiter == null || delimiter.isEmpty()) {
            throw new IllegalArgumentException("Input string or delimiter cannot be null or empty");
        }

        String[] parts = input.split(delimiter);

        if (index < 0 || index >= parts.length) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        return parts[index];
    }
}
