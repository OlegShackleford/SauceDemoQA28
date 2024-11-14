package tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.*;


public class ProductPageTest extends BaseTest{


    String productNameOne = "Sauce Labs Backpack";
    String user = "standard_user";
    String password = "secret_sauce";

    @Test
    public void checkProductOnaPage(){
        loginPage.open();
        loginPage.login(user, password);

        String product = driver.findElement(By.cssSelector("#item_4_title_link")).getText();
        assertEquals(product,productNameOne,"Expected name " + productNameOne);
    }

    @Test
    public void checkAddButton(){
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

        productsPage.clickAddToCartButton(productNameOne);
        driver.findElement(By.cssSelector("#remove-sauce-labs-backpack")).click();
        productsPage.clickShoppingCart();

        List<WebElement> webElementList = driver.findElements(By.cssSelector(".cart_quantity"));
        assertEquals(webElementList.size(),0,"If expected size > 0, cart isnt empty");
    }
}
