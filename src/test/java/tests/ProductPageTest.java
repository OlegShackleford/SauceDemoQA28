package tests;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import lombok.extern.log4j.Log4j2;

public class ProductPageTest extends BaseTest {

    String productName = "Sauce Labs Backpack";

    @Test(testName = "Проверка отображения любого продукта на странице Product",
            priority = 1,
            groups = "fast",
            retryAnalyzer = Retry.class )
    @Description("Проверка отображения любого продукта на странице Product")
    public void checkProductPage() {
        loginPage.open();
        loginPage.login(user, password);

        String product = productsPage.getProductName(productName);
        assertEquals(product, productName, "Incorrect product name");
    }

    @Test(testName = "Проверка кнопки Remove на странице Product",
            priority = 2,
            groups = "fast")
    @Description("Проверка кнопки Remove на странице Product")
    public void checkRemoveButtonO() {
        /*
        Открыть главную страницу
        Ввести логин и пароль
        Добавить продукт в корзину
        Кликнуть на кнопку Remove выбранного продукта
        Кликнуть на корзину
        Проверить что корзина пуста
         */
        loginPage.open();
        loginPage.login(user, password);

        productsPage.clickAddToCartButton(productName);
        productsPage.clickAddToCartButton(productName);
        productsPage.clickShoppingCart();

        assertEquals(productsPage.getWebElementsListOfCart().size(),
                0,
                "If expected size > 0, cart isnt empty");
    }
}
