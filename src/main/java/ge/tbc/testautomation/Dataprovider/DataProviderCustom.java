package ge.tbc.testautomation.Dataprovider;

import org.testng.annotations.DataProvider;

import static ge.tbc.testautomation.data.Constants.invalidKeyword;
import static ge.tbc.testautomation.data.Constants.validKeyword;

public class DataProviderCustom {

    @DataProvider(name = "searchKeywords")
    public static Object[][] provideSearchKeywords() {
        return new Object[][]{
                {validKeyword},   // Valid keyword
                {invalidKeyword}  // Invalid keyword
        };
    }
}