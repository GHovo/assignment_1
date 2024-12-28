package pages.image;

import helpers.WaitHelps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class ImagePage extends BasePage {
    @FindBy(xpath = "//button[span[text()='likes']]")
    public WebElement likes;
    @FindBy(xpath = "//button[span[text()='Edit this image']]")
    public WebElement editImgButton;
    @FindBy(xpath = "//button[span[text()='save']]")
    public WebElement saveButton;
    @FindBy(xpath = "//button[@data-testid='likeComponent']")
    public WebElement likeIcon;
    public ImagePage(WebDriver driver) {
        super(driver);
    }

    public void likeImage(){
        WaitHelps.getWait().waitUntilElementToBeClickable(likeIcon);
        likeIcon.click();
    }
}
