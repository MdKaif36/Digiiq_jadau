package Tests;


import Pages.AccountPage;
import Pages.ForeignLogInPage;
import Pages.Homepage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForeignLoginTest extends BaseTest{

    Homepage homepage = new Homepage();
    ForeignLogInPage foreignLogInPage = new ForeignLogInPage();
    AccountPage accountPage = new AccountPage();


    @Test
    public void test(){
        homepage.closePrivacyIcon();
        String[] countryCurrency= {"USD","EUR","AED"};

        // Run each Currency
        for(int i=0;i<countryCurrency.length;i++){

            homepage.clickCurrencySelector(countryCurrency[i]);
            homepage.clickUserIcon();
            Assert.assertTrue(foreignLogInPage.state().waitForDisplayed(),"Login page is not open ");


            // reset password  pop-up open
            foreignLogInPage.clickOnForgetPassword();
            Assert.assertTrue(foreignLogInPage.isResetPopUpOpen(),"reset pop-up not open");

            // verification code pop-up when enter reset password
            foreignLogInPage.resetPassword("ashokchau993@gmail.com");
            Assert.assertTrue(foreignLogInPage.verificationPopUpOpen(),"verification your email pop-up not open");

            foreignLogInPage.clickXicon();
            homepage.clickUserIcon();
            // assert email and password placeholder
            Assert.assertEquals(foreignLogInPage.emailPlaceholder(),"Enter your email");
            Assert.assertEquals(foreignLogInPage.passwordPlaceholder(),"Enter your password");

            // Enter unregister email and correct password
            foreignLogInPage.enterValidEmailPassword("ashokchau903@gmail.com","Password@786");
            Assert.assertTrue(foreignLogInPage.warningOnUnregisterEmailEnter(),"Register email Id enter");
            // Enter correct email and incorrect password
            foreignLogInPage.enterValidEmailPassword("ashokchau993@gmail.com","Password@700");
            Assert.assertTrue(foreignLogInPage.warningOnIncorrectPasswordEnter()," correct password enter");

            // enter correct email and password
            foreignLogInPage.enterValidEmailPassword("ashokchau993@gmail.com","Password@786");
            Assert.assertTrue(homepage.loginSuccesfully(countryCurrency[i]),"Email and password are not correct");

            homepage.clickOnUserIconAfterLogin();
            accountPage.clickLogout();
            accountPage.clickLogoutPop_up();

        }


    }

}

