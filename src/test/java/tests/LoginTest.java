package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    String user = "standard_user";
    String password = "secret_sauce";

    @Test(testName = "Проверка позитивного логина",
            priority = 1,retryAnalyzer = Retry.class, groups = "fast")
    @Epic("Модуль логина интернет-магазина") //Глобальная задача
    @Feature("TMS-25") // подгруппа эпика
    @Story("Три вопроса(кто я, что я, чего хочу")
    @Severity(SeverityLevel.CRITICAL)
    @Link("Ссылка на требования")
    @Owner("Автор теста")
    @Issue("Ссылка на баг-репорт систему")//
    @TmsLink("Ссылка на тест-кейс")//ссылки можно указывать в алюр.пропертис
    @Description("Проверка входа в магазин юзера с позитивным входом")
    //Подробное описание, это важно. Все остальное нет
    @Flaky //Если тест нестабильный
    //ретрай вешается на тесты которые работают нестабильно
    public void checkCorrectLogin() {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(productsPage.getTitle(),
                "Products",
                "Переход на страницу не выполнен");
    }

    @Test(testName = "Проверка входа с пустым User Name", priority = 2, groups = "fast")
    @Description("Проверка входа с пустым User Name")
    public void checkLoginWithEmptyUserName() {
        loginPage.open();
        loginPage.login("", password);
        assertEquals(loginPage.getError(),
                "Epic sadface: Username is required",
                "Incorrect error message");
    }

    @Test(testName = "Проверка входа с пустым Password", priority = 2, groups = "fast")
    @Description("Проверка входа с пустым Password")
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login(user, "");
        assertEquals(loginPage.getError(),
                "Epic sadface: Password is required",
                "Incorrect error message");
    }

    @Test(testName = "Проверка входа с не корректным полем User", priority = 2, groups = "fast")
    @Description("Проверка входа с не корректным полем User")
    public void checkNotCorrectUser() {
        loginPage.open();
        loginPage.login("11111", password);
        assertEquals(loginPage.getError(),
                "Epic sadface: Username and password do not match any user in this service",
                "Incorrect error message");
    }

    @Test(testName = "Проверка входа с не корректным полем Password")
    @Description("Проверка входа с не корректным полем Password")
    public void checkNotCorrectLogin() {
        loginPage.open();
        loginPage.login(user, "11111");
        assertEquals(
                loginPage.getError(),
                "Epic sadface: Username and password do not match any user in this service",
                "Incorrect error message");
    }


    @DataProvider() // Объект тестовых данных для единообразных тестов.
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"standard_user", "123123131", "Epic sadface: Username and password do not match any user in this service"},
        };
    }

//    @Test(testName = "Тестовый метод",
//            description = "Учебный пример использования dataProvider",
//            dataProvider = "loginData", enabled = true)
//    // enabled нужен если баг теста будут фиксить позже, поэтому его нужно пропустить
//    /*
//    1. В параметр аннотации передаем объект вложенного класса dataProvider с именем класса
//    2. В параметр метода передаем тестовые данные из loginData
//    3. Тест заменяет по факту три негативных теста с проверкой разных тестовых данных
//    4. Нельзя использовать в позитивных сценариях
//     */
//    public void test(String user, String password, String expectedError) {
//        loginPage.open();
//        loginPage.login(user, password);
//        assertEquals(loginPage.getError(), expectedError);
//    }
}
