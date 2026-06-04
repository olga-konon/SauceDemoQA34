package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import io.qameta.allure.testng.AllureTestNg;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureTestNg.class, TestListener.class})

public class LoginTest extends BaseTest {

    @Test(
            testName = "Check Login with Positive cred",
            groups = "smoke",
            description = "Checks login with valid username and password")

    @Epic("Sauce Demo")
    @Feature("Log in")
    @Flaky
    public void checkLoginWithPositiveCred(ITestContext iTestContext) {
        loginPage.open()
                .isPageOpened()
                .login(user, password)
                .isPageOpened();
        Assert.assertEquals(productsPage.getTitle(), "Products", "User didn't login");
    }

    @Test(
            testName = "Check Login with empty userName",
            groups = "regression",
            description = "Checks login with empty username and valid password")
    @Epic("Sauce Demo")
    @Feature("Log in")
    public void checkLoginWithEmptyUserName() {
        loginPage.open()
                .isPageOpened()
                .loginWithNotValidCred("", password)
                .isPageOpened();
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required", "Username is required");
    }

    @Test(testName = "Check Login with empty password",
            groups = "regression",
            description = "Checks login with valid username and empty password")
    @Epic("Sauce Demo")
    @Feature("Log in")
    public void checkLoginWithEmptyPassword() {
        loginPage.open()
                .isPageOpened()
                .loginWithNotValidCred(user, "")
                .isPageOpened();
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required", "Password is required");
    }

    @DataProvider(name = "test")
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "test", testName = "Check Login with not valid cred",
            groups = "regression",
            description = "Checks login with invalid username and invalid password")
    @Epic("Sauce Demo")
    @Feature("Log in")
    public void checkLoginWithNotValidCred(String user, String password, String errorMessage) {
        loginPage.open()
                .isPageOpened()
                .loginWithNotValidCred(user, password)
                .isPageOpened();
        Assert.assertEquals(loginPage.getErrorMessage(), errorMessage, "Username and password do not match any user in this service");
    }
}
