package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {


    String productNameOne = "Sauce Labs Bike Light";
    String productNameTwo = "Sauce Labs Fleece Jacket";
    String user = "standard_user";
    String password = "secret_sauce";
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void checkAddOneProductToCart() {
        loginPage.open();
        loginPage.login(user, password);

        productsPage.clickAddToCartButton(productNameOne);
        productsPage.clickShoppingCart();

        String product = cartPage.getProductName(".inventory_item_name");
        assertEquals(product, productNameOne, "Incorrect item name");
    }

    @Test
    public void checkAddTwoProductToCart() {
        loginPage.open();
        loginPage.login(user, password);

        productsPage.clickAddToCartButton(productNameOne);
        productsPage.clickAddToCartButton(productNameTwo);
        productsPage.clickShoppingCart();

        String firstProductName = cartPage.getProductName("#item_0_title_link");
        String secondProductName = cartPage.getProductName("#item_5_title_link");

        String firstProductPrice = cartPage.getProductPrice(
                "//div[text() = " +
                        "'Sauce Labs Bike Light']/ancestor::div[@class = 'cart_item']" +
                        "//div[@data-test = 'inventory-item-price']");
        String secondProductPrice = cartPage.getProductPrice(
                "//div[text()=" +
                        "'Sauce Labs Fleece Jacket']" +
                        "/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']");
        softAssert.assertEquals(
                firstProductName,
                productNameOne,
                "Incorrect product name");
        softAssert.assertEquals(
                firstProductPrice,
                "$9.99",
                "Incorrect product price");
        softAssert.assertEquals(
                secondProductName,
                productNameTwo,
                "Incorrect product name");
        softAssert.assertEquals(
                secondProductPrice,
                "$Incorrect product price");
    }

    @Test
    public void checkRemoveProductFromCart() {
        loginPage.open();
        loginPage.login(user, password);

        productsPage.clickAddToCartButton(productNameOne);
        productsPage.clickShoppingCart();

        cartPage.clickButtonRemove(productNameOne);

        assertEquals(cartPage.getWebElementsListOfCart().size(),
                0,
                "If expected size > 0, cart isnt empty");
    }

    @Test
    public void checkButtonContinueShopping() {
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

        String firstProductName = cartPage.getProductName("#item_0_title_link");
        assertEquals(firstProductName, productNameOne, "Incorrect product name");
    }
}
