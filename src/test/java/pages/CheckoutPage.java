package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
@Log4j2
public class CheckoutPage extends BasePage {

    private final By FIRST_NAME_INPUT = By.xpath("//input[@data-test = 'firstName']");
    private final By LAST_NAME_INPUT = By.xpath("//input[@data-test = 'lastName']");
    private final By ZIP_Postal_CODE = By.xpath("//input[@data-test = 'postalCode']");
    private final By CONTINUE_BUTTON = By.xpath("//input[@data-test = 'continue']");
    private final By TITLE_OVERVIEW = By.xpath("//span[text() = 'Checkout: Overview']");
    private final By ERROR_MESSAGE = By.xpath("//h3[@data-test = 'error']");
    private final String PATTERN_PRODUCT_NAME = "//div[text() = '%s']";
    private final String PATTERN_PRODUCT_PRICE = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']" +
            "//div[@class = 'inventory_item_price']";
    private final By TOTAL_PRODUCT_PRICE = By.xpath("//div[@class = 'summary_total_label']");
    private final By COMPLETE_MESSAGE = By.xpath("//h2[@class = 'complete-header']");

    @Step("Получение сообщения об успешной покупке")
    public String getCompleteMessage(){
        log.info("Get complete message");
        return driver.findElement(COMPLETE_MESSAGE).getText();
    }

    @Step("Нажатие на клавишу Finish")
    public void clickButtonFinish(){
        log.info("CLick button finish");
        driver.findElement(By.xpath("//button[@id = 'finish']")).click();
    }

    @Step("Получение название продукта")
    public String getProductName(String product) {
        log.info("Get product name: '{}'",product);
        By xPathOfProductName = By.xpath(String.format(PATTERN_PRODUCT_NAME, product));
        return driver.findElement(xPathOfProductName).getText();
    }

    @Step("Получение цены продукта")
    public String getProductPrice(String product) {
        log.info("Get product price: '{}'",product);
        By xPathOfProductPrice = By.xpath(String.format(PATTERN_PRODUCT_PRICE, product));
        return driver.findElement(xPathOfProductPrice).getText();
    }

    @Step("Получение общей стоимости")
    public String getTotalPrice() {
        log.info("Get total price");
        String totalText = driver.findElement(TOTAL_PRODUCT_PRICE).getText();
        return totalText.replace("Total: $","");
    }

    public String getTitleOverview() {
        log.info("Get title Overview");
        return driver.findElement(TITLE_OVERVIEW).getText();
    }

    @Step("Получение сообщения об ошибке")
    public String getErrorMessage() {
        log.info("Get error message");
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    @Step("Открытие страницы CheckOut:Info")
    public void openPageCheckOutInfo() {
        log.info("Open page CheckOutInfo");
        driver.get("https://www.saucedemo.com/checkout-step-one.html");
    }

    @Step("Открытие страницы CheckOut:Overview")
    public void openPageCheckOutOverview() {
        log.info("Open page CheckOut:Overview");
        driver.get("https://www.saucedemo.com/checkout-step-two.html");
    }

    @Step("Нажатие на клавишу Continue")
    public void clickToButtonContinue() {
        log.info("CLick to button Continue");
        driver.findElement(CONTINUE_BUTTON).click();
    }

    @Step("Ввод всех данных покупателя: Имя {firstName},Фамилия {lastname},ZipCode {zipCode}")
    public void inputFullInfo(String firstName, String lastName, String zipCode) {
        log.info("Input info: First name '{}', Last name '{}', Zipcode '{}'",firstName,lastName,zipCode);
        driver.findElement(FIRST_NAME_INPUT).sendKeys(firstName);
        driver.findElement(LAST_NAME_INPUT).sendKeys(lastName);
        driver.findElement(ZIP_Postal_CODE).sendKeys(zipCode);
    }

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
}
