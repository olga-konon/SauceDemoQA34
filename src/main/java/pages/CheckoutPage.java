package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log4j2
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
        log.info("Wait until Checkout page is opened");
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

    @Step("Fill сheckout information")
    public CheckoutPage fillCheckoutInformation(String firstName, String lastName, String zipCode) {
        log.info("Fill checkout information for user: {} {} ", firstName, lastName);
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zipCode);
        return this;
    }

    @Step("Get error message")
    public String getErrorMessage() {
        String error = driver.findElement(ERROR_MESSAGE).getText();
        log.info("Read checkout error message: {}", error);
        return error;
    }

    @Step("Click Continue")
    public CheckoutPage clickContinueButton() {
        log.info("Click Continue button");
        driver.findElement(CONTINUE_BUTTON).click();
        return this;
    }

    @Step("Click Finish")
    public CheckoutPage clickFinishButton() {
        log.info("Click Finish button");
        driver.findElement(FINISH_BUTTON).click();
        return this;
    }

    @Step("Get item title")
    public String getTitle() {
        String title = driver.findElement(TITLE).getText();
        log.info("Read checkout title: {}", title);
        return title;
    }

    @Step("Calculate items subtotal")
    public double calculateItemsSum() {
        double sum = 0.0;

        List<WebElement> prices = driver.findElements(ITEM_PRICE);

        for (WebElement price : prices) {
            String text = price.getText();
            sum += Double.parseDouble(text.replaceAll("[^0-9.]", ""));
        }
        log.info("Calculated items sum: {}", sum);

        return sum;
    }

    @Step("Get subtotal price")
    public double getSubtotalPrice() {
        double subtotal = Double.parseDouble(driver.findElement(SUBTOTAL_PRICE).getText().replaceAll("[^0-9.]", ""));
        log.info("Read subtotal price: {}", subtotal);
        return subtotal;
    }

    @Step("Get Tax price")
    public double getTaxPrice() {
        double tax = Double.parseDouble(driver.findElement(TAX_PRICE).getText().replaceAll("[^0-9.]", ""));
        log.info("Read tax price: {}", tax);
        return tax;

    }

    @Step("Get Total sum")
    public double getTotalPrice() {
        double total = Double.parseDouble(driver.findElement(TOTAL_PRICE).getText().replaceAll("[^0-9.]", ""));
        log.info("Read total price: {}", total);
        return total;
    }

    @Step("Calculate total sum")
    public double calculateTotalSum() {
        double total = getSubtotalPrice() + getTaxPrice();
        log.info("Calculated total sum: {}", total);
        return total;
    }
}
