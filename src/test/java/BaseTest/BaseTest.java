package BaseTest;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import ge.tbc.testautomation.util.screenshot.ModdedAllureSelenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

public class BaseTest {
    @BeforeSuite(groups = {"SauceDemoLogin","SwoopRegression"})
    public void initialSetup(){
        Configuration.timeout = 12000;
        Configuration.reopenBrowserOnFail = true;
        Configuration.screenshots = true;
        Configuration.fileDownload = FileDownloadMode.HTTPGET;
        Configuration.pageLoadTimeout = 12000;
    }

    @BeforeTest(groups = {"SauceDemoLogin","SwoopRegression"}, alwaysRun = true)
    @Parameters({"browserType"})
    public void setUp(@Optional("chrome") String browserType) {
        SelenideLogger.addListener("AllureSelenide", new ModdedAllureSelenide());
        switch (browserType.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                WebDriverRunner.setWebDriver(new ChromeDriver());
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                WebDriverRunner.setWebDriver(new EdgeDriver());
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
        Configuration.timeout = 10000;
        WebDriverRunner.getWebDriver().manage().window().maximize();
        Configuration.assertionMode = AssertionMode.STRICT;
        Configuration.holdBrowserOpen = true;
    }

    @AfterClass(groups = {"SauceDemoLogin","SwoopRegression"})
    public void tearDown(){
        Selenide.closeWindow();
    }
}

