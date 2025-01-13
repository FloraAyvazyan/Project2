package ge.tbc.testautomation.pages.Swoop;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.data.Constants;
import static com.codeborne.selenide.Selenide.*;

public class ValidPage {
    public SelenideElement
            baseTitle = $x(String.format("//h3[text() = '\"%s\"']", Constants.validKeyword));

    public ElementsCollection allOffersFromValidPage =
            $$x("//a[contains(@class, 'gap-3')]");

}

