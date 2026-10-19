package Tests;

import Pages.ForeignLogInPage;
import Pages.ForeignRegisterPage;
import Pages.Homepage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ForeignRegisterTest extends BaseTest {

    Homepage homepage = new Homepage();
    ForeignLogInPage foreignLogInPage= new ForeignLogInPage();
    ForeignRegisterPage foreignRegisterPage = new ForeignRegisterPage();

    @Test
    public void test(){
        homepage.closePrivacyIcon();
        HashMap<String,String> countryMobileNo= new HashMap<>();
        countryMobileNo.put("USD","2107780187");
        countryMobileNo.put("EUR","634556677");
        countryMobileNo.put("AED","500000000");

        for(Map.Entry<String,String> entry: countryMobileNo.entrySet()){

            homepage.clickCurrencySelector(entry.getKey());
            homepage.clickUserIcon();
            foreignLogInPage.clickOnRegisterNow();
            // open create account pop-up
            Assert.assertTrue(foreignRegisterPage.state().waitForDisplayed(),"create user page is open");
            //Assert.assertEquals(foreignRegisterPage.returnCountryCode(),"+1");

            // enter empty all fields
            foreignRegisterPage.enterUserDetails("","","","","","");
            Assert.assertTrue(foreignRegisterPage.errorMsgOnFirstname(),"error msg not show on empty");

            foreignRegisterPage.clickXicon();
            homepage.clickUserIcon();
            foreignLogInPage.clickOnRegisterNow();

            // fill all details except first name
            foreignRegisterPage.enterUserDetails("","Kaif",entry.getValue(),"mdkaif8036@gmail.com","Password@786","Password@786");
            Assert.assertTrue(foreignRegisterPage.errorMsgOnFirstname(),"error msg not show on empty");

            foreignRegisterPage.clickXicon();
            homepage.clickUserIcon();
            foreignLogInPage.clickOnRegisterNow();

            // fill all details except mob_no
            foreignRegisterPage.enterUserDetails("Md","Kaif","","mdkaif8036@gmail.com","Password@786","Password@786");
            Assert.assertTrue(foreignRegisterPage.isMobileErrorMsgShown()," empty mobile error msg not show");

            foreignRegisterPage.clickXicon();
            homepage.clickUserIcon();
            foreignLogInPage.clickOnRegisterNow();

            // fill all details except email
            foreignRegisterPage.enterUserDetails("Md","Kaif",entry.getValue(),"","Password@786","Password@786");
            Assert.assertTrue(foreignRegisterPage.isEmailErrorMsgShown(),"empty email error msg not show");

            foreignRegisterPage.clickXicon();
            homepage.clickUserIcon();
            foreignLogInPage.clickOnRegisterNow();

            // fill all details except password
            foreignRegisterPage.enterUserDetails("Md","Kaif",entry.getValue(),"mdkaif8036@gmail.com","","Password@786");
            Assert.assertTrue(foreignRegisterPage.isPasswordErrorMsgShown(),"empty password error msg not show");

            foreignRegisterPage.clickXicon();
            homepage.clickUserIcon();
            foreignLogInPage.clickOnRegisterNow();

            // fill all details except confirm password
            foreignRegisterPage.enterUserDetails("Md","Kaif",entry.getValue(),"mdkaif8036@gmail.com","Password@786","");
            Assert.assertTrue(foreignRegisterPage.isConfirmPasswordErrorMsgShown(),"empty confirm password error msg not show");

            foreignRegisterPage.clickXicon();
            homepage.clickUserIcon();
            foreignLogInPage.clickOnRegisterNow();

            // fill all details and password mismatch
            foreignRegisterPage.enterUserDetails("Md","Kaif",entry.getValue(),"mdkaif8036@gmail.com","Password@786","Password@785");
            Assert.assertTrue(foreignRegisterPage.isConfirmPasswordErrorMsgShown(),"empty confirm password error msg not show");

            /*foreignRegisterPage.clickXicon();
            homepage.clickUserIcon();
            foreignLogInPage.clickOnRegisterNow();

            // fill already exist mobile number and differ user details
            foreignRegisterPage.enterUserDetails("Md","Kaif",entry.getValue(),"mdkaif8136@gmail.com","Password@786","Password@786");
            Assert.assertTrue(foreignRegisterPage.isMobileErrorMsgShown()," empty mobile error msg not show");*/

            foreignRegisterPage.clickXicon();
            homepage.clickUserIcon();
            foreignLogInPage.clickOnRegisterNow();

            // fill already exist email and different mob_no user details
            foreignRegisterPage.enterUserDetails("Md","Kaif",entry.getValue(),"mdkaif8036@gmail.com","Password@786","Password@786");
            Assert.assertTrue(foreignRegisterPage.isEmailErrorMsgShown()," empty mobile error msg not show");

            foreignRegisterPage.clickXicon();
            homepage.clickUserIcon();
            foreignLogInPage.clickOnRegisterNow();

            // fill new user details
            foreignRegisterPage.enterUserDetails("Md","Kaif",entry.getValue(),"mdkaif8136@gmail.com","Password@786","Password@786");
            Assert.assertTrue(foreignRegisterPage.isVerificationPopUpshown(),"verification pop-up not shown");
        }


    }

}

