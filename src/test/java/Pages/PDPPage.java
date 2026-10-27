package Pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;

public class PDPPage extends Form {

    public ILabel pdpBreadcrumb = getElementFactory().getLabel(
            By.xpath("//div[@aria-label='Breadcrumb']"),"pdp breadcrumb");

    public ILabel productDescription= getElementFactory().getLabel(
            By.xpath("//div[@class='product-desc hide-in-mobile']"),"product description");

    public ILabel thumbnail= getElementFactory().getLabel(
            By.xpath("//div[@class='thumbnail-container']"),"thumbmail");

    public ILabel produactImage= getElementFactory().getLabel(
            By.xpath("//div[@class='main-image-container']"),"product image");

    public IButton nextImageBtn = getElementFactory().getButton(
            By.xpath("//button[normalize-space()='Next']"),"next btn");

    public IButton prevImageBtn = getElementFactory().getButton(
            By.xpath("//button[normalize-space()='Previous']"),"previous btn");

    public IElement productImage = getElementFactory().getLabel(
            By.xpath("//img[@id='main-image-0']"),"product image");

    public  ILabel pdpProductPrice = getElementFactory().getLabel(
            By.xpath("//span[@class='price m-0']"),"product price on PDP");

    public IButton requestPriceBtn = getElementFactory().getButton(
            By.xpath("//button[normalize-space()='Request Price']"),"request price btn");

    public IButton addToCartBtn = getElementFactory().getButton(
            By.xpath("//button[normalize-space()='Add to Cart']"),"Add to cart button");

    public ILabel cartQuantity = getElementFactory().getLabel(
            By.xpath("//span[@class='minicart-quantity']"),"cart quantity");

    public ILabel goToCart = getElementFactory().getLabel(
            By.xpath("//a[normalize-space()='Go to Cart']"),"Go to Cart");

    public ILabel shareBtn = getElementFactory().getLabel(
            By.xpath("//div[@class='share-btn']"),"share btn");

    public IButton styleConfiguratorBtn = getElementFactory().getButton(
            By.xpath("//button[@class='btn btn-secondary style-configurator-btn w-100 mw-100 gap-10']"),"style configurator btn");

    public IButton bookConsultantBtn = getElementFactory().getButton(
            By.xpath("//button[@id='videoButton']"),"book consultant button");

    public IButton whatsppChatNowBtn = getElementFactory().getButton(
            By.xpath("//button[normalize-space()='Chat Now']"),"whatspp chat now button");

    public ILabel customizeYourDesign = getElementFactory().getLabel(
            By.xpath("//div[@class='item-section item-row customize-design']//p[contains(text(),'Customize this Design')]"),"customize your design link");




    public ILabel authenticityGuarantee = getElementFactory().getLabel(
            By.xpath("//h2[contains(text(),'Authenticity Guarantee')]"),"authenticity guarantee heading");

    public ILabel similarProductCarouselSection = getElementFactory().getLabel(
            By.xpath("//h2[normalize-space()='You May also Like']"),"similar Product section");

    public IButton relatedProductBtn = getElementFactory().getButton(
            By.xpath("//button[normalize-space()='Related Products']"),"related product in carousel");
    public IButton similarStylesBtn = getElementFactory().getButton(
            By.xpath("//button[normalize-space()='Similar Styles']"),"related product in carousel");
    public IButton moreCutsaryBtn = getElementFactory().getButton(
            By.xpath("//button[normalize-space()='More Cutsary']"),"related product in carousel");

    public IButton nextCarouselProductBtn = getElementFactory().getButton(
            By.xpath("//button[@class='carousel-btn next slick-arrow']"),"next btn");
    public IButton prevCarouselProductBtn = getElementFactory().getButton(
            By.xpath("//button[@class='carousel-btn prev slick-arrow slick-disabled']"),"prev btn");

    public ILabel jadauPromiseSection = getElementFactory().getLabel(
            By.xpath("//div[@class='promise-section-container']"),"jadau promise section");

    public ILabel instagramSection = getElementFactory().getLabel(
            By.xpath("//h2[normalize-space()='Shop Our Instagram Looks']"),"instagram section");


    public ILabel termAndConditionContent = getElementFactory().getLabel(
            By.xpath("//p[contains(text(),'All jewelry items are handcrafted by various artis')]"),"Term and Condition content");



    PLPPage plpPage = new PLPPage();

    public PDPPage(){
        super(By.xpath("//span[@class='ready-to-ship']"),"PDP page");
    }

    public boolean checkImageZoomOnCick(){
        productImage.state().waitForDisplayed();
        Dimension size = productImage.getElement().getSize();
        productImage.getJsActions().click();
        Dimension newSize = productImage.getElement().getSize();

        if(!newSize.equals(size)){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkPrizeConsistant(String price){

        pdpProductPrice.state().waitForDisplayed();

        AqualityServices.getConditionalWait().waitFor(
                () -> !pdpProductPrice.getText().trim().isEmpty(),
                "PDP price text is empty");

        String pdpPrice = normalize(pdpProductPrice.getText());
        String plpPrice=  normalize(price);

        System.out.println(plpPrice+" "+pdpPrice);
        if(plpPrice.equals(pdpPrice)){
            return  true;
        }
        else{
            return  false;
        }
    }

    private String normalize(String price) {
        // removes spaces, non-breaking spaces and the currency symbol
        return price.replace("\u00A0", "").replaceAll("\\s+", "").replace("₹", "");
    }

    public void waitForRequestBtn(){
        AqualityServices.getConditionalWait().waitFor(
                () -> requestPriceBtn.state().waitForDisplayed(),
                Duration.ofSeconds(10), Duration.ofMillis(5000),
                "request price btn not shown ");


    }


    public void addToCart(){
        addToCartBtn.state().waitForClickable();
        addToCartBtn.getJsActions().click();
    }

    public boolean cartSucessfully(int prevQuantity){
        int  currentQuantity = Integer.parseInt(cartQuantity.getText());
        if(currentQuantity > prevQuantity){
            return true;
        }
        else{
            return false;
        }
    }

    public void waitUntilGotoCartDisplayed(){
        AqualityServices.getConditionalWait().waitFor(
                ()->goToCart.state().waitForDisplayed()
        );
    }

    public boolean isJadauPromiseSectionDisplayed(){
        jadauPromiseSection.getJsActions().scrollIntoView();
        return jadauPromiseSection.state().waitForDisplayed();
    }

    public  boolean isInstagramSectionDisplayed(){
        instagramSection.getJsActions().scrollIntoView();
        return instagramSection.state().waitForDisplayed();
    }

    public ILabel productDetailsLocator(String str){
        return getElementFactory().getLabel(
                By.xpath(String.format("//span[contains(text(),'%s')]",str)),"product details "+ str +" locator ");
    }

    public void checkExpandAndCollapse(String str){
        ILabel locator = productDetailsLocator(str);
        locator.getJsActions().scrollIntoView();
        locator.state().waitForClickable();
        locator.getJsActions().click();

    }

    public boolean isNextPrevCarouselBtnClickable(){
        if(nextCarouselProductBtn.state().isClickable() && prevCarouselProductBtn.state().isClickable()){
            return true;
        }
        else {
            return false;
        }
    }


}

//div[@class='custom-accordion-wrapper product-detail-wrapper']//