package pages;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

@Log4j2
public class CartPage extends BasePage {

    private final By TITLE = By.cssSelector(".title");
    private final By CHECKOUT_BUTTON = By.cssSelector("#checkout");
    private final String REMOVE_PRODUCT_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//button";
    private final By CONTINUE_SHOPPING_BUTTON = By.cssSelector("#continue-shopping");
    static final By CART_LIST = By.cssSelector(".cart_quantity");
    private final String PATTERN_OF_PRODUCT_PRICE = "//div[text()= '%s']" +
            "/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']";
    String PATTERN_OF_PRODUCT_NAME = "//div[text() = '%s']"; // универсальный путь к любому продукту в корзине

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получение названия продукта {product}")
    public String getProductName(String product) {
        log.info("Product name is : '{}'",product);
        By xPathOfProductPrice = By.xpath(String.format(PATTERN_OF_PRODUCT_NAME, product)); // Создал путь используя формат
        return driver.findElement(xPathOfProductPrice).getText(); // Поиск элемента по пути, и получение названия
    }
    @Step("Получение цены продукта {product}")
    public String getProductPrice(String product) {
        log.info("Product price is : '{}'",product);
        By xPathOfProductPrice = By.xpath(String.format(PATTERN_OF_PRODUCT_PRICE, product));
        return driver.findElement(xPathOfProductPrice).getText();
    }

    public List<WebElement> getWebElementsListOfCart() {
        return driver.findElements(CART_LIST);
    }

    public void clickCheckOutButton() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    @Step("Нажатие на кнопку Continue")
    public void clickContinueShoppingButton() {
        log.info("Click button Continue shopping");
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    @Step("Нажатие на кнопку Remove")
    public void clickButtonRemove(String product) {
        log.info("Click button remove : '{}'",product);
        By removeFromCard = By.xpath(String.format(REMOVE_PRODUCT_PATTERN, product));
        driver.findElement(removeFromCard).click();
    }

    public String getTITLE() {
        return driver.findElement(TITLE).getText();
    }
}