/*
homepage.closePrivacyIcon();
        homepage.selectCurrency("USD");
        homepage.clickUserIcon();
        foreignLogInPage.clickOnRegisterNow();
        // open create account pop-up
        Assert.assertTrue(foreignRegisterPage.state().waitForDisplayed(),"create user page is open");
        //Assert.assertEquals(foreignRegisterPage.returnCountryCode(),"+1");

        // enter empty all fields
        foreignRegisterPage.enterUserDetails("","","","","","");
        Assert.assertTrue(foreignRegisterPage.errorMsgOnFirstname(),"error msg not show on empty");

        foreignRegisterPage.clickXicon();
        homepage.clickUserIcon();
        foreignLogInPage.clickOnRegisterNow();

        // fill all details except first name
        foreignRegisterPage.enterUserDetails("","Kaif","2107780187","mdkaif8036@gmail.com","Password@786","Password@786");
        Assert.assertTrue(foreignRegisterPage.errorMsgOnFirstname(),"error msg not show on empty");

        foreignRegisterPage.clickXicon();
        homepage.clickUserIcon();
        foreignLogInPage.clickOnRegisterNow();

        // fill all details except mob_no
        foreignRegisterPage.enterUserDetails("Md","Kaif","","mdkaif8036@gmail.com","Password@786","Password@786");
        Assert.assertTrue(foreignRegisterPage.isMobileErrorMsgShown()," empty mobile error msg not show");

        foreignRegisterPage.clickXicon();
        homepage.clickUserIcon();
        foreignLogInPage.clickOnRegisterNow();

        // fill all details except email
        foreignRegisterPage.enterUserDetails("Md","Kaif","2107780187","","Password@786","Password@786");
        Assert.assertTrue(foreignRegisterPage.isEmailErrorMsgShown(),"empty email error msg not show");

        foreignRegisterPage.clickXicon();
        homepage.clickUserIcon();
        foreignLogInPage.clickOnRegisterNow();

        // fill all details except password
        foreignRegisterPage.enterUserDetails("Md","Kaif","2107780187","mdkaif8036@gmail.com","","Password@786");
        Assert.assertTrue(foreignRegisterPage.isPasswordErrorMsgShown(),"empty password error msg not show");

        foreignRegisterPage.clickXicon();
        homepage.clickUserIcon();
        foreignLogInPage.clickOnRegisterNow();

        // fill all details except confirm password
        foreignRegisterPage.enterUserDetails("Md","Kaif","2107780187","mdkaif8036@gmail.com","Password@786","");
        Assert.assertTrue(foreignRegisterPage.isConfirmPasswordErrorMsgShown(),"empty confirm password error msg not show");

        foreignRegisterPage.clickXicon();
        homepage.clickUserIcon();
        foreignLogInPage.clickOnRegisterNow();

        // fill all details and password mismatch
        foreignRegisterPage.enterUserDetails("Md","Kaif","2107780187","mdkaif8036@gmail.com","Password@786","Password@785");
        Assert.assertTrue(foreignRegisterPage.isConfirmPasswordErrorMsgShown(),"empty confirm password error msg not show");

        foreignRegisterPage.clickXicon();
        homepage.clickUserIcon();
        foreignLogInPage.clickOnRegisterNow();

        // fill already exist mobile number and differ user details
        foreignRegisterPage.enterUserDetails("Md","Kaif","6307780187","mdkaif8136@gmail.com","Password@786","Password@786");
        Assert.assertTrue(foreignRegisterPage.isMobileErrorMsgShown()," empty mobile error msg not show");

        foreignRegisterPage.clickXicon();
        homepage.clickUserIcon();
        foreignLogInPage.clickOnRegisterNow();

        // fill already exist email and different mob_no user details
        foreignRegisterPage.enterUserDetails("Md","Kaif","2107780187","mdkaif8036@gmail.com","Password@786","Password@786");
        Assert.assertTrue(foreignRegisterPage.isEmailErrorMsgShown()," empty mobile error msg not show");

        foreignRegisterPage.clickXicon();
        homepage.clickUserIcon();
        foreignLogInPage.clickOnRegisterNow();

        // fill new user details
        foreignRegisterPage.enterUserDetails("Md","Kaif","2107780187","mdkaif8136@gmail.com","Password@786","Password@786");
        Assert.assertTrue(foreignRegisterPage.isVerificationPopUpshown(),"verification pop-up not shown");
 */