package pages.search.forms;

import helpers.ElementHelper;
import helpers.WaitHelps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

public class GridWrapperForm extends BasePage {

    @FindBy(xpath = "//i[@data-testid='badge']")
    public List<WebElement> badges;
    @FindBy(xpath = "//img[@data-testid='image']")
    public List<WebElement> images;
    @FindBy(xpath = "//div[p[text()='Images']]//button[span[text()='See all']]")
    private WebElement seeAllImageButton;
    @FindBy(xpath = "//div[@id='base_card_item0']/a[div[picture]]")
    private List<WebElement> firstImage;
    @FindBy(xpath = "//div[@id='base_card_item0' and i[@data-testid='badge']]/a")
    private WebElement firstBadge;
    @FindBy(xpath = "//button[@id='try_now_button_item0']")
    private WebElement TryNowFirstImage;

    public GridWrapperForm(WebDriver driver) {
        super(driver);
    }

    public WebElement getFirstImage(){
        WaitHelps.getLongWait();
        return firstImage.get(0);
    }
    public void clickOnTheFirstIMG(){
        WaitHelps.getWait().waitUntilElementToBeClickable(getFirstImage());
        getFirstImage().click();
    }

    public String clickOnThePlusBadge(){
        WaitHelps.getLongWait();
        WaitHelps.getWait().waitUntilElementToBeClickable(firstBadge);
        String imgId = getCImgId();
        firstBadge.click();
        return imgId;
    }
    private String getCImgId(){
        WaitHelps.getWait().waitUntilElementToBeClickable(firstBadge);
        String href = ElementHelper.getAttribute(firstBadge, "href");

        return ElementHelper.splitStringByIndex(
                ElementHelper.splitStringByIndex(href, "imageId=", 1),
                "&",
                0
        );
    }
}
