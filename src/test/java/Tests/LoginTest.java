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
    public void test1() {
        Assert.assertTrue(homepage.state().waitForDisplayed(), "Homepage open");
        homepage.clickUserIcon();
        Assert.assertTrue(loginpage.state().waitForDisplayed(), "Login page open");
        Assert.assertTrue(loginpage.countryCode(), "Country code is displayed");
        Assert.assertTrue(loginpage.xiconClickable(), "X icon is clickable");
        // loginpage.clickOutSidePopup();
        // Assert.assertTrue(loginpage.popup(), "Popup is not visible after outside
        // click");
        Assert.assertEquals(loginpage.mobileNumberPlaceholder("Enter mobile number"), "Enter mobile number");
        Assert.assertTrue(loginpage.signinClickOnEnter(),"sign in is working on press Enter");


    }
}
