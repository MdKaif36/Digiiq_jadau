package Tests;

import Pages.*;
import Utilities.ScreenshotListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ScreenshotListener.class)
public class CheckOutTest extends BaseTest{

    Homepage homepage = new Homepage();
    INRLoginpage inrLoginpage = new INRLoginpage();
    NavigationBar navigationBar = new NavigationBar();
    CartPage cartPage = new CartPage();
    CheckoutPage checkoutPage = new CheckoutPage();

    @Test
    // Already Existing Address
    public void checkoutTest(){
        homepage.closePrivacyIcon();
        homepage.clickUserIcon();
        inrLoginpage.loginWithRegisterMobileNumber("6307780187");
        navigationBar.cartQuantity.click();
        cartPage.checkoutBtn.click();
        Assert.assertEquals(checkoutPage.getDeliveryColorHex(),"#ac6465");

    }

}
