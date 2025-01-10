package Saucedemo;

import BaseTest.BrowserStackTest;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.steps.dataBaseSteps.DataBaseSteps;
import ge.tbc.testautomation.steps.Saucedemo.SauceDemoBasePageSteps;
import ge.tbc.testautomation.steps.Saucedemo.StandartUserPageSteps;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.open;


@Feature("Login Functionality")
@Test
public class LoginTestBrowserStack extends BrowserStackTest {
    SauceDemoBasePageSteps sauceDemoBasePageSteps = new SauceDemoBasePageSteps();
    DataBaseSteps dataBaseSteps = new DataBaseSteps();
    StandartUserPageSteps standartUserPageSteps = new StandartUserPageSteps();
    String usernameForStandardUser = dataBaseSteps.getUserNameFromDatabase(Constants.STANDARD_USER);
    String passwordForStandardUser = dataBaseSteps.getPasswordFromDatabase(Constants.STANDARD_USER);

    //1) successfulLoginTest:
    //- Go to https://saucedemo.com
    //- Select standard_user credentials from your database.
    //- Login with this user.
    //- Validate that all images on the landing page are loaded.
    @Test(priority = 1)
    @Story("Successful Login")
    @Description("This test verifies that a standard user can successfully log in to the SauceDemo application. " +
            "The test fetches user credentials from the database, performs login, and validates that all images " +
            "on the landing page are loaded properly.")
    @Severity(SeverityLevel.CRITICAL)
    public void successfulLoginTest(){
        open(Constants.SAUCE_DEMO_URL);

        sauceDemoBasePageSteps
                .setUserName(usernameForStandardUser)
                .setPassword(passwordForStandardUser)
                .clickLogin();
        standartUserPageSteps
                .waitPageToReload()
                .validateImagesAreLoaded();

    }


    //2) bannedUserLoginTest:
    //- Go to https://saucedemo.com
    //- Select locked_out_user from your database.
    //- Login with this user.
    //- Validate that 'Epic sadface: Sorry, this user has been locked out.' message appears.
    //- Validate that the red X icon also is visible.
    @Test(priority = 2)
    @Story("Banned User Login")
    @Description("This test verifies that a locked-out user cannot log in to the SauceDemo application. " +
            "The test fetches locked-out user credentials from the database, attempts login, and validates " +
            "that the correct error message and error icons are displayed.")
    @Severity(SeverityLevel.CRITICAL)
    public void bannedUserLoginTest(){
        open(Constants.SAUCE_DEMO_URL);
        String usernameForLockedUser = dataBaseSteps.getUserNameFromDatabase(Constants.LOCKED_OUT_USER);
        String passwordForLockedUser = dataBaseSteps.getPasswordFromDatabase(Constants.LOCKED_OUT_USER);
        sauceDemoBasePageSteps
                .setUserName(usernameForLockedUser)
                .setPassword(passwordForLockedUser)
                .clickLogin()
                .validateErrorMessage()
                .validateErrorIcons();

    }



    //3) logOutTest:
    //- Go to https://saucedemo.com
    //- Select standard_user from your database.
    //- Login with this user.
    //- Log out.
    //- Validate that Username and Password inputs are empty.

    @Test( priority = 3)
    @Story("Log Out Functionality")
    @Description("This test verifies the logout functionality of the SauceDemo application. " +
            "It logs in with a standard user, performs logout, and ensures that the Username " +
            "and Password fields are cleared after logout.")
    @Severity(SeverityLevel.NORMAL)
    public void logOutTest(){
        open(Constants.SAUCE_DEMO_URL);
        sauceDemoBasePageSteps
                .setUserName(usernameForStandardUser)
                .setPassword(passwordForStandardUser)
                .clickLogin();
        standartUserPageSteps
                .menuButtonClick()
                .logOutButtonShouldAppear()
                .LogoutClick();
        sauceDemoBasePageSteps
                .validateUsernameInputIsEmpty()
                .validatePasswordInputIsEmpty();


    }

}