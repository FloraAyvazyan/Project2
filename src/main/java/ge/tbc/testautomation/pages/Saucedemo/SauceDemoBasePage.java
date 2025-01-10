package ge.tbc.testautomation.pages.Saucedemo;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class SauceDemoBasePage {
    public SelenideElement
            userNameInput = $("#user-name"),
            passwordInput = $("#password"),
            loginButton = $("#login-button"),

    errorMessage = $(".error-button");

    public ElementsCollection
            errorIcons = $$x("//*[contains(@class, 'error_icon')]");

}
//im nnew change
