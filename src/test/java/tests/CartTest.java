package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductsPage;

@Log4j2

public class CartTest extends BaseTest {

    @Test(testName = "Multiple items added to cart",
            groups = "regression",
            description = "Multiple items added to cart")
    @Epic("Sauce Demo")
    @Feature("Cart")

    public void checkMultipleItemsAddedToCart() {

        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .addToCart("Sauce Labs Backpack")
                .addToCart("Test.allTheThings() T-Shirt (Red)")
                .addToCart("Sauce Labs Onesie")
                .clickCart()
                .isPageOpened();

        Assert.assertEquals(cartPage.getCartSize(), 3, "Not all items were added");
    }
}
