package tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class ProductPageTest extends BaseTest {


    String productName = "Sauce Labs Backpack";
    String user = "standard_user";
    String password = "secret_sauce";

    @Test
    public void checkProductOnaPage() {
        loginPage.open();
        loginPage.login(user, password);

        String product = productsPage.getProductName("#item_4_title_link");
        assertEquals(product, productName, "Incorrect product name");
    }

    @Test
    public void checkAddButton() {
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
        productsPage.clickButtonRemove("#remove-sauce-labs-backpack");
        productsPage.clickShoppingCart();

        assertEquals(productsPage.getWebElementsListOfCart().size(),
                0,
                "If expected size > 0, cart isnt empty");
    }
}
