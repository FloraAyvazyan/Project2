package ge.tbc.testautomation.pages.Swoop;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class SwoopBasePage {
    public SelenideElement
            input = $x("//input[@placeholder = 'მოძებნე კომპანია ან შეთავაზება']"),
            searchButton = $x("//button[contains(@class, 'top-2.5')]"),
            specialOffersTitle = $x("//h3[text() = 'სპეციალური შეთავაზებები']"),
            specialOffersTitleEnglish = $x("//h3[text() = 'Special Offers']"),
            categoriesXPath = $x("//p[text()='კატეგორიები' and contains(@class, 'text-md')]"),
            restButton = $x("//h4[text() = 'დასვენება']"),
            mountainResorts = $x("//h4[text() = 'მთის კურორტები']"),
            agreeButton = $x("//p[text() = 'ვეთანხმები']"),
            changeLanguageButton = $("#headlessui-menu-button-\\:r0\\:"),
            changeGeorgian = $x("//p[text() = 'Georgian']"),
            changeEnglish = $x("//p[text() = 'English']"),
            eatAndDrinkGeorgian = $x("//p[text() = 'კვება']"),
            htmlElement = $("html"),
            bodyElement = $("body");


}