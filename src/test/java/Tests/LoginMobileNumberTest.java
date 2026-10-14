package Tests;

import Pages.Homepage;
import Pages.Loginpage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginMobileNumberTest extends  BaseTest{

    Homepage homepage = new Homepage();
    Loginpage loginpage = new Loginpage();

    @Test
    public void test(){
        homepage.clickUserIcon();
        // enter empty mobile number
        Assert.assertTrue(loginpage.enterEmptyMobileNumber(),"Warning text give when enter empty number");

        // enter the special character as a mob. no
        Assert.assertTrue(loginpage.enterSpecialChracterMobileNumber(),"Warning text given when enter Special Chracter mob");

        // enter the 0-9 digit mobile number
        Assert.assertTrue(loginpage.MobileNumber0_9(),"browser move opt pop up on valid number");

        // enter more than 10 digit mobile number
        //Assert.assertTrue(loginpage.InvalidMobileNumber(),"browser move opt pop up on valid number");


        // enter unregister mobile number
        Assert.assertTrue(loginpage.unregisterMob(),"warning when enter unregister mob. num.");

        // enter valid mobile number
        Assert.assertTrue(loginpage.enterValidMobileNumber(),"browser move opt pop up on valid number");



    }
}
