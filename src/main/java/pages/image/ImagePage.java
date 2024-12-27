package pages.image;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class ImagePage extends BasePage {

    @FindBy(xpath = "//button[span[text()='likes']]")
    private WebElement likes;

    @FindBy(xpath = "//button[span[text()='Edit this image']]")
    private WebElement editImgButton;
    @FindBy(xpath = "//button[span[text()='save']]")
    private WebElement saveButton;
    public ImagePage(WebDriver driver) {
        super(driver);
    }
}
