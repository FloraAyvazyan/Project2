package ge.tbc.testautomation.pages.Swoop;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class FirstOfferPage {
    public SelenideElement
            locationButton = $x("//p[text() = 'მდებარეობა']"),
            mapElement = $x("//div[contains(@class, 'leaflet-touch-zoom')]");
}

