package tests;

import api.search.FilterOptions;
import dataprovider.ResolutionsDataProvider;
import helpers.WaitHelps;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.image.ImagePage;
import pages.search.SearchPage;
import pages.search.forms.AcceptCookiesForm;
import pages.search.forms.FilterForm;
import pages.search.forms.GridWrapperForm;
import pages.sigin.SignInPage;


import static constants.common.VerificationMessages.*;
import static helpers.NavigationHelper.navigateBack;
import static helpers.NavigationHelper.refreshPage;

@Log4j2
public class SearchPageTest extends BaseTest {
    private SearchPage searchPage;
    private FilterForm filterForm;

    @BeforeMethod
    public void setup() {
        log.info("Initializing test setup...");
        AcceptCookiesForm acceptCookiesForm = new AcceptCookiesForm(driver);
        acceptCookiesForm.acceptAllCookies();
        searchPage = new SearchPage(driver);
        filterForm = new FilterForm(driver);
        log.info("Test setup completed.");
    }

    @Test(dataProvider = "resolutionProvider", dataProviderClass = ResolutionsDataProvider.class)
    public void verifyFilterFunctionalityAtDifferentResolutions(int width, int height) {
        log.info("Test started with resolution: {}x{}", width, height);
        setResolution(width, height);

        log.info("Switching to the iframe.");
        searchPage.switchToIframe(driver);


        WaitHelps.getWait().waitUntilElementToBeVisible(searchPage.filterButton);
        log.info("Verifying that the filter button is visible on the search page.");
        Assertions.assertThat(searchPage.filterButton.isDisplayed())
                .as(FILTER_BUTTON_VISIBLE)
                .isTrue();

        log.info("Closing the Filter form.");
        searchPage.closeFilter();

        log.info("Verifying that the personal license filter is hidden after closing the filter.");
        Assertions.assertThat(filterForm.personalLicense.isDisplayed())
                .as(PERSONAL_LICENSE_FILTER_HIDDEN)
                .isFalse();

        log.info("Opening the Filter form.");
        searchPage.openFilter();

        log.info("Selecting the personal license filter: {}.", FilterOptions.License.PERSONAL);
        filterForm.selectLicense(FilterOptions.License.PERSONAL);

        log.info("Closing the Filter form.");
        searchPage.closeFilter();

        GridWrapperForm imgWrapper = new GridWrapperForm(driver);
        WaitHelps.getWait().waitUntilElementToBeVisible(imgWrapper.getFirstImage());

        log.info("Verifying that no badges are present after applying the personal license filter.");
        Assertions.assertThat(imgWrapper.badges)
                .as(BADGES_AFTER_FILTER)
                .hasSizeLessThanOrEqualTo(0);

        log.info("Clicking on the first image.");
        imgWrapper.clickOnTheFirstIMG();

        ImagePage imagePage = new ImagePage(driver);

        log.info("Refreshing the page and waiting for the edit button to become visible.");
        refreshPage(driver);
        WaitHelps.getWait().waitUntilElementToBeVisible(imagePage.editImgButton);

        log.info("Verifying that the edit button is visible on the image page.");
        Assertions.assertThat(imagePage.editImgButton.isDisplayed())
                .as(String.format(ELEMENT_VISIBLE, "edit button"))
                .isTrue();

        log.info("Verifying that the like button is visible on the image page.");
        Assertions.assertThat(imagePage.likes.isDisplayed())
                .as(String.format(ELEMENT_VISIBLE, "like button"))
                .isTrue();

        log.info("Verifying that the save button is visible on the image page.");
        Assertions.assertThat(imagePage.saveButton.isDisplayed())
                .as(String.format(ELEMENT_VISIBLE, "save button"))
                .isTrue();

        log.info("Liking the image.");
        imagePage.likeImage();

        SignInPage signInPage = new SignInPage(driver);
        log.info("Waiting for the sign-in page to appear.");
        WaitHelps.getWait().waitUntilElementToBeVisible(signInPage.signInTitle);

        log.info("Verifying that the sign-in page is displayed after clicking like.");
        Assertions.assertThat(signInPage.signInTitle.isDisplayed())
                .as(SIGN_IN_PAGE_VISIBLE)
                .isTrue();

        log.info("Closing the Sign-In pop-up.");
        signInPage.closeSignInPopUp();

        log.info("Waiting for the likes button to become visible again.");
        WaitHelps.getWait().waitUntilElementToBeVisible(imagePage.likes);

        log.info("Verifying that the likes button is visible after closing the sign-in pop-up.");
        Assertions.assertThat(imagePage.likes.isDisplayed())
                .as(LIKES_BUTTON_VISIBLE)
                .isTrue();

        log.info("Navigating back to the search page.");
        navigateBack(driver);
        log.info("Switching to the iframe.");
        searchPage.switchToIframe(driver);

        log.info("Waiting for the filter button to become visible.");
        WaitHelps.getWait().waitUntilElementToBeVisible(searchPage.filterButton);

        log.info("Verifying that the filter button is displayed after navigating back to the search page.");
        Assertions.assertThat(searchPage.filterButton.isDisplayed())
                .as(FILTER_BUTTON_VISIBLE)
                .isTrue();

        log.info("Opening the filter.");
        searchPage.openFilter();

        log.info("Clearing all filters.");
        filterForm.clearAllFilters(driver);

        log.info("Closing the filter.");
        searchPage.closeFilter();

        log.info("Waiting for the sign-in page to appear.");
        WaitHelps.getWait().waitUntilElementToBeClickable(imgWrapper.firstBadge);

        log.info("Clicking on the plus badge and retrieving the image ID.");
        String clickedImgId = imgWrapper.clickOnThePlusBadge();
        log.info("Clicked on the plus badge with image ID: {}", clickedImgId);

        refreshPage(driver);

        log.info("Waiting for the sign-in page to appear.");
        WaitHelps.getWait().waitUntilElementToBeClickable(signInPage.continueWithEmailButton);

        log.info("Verifying that the sign-in page is displayed after clicking the plus badge.");
        Assertions.assertThat(signInPage.signInTitle.isDisplayed())
                .as(SIGN_IN_PAGE_VISIBLE)
                .isTrue();

        log.info("Closing the Sign-In pop-up.");
        signInPage.closeSignInPopUp();

        log.info("Verifying that the current URL contains the image ID: {}", clickedImgId);
        Assertions.assertThat(driver.getCurrentUrl())
                .as(String.format(URL_CONTAINS_CLICKED_IMG, clickedImgId))
                .contains(clickedImgId);
    }

}
