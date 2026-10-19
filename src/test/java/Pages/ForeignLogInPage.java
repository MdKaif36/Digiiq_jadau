package Pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ForeignLogInPage extends Form {

    public ITextBox emailTextbox= getElementFactory().getTextBox(
            By.xpath("//input[@id='login-form-email']"),"email textbox");

    public ITextBox passwordTextbox= getElementFactory().getTextBox(
            By.xpath("//input[@id='login-form-password']"),"password textbox");

    public IButton loginBtn = getElementFactory().getButton(
            By.xpath("//button[@id='send-email-login']"),"login btn");

    public ILabel warningEmail= getElementFactory().getLabel(
            By.xpath("//div[@class='form-group required email-field']//div[@id='form-email-error']"),"Email warning text");

    public ILabel warningPassword= getElementFactory().getLabel(
            By.xpath("//div[@class='form-group required']//div[@id='form-password-error']"),"Password warning text");

    public ILink forgetPassword = getElementFactory().getLink(
            By.xpath("//a[@id='forgotPasswordLink']"),"click forget password");

    public ILabel resetPasswordText = getElementFactory().getLabel(
            By.xpath("//form[@id='forgotPasswordEmailForm']//h2[contains(text(),'Reset Password')]"),"reset password pop-up open");

    public ITextBox resetPasswordUsingEmail = getElementFactory().getTextBox(
            By.xpath("//input[@id='forgot-password-email']"),"email textbox");

    public IButton sendOtpbtn = getElementFactory().getButton(
            By.xpath("//button[normalize-space()='Send Code']"),"send code btn");

    public ILabel verifyYourEmail = getElementFactory().getLabel(
            By.xpath("//span[normalize-space()='Verify Your Email']"),"verification email");

    public ILabel xIcon = getElementFactory().getLabel(
            By.xpath("//div[@id='signup-login-popup']//div[@class='modal-content']//*[name()='svg']"),"click x icon");

    public ILink registerLink = getElementFactory().getLink(
            By.xpath("//a[normalize-space()='Register Now']"),"click on register now");

    public ForeignLogInPage(){
        super(By.xpath("//h2[normalize-space()='Log In']"),"Foreign Login page open");
    }

    public String emailPlaceholder(){
        return emailTextbox.getAttribute("placeholder");
    }

    public String passwordPlaceholder(){
        return passwordTextbox.getAttribute("placeholder");
    }

    public void clickLoginBtn(){
        loginBtn.click();
    }

    public void enterValidEmailPassword(String email, String password){
        emailTextbox.clearAndType(email);
        passwordTextbox.clearAndType(password);
        clickLoginBtn();
    }

    public boolean warningOnUnregisterEmailEnter(){
        return warningEmail.state().waitForDisplayed();
    }

    public boolean warningOnIncorrectPasswordEnter(){
        return warningPassword.state().waitForDisplayed();
    }

    public void clickOnForgetPassword(){
        forgetPassword.click();
    }

    public boolean isResetPopUpOpen(){
        return resetPasswordText.state().waitForDisplayed();
    }

    public void resetPassword(String email){
        resetPasswordUsingEmail.clearAndType(email);
        sendOtpbtn.click();
    }

    public boolean verificationPopUpOpen(){
        return verifyYourEmail.state().waitForDisplayed();
    }

    public void clickXicon(){
        xIcon.click();
    }

    public void clickOnRegisterNow(){
        registerLink.click();
    }

}
