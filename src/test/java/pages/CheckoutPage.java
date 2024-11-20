package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public String getProductName(String product) {
        By xPathOfProductName = By.xpath(String.format(PATTERN_PRODUCT_NAME, product));
        return driver.findElement(xPathOfProductName).getText();
    }

    public String getProductPrice(String product) {
        By xPathOfProductPrice = By.xpath(String.format(PATTERN_PRODUCT_PRICE, product));
        return driver.findElement(xPathOfProductPrice).getText();
    }

    public String getTotalPrice() {
        String totalText = driver.findElement(TOTAL_PRODUCT_PRICE).getText();
        return totalText.replace("Total: $","");
    }

    public String getTitleOverview() {
        return driver.findElement(TITLE_OVERVIEW).getText();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public void openPageCheckOutInfo() {
        driver.get("https://www.saucedemo.com/checkout-step-one.html");
    }

    public void openPageCheckOutOverview() {
        driver.get("https://www.saucedemo.com/checkout-step-two.html");
    }

    public void clickToButtonContinue() {
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public void inputFullInfo(String firsName, String lastname, String zipcode) {
        driver.findElement(FIRST_NAME_INPUT).sendKeys(firsName);
        driver.findElement(LAST_NAME_INPUT).sendKeys(lastname);
        driver.findElement(ZIP_Postal_CODE).sendKeys(zipcode);
    }

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
}
