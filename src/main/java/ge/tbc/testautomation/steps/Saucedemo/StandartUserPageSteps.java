package ge.tbc.testautomation.steps.Saucedemo;


import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.pages.Saucedemo.StandartUserPage;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.appear;

public class StandartUserPageSteps extends StandartUserPage {

    @Step("Validate that all images in offers are loaded")
    public StandartUserPageSteps validateImagesAreLoaded(){
        allOffers.forEach(offer -> {
            SelenideElement img = offer.$("a img");
            // System.out.println(img);
            img.should(appear);
            Assert.assertTrue(img.isDisplayed(),"Image is not displayed in offer" );
        });
        return this;
    }

    @Step("Click on menu button")
    public StandartUserPageSteps menuButtonClick(){
        menuButton.click();
        return this;
    }

    @Step("logout Button should appear")
    public StandartUserPageSteps logOutButtonShouldAppear(){
        logOutButton.should(appear);
        return this;

    }

    @Step("Click on logout button")
    public StandartUserPageSteps  LogoutClick(){
        logOutButton.click();
        return this;
    }

    @Step("Wait page to reload")
    public StandartUserPageSteps waitPageToReload(){
        productsButton.should(appear);
        return this;
    }
}
