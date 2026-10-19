package Pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ForeignRegisterPage extends Form {

    public ITextBox firstName= getElementFactory().getTextBox(
            By.xpath("//input[@id='firstname']"),"firstname");

    public ITextBox lastName= getElementFactory().getTextBox(
            By.xpath("//input[@id='lastname']"),"lastname");

    public ITextBox email= getElementFactory().getTextBox(
            By.xpath("//input[@id='email']"),"email");

    public ITextBox mob_No= getElementFactory().getTextBox(
            By.xpath("//input[@id='mobile']"),"firstname");

    public ITextBox password= getElementFactory().getTextBox(
            By.xpath("//input[@id='password']"),"password");

    public ITextBox confirmPassword= getElementFactory().getTextBox(
            By.xpath("//input[@id='confirmPassword']"),"confirm password");

    public ILabel checkBox = getElementFactory().getLabel(
            By.xpath("//label[contains(text(),'By continuing, I agree to the')]"),"checkbox");

    public IButton createAccBtn= getElementFactory().getButton(
            By.xpath("//button[@id='send-otp-signup']"),"create account button");

    public ILabel firstNameErrorMsg = getElementFactory().getLabel(
            By.xpath("//div[@id='form-firstname-error']"),"first name error msg");

    public ILabel mobileErrorMsg = getElementFactory().getLabel(
            By.xpath("//div[@id='form-mobile-error']"),"first name error msg");

    public ILabel emailErrorMsg = getElementFactory().getLabel(
            By.xpath("//div[@class='form-group']//div[@id='form-email-error']"),"first name error msg");

    public ILabel passwordErrorMsg = getElementFactory().getLabel(
            By.xpath("//div[@class='form-group']//div[@id='form-password-error']"),"first name error msg");

    public ILabel confirmPasswordErrorMsg = getElementFactory().getLabel(
            By.xpath("//div[@id='form-confirmPassword-error']"),"first name error msg");

    public ILabel Xicon = getElementFactory().getLabel(
            By.xpath("//div[@id='signup-login-popup']//div[@class='modal-content']//*[name()='svg']"),"X_icon");

    public ILabel verificationOtpPopUp= getElementFactory().getLabel(
            By.xpath("//h2[normalize-space()='Verify Your Email']"),"verification pop up ");
    public ILabel countryCode =getElementFactory().getLabel(
            By.xpath("//form[@id='signUpEmailPasswordForm']//div[@class='iti__selected-dial-code'][normalize-space()='+1']"),"country code");

    public ForeignRegisterPage(){
        super(By.xpath("//h2[normalize-space()='Create New Account']"),"Create Account pop-up open");
    }

    public void fillUserDetails(String firstname,String lastname,String mob_no,String mail,String Password,String ConfirmPassword){
        firstName.clearAndType(firstname);
        lastName.clearAndType(lastname);
        mob_No.clearAndType(mob_no);
        email.clearAndType(mail);
        password.clearAndType(Password);
        confirmPassword.clearAndType(ConfirmPassword);

    }

    public void clickCheckBox(){
        checkBox.getJsActions().click();
    }

    public void clickCreateAccountBtn(){
        createAccBtn.click();
    }

    public void enterUserDetails(String firstname,String lastname,String mob_no,String mail,String Password, String ConfirmPassword){
        fillUserDetails(firstname,lastname,mob_no,mail,Password,ConfirmPassword);
        clickCheckBox();
        clickCreateAccountBtn();
    }

    public void clickXicon(){
        Xicon.click();
    }

    public boolean errorMsgOnFirstname(){
        return firstNameErrorMsg.state().waitForDisplayed();
    }

    public String returnCountryCode(){
        return countryCode.getText();
    }

    public boolean isMobileErrorMsgShown(){
        return mobileErrorMsg.state().waitForDisplayed();
    }

    public boolean isEmailErrorMsgShown(){
        return emailErrorMsg.state().waitForDisplayed();
    }
    public  boolean isPasswordErrorMsgShown(){
        return passwordErrorMsg.state().waitForDisplayed();
    }
    public boolean isConfirmPasswordErrorMsgShown(){
        return confirmPasswordErrorMsg.state().waitForDisplayed();
    }

    public boolean isVerificationPopUpshown(){
        return verificationOtpPopUp.state().waitForDisplayed();
    }
}
