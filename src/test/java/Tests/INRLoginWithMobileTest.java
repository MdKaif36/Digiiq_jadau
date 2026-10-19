package Tests;

import Pages.Homepage;
import Pages.INRLoginpage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class INRLoginWithMobileTest extends  BaseTest{

    Homepage homepage = new Homepage();
    INRLoginpage loginpage = new INRLoginpage();

    @Test
    public void test(){

        // check homepage open
        Assert.assertTrue(homepage.state().waitForDisplayed(), "Homepage open");
        homepage.clickUserIcon();
        // login page open
        Assert.assertTrue(loginpage.state().waitForDisplayed(), "Login page open");
        // check country code shown
        Assert.assertTrue(loginpage.countryCode(), "Country code is displayed");
        // check xicon is clickable or not
        Assert.assertTrue(loginpage.xiconClickable(), "X icon is clickable");
        // check mobile number placeholder
        Assert.assertEquals(loginpage.mobileNumberPlaceholder("Enter mobile number"), "Enter mobile number");

        // enter empty mobile number and use "Enter" key for click btn
        Assert.assertTrue(loginpage.enterEmptyMobileNumber(),"Warning text give when enter empty number");

        // enter the special character as a mob. no
        Assert.assertTrue(loginpage.enterSpecialChracterMobileNumber(),"Warning text given when enter Special Chracter mob");

        // enter the 0-9 digit mobile number
        Assert.assertTrue(loginpage.MobileNumber0_9(),"browser move opt pop up on valid number");

        // enter more than 10 digit mobile number
        // Assert.assertTrue(loginpage.InvalidMobileNumber(),"browser move opt pop up on valid number");

        // enter unregister mobile number
        Assert.assertTrue(loginpage.unregisterMob(),"warning when enter unregister mob. num.");

        // enter valid mobile number
        Assert.assertTrue(loginpage.enterValidMobileNumber(),"browser move opt pop up on valid number");



    }
}
