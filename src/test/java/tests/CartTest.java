package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test(testName = "Multiple items added to cart",
            groups = "regression",
            description = "Multiple items added to cart")
    public void checkMultipleItemsAddedToCart() {

        // Login in
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        // Add items to cart
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Test.allTheThings() T-Shirt (Red)");
        productsPage.addToCart("Sauce Labs Onesie");

        //Open Cart and click Checkout
        productsPage.clickCart();
        // Verify that multiple items are added to the cart
        Assert.assertEquals(cartPage.getCartSize(), 3, "Not all items were added");
    }
}
