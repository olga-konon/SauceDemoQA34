package tests;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorsTest extends BaseTest {

    @Test
    public void checkLocators() {
        driver.get("https://www.saucedemo.com/");
        // id -> finds element by unique id
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        // name -> finds element by name attribute
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        // className -> finds element by class name
        driver.findElement(By.className("submit-button")).click();
        // tagName -> finds first element with given tag
        driver.findElement(By.tagName("div"));
        // linkText -> finds link by exact visible text
        driver.findElement(By.linkText("Sauce Labs Backpack"));
        // partialLinkText -> finds link by partial visible text
        driver.findElement(By.partialLinkText("Fleece Jacket"));
        // XPath by attribute -> exact attribute match
        driver.findElement(By.xpath("//div[@id='shopping_cart_container']"));
        // XPath by text -> exact visible text
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        // XPath contains attribute -> partial attribute match
        driver.findElement(By.xpath("//img[contains(@alt,'Sauce')]"));
        // XPath contains text -> partial text match
        driver.findElement(By.xpath("//div[contains(text(),'Sauce')]"));
        // ancestor -> goes up to matching parent/ancestor
        driver.findElement(By.xpath("//*[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item'][1]"));
        // descendant -> goes down to child elements
        driver.findElement(By.xpath("//*[@class='footer']/descendant::ul"));
        // following -> finds element after current element
        driver.findElement(By.xpath("//*[@class='social_twitter']/following::li"));
        // parent -> goes up one direct level
        driver.findElement(By.xpath("//*[@class='social_twitter']/parent::ul"));
        // preceding -> finds element before current element
        driver.findElement(By.xpath("//*[@class='social_linkedin']/preceding::li"));
        // AND -> checks multiple conditions in one XPath
        driver.findElement(By.xpath("//div[@class='inventory_item_name ' and text()='Test.allTheThings() T-Shirt (Red)']"));
        // .class -> finds element by class
        driver.findElement(By.cssSelector(".page_wrapper"));
        // .class -> finds element by class
        driver.findElement(By.cssSelector(".inventory_item_name"));
        // .class1.class2 -> same element must contain both classes
        driver.findElement(By.cssSelector(".btn.btn_primary"));
        // #id -> finds element by id
        driver.findElement(By.cssSelector("#page_wrapper"));
        // tagname -> finds element by tag
        driver.findElement(By.cssSelector("body"));
        // tagname.class -> finds tag with class
        driver.findElement(By.cssSelector("div.page_wrapper"));
        // [attribute=value] -> exact attribute match
        driver.findElement(By.cssSelector("div[id='page_wrapper']"));
        // [attribute~=value] -> attribute contains whole word
        driver.findElement(By.cssSelector("img[alt~='Open']"));
        // [attribute|=value] -> exact value or value starting with value-
        driver.findElement(By.cssSelector("button[id|='add-to-cart']"));
        // [attribute^=value] -> attribute starts with value
        driver.findElement(By.cssSelector("button[id^='add-t']"));
        // [attribute$=value] -> attribute ends with value
        driver.findElement(By.cssSelector("button[id$='backpack']"));
        // [attribute*=value] -> attribute contains value
        driver.findElement(By.cssSelector("button[id*='backpa']"));
    }
}
