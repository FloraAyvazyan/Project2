package ge.tbc.testautomation.steps.Swoop;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.pages.Swoop.MountainResortsPage;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.appear;


public class MountainResortsSteps extends MountainResortsPage {
    List<String> firstOfferTexts = new ArrayList<>();
    List<String> SecondOfferTexts = new ArrayList<>();

    @Step("Wait until the Mountain Resorts page reloads")
    public MountainResortsSteps waitUntilMountainPageReload() {
        mountainTitle.should(appear);
        return this;
    }

    @Step("Click on the next arrow to navigate to the next page")
    public MountainResortsSteps clickOnNextArrow() {
        nextArrowButton.click();
        return this;
    }

    @Step("Click on the previus arrow to navigate to the previus page")
    public MountainResortsSteps clickOnPrevArrow(){
        prevArrowButton.click();
        return this;
    }

    @Step("Get texts of offers on the first page")
    public MountainResortsSteps getFirstPageOffersTexts() {
        waitUntilMountainPageReload();
        System.out.println("Offers size: " + allOffersInOnePage.size());
        for (SelenideElement offer : allOffersInOnePage) {
            offer.should(appear);
            offer.shouldBe(Condition.visible, Duration.ofSeconds(15)).scrollTo().hover();
            String offerText = offer.getText();
            firstOfferTexts.add(offerText);
        }
        System.out.println(firstOfferTexts);
        return this;
    }


    @Step("Validate page navigation to the next page")
    public MountainResortsSteps validatePageNavigationToNext() {
        String backgroundColor = secondPaginationButton.getCssValue("background-color");
        Assert.assertTrue(
                backgroundColor.equals("rgba(103, 172, 61, 1)") || backgroundColor.equals("rgb(103, 172, 61)"),
                "Unexpected background color: " + backgroundColor
        ); //აქ FaiFoxze სხვაა opacity და ადანარჩენ ბრაუზერებზე სხვა ამიტომ ასე მაქვს ვალიდაცია
        return this;
    }

    @Step("Get texts of offers on the second page")
    public MountainResortsSteps getSecondPageOffersTexts() {
        System.out.println("Offers size: " + allOffersInOnePage.size());
        for (SelenideElement offer : allOffersInOnePage) {
            offer.should(appear);
            offer.shouldBe(Condition.visible, Duration.ofSeconds(15)).scrollTo().hover();
            String offerText = offer.getText();
            SecondOfferTexts.add(offerText);
        }
        System.out.println(SecondOfferTexts);
        return this;
    }

    @Step("Validate that results on the second page differ from the first page")
    public MountainResortsSteps validateDifferResults() {
        Assert.assertNotEquals(firstOfferTexts, SecondOfferTexts);
        return this;
    }

    @Step("Scroll through pagination buttons")
    public MountainResortsSteps paginationButtonsScroll() {
        for (int i = 0; i < paginationButtons.size(); i++) {
            paginationButtons.get(i).scrollTo();

        }
        return this;
    }

    @Step("Validate pagination buttons' click and background color")
    public MountainResortsSteps validatePaginationButtons() {
        for (int i = 0; i < paginationButtons.size(); i++) {
            paginationButtons.get(i).click();
            String backgroundColor = paginationButtons.get(i).getCssValue("background-color");
            Assert.assertTrue(
                    backgroundColor.equals("rgba(103, 172, 61, 1)") || backgroundColor.equals("rgb(103, 172, 61)"),
                    "Unexpected background color: " + backgroundColor
            );
        }
        return this;
    }

    @Step("Click on the first offer")
    public MountainResortsSteps clickOnFirstOffer() {
        allOffersInOnePage.get(0).click();
        return this;
    }


}
