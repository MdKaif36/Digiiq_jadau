package Tests;

import Pages.Homepage;
import Pages.INRLoginpage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class INRLoginWithEmailTest extends BaseTest{

    Homepage homepage = new Homepage();
    INRLoginpage loginpage = new INRLoginpage();

    @Test
    public void test(){
        homepage.clickUserIcon();
        loginpage.loginWithEmail();
        Assert.assertTrue(loginpage.loginWithEmailPopup(),"login with email pop up not open");
        loginpage.loginEmptyMail();
        Assert.assertTrue(loginpage.warningWhenEmptyEmail(),"Login with empty email id");
        loginpage.loginWithInvalidEmail();
        Assert.assertTrue(loginpage.warningTextForInvalidEmail(),"warning text not shown when invalid mail enter");
        loginpage.loginWithUnregisterEmail();
        Assert.assertTrue(loginpage.warningTextForUnregisterEmail(),"warning text is not showm when unregister email enter");
    }
}
