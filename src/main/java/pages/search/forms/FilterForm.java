package pages.search.forms;

import api.search.FilterOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import pages.BasePage;

import java.util.List;

import static api.search.FilterOptions.License.COMMERCIAL;

public class FilterForm extends BasePage {
    @FindBy(xpath = "//h4[text()='License']")
    private WebElement licenseTitle;
    @FindBy(xpath = "//li[@aria-label='licenses-All']")
    private WebElement allLicense;

    @FindBy(xpath = "//li[@aria-label='licenses-Commercial']")
    private WebElement commercialLicense;

    @FindBy(xpath = "//li[@aria-label='licenses-Personal']")
    private WebElement personalLicense;
    @FindBy(xpath = "//div[h3[text()='Chosen Filters']]")
    private WebElement chosenFiltersTitle;
    @FindBy(xpath = "//button[span[text()='Personal']]")
    private WebElement chosenFilter;
    @FindBy(xpath = "//div[@data-testid='all-content-grid-root']//i[@data-testid='badge']")
    private List<WebElement> badges;
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
    }

    public int getBadgeCount(){
        return badges.size();
    }
}
