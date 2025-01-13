package ge.tbc.testautomation.steps.Swoop;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import ge.tbc.testautomation.pages.Swoop.FirstOfferPage;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.appear;

public class FirstOfferSteps extends FirstOfferPage {


    @Step("Scroll to the location section")
    public FirstOfferSteps locationScroll() {
        Selenide.executeJavaScript("window.scrollBy(0, 50);");
        return this;
    }


    @Step("Location Button should appear")
    public FirstOfferSteps locationButtonShouldAppear(){
        locationButton.should(appear);
        return this;
    }

    @Step("Click on the location button")
    public FirstOfferSteps locationClick() {
        locationButton.click();
        return this;
    }

    @Step("Map Element should be visible")
    public FirstOfferSteps mapElementShouldBeVisible(){
        mapElement.shouldBe(Condition.visible);
        return this;

    }

    @Step("Validate that the map is visible after clicking the location button")
    public FirstOfferSteps validateWindowScrolledToMap() {
        Assert.assertTrue(mapElement.isDisplayed(), "Map element is not visible!");
        return this;
    }
}
