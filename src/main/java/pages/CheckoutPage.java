package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // Checkout: Your Information
    private final By FIRSTNAME_FIELD = By.cssSelector("[data-test='firstName']");
    private final By LASTNAME_FIELD = By.cssSelector("[data-test='lastName']");
    private final By ZIPCODE_FIELD = By.cssSelector("[data-test='postalCode']");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");
    private final By CONTINUE_BUTTON = By.cssSelector("[data-test='continue']");
    // Checkout: Overview
    private final By FINISH_BUTTON = By.cssSelector("[data-test='finish']");
    private final By ITEM_PRICE = By.cssSelector("[data-test='inventory-item-price']");
    private final By SUBTOTAL_PRICE = By.cssSelector("[data-test='subtotal-label']");
    private final By TAX_PRICE = By.cssSelector("[data-test='tax-label']");
    private final By TOTAL_PRICE = By.cssSelector("[data-test='total-label']");
    // Checkout: Complete!
    private final By TITLE = By.cssSelector("[data-test='title']");

    @Override
    public CheckoutPage isPageOpened() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(FIRSTNAME_FIELD),
                ExpectedConditions.visibilityOfElementLocated(FINISH_BUTTON),
                ExpectedConditions.visibilityOfElementLocated(TITLE)
        ));
        return this;
    }

    @Step("Enter: '{firstName}'")
    public CheckoutPage enterFirstName(String firstName) {
        driver.findElement(FIRSTNAME_FIELD).sendKeys(firstName);
        return this;
    }

    @Step("Enter: '{lastName}'")
    public CheckoutPage enterLastName(String lastName) {
        driver.findElement(LASTNAME_FIELD).sendKeys(lastName);
        return this;
    }

    @Step("Enter: '{zipCode}'")
    public CheckoutPage enterZipCode(String zipCode) {
        driver.findElement(ZIPCODE_FIELD).sendKeys(zipCode);
        return this;
    }

    @Step("Fill сheckout information'")
    public CheckoutPage fillCheckoutInformation(String firstName, String lastName, String zipCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zipCode);
        return this;
    }

    @Step("Get error message")
    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    @Step("Click Continue")
    public CheckoutPage clickContinueButton() {
        driver.findElement(CONTINUE_BUTTON).click();
        return this;
    }

    @Step("Click Finish")
    public CheckoutPage clickFinishButton() {
        driver.findElement(FINISH_BUTTON).click();
        return this;
    }

    @Step("Get item title")
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Calculate items subtotal")
    public double calculateItemsSum() {
        double sum = 0.0;

        List<WebElement> prices = driver.findElements(ITEM_PRICE);

        for (WebElement price : prices) {
            String text = price.getText();
            sum += Double.parseDouble(text.replaceAll("[^0-9.]", ""));
        }

        return sum;
    }

    @Step("Get subtotal price")
    public double getSubtotalPrice() {
        String text = driver.findElement(SUBTOTAL_PRICE).getText();
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }

    @Step("Get Tax price")
    public double getTaxPrice() {
        String text = driver.findElement(TAX_PRICE).getText();
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }

    @Step("Get Total sum")
    public double getTotalPrice() {
        String text = driver.findElement(TOTAL_PRICE).getText();
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }

    @Step("Calculate total sum")
    public double calculateTotalSum() {
        return getSubtotalPrice() + getTaxPrice();
    }
}
