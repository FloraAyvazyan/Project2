package ge.tbc.testautomation.steps.Swoop;

import ge.tbc.testautomation.pages.Swoop.InvalidPage;
import io.qameta.allure.Step;
import org.testng.Assert;


public class InvalidPageSteps extends InvalidPage {

    @Step("Validate that the 'No Results Found' message is displayed")
    public InvalidPageSteps validateNoResultsMessage() {
        Assert.assertEquals(notFoundMessage.getText(), "შეთავაზება არ მოიძებნა");
        return this;
    }



}
