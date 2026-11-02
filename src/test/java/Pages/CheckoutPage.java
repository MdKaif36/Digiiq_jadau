package Pages;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.support.Color;

public class CheckoutPage extends Form {

    public CheckoutPage(){
        super(By.xpath("//h2[normalize-space()='Order Summary']"),"Checkout Page");
    }

    private final ILabel deliveryStepTextLbl = getElementFactory().getLabel(
            By.xpath("//span[@class='step-text active']"), "Delivery step text");

    //span[@class='step-text active']

    public String getDeliveryColorHex() {
        deliveryStepTextLbl.state().waitForDisplayed();
        String rgba = deliveryStepTextLbl.getCssValue("color");   // rgba(172, 100, 101, 1)
        return Color.fromString(rgba).asHex();                    // #ac6465
    }
}
