package Pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class Homepage extends Form {

    public ILabel userIcon = AqualityServices.getElementFactory().getLabel(
            By.xpath("//a[contains(@class,'login-modal')]"), "User icon");

    public Homepage() {
        super(By.xpath("//*[contains(text(),\"WORLD OF JADAU\")]"), "Homepage");
    }

    public void clickUserIcon() {
        userIcon.state().waitForDisplayed();
        userIcon.click();
    }

}