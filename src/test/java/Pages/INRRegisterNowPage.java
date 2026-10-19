package Pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class INRRegisterNowPage extends Form {

    Homepage homepage =  new Homepage();
    INRLoginpage loginpage = new INRLoginpage();

    public ILabel xIcon = AqualityServices.getElementFactory().getLabel(
            By.xpath("//div[@id='signup-login-popup']//div[@class='modal-content']//*[name()='svg']"),"X icon");

    public ITextBox firstNamelocator = AqualityServices.getElementFactory().getTextBox(
            By.xpath("//input[@id='firstname']"),"enter the first name");

    public ITextBox lastNameLocator = AqualityServices.getElementFactory().getTextBox(
            By.xpath("//input[@id='lastname']"),"Enter last name");

    public ITextBox MobileNo = AqualityServices.getElementFactory().getTextBox(
            By.xpath("//input[@id='mobile']"),"enter mobile no");

    public ITextBox emailID = AqualityServices.getElementFactory().getTextBox(
            By.xpath("//input[@id='email']"),"Enter email id");

    public ITextBox pincodeLocator = AqualityServices.getElementFactory().getTextBox(
            By.xpath("//input[@id='pincode']"),"enter pincode");

    public IButton sendOtpBtn = AqualityServices.getElementFactory().getButton(
            By.xpath("//button[@id='send-otp-signup']"),"click send otp btn");

    public ILabel verificationOtp= AqualityServices.getElementFactory().getLabel(
            By.xpath("//h2[normalize-space()='Enter Verification Code']"),"verification pop up open");

    /*public ICheckBox checkBox = AqualityServices.getElementFactory().getCheckBox(
            By.xpath("//input[@id='termsAgreementRegister']"),"Checkbox");
    private ICheckBox termsCheckbox = getElementFactory().getCheckBox(
            By.id("termsAgreementRegister"), "Terms checkbox");*/

    public ILabel termsCheckboxLabel = AqualityServices.getElementFactory().getLabel(
            By.xpath("//label[@for='termsAgreementRegister']"), "Terms & Privacy Checkbox Label");

    public ILabel warningAlreadyExistUser = AqualityServices.getElementFactory().getLabel(
            By.xpath("//div[@id='form-mobile-error']"),"Warning text show when already exist user register");

    public ILabel warningFirstName = getElementFactory().getLabel(
            By.xpath("//div[@id='form-firstname-error']"),"warning on empty FirstName");

    public ILabel warningLastName = getElementFactory().getLabel(
            By.xpath("//div[@id='form-lastname-error']"),"warning on empty lastName");

    public ILabel warningMob_no = getElementFactory().getLabel(
            By.xpath("//div[@id='form-mobile-error']"),"warning on empty Mobile num");

    public ILabel warningEmail= getElementFactory().getLabel(
            By.xpath("//div[@class='form-group']//div[@id='form-email-error']"),"warning on empty email text");

    public ILabel warningPincode= getElementFactory().getLabel(
            By.xpath("//div[@id='form-pincode-error']"),"warning on empty pincode text");

    public ILabel warningCheckbox = getElementFactory().getLabel(
            By.xpath("//div[@id='signUpFormOtpError']"),"warning text on checkbox");

    public INRRegisterNowPage(){
        super(By.xpath("//h2[normalize-space()='Create New Account']"),"Register now page open");
    }

    public boolean isXiconDisplayed(){
        return xIcon.state().waitForDisplayed();
    }

    public void clickXicon(){
        xIcon.click();
    }


    public void enterAlreadyExistUserDetails(String firstName,String lastName,String Mob_no,String emailId,String pincode){
        firstNamelocator.clearAndType(firstName);
        lastNameLocator.clearAndType(lastName);
        MobileNo.clearAndType(Mob_no);
        emailID.clearAndType(emailId);
        pincodeLocator.clearAndType(pincode);

    }



    public void enterDetailsThenPressXicon(String firstName,String lastName,String Mob_no,String emailId,String pincode){
        enterAlreadyExistUserDetails(firstName,lastName,Mob_no,emailId,pincode);
        clickXicon();
        homepage.clickUserIcon();
        loginpage.clickOnRegisterNow();
    }

    public boolean verifyDataClearedwhenXicon(){
        String firstname = firstNamelocator.getText();
        if(firstname.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    public void RegisterUserDetails(String firstName,String lastName,String Mob_no,String emailId,String pincode){
        enterAlreadyExistUserDetails(firstName,lastName,Mob_no,emailId,pincode);
        termsCheckboxLabel.getJsActions().click();
        sendOtpBtn.click();
    }

    public boolean isWarningOnAlreadyExistUser(){
        return warningAlreadyExistUser.state().waitForDisplayed();
    }

    public boolean verifyOtpPopupOpen(){
        return verificationOtp.state().waitForDisplayed();
    }

    public boolean warningFirstNameEmpty(){
        return warningFirstName.state().waitForDisplayed();
    }

    public boolean warningLastNameEmpty(){
        return warningLastName.state().waitForDisplayed();
    }

    public boolean warningMob_noEmpty(){
        return warningMob_no.state().waitForDisplayed();
    }

    public boolean warningEmailEmpty(){
        return warningEmail.state().waitForDisplayed();
    }

    public boolean warningPincodeEmpty(){
        return warningPincode.state().waitForDisplayed();
    }

    public boolean warningCheckboxNotCheck(){
        return warningCheckbox.state().waitForDisplayed();
    }

    public void RegisterUserDetailsWithoutCheckboxCheck(String firstName,String lastName,String Mob_no,String emailId,String pincode){
        enterAlreadyExistUserDetails(firstName,lastName,Mob_no,emailId,pincode);
        sendOtpBtn.click();
    }






}
