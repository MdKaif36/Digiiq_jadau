package Pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class Homepage extends Form {

    public ILabel userIcon = AqualityServices.getElementFactory().getLabel(
            By.xpath("//a[contains(@class,'login-modal')]"), "User icon");

    public IButton xPrivacyIcon = AqualityServices.getElementFactory().getButton(
            By.xpath("//button[@id='jadauConsentClose']"),"close privacy pop-up");

    public ILabel currencyDropdown = getElementFactory().getLabel(
            By.xpath("//div[@class='search-group-section']//span[@class='selectedText']"),
            "currency dropdown");;

    // Dropdown trigger (the "₹INR" button near the search icon)
    private final ILabel currencyTrigger = getElementFactory().getLabel(
            By.xpath("//div[contains(@class,'currency-selector')]//div[contains(@class,'customSelect')]"),
            "Currency selector");

    public ILabel userIconAfterLogin = getElementFactory().getLabel(
            By.xpath("//div[@class='header-account-section']//a[@aria-label='My Account']//*[name()='svg']"),"click on user icon after login");


    public Homepage() {
        super(By.xpath("//*[contains(text(),\"WORLD OF JADAU\")]"), "Homepage");
    }

    public void closePrivacyIcon(){
        xPrivacyIcon.click();
    }

    public void clickUserIcon() {
        userIcon.state().waitForDisplayed();
        userIcon.click();
    }

    // Dynamic option locator by data-value
    private ILabel currencyOption(String code) {
        return getElementFactory().getLabel(
                By.xpath("//div[contains(@class,'custom-dropdown-menu')]//div[@data-value='" + code + "']"),
                "Currency option " + code);

    }

    public boolean loginSuccesfully(String code){
        currencyTrigger.click();
        ILabel option = currencyOption(code);
        return option.state().waitForDisplayed();
    }

    public void clickOnUserIconAfterLogin(){
        userIconAfterLogin.click();
    }

    public void clickCurrencySelector(String code ){
        currencyDropdown.getJsActions().click();
        ILabel option = currencyOption(code);
        option.state().waitForDisplayed();             // wait for menu to render
        option.getJsActions().click();
    }



}