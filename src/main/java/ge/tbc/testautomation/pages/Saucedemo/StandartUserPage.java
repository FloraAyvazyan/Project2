package ge.tbc.testautomation.pages.Saucedemo;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class StandartUserPage {
    public ElementsCollection
            allOffers = $$x("//div[@class = 'inventory_item']");

    public SelenideElement
            menuButton= $("#react-burger-menu-btn"),
            logOutButton = $("#logout_sidebar_link"),
            productsButton = $x("//span[text() = 'Products']");



}