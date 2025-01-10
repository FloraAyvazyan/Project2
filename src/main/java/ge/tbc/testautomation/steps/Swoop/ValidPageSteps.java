package ge.tbc.testautomation.steps.Swoop;

import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.pages.Swoop.ValidPage;
import io.qameta.allure.Step;
import org.testng.Assert;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.back;
import static org.testng.AssertJUnit.assertTrue;


public class ValidPageSteps extends ValidPage {
    //------------------------------------------------------------------------------------------------------
    @Step("Validate that the base title is found")
    public ValidPageSteps validateBaseTitleIsFound() {
        System.out.println(baseTitle.getText() + "is found");
        Assert.assertTrue(baseTitle.isEnabled());
        return this;
    }

    @Step("Navigate back to the previous page")
    public ValidPageSteps navigateBack() {
        back();
        return this;
    }
    //-----------------------------------------------------------------------------------------------------------

    @Step("Wait until the page has loaded")
    public ValidPageSteps waitUntilPageLoad() {
        baseTitle.should(appear);
        return this;
    }

    @Step("Validate that the results match the query: {0}")
    public ValidPageSteps ValidateResultsMatchTheQuery(String keyword) {
        for (SelenideElement offer : allOffersFromValidPage) {
            offer.should(appear);
            System.out.println(offer.getText());
            assertTrue("Offer does not contain the keyword", offer.getText().contains(keyword));
        }
        return this;
    }

}