package tests;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest{


    @Test
    public void checkCorrectLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(
                productsPage.getTitle(),
                "Products",
                "Переход на страницу не выполнен");
    }

    @Test
    public void checkLoginWithEmptyUserName() {
        loginPage.open();
        loginPage.login("","secret_sauce");
        assertEquals(loginPage.getError(),
                "Epic sadface: Username is required",
                "Expected error: Epic sadface: Username is required");
    }

    @Test
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("user-name","");
        assertEquals(loginPage.getError(),
                "Epic sadface: Password is required",
                "Expected error: Epic sadface: Password is required");
    }

    @Test
    public void checkNotCorrectUser() {
        loginPage.open();
        loginPage.login("11111", "secret_sauce");
        assertEquals(
                loginPage.getError(),
                "Epic sadface: Username and password do not match any user in this service",
                "Expected message - Username and password do not match any user in this service");
    }

    @Test
    public void checkNotCorrectLogin() {
        loginPage.open();
        loginPage.login("standard_user", "11111");
        assertEquals(
                loginPage.getError(),
                "Epic sadface: Username and password do not match any user in this service",
                "Expected message - Username and password do not match any user in this service");
    }
}
