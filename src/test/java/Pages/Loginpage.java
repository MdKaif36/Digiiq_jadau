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

    public ITextBox mobileNumberTextbox = AqualityServices.getElementFactory().getTextBox(
            By.xpath("//input[@id='login-form-mobile-number']"), "Mobile Number");

    public ILabel xicon = AqualityServices.getElementFactory().getLabel(
            By.xpath("//div[@id='signup-login-popup']//div[@class='modal-content']//*[name()='svg']"), "X Icon");

    public IButton signBtn= AqualityServices.getElementFactory().getButton(
            By.xpath("//button[@id='send-otp-login']"),"sign_in button");
    public ILabel warningLocator = AqualityServices.getElementFactory().getLabel(
            By.xpath("//*[contains(text(),\"This field\")]"),"warning without enter number");

    public ILabel verificationOtpPopup= AqualityServices.getElementFactory().getLabel(
            By.xpath("//*[contains(text(),\"Enter Verification\")]"),"Verification otp pop-up");

    public ILabel warningForLessThan10MobileNumber = AqualityServices.getElementFactory().getLabel(
            By.xpath("(//div[@id='form-email-error'])[1]"),"warning for enter less than 10 digit mob");

    public ILabel warningForUnregisterMob= AqualityServices.getElementFactory().getLabel(
            By.xpath("(//div[@id='form-email-error'])[1]"),"warning unregister mobile number");

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
        return mobileNumberTextbox.getAttribute("placeholder");
    }

    public boolean signinByPressEnter(){
        mobileNumberTextbox.sendKeys("6307780187");
        signBtn.getJsActions().setFocus();
        signBtn.sendKeys(Keys.ENTER);
        return verificationOtpPopup.state().waitForDisplayed();
    }

    public boolean enterEmptyMobileNumber(){
        signBtn.state().waitForDisplayed();
        signBtn.click();
        return warningLocator.state().waitForDisplayed();
    }

    public boolean enterValidMobileNumber(){
        mobileNumberTextbox.clearAndType("6307780187");
        //mobileNumberTextbox.sendKeys("6307780187");
        signBtn.state().waitForDisplayed();
        signBtn.click();
        return verificationOtpPopup.state().waitForDisplayed();

    }

    public boolean MobileNumber0_9(){
        mobileNumberTextbox.clearAndType("00000");
        //mobileNumberTextbox.sendKeys("000000");
        signBtn.state().waitForDisplayed();
        signBtn.click();
        return warningForLessThan10MobileNumber.state().waitForDisplayed();

    }

    public boolean InvalidMobileNumber(){
        mobileNumberTextbox.clearAndType("63077801870");
        //mobileNumberTextbox.sendKeys("63077801871");
        signBtn.state().waitForDisplayed();
        signBtn.click();
        //return warningForLessThan10MobileNumber.state().waitForDisplayed();
        return verificationOtpPopup.state().waitForDisplayed();

    }

    public boolean unregisterMob(){
        mobileNumberTextbox.clearAndType("7362589563");
        signBtn.state().waitForDisplayed();
        signBtn.click();
        return warningForUnregisterMob.state().waitForDisplayed();
    }

    public boolean enterSpecialChracterMobileNumber(){
        mobileNumberTextbox.clearAndType("@#$%%$#@#$");
        signBtn.state().waitForDisplayed();
        signBtn.click();
        return warningLocator.state().waitForDisplayed();
    }




}
