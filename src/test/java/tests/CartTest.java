package tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.*;

public class CartTest extends BaseTest{


    String productNameOne = "Sauce Labs Bike Light";
    String productNameTwo = "Sauce Labs Fleece Jacket";
    String user = "standard_user";
    String password = "secret_sauce";

    @Test
    public void checkAddOneProductToCart() {
        loginPage.open();
        loginPage.login(user, password);

        productsPage.clickAddToCartButton(productNameOne);
        productsPage.clickShoppingCart();

        String product = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
        assertEquals(product, "Sauce Labs Bike Light","Expected text - " + productNameOne);
    }

    @Test
    public void checkAddTwoProductToCart() {
        loginPage.open();
        loginPage.login(user, password);

        productsPage.clickAddToCartButton(productNameOne);
        productsPage.clickAddToCartButton(productNameTwo);
        productsPage.clickShoppingCart();

        String firstProductName = driver.findElement(By.cssSelector("#item_0_title_link")).getText();
        String secondProductName = driver.findElement(By.cssSelector("#item_5_title_link")).getText();

        String firstProductPrice = driver.findElement(By.xpath(
                "//div[text() = 'Sauce Labs Bike Light']/ancestor::div[@class = 'cart_item']" +
                        "//div[@data-test = 'inventory-item-price']")).getText();

        String secondProductPrice = driver.findElement(By.xpath(
                "//div[text()='Sauce Labs Fleece Jacket']" +
                        "/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']")).getText();
        assertEquals(firstProductName,
                productNameOne,
                "Expected text - " + productNameOne);
        assertEquals(firstProductPrice,"$9.99","Expected price - $9.99");
        assertEquals(secondProductName,
                productNameTwo,
                "Expected text - " + productNameTwo);
        assertEquals(secondProductPrice,"$49.99");
    }

    @Test
    public void checkRemoveProductFromCart(){
        loginPage.open();
        loginPage.login(user,password);

        productsPage.clickAddToCartButton(productNameOne);
        productsPage.clickShoppingCart();

        cartPage.clickButtonRemove(productNameOne);
        List<WebElement> webElementList = driver.findElements(By.cssSelector(".cart_quantity"));
        assertEquals(webElementList.size(),0,"If expected size > 0, cart isnt empty");
    }

    @Test
    public void checkButtonContinueShopping(){
        /*
        Открыть главную страницу
        Ввести логин и пароль
        Кликнуть на корзину
        Кликнуть на кнопку продолжить покупки
        Проверить наличие любого товара на странице с продуктами
         */
        loginPage.open();
        loginPage.login(user, password);

        productsPage.clickShoppingCart();
        cartPage.clickContinueShoppingButton();

        String firstProductName = driver.findElement(By.cssSelector("#item_0_title_link")).getText();
        assertEquals(firstProductName,
                productNameOne,
                "Expected text - " + productNameOne);
    }
}
