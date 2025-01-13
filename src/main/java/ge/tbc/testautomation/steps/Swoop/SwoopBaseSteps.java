package ge.tbc.testautomation.steps.Swoop;

import com.codeborne.selenide.Condition;
import com.github.pemistahl.lingua.api.Language;
import com.github.pemistahl.lingua.api.LanguageDetector;
import com.github.pemistahl.lingua.api.LanguageDetectorBuilder;
import ge.tbc.testautomation.pages.Swoop.SwoopBasePage;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.appear;


public class SwoopBaseSteps extends SwoopBasePage {
    LanguageDetector detector = LanguageDetectorBuilder.fromAllLanguages().build();
    String TitleEnglishVersion;
    String TitleFromGeorgianVersion;

    //---------------------------------------------------------------------------------------------------
    @Step("Click on the agree button")
    public SwoopBaseSteps agreeButtonClick() {
        agreeButton.click();
        return this;
    }

    //---------------------------------------------------------------------------------------------------
    @Step("Click on the language change button")
    public SwoopBaseSteps changeLanguageButtonClick() {
        changeLanguageButton.click();
        return this;
    }

    @Step("Change language to English")
    public SwoopBaseSteps changeToEnglish() {
        changeEnglish.click();
        return this;
    }

    @Step("Change language to Georgian")
    public SwoopBaseSteps changeToGeorgian() {
        changeGeorgian.click();
        return this;
    }

    @Step("Search with the valid keyword: {0}")
    public SwoopBaseSteps searchWithValidKeyword(String keyword) {
        input.setValue(keyword);
        return this;
    }

    @Step("Click the search button")
    public SwoopBaseSteps clickSearch() {
        searchButton.click();
        return this;
    }


    @Step("Wait until the base page reloads and store the title in Georgian version")
    public SwoopBaseSteps waitBasePageToReload() {
        TitleFromGeorgianVersion = specialOffersTitle.getText();
        specialOffersTitle.should(appear);
        return this;
    }

    @Step("Click on categories button")
    public SwoopBaseSteps clickOnCategories() {
        categoriesXPath.click();
        return this;
    }

    @Step("Hover on the Rest button")
    public SwoopBaseSteps hoverOnRestButton() {
        restButton.hover();
        return this;
    }

    @Step("Click on the Mountain Resorts button")
    public SwoopBaseSteps clickOnMountainResortsButton() {
        mountainResorts.click();
        return this;
    }

    @Step("Click on the Eat and Drink section")
    public SwoopBaseSteps eatAndDrinkClick() {
        eatAndDrinkGeorgian.click();
        return this;
    }



    @Step("Wait until the English version of the base page reloads")
    public SwoopBaseSteps waitEnglishBasePageToReload() {
        TitleEnglishVersion = specialOffersTitleEnglish.getText();
        specialOffersTitleEnglish.should(appear);
        return this;
    }

    @Step("Validate that the language is different between the Georgian and English versions")
    public SwoopBaseSteps validateTheLanguageIsDifferent() {
        Assert.assertNotEquals(TitleEnglishVersion, TitleFromGeorgianVersion);
        return this;
    }

    @Step("Validate the HTML language attribute is set to English")
    public SwoopBaseSteps validateHtml() {
        htmlElement.shouldHave(Condition.attribute("lang", "en"));
        return this;
    }

    @Step("Test the actual text and detect the language")
    public SwoopBaseSteps actualTextTest() {
        String actualText = bodyElement.getText();
        Language detectedLanguage = detector.detectLanguageOf(actualText);

        Assert.assertEquals(detectedLanguage, Language.ENGLISH, "Language detection failed!");
        if (detectedLanguage == Language.ENGLISH) {
            System.out.println("Language changed to English successfully!");
        } else {
            throw new AssertionError("Language detection failed! Detected: " + detectedLanguage);
        }
        return this;
    }




}
