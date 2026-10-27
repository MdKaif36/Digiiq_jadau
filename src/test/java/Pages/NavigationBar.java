package Pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NavigationBar extends Form {

    public ILabel homeIcon = getElementFactory().getLabel(
            By.xpath("//div[@class='hide-in-mobile']//div[@class='jadau-logo-container header-logo']//img[@alt='JADAU']"),"home icon");

    public ILabel cartQuantity = getElementFactory().getLabel(
            By.xpath("//div[@class='cart-group-section']//span[contains(@class,'minicart-quantity')]"),"cart quantity");

    public NavigationBar(){
        super(By.xpath("//div[@class='hide-in-mobile']//div[@class='jadau-logo-container header-logo']//img[@alt='JADAU']"),"Home icon");
    }


    public int getPreviousQuantity(){
        //System.out.println(cartQuantity.state().waitForExist());
        return Integer.parseInt(cartQuantity.getText());
    }


}
