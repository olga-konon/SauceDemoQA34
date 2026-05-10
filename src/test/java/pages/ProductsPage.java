package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test='title']");
    private final String ADD_TO_CART_PATTERN="//*[text()='%s']//ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    private final By CART = By.cssSelector("[data-test = shopping-cart-link]");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public void addToCart(String product) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
    }

    public void clickCart() {
        driver.findElement(CART).click();
    }
}

