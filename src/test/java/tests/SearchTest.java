package tests;

import api.search.FilterOptions;
import dataprovider.ResolutionsDataProvider;
import helpers.WaitHelps;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.image.ImagePage;
import pages.search.SearchPage;
import pages.search.forms.AcceptCookiesForm;
import pages.search.forms.FilterForm;
import pages.search.forms.GridWrapperForm;
import pages.sigin.SignInPage;

import static helpers.Help.navigateBack;

public class SearchTest extends BaseTest{
    private AcceptCookiesForm acceptCookiesForm;
    private SearchPage searchPage;
    private FilterForm filterForm;

    @BeforeMethod
    public void setup(){
        acceptCookiesForm = new AcceptCookiesForm(driver);
        acceptCookiesForm.acceptAllCookies();
        searchPage = new SearchPage(driver);
        filterForm = new FilterForm(driver);
    }

    @Test(dataProvider = "resolutionProvider", dataProviderClass = ResolutionsDataProvider.class)
    public void test(int width, int height){
        setResolution(width, height);

        searchPage.switchToIframe(driver);
        Assertions.assertThat(searchPage.filterButton.isDisplayed())
                .as("hhhh")
                .isTrue();
        searchPage.clickFilterButton(driver);

        Assertions.assertThat(filterForm.personalLicense.isDisplayed())
                .as("hhhh")
                .isFalse();

        searchPage.clickFilterButton(driver);
        filterForm.selectLicense(FilterOptions.License.PERSONAL);
        GridWrapperForm imgWrapper = new GridWrapperForm(driver);
        Assertions.assertThat( imgWrapper.badges )
                .hasSizeLessThanOrEqualTo(0);

        imgWrapper.clickOnTheFirstIMG();
        ImagePage imagePage = new ImagePage(driver);
        WaitHelps.getWait().waitUntilElementToBeVisible(imagePage.likeIcon);

        Assertions.assertThat(imagePage.editImgButton.isDisplayed())
                .as("hhhh")
                .isTrue();

        Assertions.assertThat(imagePage.likes.isDisplayed())
                .as("hhhh")
                .isTrue();

        Assertions.assertThat(imagePage.saveButton.isDisplayed())
                .as("hhhh")
                .isTrue();

        imagePage.likeImage();
        SignInPage signInPage = new SignInPage(driver);
        WaitHelps.getWait().waitUntilElementToBeVisible(signInPage.signInTitle);

        Assertions.assertThat(signInPage.signInTitle.isDisplayed())
                .as("hhhh")
                .isTrue();
        signInPage.closeSignInPopUp();

        WaitHelps.getWait().waitUntilElementToBeVisible(imagePage.likes);
        Assertions.assertThat(imagePage.likes.isDisplayed())
                .as("hhhh")
                .isTrue();

        navigateBack(driver);
        searchPage.switchToIframe(driver);
        WaitHelps.getWait().waitUntilElementToBeVisible(searchPage.filterButton);
        Assertions.assertThat(searchPage.filterButton.isDisplayed())
                .as("hhhh")
                .isTrue();

        filterForm.clearAllFilters();

        String clickedImgId =  imgWrapper.clickOnThePlusBadge();

        WaitHelps.getWait().waitUntilElementToBeVisible(signInPage.signInTitle);

        Assertions.assertThat(signInPage.signInTitle.isDisplayed())
                .as("hhhh")
                .isTrue();
        signInPage.closeSignInPopUp();

        Assertions.assertThat(driver.getCurrentUrl())
                .as("Verify that the current URL contains the extracted imageId")
                .contains(clickedImgId);

    }
}
