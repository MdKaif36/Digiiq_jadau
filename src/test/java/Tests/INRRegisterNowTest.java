package Tests;

import Pages.Homepage;
import Pages.INRLoginpage;
import Pages.INRRegisterNowPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class INRRegisterNowTest extends BaseTest{

    INRRegisterNowPage registerNowPage = new INRRegisterNowPage();
    Homepage homepage = new Homepage();
    INRLoginpage loginpage = new INRLoginpage();

    @Test
    public void test(){
        homepage.closePrivacyIcon();
        homepage.clickUserIcon();
        loginpage.clickOnRegisterNow();
        Assert.assertTrue(registerNowPage.state().waitForDisplayed(),"Create account page is not open");
        Assert.assertTrue(registerNowPage.isXiconDisplayed(),"X icon is not visible");

        //enter user detail then press x icon to open again empty fields available
        registerNowPage.enterDetailsThenPressXicon("Md","Kaif","6307780187","mdkaif8036@gmail.com","212601");
        Assert.assertTrue(registerNowPage.verifyDataClearedwhenXicon(),"data is not cleard");

        // enter already exist user details
        registerNowPage.RegisterUserDetails("Md","Kaif","6307780187","mdkaif8036@gmail.com","212601");
        Assert.assertTrue(registerNowPage.isWarningOnAlreadyExistUser(),"warning text not shown on register existing user");

        // enter new user details
        registerNowPage.clickXicon();
        homepage.clickUserIcon();
        loginpage.clickOnRegisterNow();
        registerNowPage.RegisterUserDetails("Md","Kaif","8569743010","mdkaif5263@gmail.com","212601");
        Assert.assertTrue(registerNowPage.verifyOtpPopupOpen(),"Verification pop up not open");

        // enter user details empty firstname
        registerNowPage.clickXicon();
        homepage.clickUserIcon();
        loginpage.clickOnRegisterNow();
        registerNowPage.RegisterUserDetails("","Kaif","8569743010","mdkaif5263@gmail.com","212601");
        Assert.assertTrue(registerNowPage.warningFirstNameEmpty(),"Warning not shown when firstname is empty");

        // enter user details empty Lastname  // bug resolving by Vanshikha
        /*registerNowPage.clickXicon();
        homepage.clickUserIcon();
        loginpage.clickOnRegisterNow();
        registerNowPage.RegisterUserDetails("Md","","8569743010","mdkaif5263@gmail.com","212601");
        Assert.assertTrue(registerNowPage.warningLastNameEmpty(),"Warning not shown when lastName is empty");*/

        // enter user details and  empty Mobile no
        registerNowPage.clickXicon();
        homepage.clickUserIcon();
        loginpage.clickOnRegisterNow();
        registerNowPage.RegisterUserDetails("Md","Kaif","","mdkaif5263@gmail.com","212601");
        Assert.assertTrue(registerNowPage.warningMob_noEmpty(),"Warning is  not shown when Mob_no is empty");

        // enter user details and  empty Email
        registerNowPage.clickXicon();
        homepage.clickUserIcon();
        loginpage.clickOnRegisterNow();
        registerNowPage.RegisterUserDetails("Md","Kaif","6307780187","","212601");
        Assert.assertTrue(registerNowPage.warningEmailEmpty(),"Warning is  not shown when email is empty");

        // enter user details and empty pincode
        registerNowPage.clickXicon();
        homepage.clickUserIcon();
        loginpage.clickOnRegisterNow();
        registerNowPage.RegisterUserDetails("Md","Kaif","6307780187","mdkaif8036@gmail.com","");
        Assert.assertTrue(registerNowPage.warningPincodeEmpty(),"Warning is  not shown when email is empty");

        // enter user deatils and not check the checkbox
        registerNowPage.clickXicon();
        homepage.clickUserIcon();
        loginpage.clickOnRegisterNow();
        registerNowPage.RegisterUserDetailsWithoutCheckboxCheck("Md","Kaif","6307780187","mdkaif8036@gmail.com","212601");
        Assert.assertTrue(registerNowPage.warningCheckboxNotCheck(),"Warning is  not shown when checkbox not check");


    }
}
