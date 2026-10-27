package Pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.concurrent.TimeoutException;


public class PLPPage extends Form {

    public ILabel shopBreadcrumb = getElementFactory().getLabel(
            By.xpath("//a[@class='text-decoration-none'][normalize-space()='SHOP']"),"Shop humberger");

    public ILabel homeBreadcrumb = getElementFactory().getLabel(
            By.xpath("//a[normalize-space()='Home']"),"Home Breadcrumb");

    public ILabel carousel = getElementFactory().getLabel(
            By.xpath("//div[@class='card-nav slick-slide slick-current slick-active']"),"carousel");

    public ILabel productCard = getElementFactory().getLabel(
            By.xpath("//body//div[@class='page jadau-page']//div[@class='row']//div[@class='row']//div[1]//div[1]//div[1]//div[1]//a[1]//div[1]//img[1]"),"Product card");

    public ILabel priceOnRequestLabel = getElementFactory().getLabel(
            By.xpath("//body//div[@class='page jadau-page']//div[@class='row']//div[@class='row']//div[1]//div[1]//div[1]//div[2]//div[1]//span[1]//span[1]//span[1]"),"price on request");

//    public ILabel sortByDropdown= getElementFactory().getLabel(
//            By.xpath("//span[normalize-space()='Sort By']"),"click sort by");

//    public ILabel selectSorting= getElementFactory().getLabel(
//            By.xpath(String.format("//div[normalize-space()='%s]",sortingText)),"select Sorting");

    public IButton rightCarouselBtn= getElementFactory().getButton(
            By.xpath("//button[@id='nextBtn']"),"right btn");

    public IButton leftCarouselBtn= getElementFactory().getButton(
            By.xpath("//button[@id='prevBtn']"),"left btn");

    public ILabel discoverBtn = getElementFactory().getLabel(
            By.xpath("//div[@class='product-info']//a[contains(text(),'Discover')]"),"Discover");
                                    //body//div[@class='page jadau-page']//div[@class='row']//div[@class='row']//div[1]//div[1]//div[1]//div[2]//div[2]//a[1]
    public ILabel allFilter = getElementFactory().getLabel(
            By.xpath("//div[@class='refinement-bar jadau-refinement-bar col-lg-2 p-0']//div[@class='hide-in-mobile']"),"filter");

    public String expand = "(//*[name()='svg'][@class='icon icon-plus'])[%d]";
    public String collapse= "(//*[name()='svg'][@class='icon icon-minus'])[%d]";

    public ILabel selectMetalFilter = getElementFactory().getLabel(
            By.xpath("//label[@for='filter-22k']"),"select metal filter");

    public ILabel showFilter22K = getElementFactory().getLabel(
            By.xpath("//span[@class='m-1']"),"show 22k filter");

    public ILabel under5L= getElementFactory().getLabel(
            By.xpath("//div[@class='jadau-card collapsible-sm refinement refinement-by-price active']//label[@for='filter-under-5l'][normalize-space()='Under 5L']"),"under 5 lakh");

    public ILabel firstProductPriceUnder5L= getElementFactory().getLabel(
            By.xpath("//span[contains(text(),'₹ 4,53,200')]"),"under 5L product");

    public ILink clearAll = getElementFactory().getLink(
            By.xpath("//a[normalize-space()='Clear All']"),"clear all filter");

    public ILabel plpProductPrice = getElementFactory().getLabel(
            By.xpath("//p[@class='product-name' and normalize-space()='Floral Polki Pachi Necklace with Elegant Detailing']/ancestor::div[@class='product-info']//span[@class='sales']"),"product prize on PLP");

    public ILabel wishlistIcon = getElementFactory().getLabel(
            By.xpath("(//*[name()='svg'][@class='icon wishlist-icon'])[1]"),"wishlist icon");

    public ILabel requestOnPriceOnPLp= getElementFactory().getLabel(
            By.xpath("//span[contains(@class,'value') and normalize-space()='Price on request']"),"request on price on plp");

    public PLPPage(){
        super(By.xpath("//h2[normalize-space()='SHOP']"),"PLP page Open");
    }

