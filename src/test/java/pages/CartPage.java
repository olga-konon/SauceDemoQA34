package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage  extends BasePage{

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private final By CART_ITEMS = By.cssSelector("[data-test='inventory-item']");
    private final By ITEM_NAME = By.cssSelector("[data-test='inventory-item-name']");
    private final By ITEM_PRICE = By.cssSelector("[data-test='inventory-item-price']");
    private final By REMOVE_BUTTON = By.tagName("button");

    public  List<WebElement>  getCartItems() {
        List<WebElement> cartItems = driver.findElements(CART_ITEMS);

        for (WebElement item : cartItems) {
            String name = item.findElement(ITEM_NAME).getText();
            String priceText = item.findElement(ITEM_PRICE).getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            String button = item.findElement(REMOVE_BUTTON).getText();

        }
        return cartItems;
    }

    public int getCartSize() {
        return getCartItems().size();
    }
}
