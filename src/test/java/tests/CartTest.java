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

    @Test(testName = "Проверка одного продукта в корзине", priority = 1, groups = "fast")
    public void checkAddOneProductToCart() {
        loginPage.open();
        loginPage.login(user, password);

        productsPage.clickAddToCartButton(productNameOne);
        productsPage.clickShoppingCart();

        String product = cartPage.getProductName(productNameOne);
        assertEquals(product, productNameOne, "Incorrect item name");
    }

    @Test(testName = "Проверка двух товаров в корзине", priority = 2, groups = "fast")
    public void checkAddTwoProductToCart() {
        loginPage.open();
        loginPage.login(user, password);

        productsPage.clickAddToCartButton(productNameOne);
        productsPage.clickAddToCartButton(productNameTwo);
        productsPage.clickShoppingCart();

        String firstProductName = cartPage.getProductName(productNameOne);
        String secondProductName = cartPage.getProductName(productNameTwo);

        String firstProductPrice = cartPage.getProductPrice(productNameOne);
        String secondProductPrice = cartPage.getProductPrice(productNameTwo);

        softAssert.assertEquals(firstProductName, productNameOne, "Incorrect product name");
        softAssert.assertEquals(firstProductPrice, "$9.99", "Incorrect product price");
        softAssert.assertEquals(secondProductName, productNameTwo, "Incorrect product name");
        softAssert.assertEquals(secondProductPrice, "$49.99", "Incorrect product price");
        softAssert.assertAll();
    }

    @Test(testName = "Проверка удаления продукта из корзины", priority = 2, groups = "slow")
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

    @Test(testName = "Проверка функции продолжить покупки",
            description = "Переход из корзины на страницу Product",
            priority = 2, groups = "fast")
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

        String firstProductName = cartPage.getProductName(productNameOne);
        assertEquals(firstProductName, productNameOne, "Incorrect product name");
    }
}
