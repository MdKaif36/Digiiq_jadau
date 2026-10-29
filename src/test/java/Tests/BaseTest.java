package Tests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import aquality.selenium.browser.AqualityServices;

import java.time.Duration;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class BaseTest {

    /*static {
        System.setProperty("browserName", "edge");
        System.setProperty("webdriver.edge.driver", "C:\\driver\\msedgedriver.exe");
    }*/

    public final String url = "https://sfcc-staging.jadau.com/";

    WebDriverWait wait = new WebDriverWait(
            AqualityServices.getBrowser().getDriver(),
            Duration.ofSeconds(1)
    );

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
