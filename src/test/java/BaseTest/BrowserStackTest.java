package BaseTest;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import com.codeborne.selenide.Selenide;
import ge.tbc.testautomation.data.Constants;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.yaml.snakeyaml.Yaml;


public class BrowserStackTest {
    public RemoteWebDriver driver;
    public static String userName, accessKey;
    public static Map<String, Object> browserStackYamlMap;
    public static final String USER_DIR = "user.dir";

    public BrowserStackTest() {
        File file = new File(getUserDir() + "/browserstack.yml");
        this.browserStackYamlMap = convertYamlFileToMap(file, new HashMap<>());
        userName = System.getenv("BROWSERSTACK_USERNAME") != null ? System.getenv("BROWSERSTACK_USERNAME") : (String) browserStackYamlMap.get("userName");
        accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY") != null ? System.getenv("BROWSERSTACK_ACCESS_KEY") : (String) browserStackYamlMap.get("accessKey");
    }


    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "os", "osVersion"})
    public void setUp(@Optional("Chrome") String browser, @Optional("Windows") String os,  @Optional("11")String osVersion) throws Exception {

        MutableCapabilities capabilities = new MutableCapabilities();

        // Set browser-specific capabilities
        capabilities.setCapability("browserName", browser);

        // BrowserStack-specific options
        HashMap<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("os", os);
        bstackOptions.put("osVersion", osVersion);
        bstackOptions.put("source", "selenide:sample-master:v1.2");

        capabilities.setCapability("bstack:options", bstackOptions);

        // Remote WebDriver initialization
        driver = new RemoteWebDriver(
                new URL(String.format("https://%s:%s@hub-cloud.browserstack.com/wd/hub", userName, accessKey)),
                capabilities
        );

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverRunner.setWebDriver(driver);
        Selenide.open(Constants.SAUCE_DEMO_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverRunner.getWebDriver().quit();
    }

    private String getUserDir() {
        return System.getProperty(USER_DIR);
    }

    private Map<String, Object> convertYamlFileToMap(File yamlFile, Map<String, Object> map) {
        try {
            InputStream inputStream = Files.newInputStream(yamlFile.toPath());
            Yaml yaml = new Yaml();
            Map<String, Object> config = yaml.load(inputStream);
            map.putAll(config);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Malformed browserstack.yml file - %s.", e));
        }
        return map;
    }
}