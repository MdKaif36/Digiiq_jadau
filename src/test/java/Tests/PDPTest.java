package Tests;

import Pages.*;
import Utilities.ScreenshotListener;
import aquality.selenium.browser.AqualityServices;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ScreenshotListener.class)
public class PDPTest extends BaseTest{

    Homepage homepage = new Homepage();
    PLPPage plpPage = new PLPPage();
    PDPPage pdpPage = new PDPPage();
    NavigationBar navigationBar = new NavigationBar();
    INRLoginpage inrLoginpage = new INRLoginpage();


    @Test
    public void pdpTest(){
        homepage.closePrivacyIcon();
        homepage.clickUserIcon();
        inrLoginpage.loginWithRegisterMobileNumber("6307780187");
        homepage.clickOnShop();
        String plpPrice = plpPage.plpProductPrice.getText();
        plpPage.discoverBtn.getJsActions().click();
        Assert.assertTrue(pdpPage.state().waitForDisplayed(),"pdp page is not open");
        Assert.assertTrue(pdpPage.pdpBreadcrumb.state().waitForDisplayed(),"pdp breadcrumb is not displayed");
        Assert.assertTrue(pdpPage.productDescription.state().waitForDisplayed(),"product description is not displayed");
        Assert.assertTrue(pdpPage.thumbnail.state().waitForDisplayed() && pdpPage.produactImage.state().waitForDisplayed(),"thumbnail is not displayed");
        Assert.assertTrue(pdpPage.nextImageBtn.state().isClickable() && pdpPage.prevImageBtn.state().isClickable(),"next and previous both btn not work");
        Assert.assertTrue(pdpPage.checkImageZoomOnCick(),"image is not zoom in on click");
        Assert.assertTrue(pdpPage.checkPrizeConsistant(plpPrice),"On PLP and PDP price is not consistant");
        AqualityServices.getBrowser().goBack();
        plpPage.chooseSorting("Sort By","Price Low To High");
        plpPage.clickDiscoverBtn();
        Assert.assertTrue(pdpPage.requestPriceBtn.state().waitForDisplayed(),"request price button is not displayed");
        // adding product to add to cart
        AqualityServices.getBrowser().goBack();
        plpPage.chooseSorting("Price Low To High","None");
        plpPage.discoverBtn.getJsActions().click();


        int prevQuanity = navigationBar.getPreviousQuantity();
        pdpPage.addToCart();
        pdpPage.waitUntilGotoCartDisplayed();
        Assert.assertTrue(pdpPage.cartSucessfully(prevQuanity),"product not add to cart");

        // wishlist  ------?--------

        // button is clickable
        Assert.assertTrue(pdpPage.shareBtn.state().isClickable(),"share button is not clickable");
        Assert.assertTrue(pdpPage.styleConfiguratorBtn.state().isClickable(),"style configurator button is not clickable");
        Assert.assertTrue(pdpPage.bookConsultantBtn.state().isClickable(),"book consultant button is not clickable");
        Assert.assertTrue(pdpPage.whatsppChatNowBtn.state().isClickable(),"whatsapp chat now button is not clickable");
        Assert.assertTrue(pdpPage.customizeYourDesign.state().isClickable(),"customize your design link not redirect customize page");

        //
        String[] productDetails = {"Product","Returns","Conditions"};
        for(String str: productDetails){
            pdpPage.checkExpandAndCollapse(str);
        }

        // authenticity guarantee section
        Assert.assertTrue(pdpPage.authenticityGuarantee.state().waitForDisplayed(),"Authenticity guarantee section is not loaded");
        Assert.assertTrue(pdpPage.termAndConditionContent.state().waitForDisplayed(),"term and condition content not displayed");

        // carousel in PDP
        Assert.assertTrue(pdpPage.similarProductCarouselSection.state().waitForDisplayed(),"similar product carousel is not displayed");
        Assert.assertTrue(pdpPage.relatedProductBtn.state().isClickable(),"related product is not present in carousel");
        Assert.assertTrue(pdpPage.similarStylesBtn.state().isClickable(),"similar product is not present in carousel");
        Assert.assertTrue(pdpPage.moreCutsaryBtn.state().isClickable(),"more cutsary is not present in carousel");
        Assert.assertTrue(pdpPage.isNextPrevCarouselBtnClickable(), "next and prev btn of carousel section is not clickable");

        Assert.assertTrue(pdpPage.isJadauPromiseSectionDisplayed(),"Jadau Promise Section is not present in PDP");
        Assert.assertTrue(pdpPage.isInstagramSectionDisplayed(),"Instagram section is not present in PDP");








    }
}
