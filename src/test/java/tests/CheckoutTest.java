package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @BeforeMethod (alwaysRun = true)
    public void openCheckoutPage() {
        // Login in
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(
            testName = "Check Checkout with valid data",
            groups = "smoke",
            description = "Checks checkout with not empty cart and valid firstName, lastName, zipCode")
    public void checkCheckoutWithPositiveUserData() {
        // Add items to cart
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Test.allTheThings() T-Shirt (Red)");

        //Open Cart and click Checkout
        productsPage.clickCart();
        checkoutPage.clickCheckoutButton();

        // Fill in User data and click Continue
        checkoutPage.fillCheckoutInformation("Volha", "Konan", "00-849");
        checkoutPage.clickContinueButton();

        // Verify that user on Checkout Overview page
        Assert.assertEquals(checkoutPage.getTitle(), "Checkout: Overview", "Overview page was not opened");
        checkoutPage.clickFinishButton();

        // Verify that user on Checkout Complete page
        Assert.assertEquals(checkoutPage.getTitle(), "Checkout: Complete!", "User didn't checkout");
    }

    @Test(testName = "Check subtotal Price equals to sum of items",
            groups = "regression",
            description = "Check subtotal Price equals to sum of items")
    public void checkSubtotalPriceEqualsToSumOfItems() {
        // Add items to cart
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Test.allTheThings() T-Shirt (Red)");

        //Open Cart and click Checkout
        productsPage.clickCart();
        checkoutPage.clickCheckoutButton();

        // Fill in User data and click Continue
        checkoutPage.fillCheckoutInformation("Volha", "Konan", "00-849");
        checkoutPage.clickContinueButton();
        // Verify that Item total (subtotal) equals to sum of added items
        Assert.assertEquals(checkoutPage.calculateItemsSum(), checkoutPage.getSubtotalPrice(), "Sum of items is not equal");
    }

    @Test(testName = "Check total price equals subtotal plus tax",
            groups = "regression",
            description = "Check total price equals subtotal plus tax")
    public void checkTotalPriceEqualsSubtotalPlusTax() {
        // Add items to cart
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Test.allTheThings() T-Shirt (Red)");

        //Open Cart and click Checkout
        productsPage.clickCart();
        checkoutPage.clickCheckoutButton();

        // Fill in User data and click Continue
        checkoutPage.fillCheckoutInformation("Volha", "Konan", "00-849");
        checkoutPage.clickContinueButton();

        //  Verify that total equals to sum Item total (subtotal) + tax
        Assert.assertEquals(checkoutPage.getTotalPrice(), checkoutPage.calculateTotalSum(), 0.01, "TotalSum is not equal");
    }

    @Test(testName = "Check checkout with empty user data",
            groups = "regression",
            description = "Check checkout with empty user data")
    public void checkCheckoutWithEmptyUserData() {
        // Add items to cart
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Test.allTheThings() T-Shirt (Red)");

        //Open Cart and click Checkout
        productsPage.clickCart();
        checkoutPage.clickCheckoutButton();

        //  Leave empty user data form and click Continue
        checkoutPage.clickContinueButton();

        // Verify that user with empty user data cannot continue and it throws an error
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required", "User data is missing");
    }

    @Test(testName = "Check checkout with empty cart",
            groups = "regression",
            description = "Check checkout with cart")
    public void checkCheckoutWithEmptyCart() {
        //Open Cart and click Checkout
        productsPage.clickCart();
        checkoutPage.clickCheckoutButton();

        // Fill in User data and click Continue
        checkoutPage.fillCheckoutInformation("Volha", "Konan", "00-849");
        checkoutPage.clickContinueButton();

        // Verify that Item total (subtotal) equals 0.0, if no items added
        Assert.assertEquals(checkoutPage.calculateItemsSum(), 0.0, 0.01, "Cart is empty");
    }
}
