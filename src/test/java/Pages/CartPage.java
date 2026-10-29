package Pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import static java.sql.DriverManager.getDriver;

public class CartPage extends Form {

    public ILabel shoppingBagProductQuantity = getElementFactory().getLabel(
            By.xpath("//a[contains(text(),'Shopping Bag')]//span[@class='bag-quantity']"),"shopping bag quantity");

    public ILabel shoppingBag = getElementFactory().getLabel(
            By.xpath("//a[contains(text(),'Shopping Bag')]"),"shopping bag");


    public ILabel trialBagProductQuantity = getElementFactory().getLabel(
            By.xpath("//a[contains(text(),'Trial Bag')]//span[@class='bag-quantity']"),"Trial Bag quantiy");

    public ILabel trialBag = getElementFactory().getLabel(
            By.xpath("//a[contains(text(),'Trial Bag')]"),"Trial Bag");

    public ILabel tryNearYou = getElementFactory().getLabel(
            By.xpath("//h2[normalize-space()='Try Near You']"),"try near you");

    public ILabel noticeBanner = getElementFactory().getLabel(
            By.xpath("//span[@class='made-to-order-items-span']")," notice banner");

    public ILink learnMore = getElementFactory().getLink(
            By.xpath("//a[@class='made-to-order-items-span2 learn-more']"),"learn more");


    public ILabel totalAmount = getElementFactory().getLabel(
            By.xpath("//p[contains(@class,'grand-total grand-total-text')]"),"total amount");

    public CartPage(){
        super(By.xpath("//a[contains(text(),'Shopping Bag')]"),"Cart page");
    }

    public int getCountOfProductInCart(){
        return AqualityServices.getBrowser()
                .getDriver()
                .findElements(By.xpath("//div[contains(@class,'card product-info')]"))
                .size();
    }


    public ILabel productImage(int i){
        return getElementFactory().getLabel(
                By.xpath("//div[contains(@class,'card product-info')]["+ i+ "]//*[@class='product-image']"),"product Image");

    }

    public ILabel productName(int i){
        return getElementFactory().getLabel(
                By.xpath("//div[contains(@class,'card product-info')]["+i+"]//div[@class='line-item-name']"),"product Name");

    }

    public ILabel productCode(int i){
        return getElementFactory().getLabel(
                By.xpath("//div[contains(@class,'card product-info')]["+i+"]//div[@class='product-info-id']"),"product code");

    }

    public ILabel productPrize(int i){
        return getElementFactory().getLabel(
                By.xpath("//div[contains(@class,'card product-info')]["+i+"]//div[contains(@class,'pricing line-item-total')]"),"product Prize");

    }
    public ILabel RTSorMTO(int i){
        return getElementFactory().getLabel(
                By.xpath("//div[contains(@class,'card product-info')]["+i+"]//div[@class='pdp-tile-tag']")," Ready to Ship/Made To Order");

    }

    public ILabel increaseQuantityBtn(int i){
        return getElementFactory().getLabel(
                By.xpath("//div[contains(@class,'card product-info')]["+i+"]//button[contains(@class,'btn-increase')]")," increase quantity btn");

    }

    public ILabel decreaseQuantityBtn(int i){
        return getElementFactory().getLabel(
                By.xpath("//div[contains(@class,'card product-info')]["+i+"]//button[contains(@class,'btn-decrease')]"),"decrease quantity btn");

    }

    public ILabel quantityValue(int i){
        return getElementFactory().getLabel(
                By.xpath("//div[contains(@class,'card product-info')][1]//span[contains(@class,'quantity-value')]"),"current quantity");
    }

    public boolean checkDecreaseIconDisableOnOneProduct(int i){
        int quantity = Integer.parseInt(quantityValue(i).getText());
        if(quantity ==1 && !quantityValue(i).state().isClickable()){
            return true;
        }
        else{
            return false;
        }
    }


}// //div[contains(@class,'card product-info')][1]//div[contains(@class,'pricing line-item-total')]
