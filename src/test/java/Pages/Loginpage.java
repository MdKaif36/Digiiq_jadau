package Pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class Loginpage extends Form {

    public ILabel code = AqualityServices.getElementFactory().getLabel(
            By.xpath(
                    "//div[@class='form-group required mobile-field alternate-login-option-field activeMode']//span[@class='countryCodeMobile'][normalize-space()='+91']"),
            "Country Code");

    public ITextBox mobileNumber = AqualityServices.getElementFactory().getTextBox(
            By.xpath("//input[@id='login-form-mobile-number']"), "Mobile Number");

    public ILabel xicon = AqualityServices.getElementFactory().getLabel(
            By.xpath("//div[@id='signup-login-popup']//div[@class='modal-content']//*[name()='svg']"), "X Icon");

    public IButton signBtn= AqualityServices.getElementFactory().getButton(
            By.xpath("//button[@id='send-otp-login']"),"sign_in button");
    public ILabel warningLocator = AqualityServices.getElementFactory().getLabel(
            By.xpath("//*[contains(text(),\"This field\")]"),"warning without enter number");

    public Loginpage() {
        super(By.xpath("//h2[normalize-space()='Log In']"), "Loginpage");
    }

    public Boolean countryCode() {
        return code.state().isDisplayed();
    }

    public Boolean xiconClickable() {
        return xicon.state().isClickable();
    }

    public void clickOutSidePopup() {
        AqualityServices.getBrowser().executeScript("document.elementFromPoint(5, 5).click();");

    }

    public boolean popup() {
        if (code.state().isDisplayed()) {
            return false;
        } else {
            return true;
        }
    }

    public String mobileNumberPlaceholder(String placeholer) {
        return mobileNumber.getAttribute("placeholder");
    }

    public boolean signinClickOnEnter(){
        //mobileNumber.sendKeys("6307780187");
        signBtn.getJsActions().setFocus();
        signBtn.sendKeys(Keys.ENTER);
        return warningLocator.state().waitForDisplayed();
    }


}

