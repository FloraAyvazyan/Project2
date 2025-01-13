package ge.tbc.testautomation.pages.Swoop;

import com.codeborne.selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class InvalidPage {
    public SelenideElement
            notFoundMessage = $x("//h2[contains(@class, 'text-2lg')]");
}
