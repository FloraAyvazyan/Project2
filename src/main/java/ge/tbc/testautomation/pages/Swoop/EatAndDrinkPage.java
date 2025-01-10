package ge.tbc.testautomation.pages.Swoop;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Selenide.*;

public class EatAndDrinkPage {
    public ElementsCollection
            NumberOfGuestsGeorgian = $$x("//input[@name = 'radio-სტუმრების რაოდენობა']"),
            guestsText = $$x("//input[@name = 'radio-სტუმრების რაოდენობა']/following-sibling::span"),
            offersDescription = $$x("//h4[contains(@class, 'line-clamp-2')]");
    public SelenideElement
            pageTitleInGeorgian = $x("//h3[text() = 'კვება']"),
            deleteAllButton = $x("//p[text() = 'ყველას წაშლა']");


}
