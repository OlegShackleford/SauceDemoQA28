package pages;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import static pages.CartPage.CART_LIST;

@Log4j2
public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector(".title");
    private final By CART_LINK = By.cssSelector("[data-test = shopping-cart-link]");
    private final String ADD_TO_CART_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'inventory_item']//button";
    private String PATTERN_OF_PRODUCT_NAME = "//div[text() = '%s']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получение название продукта")
    public String getProductName(String product) {
        log.info("Get product name: '{}'",product);
        By xPathOfProductName = By.xpath(String.format(PATTERN_OF_PRODUCT_NAME, product));
        return driver.findElement(xPathOfProductName).getText();
    }

    public List<WebElement> getWebElementsListOfCart() {
        return driver.findElements(CART_LIST);
    }

    @Step("Нажатие на клавишу Add")
    public void clickAddToCartButton(String product) {
        log.info("Click button ADD '{}'",product);
        By addToCart = By.xpath(String.format(ADD_TO_CART_PATTERN, product));
        driver.findElement(addToCart).click();
    }

    @Step("Переход в корзину")
    public void clickShoppingCart() {
        log.info("CLick to shopping cart");
        driver.findElement(CART_LINK).click();
    }

    public String getTitle() {
        log.info("Get title");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return driver.findElement(TITLE).getText();
    }
}
