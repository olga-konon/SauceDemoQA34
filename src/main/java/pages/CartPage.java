package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log4j2
public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private final By CHECKOUT_BUTTON = By.cssSelector("[data-test='checkout']");
    private final By CART_ITEMS = By.cssSelector("[data-test='inventory-item']");
    private final By ITEM_NAME = By.cssSelector("[data-test='inventory-item-name']");
    private final By ITEM_PRICE = By.cssSelector("[data-test='inventory-item-price']");
    private final By REMOVE_BUTTON = By.tagName("button");

    @Override
    public CartPage isPageOpened() {
        log.info("Wait until Cart page is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_BUTTON));
        return this;
    }

    @Step("Click Checkout button")
    public CheckoutPage clickCheckoutButton() {
        log.info("Click Checkout button");
        driver.findElement(CHECKOUT_BUTTON).click();
        return new CheckoutPage(driver);
    }

    public List<WebElement> getCartItems() {
        List<WebElement> cartItems = driver.findElements(CART_ITEMS);

        for (WebElement item : cartItems) {
            String name = item.findElement(ITEM_NAME).getText();
            String priceText = item.findElement(ITEM_PRICE).getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            String button = item.findElement(REMOVE_BUTTON).getText();

        }
        return cartItems;
    }

    @Step("Get Cart size")
    public int getCartSize() {
        int cartSize = getCartItems().size();
        log.info("Cart size: {}", cartSize);
        return cartSize;
    }
}
