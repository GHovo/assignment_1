package pages.search.forms;

import helpers.WaitHelps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class AcceptCookiesForm extends BasePage {

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    public WebElement acceptAllCookiesButton;
    public AcceptCookiesForm(WebDriver driver) {
        super(driver);
    }

    public void acceptAllCookies() {
        WaitHelps.getWait().waitUntilElementToBeVisible(acceptAllCookiesButton);
        acceptAllCookiesButton.click();
    }
}
