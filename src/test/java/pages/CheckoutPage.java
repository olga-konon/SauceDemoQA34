package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private final By CHECKOUT_BUTTON = By.cssSelector("[data-test='checkout']");
    // Checkout: Your Information
    private final By FIRSTNAME_FIELD = By.cssSelector("[data-test='firstName']");
    private final By LASTNAME_FIELD = By.cssSelector("[data-test='lastName']");
    private final By ZIPCODE_FIELD = By.cssSelector("[data-test='postalCode']");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");
    private final By CANCEL_BUTTON = By.cssSelector("[data-test='cancel']");
    private final By CONTINUE_BUTTON = By.cssSelector("[data-test='continue']");
    // Checkout: Overview
    private final By FINISH_BUTTON = By.cssSelector("[data-test='finish']");
    private final By ITEM_PRICE = By.cssSelector("[data-test='inventory-item-price']");
    private final By SUBTOTAL_PRICE = By.cssSelector("[data-test='subtotal-label']");
    private final By TAX_PRICE = By.cssSelector("[data-test='tax-label']");
    private final By TOTAL_PRICE = By.cssSelector("[data-test='total-label']");
    // Checkout: Complete!
    private final By TITLE = By.cssSelector("[data-test='title']");
    private final By BACKHOME_BUTTON = By.cssSelector("[data-test='back-to-products']");

    public void clickCheckoutButton() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public void enterFirstName(String firstName) {
        driver.findElement(FIRSTNAME_FIELD).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(LASTNAME_FIELD).sendKeys(lastName);
    }

    public void enterZipCode(String zipCode) {
        driver.findElement(ZIPCODE_FIELD).sendKeys(zipCode);
    }

    public void fillCheckoutInformation(String firstName, String lastName, String zipCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zipCode);
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public void clickContinueButton() {
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public void clickCancelButton() {
        driver.findElement(CANCEL_BUTTON).click();
    }

    public void clickFinishButton() {
        driver.findElement(FINISH_BUTTON).click();
    }

    public void clickBackHomeButton() {
        driver.findElement(BACKHOME_BUTTON).click();
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public double getItemPrice() {
        String text = driver.findElement(ITEM_PRICE).getText();
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }

    public double calculateItemsSum() {
        double sum = 0.0;

        List<WebElement> prices = driver.findElements(ITEM_PRICE);

        for (WebElement price : prices) {
            String text = price.getText();
            sum += Double.parseDouble(text.replaceAll("[^0-9.]", ""));
        }

        return sum;
    }

    public double getSubtotalPrice() {
        String text = driver.findElement(SUBTOTAL_PRICE).getText();
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }

    public double getTaxPrice() {
        String text = driver.findElement(TAX_PRICE).getText();
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }

    public double getTotalPrice() {
        String text = driver.findElement(TOTAL_PRICE).getText();
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }

    public double calculateTotalSum() {
        return getSubtotalPrice() + getTaxPrice();
    }
}
