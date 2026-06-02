package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test='title']");
    private final String ADD_TO_CART_PATTERN = "//*[text()='%s']//ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    private final By CART = By.cssSelector("[data-test = shopping-cart-link]");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get title")
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Login page is opened")
    public ProductsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    @Step("Add to cart: '{product}'")
    public ProductsPage addToCart(String product) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        return new ProductsPage(driver);
    }

    @Step("Click Cart button")
    public CartPage clickCart() {
        driver.findElement(CART).click();
        return new CartPage(driver);
    }
}
