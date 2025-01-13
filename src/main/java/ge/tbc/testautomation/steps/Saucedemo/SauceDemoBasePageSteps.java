package ge.tbc.testautomation.steps.Saucedemo;


import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.pages.Saucedemo.SauceDemoBasePage;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static org.testng.AssertJUnit.assertTrue;


public class SauceDemoBasePageSteps extends SauceDemoBasePage {

    @Step("Set username: {0}")
    public SauceDemoBasePageSteps setUserName(String username) {
        userNameInput.setValue(username);
        return this;
    }

    @Step("Set password")
    public SauceDemoBasePageSteps setPassword(String password){
        passwordInput.setValue(password);
        return this;
    }

    @Step("Click on login button")
    public SauceDemoBasePageSteps clickLogin(){
        loginButton.click();
        return this;

    }

    @Step("Validate error message is displayed")
    public SauceDemoBasePageSteps validateErrorMessage(){
        Assert.assertTrue(errorMessage.isDisplayed());
        return this;
    }

    @Step("Validate error icons are displayed")
    public SauceDemoBasePageSteps validateErrorIcons() {
        assertTrue("Expected to find at least two error icons.", errorIcons.size() == 2);
        for (SelenideElement icon : errorIcons) {
            Assert.assertTrue(icon.isDisplayed());
            icon.shouldBe(visible);
        }
        return this;
    }

    @Step("Validate username input is empty")
    public SauceDemoBasePageSteps validateUsernameInputIsEmpty(){
        Assert.assertTrue(userNameInput.getValue().isEmpty(), "Username input should be empty.");
        return this;
    }

    @Step("Validate password input is empty")
    public SauceDemoBasePageSteps validatePasswordInputIsEmpty(){
        Assert.assertTrue(passwordInput.getValue().isEmpty(), "Password input should be empty.");
        return this;

    }


}

