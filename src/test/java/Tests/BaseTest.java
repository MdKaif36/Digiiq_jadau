package Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import aquality.selenium.browser.AqualityServices;
import static aquality.selenium.browser.AqualityServices.getBrowser;

public class BaseTest {

    public final String url = "https://sfcc-staging.jadau.com/";

    @BeforeMethod
    public void setup() {
        getBrowser().maximize();
        getBrowser().goTo(url);
    }

    @AfterMethod
    public void teardown() {
        if (AqualityServices.isBrowserStarted()) {
            getBrowser().quit();
        }
    }
}
