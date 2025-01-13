package ge.tbc.testautomation.steps.Swoop;


import com.codeborne.selenide.Selenide;
import ge.tbc.testautomation.pages.Swoop.EatAndDrinkPage;
import ge.tbc.testautomation.util.helperFunction.Util;
import io.qameta.allure.Step;
import java.util.List;
import static com.codeborne.selenide.Condition.appear;
import static ge.tbc.testautomation.util.helperFunction.Util.validateNumbersInRangeUtil;

public class EatAndDrinkSteps extends EatAndDrinkPage {
    private int min;
    private int max;


    @Step("Wait until the 'Eat and Drink' page is fully loaded")
    public EatAndDrinkSteps waitUntilEatAndDrinkPageLoads() {
        pageTitleInGeorgian.should(appear);
        return this;
    }

    @Step("Scroll to the 'Guests' button")
    public EatAndDrinkSteps scrollToGuestsButton() {
        Selenide.executeJavaScript("window.scrollBy(0, 150);");
        return this;

    }

    @Step("Click the 'Number of Guests' button")
    public EatAndDrinkSteps clickNumberOfGuestsButton() {
        NumberOfGuestsGeorgian.get(0).click();
        return this;
    }

    @Step("Wait until the filtered page is fully loaded")
    public EatAndDrinkSteps waitUntilFilteredPageIsLoad() {
        deleteAllButton.should(appear);
        return this;
    }


    @Step("Extract the minimum and maximum number of guests from the offer description")
    public EatAndDrinkSteps getGuestsCount() {
        List<String> texts = guestsText.texts();
        String firstText = texts.get(0);
        int[] minMax = Util.extractMinMax(firstText);
        min = minMax[0];
        max = minMax[1];

        System.out.println("Min: " + min);
        System.out.println("Max: " + max);

        return this;
    }

    @Step("Validate that the numbers in the offer description are within the acceptable range")
    public EatAndDrinkSteps validateNumbersInRange() {
        validateNumbersInRangeUtil(offersDescription, min, max);
        return this;
    }


}
