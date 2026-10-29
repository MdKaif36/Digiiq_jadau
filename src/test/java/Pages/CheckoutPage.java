package Pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CheckoutPage extends Form {

    public CheckoutPage(){
        super(By.xpath("//h2[normalize-space()='Order Summary']"),"Checkout Page");
    }
}
