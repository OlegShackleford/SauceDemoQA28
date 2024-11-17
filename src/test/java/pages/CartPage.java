package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CartPage extends BasePage {

    private final By TITLE = By.cssSelector(".title");
    private final By CHECKOUT_BUTTON = By.cssSelector("#checkout");
    private final String REMOVE_PRODUCT_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//button";
    private final By CONTINUE_SHOPPING_BUTTON = By.cssSelector("#continue-shopping");
    static final By CART_LIST = By.cssSelector(".cart_quantity");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName(String cssLocatorOfProductName) {
        return driver.findElement(By.cssSelector(cssLocatorOfProductName)).getText();
    }

    public String getProductPrice(String xPathLocatorOfProductPrice) {
        return driver.findElement(By.xpath(xPathLocatorOfProductPrice)).getText();
    }

    public List<WebElement> getWebElementsListOfCart() {
        return driver.findElements(CART_LIST);
    }

    public void clickCheckOutButton() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public void clickContinueShoppingButton() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    public void clickButtonRemove(String product) {
        By removeFromCard = By.xpath(String.format(REMOVE_PRODUCT_PATTERN, product));
        driver.findElement(removeFromCard).click();
    }

    public String getTITLE() {
        return driver.findElement(TITLE).getText();
    }
}
