package Pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import Utilities.Utils;

import org.openqa.selenium.By;
import java.time.Duration;

public class CartPage extends Form {

        public ILabel shoppingBagProductQuantity = getElementFactory().getLabel(
                        By.xpath("//a[contains(text(),'Shopping Bag')]//span[@class='bag-quantity']"),
                        "shopping bag quantity");

        public ILabel shoppingBag = getElementFactory().getLabel(
                        By.xpath("//a[contains(text(),'Shopping Bag')]"), "shopping bag");

        public ILabel trialBagProductQuantity = getElementFactory().getLabel(
                        By.xpath("//a[contains(text(),'Trial Bag')]//span[@class='bag-quantity']"),
                        "Trial Bag quantiy");

        public ILabel trialBag = getElementFactory().getLabel(
                        By.xpath("//a[contains(text(),'Trial Bag')]"), "Trial Bag");

        public ILabel tryNearYou = getElementFactory().getLabel(
                        By.xpath("//h2[normalize-space()='Try Near You']"), "try near you");

        public ILabel noticeBanner = getElementFactory().getLabel(
                        By.xpath("//span[@class='made-to-order-items-span']"), " notice banner");

        public ILink learnMore = getElementFactory().getLink(
                        By.xpath("//a[@class='made-to-order-items-span2 learn-more']"), "learn more");

        public ILabel subTotalAmount = getElementFactory().getLabel(
                        By.xpath("//p[@class='text-right sub-total text-nowrap']"), "total amount");

        public ILabel totalAmount = getElementFactory().getLabel(
                        By.xpath("//p[contains(@class,'grand-total grand-total-text')]"), "total amount");

        public ILink checkoutBtn = getElementFactory().getLink(
                        By.xpath("//a[normalize-space()='Checkout']"), "checkout btn");

        public ILabel shippingCharge = getElementFactory().getLabel(
                        By.xpath("//p[@class='text-right shipping-cost']"), "shipping chrage");

        public ILabel authenticityGaurantee = getElementFactory().getLabel(
                        By.xpath("//*[text()='Authenticity Guarantee']"), "authenticity gaurantee");

        public ILabel authenticityGauranteeSection = getElementFactory().getLabel(
                        By.xpath("//div[contains(@class,'jadau-values-wrapper-new')]/*[@class='col-6']"),
                        "authenticity gaurantee section");

        public ILabel shipping_Return = getElementFactory().getLabel(
                        By.xpath("//span[normalize-space()='Shipping & Returns']"), "shipping and return");

        public ILabel term_Conditions = getElementFactory().getLabel(
                        By.xpath("//span[normalize-space()='Terms & Conditions']"), "term and conditions");

        public ILabel term_ConditionsContent = getElementFactory().getLabel(
                        By.xpath("//div[@id='collapseTC']//p[@class='product-detail-content']"),
                        "term and conditions content");

        public ILabel shipping_ReturnContent = getElementFactory().getLabel(
                        By.xpath("//div[@id='collapseShipping']//p[@class='product-detail-content']"),
                        "Shipping & Return content");

        public CartPage() {
                super(By.xpath("//a[contains(text(),'Shopping Bag')]"), "Cart page");
        }

        public int getCountOfProductInCart() {
                return AqualityServices.getBrowser()
                                .getDriver()
                                .findElements(By.xpath("//div[contains(@class,'card product-info')]"))
                                .size();
        }

        public ILabel productImage(int i) {
                return getElementFactory().getLabel(
                                By.xpath("//div[contains(@class,'card product-info')][" + i
                                                + "]//*[@class='product-image']"),
                                "product Image");

        }

        public ILabel productName(int i) {
                return getElementFactory().getLabel(
                                By.xpath("//div[contains(@class,'card product-info')][" + i
                                                + "]//div[@class='line-item-name']"),
                                "product Name");

        }

        public ILabel productCode(int i) {
                return getElementFactory().getLabel(
                                By.xpath("//div[contains(@class,'card product-info')][" + i
                                                + "]//div[@class='product-info-id']"),
                                "product code");

        }

        public ILabel productPrize(int i) {
                return getElementFactory().getLabel(
                                By.xpath("//div[contains(@class,'card product-info')][" + i
                                                + "]//div[contains(@class,'pricing line-item-total')]"),
                                "product Prize");

        }

        public ILabel RTSorMTO(int i) {
                return getElementFactory().getLabel(
                                By.xpath("//div[contains(@class,'card product-info')][" + i
                                                + "]//div[@class='pdp-tile-tag']"),
                                " Ready to Ship/Made To Order");

        }

