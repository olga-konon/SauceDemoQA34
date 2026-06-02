package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Log4j2
public class CheckoutTest extends BaseTest {

    @Test(
            testName = "Check Checkout with valid data",
            groups = "smoke",
            description = "Checks checkout with not empty cart and valid firstName, lastName, zipCode")
    @Epic("Sauce Demo")
    @Feature("Checkout")
    public void checkCheckoutWithPositiveUserData() {

        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .addToCart("Sauce Labs Backpack")
                .addToCart("Test.allTheThings() T-Shirt (Red)")
                .clickCart()
                .isPageOpened()
                .clickCheckoutButton()
                .isPageOpened()
                .fillCheckoutInformation("Volha", "Konan", "00-849")
                .clickContinueButton()
                .isPageOpened();

        // Verify that user on Checkout Overview page
        Assert.assertEquals(checkoutPage.getTitle(), "Checkout: Overview", "Overview page was not opened");
        checkoutPage.clickFinishButton();

        // Verify that user on Checkout Complete page
        Assert.assertEquals(checkoutPage.getTitle(), "Checkout: Complete!", "User didn't checkout");
    }

    @Test(testName = "Check subtotal Price equals to sum of items",
            groups = "regression",
            description = "Check subtotal Price equals to sum of items")
    @Epic("Sauce Demo")
    @Feature("Checkout")
    public void checkSubtotalPriceEqualsToSumOfItems() {

        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .addToCart("Sauce Labs Backpack")
                .addToCart("Test.allTheThings() T-Shirt (Red)")
                .clickCart()
                .isPageOpened()
                .clickCheckoutButton()
                .isPageOpened()
                .fillCheckoutInformation("Volha", "Konan", "00-849")
                .clickContinueButton()
                .isPageOpened();
        // Verify that Item total (subtotal) equals to sum of added items
        Assert.assertEquals(checkoutPage.calculateItemsSum(), checkoutPage.getSubtotalPrice(), "Sum of items is not equal");
    }

    @Test(testName = "Check total price equals subtotal plus tax",
            groups = "regression",
            description = "Check total price equals subtotal plus tax")
    @Epic("Sauce Demo")
    @Feature("Checkout")
    public void checkTotalPriceEqualsSubtotalPlusTax() {

        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .addToCart("Sauce Labs Backpack")
                .addToCart("Test.allTheThings() T-Shirt (Red)")
                .clickCart()
                .isPageOpened()
                .clickCheckoutButton()
                .isPageOpened()
                .fillCheckoutInformation("Volha", "Konan", "00-849")
                .clickContinueButton()
                .isPageOpened();
        //  Verify that total equals to sum Item total (subtotal) + tax
        Assert.assertEquals(checkoutPage.getTotalPrice(), checkoutPage.calculateTotalSum(), 0.01, "TotalSum is not equal");
    }

    @Test(testName = "Check checkout with empty user data",
            groups = "regression",
            description = "Check checkout with empty user data")
    @Epic("Sauce Demo")
    @Feature("Checkout")
    public void checkCheckoutWithEmptyUserData() {

        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .addToCart("Sauce Labs Backpack")
                .addToCart("Test.allTheThings() T-Shirt (Red)")
                .clickCart()
                .isPageOpened()
                .clickCheckoutButton()
                .isPageOpened()
                .clickContinueButton()
                .isPageOpened();

        // Verify that user with empty user data cannot continue and it throws an error
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required", "User data is missing");
    }

    @Test(testName = "Check checkout with empty cart",
            groups = "regression",
            description = "Check checkout with cart")
    @Epic("Sauce Demo")
    @Feature("Checkout")
    public void checkCheckoutWithEmptyCart() {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .clickCart()
                .isPageOpened()
                .clickCheckoutButton()
                .isPageOpened()
                .fillCheckoutInformation("Volha", "Konan", "00-849")
                .clickContinueButton()
                .isPageOpened();

        // Verify that Item total (subtotal) equals 0.0, if no items added
        Assert.assertEquals(checkoutPage.calculateItemsSum(), 0.0, 0.01, "Cart is empty");
    }
}
