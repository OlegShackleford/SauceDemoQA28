package tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {


    String user = "standard_user";
    String password = "secret_sauce";

    @Test
    public void checkCorrectLogin() {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(
                productsPage.getTitle(),
                "Products",
                "Переход на страницу не выполнен");
    }

    @Test
    public void checkLoginWithEmptyUserName() {
        loginPage.open();
        loginPage.login("", password);
        assertEquals(loginPage.getError(),
                "Epic sadface: Username is required",
                "Incorrect error message");
    }

    @Test
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login(user, "");
        assertEquals(loginPage.getError(),
                "Epic sadface: Password is required",
                "Incorrect error message");
    }

    @Test
    public void checkNotCorrectUser() {
        loginPage.open();
        loginPage.login("11111", password);
        assertEquals(
                loginPage.getError(),
                "Epic sadface: Username and password do not match any user in this service",
                "Incorrect error message");
    }

    @Test
    public void checkNotCorrectLogin() {
        loginPage.open();
        loginPage.login(user, "11111");
        assertEquals(
                loginPage.getError(),
                "Epic sadface: Username and password do not match any user in this service",
                "Incorrect error message");
    }
}
