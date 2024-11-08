import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LocatorTest extends BaseTest{

    @Test
    public void locatorTest() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test = login-button]")).click();

        WebElement elementName = driver.findElement(By.name("add-to-cart-sauce-labs-backpack"));
        WebElement elementClass = driver.findElement(By.className("footer_copy"));
        WebElement elementTagName = driver.findElement(By.tagName("span"));
        WebElement elementLinkText = driver.findElement(By.linkText("Sauce Labs Onesie"));
        WebElement elementPartialLinkText = driver.findElement(By.partialLinkText("(Red)"));
        // Xpath
        WebElement elementAttrXp = driver.findElement(By.xpath("//a[@data-test = 'item-1-title-link']"));
        WebElement elementTextXp = driver.findElement(By.xpath("//div[text() = 'Sauce Labs Backpack']"));
        WebElement elementPartTextXp = driver.findElement(By.xpath(
                "//div[contains(text(), 'Fleece Jacket')]"));
        WebElement elementPartAttrXp = driver.findElement(By.xpath(
                "//a[contains(@data-test, 'item-0-img-link')]"));
        WebElement elementAncestorXp = driver.findElement(By.xpath(
                "//a[@data-test = 'item-4-title-link']" +
                "/ancestor::div[@class = 'inventory_item']//button"));
        WebElement elementDescButton = driver.findElement(By.xpath(
                "//div[@class='inventory_item']//" +
                        "descendant::button[@data-test = 'add-to-cart-sauce-labs-bolt-t-shirt']"));
        WebElement elementFollowing = driver.findElement(By.xpath(
                "//button[@id = 'add-to-cart-sauce-labs-bolt-t-shirt']/following::div"));
        WebElement elementParent = driver.findElement(By.xpath(
                "//button[@id = 'add-to-cart-sauce-labs-bolt-t-shirt']/parent::div"));
        WebElement elementPreceding = driver.findElement(By.xpath(
                "//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']/preceding::div"));
        WebElement elementAND = driver.findElement(By.xpath(
                "//button[@data-test='add-to-cart-sauce-labs-bolt-t-shirt' " +
                        "and @name='add-to-cart-sauce-labs-bolt-t-shirt']"));
        //CSS
        WebElement cssClassElement = driver.findElement(By.cssSelector(".app_logo"));
        WebElement cssIdElement = driver.findElement(By.cssSelector("#menu_button_container"));
        WebElement cssClass1Class2Element = driver.findElement(By.cssSelector(".btn.btn_primary"));
        WebElement cssClass1and2 = driver.findElement(By.cssSelector(".pricebar .inventory_item_price"));
        WebElement cssTagName = driver.findElement(By.cssSelector("button"));
        WebElement cssTagClass = driver.findElement(By.cssSelector("div.footer_copy"));
        WebElement cssAttrValue1 = driver.findElement(By.cssSelector("[data-test = item-2-title-link]"));
        WebElement cssAttrValue2 = driver.findElement(By.cssSelector("[data-test ~= 'inventory-item-price']"));
        WebElement cssAttrValue3 = driver.findElement(By.cssSelector("[data-test |= 'inventory']"));
        WebElement cssAttrValue4 = driver.findElement(By.cssSelector("[class ^= 'footer']"));
        WebElement cssAttrValue5 = driver.findElement(By.cssSelector("[href $= '.com/saucelabs']"));
        WebElement cssAttrValue6 = driver.findElement(By.cssSelector("[data-test *= 'social']"));
    }
}
