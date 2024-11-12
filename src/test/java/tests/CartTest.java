package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest{

    @Test
    public void checkCart() {
        String productName = "Sauce Labs Bike Light";

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.clickAddToCartButton(productName);
        productsPage.clickShoppingCart();

        String product = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
        assertEquals(product, "Sauce Labs Bike Light");
    }
}
