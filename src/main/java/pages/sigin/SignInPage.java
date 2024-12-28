package pages.sigin;

import helpers.WaitHelps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class SignInPage extends BasePage {

    @FindBy(xpath = "//h3[text()='Get started with Picsart']")
    public WebElement signInTitle;
    @FindBy(css = "svg[data-testid='modal-close-icon']")
    public WebElement closeButton;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public void closeSignInPopUp(){
        WaitHelps.getWait().waitUntilElementToBeVisible(closeButton);
        closeButton.click();
    }
}
