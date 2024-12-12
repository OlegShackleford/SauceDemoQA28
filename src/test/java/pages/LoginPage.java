package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.AllureUtils;

@Log4j2
public class LoginPage extends BasePage {


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By USER_INPUT = By.id("user-name");
    private final By PASSWORD_INPUT = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR_MESSAGE = By.xpath("//h3[@data-test = 'error']");

    @Step("Открытие страницы логина")
    public void open() {
        log.info("Open mane page");
        driver.get("https://www.saucedemo.com/");
    }

    @Step("Вход в систему с логином {user} и паролем {password}")
    public void login(String user, String password) {
        log.info("Login user '{}', password '{}'",user,password);
        driver.findElement(USER_INPUT).sendKeys(user);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
//        AllureUtils.takeScreenshot(driver);// Можно добавить этц строку, чтоб каждый раз делался скриншот
    }

    public String getError() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
