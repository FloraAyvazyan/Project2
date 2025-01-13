package Swoop;

import BaseTest.BaseTest;
import ge.tbc.testautomation.Dataprovider.DataProviderCustom;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.steps.Swoop.*;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;

@Epic("Swoop.ge Functionality Tests")
@Test(groups = {"SwoopRegression"})
public class OfferTests extends BaseTest {
    SwoopBaseSteps swoopBaseSteps = new SwoopBaseSteps();
    ValidPageSteps validPageSteps = new ValidPageSteps();
    InvalidPageSteps invalidPageSteps = new InvalidPageSteps();
    MountainResortsSteps mountainResortsSteps = new MountainResortsSteps();
    FirstOfferSteps firstOfferSteps = new FirstOfferSteps();
    EatAndDrinkSteps eatAndDrinkSteps = new EatAndDrinkSteps();

    //Create class OfferTests in swoop package:
    //For each test in this homework define Severity & Priority
    //1) searchTest:
    //- Navigate to https://swoop.ge
    //- Perform a search with valid keywords.
    //- Validate that results match the query.
    //- Search with invalid or gibberish keywords.
    //- Ensure a "No Results Found" message appears.
//++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Test(dataProvider = "searchKeywords", dataProviderClass = DataProviderCustom.class, priority = 1)
    @Feature("Search Functionality")
    @Story("Search Functionality")
    @Description("Validates the search functionality on Swoop.ge. The test ensures results match the query for valid keywords " +
            "and displays a 'No Results Found' message for invalid or gibberish keywords.")
    @Severity(SeverityLevel.CRITICAL)
    public void searchTest2(String keyword) {
        open(Constants.SWOOP_URL);
        swoopBaseSteps
                .waitBasePageToReload()
                .searchWithValidKeyword(keyword)
                .clickSearch();
        if (keyword.equals(Constants.validKeyword)) {
            validPageSteps
                    .waitUntilPageLoad()
                    .validateBaseTitleIsFound()
                    .ValidateResultsMatchTheQuery(keyword);
        }else{
            invalidPageSteps
                    .validateNoResultsMessage();

        }

    }



    //2) paginationTest:
    //- Navigate to https://swoop.ge
    //- Go to "კატეგორიები".
    //- Hover on any category and choose any sub-category.
    //- Navigate to the second and third pages of results.
    //- Validate that results differ from the first page and match filters.+++++++
    //- Click "Next" and "Previous" buttons and ensure navigation is smooth.
    // ------------ სხვანაირად მაქვს აქ ვალიდაცია, ანუ ისედაც მქონდა გადავიდა ნექსთზე და ამოწმება რო ოფფერები მართა შეიცალა
    //და იგივე რო არ იყოს ეხლა ღილაკებიტ ყველა გვერდზე გადადის და ამოწმებს გადავიდა თუ არა
    //++++++++++++++++=
    @Test
    @Story("Pagination Testing")
    @Feature("Pagination")
    @Description("Tests the pagination functionality in the 'Categories' section. It validates navigation across pages, " +
            "ensures results differ between pages, and checks the 'Next' and 'Previous' buttons.")
    @Severity(SeverityLevel.NORMAL)
    public void paginationTest(){
        open(Constants.SWOOP_URL);
        swoopBaseSteps
                .clickOnCategories()
                .hoverOnRestButton()
                .clickOnMountainResortsButton();
        mountainResortsSteps
                .waitUntilMountainPageReload()
                .getFirstPageOffersTexts()
                .clickOnNextArrow()
                .waitUntilMountainPageReload()
                .validatePageNavigationToNext()
                .getSecondPageOffersTexts()
                .validateDifferResults()
                .clickOnPrevArrow()
                .waitUntilMountainPageReload()
                .paginationButtonsScroll()
                .validatePaginationButtons();


    }


    //3) offerLocationTest:
    //- Navigate to https://swoop.ge
    //- Go to "კატეგორიები"
    //- Hover on any category and choose any sub-category.
    //- Navigate to the first returned item and click to Location.
    //- Validate that window has scrolled to the Map.
    //+++++++++++++++++++++++++++++
    @Test(priority = 3)
    @Story("Offer Location Testing")
    @Feature("Offer Location")
    @Description("Verifies that the location button scrolls the page to the map on the offer's details page.")
    @Severity(SeverityLevel.MINOR)
    public void offerLocationTest(){
        open(Constants.SWOOP_URL);
        swoopBaseSteps
                .clickOnCategories()
                .hoverOnRestButton()
                .clickOnMountainResortsButton();
        mountainResortsSteps
                .clickOnFirstOffer();
        firstOfferSteps
                .locationScroll()
                .locationButtonShouldAppear()
                .locationClick()
                .mapElementShouldBeVisible()
                .validateWindowScrolledToMap();
    }


    //4) numberOfGuestsTest:
    //- Navigate to https://swoop.ge
    //- Go to any Eat&Drink. +++
    //- Filter with 'Number of Guests'.
    //- Validate with the offer description that the filter worked correctly.
    //+++++++++++++++++++++++++
    @Test(priority = 4)
    @Feature("Eat & Drink")
    @Story("Eat & Drink Functionality")
    @Description("Tests the 'Number of Guests' filter in the 'Eat & Drink' section. Validates that the filter is applied correctly " +
            "based on the offer descriptions.")
    @Severity(SeverityLevel.NORMAL)
    public void numberOfGuestsTest() {
        open(Constants.SWOOP_URL);
        swoopBaseSteps
                .eatAndDrinkClick();
        eatAndDrinkSteps
                .waitUntilEatAndDrinkPageLoads()
                .scrollToGuestsButton()
                .clickNumberOfGuestsButton()
                .waitUntilFilteredPageIsLoad()
                .getGuestsCount()
                .validateNumbersInRange();

    }





    //5) changeLanguageTest:
    //- Navigate to https://swoop.ge
    //- Switch between English and Georgian.
    //- Validate that UI text and labels update correctly.
    //+++++++++++++++
    @Test(priority = 5)
    @Feature("Language Change")
    @Story("Language Change")
    @Description("Validates that switching between English and Georgian updates the UI text and labels correctly.")
    @Severity(SeverityLevel.MINOR)
    public void changeLanguageTest(){
        open(Constants.SWOOP_URL);
        swoopBaseSteps
                .changeLanguageButtonClick()
                .changeToEnglish()
                .waitEnglishBasePageToReload()
                .changeLanguageButtonClick()
                .changeToGeorgian()
                .validateTheLanguageIsDifferent();

    }
    @Test(priority = 6)
    @Feature("Language Change")
    @Story("Language Change")
    @Description("Tests that switching languages updates the HTML content and UI text accurately.")
    @Severity(SeverityLevel.MINOR)
    public void changeLanguageTest2() {
        open(Constants.SWOOP_URL);
        swoopBaseSteps
                .changeLanguageButtonClick()
                .changeToEnglish()
                .validateHtml()
                .actualTextTest();

    }


}

