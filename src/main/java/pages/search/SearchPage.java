package pages.search;

import helpers.ElementHelper;
import helpers.WaitHelps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//iframe[@data-testid='com.picsart.social.search']")
    private WebElement iframe;
    @FindBy(id = "filter_icon")
    public WebElement filterButton;
    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    public WebElement acceptAllCookiesButton;


    public void clickFilterButton(WebDriver driver) {
        ElementHelper.scrollToElement( filterButton ,driver );
        WaitHelps.getWait().waitUntilElementToBeVisible(filterButton);
        filterButton.click();
    }
    public void acceptAllCookies() {
        WaitHelps.getWait().waitUntilElementToBeVisible(acceptAllCookiesButton);
        acceptAllCookiesButton.click();
    }

    public void switchToIframe(WebDriver driver) {
        WaitHelps.getWait().waitUntilElementToBeVisible(iframe);
        driver.switchTo().frame(iframe);
    }

}
