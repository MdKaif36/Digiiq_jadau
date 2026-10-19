package Pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AccountPage extends Form {

    public IButton logoutBtn = getElementFactory().getButton(
            By.xpath("//a[@class='btn']"),"logout button");

    public ILink logoutPopUp = getElementFactory().getLink(
            By.xpath("//a[@class='w-100 btn btn-primary js-logout-confirm-btn']"),"logout link popup");

    public AccountPage(){
        super(By.xpath("//h1[@id='dashboard-title']"),"account page");
    }

    public void clickLogout(){
        logoutBtn.getJsActions().scrollIntoView();
        logoutBtn.getJsActions().click();
    }

    public void clickLogoutPop_up(){
        logoutPopUp.click();
    }
}