        public IButton increaseQuantityBtn(int i) {
                return getElementFactory().getButton(
                                By.xpath("//div[contains(@class,'card product-info')][" + i
                                                + "]//button[contains(@class,'btn-increase')]"),
                                " increase quantity btn");

        }

        public IButton decreaseQuantityBtn(int i) {
                return getElementFactory().getButton(
                                By.xpath("//div[contains(@class,'card product-info')][" + i
                                                + "]//button[contains(@class,'btn-decrease')]"),
                                "decrease quantity btn");

        }

        public ILabel quantityValue(int i) {
                return getElementFactory().getLabel(
                                By.xpath("//div[contains(@class,'card product-info')][" + i
                                                + "]//span[contains(@class,'quantity-value')]"),
                                "current quantity");
        }

        public boolean checkDecreaseIconDisableOnOneProduct(int i) {
                int quantity = Integer.parseInt(quantityValue(i).getText());
                if (quantity == 1 && !quantityValue(i).state().isClickable()) {
                        return true;
                } else {
                        return false;
                }
        }

        public int getQuantity(int index) {
                return Integer.parseInt(quantityValue(index).getText().trim());
        }

        Utils utils = new Utils();

        public long getTotalProductAmount() {
                long totalProductPrice = 0;
                int productOnCart = getCountOfProductInCart();
                for (int i = 1; i <= productOnCart; i++) {
                        long productPrice = utils.parseINRCurrency(productPrize(i).getText());
                        System.out.println("for" + i + " product price " + productPrice);
                        long quantity = utils.parseINRCurrency(quantityValue(i).getText());
                        System.out.println("for" + i + " quantity " + quantity);
                        totalProductPrice += productPrice * quantity;

                }

                return totalProductPrice;
        }

        public void increaseProductQuantity(int productIndex, int times) {
                int expected = 1;
                for (int i = 1; i <= times; i++) {
                        expected++;
                        System.out.println("Click increase button " + i + " times");

                        final int exp = expected;

                        increaseQuantityBtn(productIndex).getJsActions().scrollIntoView();
                        increaseQuantityBtn(productIndex).getJsActions().click();

                        AqualityServices.getConditionalWait().waitFor(
                                        () -> Integer.parseInt(quantityValue(productIndex).getText()) == exp,
                                        Duration.ofSeconds(10),
                                        Duration.ofMillis(300),
                                        "Quantity did not update to " + exp);
                }
        }

        public void clickIncreaseQuantityIcon(int productIndex) {
                int beforeClickQuantity = Integer.parseInt(quantityValue(productIndex).getText());
                increaseQuantityBtn(productIndex).getJsActions().scrollIntoView();
                increaseQuantityBtn(productIndex).getJsActions().click();

                AqualityServices.getConditionalWait().waitFor(
                                () -> Integer.parseInt(quantityValue(productIndex).getText()) == beforeClickQuantity
                                                + 1,
                                Duration.ofSeconds(10), Duration.ofMillis(300),
                                "Quantity did not update to " + (beforeClickQuantity + 1));
        }

        public void clickDecreaseQuantityIcon(int productIndex) {
                int beforeClickQuantity = Integer.parseInt(quantityValue(productIndex).getText());
                decreaseQuantityBtn(productIndex).getJsActions().scrollIntoView();
                decreaseQuantityBtn(productIndex).getJsActions().click();

                AqualityServices.getConditionalWait().waitFor(
                                () -> Integer.parseInt(quantityValue(productIndex).getText()) == beforeClickQuantity
                                                - 1,
                                Duration.ofSeconds(10), Duration.ofMillis(300),
                                "Quantity did not update to " + (beforeClickQuantity - 1));
        }

        public void decreaseProductQuantity(int productIndex, int times) {
                int expected = getQuantity(productIndex);
                for (int i = 1; i <= times; i++) {
                        expected--;
                        System.out.println("Click decrease button " + i + " times");

                        final int exp = expected;

                        decreaseQuantityBtn(productIndex).getJsActions().scrollIntoView();
                        decreaseQuantityBtn(productIndex).getJsActions().click();

                        AqualityServices.getConditionalWait().waitFor(
                                        () -> Integer.parseInt(quantityValue(productIndex).getText()) == exp,
                                        Duration.ofSeconds(10),
                                        Duration.ofMillis(300),
                                        "Quantity did not update to " + exp);
                }
        }

        public int isShippingChargePayableOrFree() {

                long cartAmount = getTotalProductAmount();
                if (cartAmount > 500000)
                        return 0;
                else
                        return 1000;

        }

}
