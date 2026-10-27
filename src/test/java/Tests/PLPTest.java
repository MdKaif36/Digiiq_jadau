package Tests;

import Pages.Homepage;
import Pages.PDPPage;
import Pages.PLPPage;
import Utilities.ScreenshotListener;
import aquality.selenium.browser.AqualityServices;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;


@Listeners(ScreenshotListener.class)
public class PLPTest extends BaseTest {

    Homepage homepage = new Homepage();
    PLPPage plpPage = new PLPPage();
    PDPPage pdpPage = new PDPPage();

    @Test
    public void plpTest() throws TimeoutException {
        homepage.closePrivacyIcon();
        homepage.clickOnShop();
        // check plp page is open
        Assert.assertTrue(plpPage.state().waitForDisplayed(),"PLP page is not open");
        // check breadcrumb displayed
        Assert.assertTrue(plpPage.shopBreadcrumb.state().waitForDisplayed(),"breadcrumb is not displayed");
        plpPage.clickOnHomeBreadcrumb();
        // check home breadcrumb is navigate to homepage
        Assert.assertTrue(homepage.state().waitForDisplayed(),"home breadcrumb is not navigate to homepage");
        homepage.clickOnShop();
        // check carousel displayed
        Assert.assertTrue(plpPage.carousel.state().waitForDisplayed(),"carousel is not displayed");
        // check right and left btn is clickable
        Assert.assertTrue(plpPage.leftCarouselBtn.state().isClickable(),"left btn is not clickable");
        Assert.assertTrue(plpPage.rightCarouselBtn.state().isClickable(),"right btn is not clickable");
        // check all filter displayed
        Assert.assertTrue(plpPage.allFilter.state().waitForDisplayed(),"all filter are not displayed");
        // click all filter expand and collapse
        HashMap<String , Integer> filterCategory = new HashMap<>();
        filterCategory.put("category",1);
        filterCategory.put("metal",2);
        filterCategory.put("price",3);
        filterCategory.put("gemstone",4);
        filterCategory.put("stoneColor",5);
        filterCategory.put("above",6);

        for(Map.Entry<String,Integer> entry: filterCategory.entrySet()){
            plpPage.filterHeader(entry.getValue()).getJsActions().click();
            Assert.assertTrue(plpPage.collapseFilter(entry.getValue()).state().waitForDisplayed(),
                    "Filter " + entry.getValue() + " did not expand (minus icon not shown)");

            // collapse
            plpPage.filterHeader(entry.getValue()).getJsActions().click();
            Assert.assertTrue(plpPage.expandFilter(entry.getValue()).state().waitForDisplayed(),
                    "Filter " + entry.getValue() + " did not collapse (plus icon not shown)");
        }
        // price filter
        plpPage.filterHeader(filterCategory.get("price")).getJsActions().click();
        plpPage.under5L.getJsActions().click();
        Assert.assertTrue(plpPage.compareFilterAndProductPrice(500000),"filter error");

        plpPage.clearAll.getJsActions().click();

        // select and check single filter
        plpPage.filterHeader(filterCategory.get("metal")).getJsActions().click();
        plpPage.selectMetalFilter.getJsActions().click();
        Assert.assertTrue(plpPage.showFilter22K.state().waitForDisplayed(),"checkbox is not checked");
        plpPage.discoverBtn.getJsActions().click();
        Assert.assertTrue(pdpPage.state().waitForDisplayed(),"pdp page is not open");

        AqualityServices.getBrowser().goBack();

        Assert.assertTrue(plpPage.showFilter22K.state().waitForDisplayed(),"checkbox is not checked");

        Assert.assertTrue(plpPage.state().waitForDisplayed(),"plp page is not open");


        // check product card is displayed
        //Assert.assertTrue(plpPage.productCard.state().waitForDisplayed(),"product is not displayed");
        // check price on request
        //plpPage.clickLowToHigh();
        //Assert.assertTrue(plpPage.priceOnRequestLabel.state().waitForDisplayed(),"price on request is not shown on product card");
        // click on discover on product card
       // plpPage.discoverBtn.getJsActions().click();
        // check pdp page open
        //Assert.assertTrue(pdpPage.state().waitForDisplayed(),"pdp page is not open");
        //AqualityServices.getBrowser().goBack();
       // Assert.assertTrue(plpPage.state().waitForDisplayed(),"plp page is not open");

        plpPage.clearAll.getJsActions().click();

        LinkedHashMap<String , String > currencyChoose = new LinkedHashMap<>();
        currencyChoose.put("INR","₹");
        currencyChoose.put("USD","$");
        currencyChoose.put("EUR","€");
        currencyChoose.put("AED","AED");

        for(Map.Entry<String , String > entry : currencyChoose.entrySet()){
            homepage.clickCurrencySelector(entry.getKey());
            Assert.assertEquals(plpPage.getCurrencySymbol(entry.getValue()),entry.getValue());
        }

        // <-----! wishlist locator not found--------->
        /*plpPage.wishlistIcon.getJsActions().scrollIntoView();
        plpPage.wishlistIcon.state().waitForDisplayed();
        plpPage.wishlistIcon.getJsActions().click();*/













    }
}
