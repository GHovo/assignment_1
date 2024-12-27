package pages.search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="filter_icon")
    private WebElement filterButton;
    @FindBy()
    private WebElement licenseCheckbox;
    @FindBy()
    private WebElement likeButton;
    @FindBy()
    private WebElement plusAsset;

    public void clickFilterButton() {
        filterButton.click();
    }


}
