package Tests;

import Pages.*;
import Utilities.ScreenshotListener;
import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.time.Duration;


@Listeners(ScreenshotListener.class)
public class CheckoutWithLoginTest extends BaseTest{

    Homepage homepage = new Homepage();
    INRLoginpage inrLoginpage = new INRLoginpage();
    NavigationBar navigationBar =new NavigationBar();
    CartPage cartPage = new CartPage();
    PDPPage pdpPage = new PDPPage();


    @Test
    public void checkoutWithLoginTest(){
        homepage.closePrivacyIcon();
        homepage.clickUserIcon();
        inrLoginpage.loginWithRegisterMobileNumber("mdkaif8036@gmail.com");
        Assert.assertTrue(homepage.state().waitForDisplayed(),"Homepage open");
        int cartQuantity = navigationBar.getPreviousQuantity();
        navigationBar.cartQuantity.click();
        Assert.assertTrue(cartPage.state().waitForDisplayed(),"cart page is open");
        Assert.assertEquals(Integer.parseInt(cartPage.shoppingBagProductQuantity.getText()) +
                Integer.parseInt(cartPage.trialBagProductQuantity.getText()), cartQuantity);

        cartPage.trialBag.click();
        Assert.assertTrue(cartPage.tryNearYou.state().waitForDisplayed(),"try near you is not click");
        cartPage.shoppingBag.click();
        Assert.assertTrue(cartPage.state().waitForDisplayed(),"shopping bag is not open");
        AqualityServices.getBrowser().refresh();
        Assert.assertEquals(Integer.parseInt(cartPage.shoppingBagProductQuantity.getText()) +
                Integer.parseInt(cartPage.trialBagProductQuantity.getText()), cartQuantity);

        Assert.assertTrue(cartPage.noticeBanner.state().waitForDisplayed(),"notice banner is not displayed");
        Assert.assertTrue(cartPage.learnMore.state().isClickable(),"learn more is not clickable");

        // find how many product in cart
        int productOnCart =cartPage.getCountOfProductInCart();
        System.out.println("cart quantity = "+productOnCart);
        // check all product elements displayed
        for(int i=1;i<=productOnCart;i++){
            System.out.println("For "+ i);
            Assert.assertTrue(cartPage.productImage(i).state().waitForDisplayed(),"product Image is not displayed");
            Assert.assertTrue(cartPage.productName(i).state().waitForDisplayed(),"product Name is not displayed");
            Assert.assertTrue(cartPage.productCode(i).state().waitForDisplayed(),"product Code is not displayed");
            Assert.assertTrue(cartPage.productPrize(i).state().waitForDisplayed(),"product Prize  is not displayed");
            Assert.assertTrue(cartPage.RTSorMTO(i).state().waitForDisplayed(),"product RTS or MTO  is not displayed");
            cartPage.productImage(i).getJsActions().scrollIntoView();
            cartPage.productImage(i).click();
            Assert.assertTrue(pdpPage.state().waitForDisplayed(),"pdp is open");
            AqualityServices.getBrowser().goBack();
            Assert.assertTrue(cartPage.state().waitForDisplayed(),"cart is open");
            cartPage.increaseQuantityBtn(i).getJsActions().scrollIntoView();
            cartPage.increaseQuantityBtn(i).click();
            cartPage.decreaseQuantityBtn(i).getJsActions().scrollIntoView();
            cartPage.decreaseQuantityBtn(i).click();
            // check decrease icon disable when quantity 1
            cartPage.checkDecreaseIconDisableOnOneProduct(i);
        }

        // increase rapid 10 quantity of any product
        int tenTimesIncrease =10;
        int expected=1;
        int beforeClick = Integer.parseInt(cartPage.quantityValue(1).getText());

        for(int i=0;i<tenTimesIncrease;i++){
            System.out.println("Click button + icon for "+ i +" times --> ");
            expected++;
            final int exp = expected;
            cartPage.increaseQuantityBtn(1).getJsActions().scrollIntoView();
            cartPage.increaseQuantityBtn(1).getJsActions().click();

            AqualityServices.getConditionalWait().waitFor(
                    () -> Integer.parseInt(cartPage.quantityValue(1).getText().trim()) == exp,
                    Duration.ofSeconds(1), Duration.ofMillis(300),
                    "Quantity did not update to " + exp
            );

        }
        int afterClick = Integer.parseInt(cartPage.quantityValue(1).getText());
        System.out.println(beforeClick+tenTimesIncrease);
        System.out.println(afterClick);
        Assert.assertEquals(afterClick,beforeClick+tenTimesIncrease);

        // decrease quantity 10 times
        for(int i=0;i<tenTimesIncrease;i++){
            System.out.println("decrease for "+ i);
            cartPage.decreaseQuantityBtn(1).state().waitForClickable();
            cartPage.decreaseQuantityBtn(1).getJsActions().scrollIntoView();
            cartPage.decreaseQuantityBtn(1).click();
        }

        // click on product name and go back
//        cartPage.productName.click();
//        Assert.assertTrue(pdpPage.state().waitForDisplayed(),"pdp is open");
//        AqualityServices.getBrowser().goBack();
//        Assert.assertTrue(cartPage.state().waitForDisplayed(),"pdp is open");

        // check price is persistent on refresh
        AqualityServices.getBrowser().refresh();
        Assert.assertEquals(cartPage.totalAmount.getText(),cartPage.totalAmount.getText());


    }
}
