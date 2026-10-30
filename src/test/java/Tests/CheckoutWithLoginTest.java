package Tests;

import Pages.*;
import Utilities.ScreenshotListener;
import Utilities.Utils;
import aquality.selenium.browser.AqualityServices;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ScreenshotListener.class)
public class CheckoutWithLoginTest extends BaseTest {

        Homepage homepage = new Homepage();
        INRLoginpage inrLoginpage = new INRLoginpage();
        NavigationBar navigationBar = new NavigationBar();
        CartPage cartPage = new CartPage();
        PDPPage pdpPage = new PDPPage();
        Utils utils = new Utils();
        CheckoutPage checkoutPage = new CheckoutPage();

        @Test
        public void checkoutWithLoginTest() throws InterruptedException {
                homepage.closePrivacyIcon();
                homepage.clickUserIcon();
                inrLoginpage.loginWithRegisterMobileNumber("mdkaif8036@gmail.com");
                Assert.assertTrue(homepage.state().waitForDisplayed(), "Homepage open");
                int cartQuantity = navigationBar.getPreviousQuantity();
                navigationBar.cartQuantity.click();
                Assert.assertTrue(cartPage.state().waitForDisplayed(), "cart page is open");
                Assert.assertEquals(Integer.parseInt(cartPage.shoppingBagProductQuantity.getText()) +
                                Integer.parseInt(cartPage.trialBagProductQuantity.getText()), cartQuantity);

                cartPage.trialBag.click();
                Assert.assertTrue(cartPage.tryNearYou.state().waitForDisplayed(), "try near you is not click");
                cartPage.shoppingBag.click();
                Assert.assertTrue(cartPage.state().waitForDisplayed(), "shopping bag is not open");
                AqualityServices.getBrowser().refresh();
                Assert.assertEquals(Integer.parseInt(cartPage.shoppingBagProductQuantity.getText()) +
                                Integer.parseInt(cartPage.trialBagProductQuantity.getText()), cartQuantity);

                Assert.assertTrue(cartPage.noticeBanner.state().waitForDisplayed(), "notice banner is not displayed");
                Assert.assertTrue(cartPage.learnMore.state().isClickable(), "learn more is not clickable");

                // find how many product in cart
                int productOnCart = cartPage.getCountOfProductInCart();
                System.out.println("cart quantity = " + productOnCart);
                // check all product elements displayed
                for (int i = 1; i <= productOnCart; i++) {
                        System.out.println("For " + i);
                        Assert.assertTrue(cartPage.productImage(i).state().waitForDisplayed(),
                                        "product Image is not displayed");
                        Assert.assertTrue(cartPage.productName(i).state().waitForDisplayed(),
                                        "product Name is not displayed");
                        Assert.assertTrue(cartPage.productCode(i).state().waitForDisplayed(),
                                        "product Code is not displayed");
                        Assert.assertTrue(cartPage.productPrize(i).state().waitForDisplayed(),
                                        "product Prize  is not displayed");
                        Assert.assertTrue(cartPage.RTSorMTO(i).state().waitForDisplayed(),
                                        "product RTS or MTO  is not displayed");
                        cartPage.productImage(i).getJsActions().scrollIntoView();
                        cartPage.productImage(i).click();
                        Assert.assertTrue(pdpPage.state().waitForDisplayed(), "pdp is open");
                        AqualityServices.getBrowser().goBack();
                        Assert.assertTrue(cartPage.state().waitForDisplayed(), "cart is open");
                        final int productIndex = i;

                        cartPage.clickIncreaseQuantityIcon(productIndex);

                        cartPage.clickDecreaseQuantityIcon(productIndex);

                        // check decrease icon disable when quantity 1
                        // Assert.assertTrue(cartPage.checkDecreaseIconDisableOnOneProduct(i),"decrease
                        // icon clickable on quantity 1");

                }

                // Increase quantity 10 times
                System.out.println("========== BEFORE INCREASE QUANTITY ==========");

                int productIndexForIncrease = 1;
                int timesToIncrease = 5;
                int beforeIncrease = Integer.parseInt(cartPage.quantityValue(productIndexForIncrease).getText());
                cartPage.increaseProductQuantity(productIndexForIncrease, timesToIncrease);
                int afterIncrease = Integer.parseInt(cartPage.quantityValue(productIndexForIncrease).getText());

                System.out.println("Expected quantity: " + (beforeIncrease +
                                timesToIncrease));
                System.out.println("Actual quantity: " + afterIncrease);

                Assert.assertEquals(afterIncrease, beforeIncrease + timesToIncrease);

                // Decrease quantity 10 times
                System.out.println("========== DECREASE QUANTITY ==========");

                int productIndexForDecrease = 1;
                int timesToDecrease = 5;
                int beforeDecrease = Integer.parseInt(cartPage.quantityValue(productIndexForIncrease).getText());
                cartPage.decreaseProductQuantity(productIndexForDecrease, timesToDecrease);
                int afterDecrease = Integer.parseInt(cartPage.quantityValue(productIndexForIncrease).getText());
                System.out.println("Expected quantity: " + (beforeDecrease -
                                timesToDecrease));
                System.out.println("Actual quantity: " + afterDecrease);
                Assert.assertEquals(afterDecrease, beforeDecrease - timesToDecrease);

                // check price is persistent on refresh

                String amountBeforeRefresh = cartPage.totalAmount.getText();
                System.out.println("amount before refresh " + amountBeforeRefresh);
                AqualityServices.getBrowser().refresh();
                System.out.println("amount after refresh " + cartPage.totalAmount.getText());
                Assert.assertEquals(cartPage.totalAmount.getText(), amountBeforeRefresh);

                // price summary
                System.out.println("totalCalculatedProductPrice " +
                                cartPage.getTotalProductAmount());
                System.out.println("subtaotal amount " +
                                utils.parseINRCurrency(cartPage.subTotalAmount.getText()));
                System.out.println("total amount " +
                                utils.parseINRCurrency(cartPage.totalAmount.getText()));

                Assert.assertEquals(cartPage.getTotalProductAmount(),
                                utils.parseINRCurrency(cartPage.subTotalAmount.getText()));
                Assert.assertEquals(utils.parseINRCurrency(cartPage.totalAmount.getText()),
                                utils.parseINRCurrency(cartPage.subTotalAmount.getText()),
                                "Total product price does not match sub total amount");

                // increase each product quantity by 1 check amount reflect or not

                System.out.println("---------------Increase quantity by 1--------------");
                for (int i = 1; i <= productOnCart; i++) {
                        System.out.println("Increase quantity for Product " + i);
                        cartPage.clickIncreaseQuantityIcon(i);
                }

                Assert.assertEquals(cartPage.getTotalProductAmount(),
                                utils.parseINRCurrency(cartPage.totalAmount.getText()));

                // checkout btn
                // cartPage.checkoutBtn.getJsActions().scrollIntoView();
                Assert.assertTrue(cartPage.checkoutBtn.state().waitForDisplayed(), "checkout btn is not displayed");
                cartPage.checkoutBtn.click();
                Assert.assertTrue(checkoutPage.state().waitForDisplayed(), "checkout page is not open");
                AqualityServices.getBrowser().goBack();
                Assert.assertTrue(cartPage.state().waitForDisplayed(), "cart page is open");

                AqualityServices.getBrowser().waitForPageToLoad();

                System.out.println("shipping charge calculated-----" + cartPage.isShippingChargePayableOrFree());
                System.out.println("shipping charge on website------"
                                + utils.parseINRCurrencyInt(cartPage.shippingCharge.getText()));

                // check shipping according to requirement
                Assert.assertEquals(cartPage.isShippingChargePayableOrFree(),
                                utils.parseINRCurrencyInt(cartPage.shippingCharge.getText()));

                // authenticity guarantee section

                Assert.assertTrue(cartPage.authenticityGaurantee.state().waitForDisplayed(),
                                "authenticity guarantee is not displayed");
                // Assert.assertTrue(cartPage.authenticityGauranteeSection.state().waitForDisplayed(),
                // "authenticity guarantee section is not displayed");
                Assert.assertTrue(cartPage.shipping_Return.state().waitForDisplayed(),
                                "shipping and return is not displayed");
                Assert.assertTrue(cartPage.term_Conditions.state().waitForDisplayed(),
                                "term and conditions is not displayed");

                cartPage.shippingCharge.getJsActions().scrollIntoView();
                cartPage.shipping_Return.click();
                Assert.assertTrue(cartPage.shipping_ReturnContent.state().waitForDisplayed(),
                                "shipping and return content is not displayed");

                cartPage.term_Conditions.getJsActions().scrollIntoView();
                ;
                cartPage.term_Conditions.click();
                Assert.assertTrue(cartPage.term_ConditionsContent.state().waitForDisplayed(),
                                "term and conditions content is not displayed");

        }
}
