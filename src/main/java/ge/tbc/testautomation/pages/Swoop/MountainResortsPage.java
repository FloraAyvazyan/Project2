package ge.tbc.testautomation.pages.Swoop;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class MountainResortsPage {
    public SelenideElement
            nextArrowButton = $x("//img[@alt='right arrow']/.."),
            prevArrowButton = $x("//img[@alt='left arrow']/.."),
            mountainTitle = $x("//h3[text()= 'მთის კურორტები']"),
            secondPaginationButton = $x("//div[text() = '2']");
    public ElementsCollection
            allOffersInOnePage = $$x("//a[contains(@class ,'gap-3')]"),
            paginationButtons = $$x("//div[contains(@class, 'font-tbcx-medium')]");

}