    public void clickOnHomeBreadcrumb(){
        homeBreadcrumb.getJsActions().click();
    }

    public ILabel sortByDropdown(String sortBy){
        return getElementFactory().getLabel(
                By.xpath(String.format("//span[normalize-space()='%s']",sortBy)),"click sort by");
    }

    public ILabel selectSorting(String sortingText) {
        return getElementFactory().getLabel(
                By.xpath(String.format("//div[normalize-space()='%s']", sortingText)),
                "Select sorting"
        );
    }

    public void chooseSorting(String sort, String sorting){
        sortByDropdown(sort).getJsActions().click();
        selectSorting(sorting).getJsActions().click();
    }

//    public ILabel expandFilter(int i) {
//        return getElementFactory().getLabel(
//                By.xpath(String.format(expand, i)),
//                "Expand Filter"
//        );
//    }
//
//    public ILabel collapseFilter(int i) {
//        return getElementFactory().getLabel(
//                By.xpath(String.format(collapse, i)),
//                "collapse Filter"
//        );
//    }

    private final String accordionHeader = "(//div[contains(@class,'accordion-header')])[%d]";
    private final String plusIcon  = accordionHeader + "//*[name()='svg' and contains(@class,'icon-plus')]";
    private final String minusIcon = accordionHeader + "//*[name()='svg' and contains(@class,'icon-minus')]";

    // Click target: HTML element, so JS click works
    public ILabel filterHeader(int i) {
        return getElementFactory().getLabel(
                By.xpath(String.format(accordionHeader, i)), "Filter header " + i);
    }

    public ILabel expandFilter(int i) {
        return getElementFactory().getLabel(
                By.xpath(String.format(plusIcon, i)), "Expand icon " + i);
    }

    public ILabel collapseFilter(int i) {
        return getElementFactory().getLabel(
                By.xpath(String.format(minusIcon, i)), "Collapse icon " + i);
    }

    public boolean compareFilterAndProductPrice(int amount){
        String[] actualPrize = firstProductPriceUnder5L.getText().split(" ");

        int productPrize= Integer.parseInt(actualPrize[1].replace(",",""));
        System.out.println(productPrize);
        if(productPrize <= amount){
            return true;
        }
        else{
            return false;
        }
    }
//
//    public String getCurrencySymbol() {
//
//        productPrice.getJsActions().scrollIntoView();
//        String[] fullPrize = productPrice.getText().split(" ");
//        System.out.println(fullPrize[0]);
//        return fullPrize[0];
//
//    }

    public String getCurrencySymbol(String expectedSymbol) throws TimeoutException {
        plpProductPrice.getJsActions().scrollIntoView();

        // wait until the price shows the expected currency
        AqualityServices.getConditionalWait().waitForTrue(
                () -> plpProductPrice.getText().trim().startsWith(expectedSymbol),
                Duration.ofSeconds(10), Duration.ofMillis(5000),
                "Price did not change to " + expectedSymbol);

        String[] price = plpProductPrice.getText().split(" ");
        return price[0];

    }
    public ILink discoverbtnNew =getElementFactory().getLink(
            By.xpath("//div[@class='price']//span[contains(text(),'Price on request')]/ancestor::div[@class='product-info']//a[contains(@class,'discover-btn')]"),"new discover btn");

    public void clickDiscoverBtn(){
        AqualityServices.getConditionalWait().waitFor(
                () -> requestOnPriceOnPLp.state().waitForDisplayed(),
                Duration.ofSeconds(10), Duration.ofMillis(10000),
                "wait for request on price label");

        //discoverbtnNew.getJsActions().scrollIntoView();
        discoverbtnNew.getJsActions().click();

    }

// //body//div[@class='page jadau-page']//div[@class='row']//div[@class='row']//div[1]//div[1]//div[1]//div[2]//div[2]//a[1]
// //body//div[@class='page jadau-page']//div[@class='row']//div[@class='row']//div[1]//div[1]//div[1]//div[2]//div[2]//a[1]










}
