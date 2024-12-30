package pages.search.forms;

import api.search.FilterOptions;
import helpers.ElementHelper;
import helpers.WaitHelps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class FilterForm extends BasePage {
    @FindBy(xpath = "//h4[text()='License']")
    private WebElement licenseTitle;
    @FindBy(xpath = "//li[@aria-label='licenses-All']")
    private WebElement allLicense;

    @FindBy(xpath = "//li[@aria-label='licenses-Commercial']")
    public WebElement commercialLicense;

    @FindBy(xpath = "//li[@aria-label='licenses-Personal']")
    public WebElement personalLicense;
    @FindBy(xpath = "//div[h3[text()='Chosen Filters']]")
    public WebElement chosenFiltersTitle;
    @FindBy(xpath = "//button[span[text()='Personal']]")
    public WebElement chosenFilter;
    @FindBy(id = "search-filter-header-clear")
    public WebElement clearAllButton;
    public FilterForm(WebDriver driver) {
        super(driver);
    }

    public void selectLicense(FilterOptions.License licenseType) {
        switch (licenseType) {
            case ALL -> allLicense.click();
            case COMMERCIAL -> commercialLicense.click();
            case PERSONAL -> personalLicense.click();
            default ->
                    throw new IllegalArgumentException("Invalid License Type: " + licenseType);
        }
        WaitHelps.getWait().waitUntilElementToBeVisible(clearAllButton);
    }

    public void clearAllFilters(WebDriver driver){
        try {
            WaitHelps.getWait().waitUntilElementToBeClickable(clearAllButton);
        }catch (Exception e){
            ElementHelper.scrollToElement(clearAllButton, driver);
        }
        clearAllButton.click();
    }
}
