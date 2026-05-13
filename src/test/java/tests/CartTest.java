package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.List;

public class CartTest extends BaseTest {

    String expectedProductName = "Sauce Labs Backpack";
    String expectedPriceOfProduct = "29.99";

    @Test
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
    

