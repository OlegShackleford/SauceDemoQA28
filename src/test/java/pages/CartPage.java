package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{


    private final By TITLE = By.cssSelector(".title");
    private final By CHECKOUT_BUTTON = By.cssSelector("#checkout");
    private final String REMOVE_PRODUCT_PATTERN =
            "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//button";

    private final By CONTINUE_SHOPPING_BUTTON = By.cssSelector("#continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckOutButton(){
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public void clickContinueShoppingButton(){
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    public void clickButtonRemove(String product){
        By removeFromCard = By.xpath(String.format(REMOVE_PRODUCT_PATTERN,product));
        driver.findElement(removeFromCard).click();
    }

    public String getTITLE() {
        return driver.findElement(TITLE).getText();
    }


}
