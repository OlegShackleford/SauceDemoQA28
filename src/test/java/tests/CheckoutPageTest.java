package tests;

import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

public class CheckoutPageTest extends BaseTest {

    public String firstName = "Vlad";
    public String lastName = "Tepes";
    public String zipcode = "010012";
    SoftAssert softAssert = new SoftAssert();

    @Test(testName = "Позитивный тест на заполнение трех полей",
            priority = 1,
            groups = "priority1", retryAnalyzer = Retry.class)
    @Description("Позитивный тест на заполнение трех полей на странице Checkout: Your Information")
    public void checkFullInformation() {
        loginPage.open();
        loginPage.login(user,password);
        checkoutPage.openPageCheckOutInfo();
        checkoutPage.inputFullInfo(firstName, lastName, zipcode);
        checkoutPage.clickToButtonContinue();

        assertEquals(checkoutPage.getTitleOverview(), "Checkout: Overview", "Incorrect text");
    }

    @Test(testName = "Проверка название товара, цены и общей стоимости",
            priority = 1,
            groups = "fast")
    @Description("Проверка название товара, цены и общей стоимости товара")
    public void checkOverviewOneProductNameAndPrice() {
        /*
        1.Открыть страницу Login
        2.Ввести логин и пароль
        3.Добавить продукт в корзину
        4.Переходим на страницу Your Information и заполняем поля
        5.Переходим на страницу OverView
        6.Сверяем Название продукта, цену и общую цену с налогом
         */
        loginPage.open();
        loginPage.login(user,password);
        productsPage.clickAddToCartButton("Sauce Labs Onesie");
        checkoutPage.openPageCheckOutInfo();
        checkoutPage.inputFullInfo(firstName, lastName, zipcode);
        checkoutPage.openPageCheckOutOverview();

        softAssert.assertEquals(checkoutPage.getProductName("Sauce Labs Onesie"),
                "Sauce Labs Onesie",
                "Incorrect producte name");
        softAssert.assertEquals(checkoutPage.getProductPrice("Sauce Labs Onesie"),
                "$7.99",
                "Incorrect price");
        softAssert.assertEquals(checkoutPage.getTotalPrice(),
                "8.63",
                "Incorrect total price");
        softAssert.assertAll();
    }

    @Test(testName = "Проверка сообщения об успешном завершении покупки",
            priority = 1,
            groups = "fast")
    @Description("Проверка сообщения об успешном завершении покупки")
    public void CheckCompleteCheckout() {
        /*
        1.Открыть страницу Login
        2.Ввести логин и пароль
        3.Добавить продукт в корзину
        4.Переходим на страницу Your Information и заполняем поля
        5.Переходим на страницу OverView
        6.Нажимаем кнопку Finish
        7.Проверяем сообщение об успешной покупке
         */
        loginPage.open();
        loginPage.login(user,password);
        productsPage.clickAddToCartButton("Sauce Labs Onesie");
        checkoutPage.openPageCheckOutInfo();
        checkoutPage.inputFullInfo(firstName, lastName, zipcode);
        checkoutPage.openPageCheckOutOverview();
        checkoutPage.clickButtonFinish();
        assertEquals(checkoutPage.getCompleteMessage(),
                "Thank you for your order!",
                "Incorrect message");
//ОШИБКА ТУТ
    }

    @Test(testName = "Негативные тесты страницы Checkout:info",
            description = "Тесты: пустое поле user,password,zipcode",
            priority = 2, groups = "fast",
            dataProvider = "inputData")
    @Description("Негативные тесты страницы Checkout:info. Тестирование пустое поле user, password, zipcode")
    public void checkIncorrectInputInfo(String userName, String lastName, String zipcode, String errorMessage) {
        /*
        1.Открыть страницу Login
        2.Ввести логин и пароль
        3.Открываем первую страницу CheckOut:Your Information
        4.Вводим некорректные данные
        5.Нажимаем клавишу Continue для попытки перехода на следующую страницу
        6.Сверяем сообщения об ошибках
         */
        loginPage.open();
        loginPage.login(user,password);
        checkoutPage.openPageCheckOutInfo();
        checkoutPage.inputFullInfo(userName, lastName, zipcode);
        checkoutPage.clickToButtonContinue();
        assertEquals(checkoutPage.getErrorMessage(), errorMessage);
    }

    @DataProvider()
    public Object[][] inputData() {
        return new Object[][]{
                {"", lastName, zipcode, "Error: First Name is required"},
                {firstName, "", zipcode, "Error: Last Name is required"},
                {firstName, lastName, "", "Error: Postal Code is required"}
        };
    }
}
