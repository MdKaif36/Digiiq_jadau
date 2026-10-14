package Tests;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.Homepage;
import Pages.Loginpage;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;

public class LoginTest extends BaseTest {

    Homepage homepage = new Homepage();
    Loginpage loginpage = new Loginpage();

    @Test
    public void test() {
        // check homepage open
        Assert.assertTrue(homepage.state().waitForDisplayed(), "Homepage open");
        homepage.clickUserIcon();
        // login page open
        Assert.assertTrue(loginpage.state().waitForDisplayed(), "Login page open");
        // check country code shown
        Assert.assertTrue(loginpage.countryCode(), "Country code is displayed");
        // check xicon is clickable or not
        Assert.assertTrue(loginpage.xiconClickable(), "X icon is clickable");

        // loginpage.clickOutSidePopup();
        // Assert.assertTrue(loginpage.popup(), "Popup is not visible after outsideclick");

        // check mobile number placeholder
        Assert.assertEquals(loginpage.mobileNumberPlaceholder("Enter mobile number"), "Enter mobile number");
        // check sign in enable using "Enter"
        Assert.assertTrue(loginpage.signinByPressEnter(),"sign in is working on press Enter");



    }
}
