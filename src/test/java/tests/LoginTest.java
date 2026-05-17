package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(
            testName = "Check Login with Positive cred",
            groups = "smoke",
            description = "Checks login with valid username and password")
    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.getTitle(), "Products", "User didn't login");
    }

    @Test(
            testName = "Check Login with empty userName",
            groups = "regression",
            description = "Checks login with empty username and valid password")
    public void checkLoginWithEmptyUserName() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required", "Username is required");
    }

    @Test(testName = "Check Login with empty password",
            groups = "regression",
            description = "Checks login with valid username and empty password")
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
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
    public void checkLoginWithNotValidCred(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        Assert.assertEquals(loginPage.getErrorMessage(), errorMessage, "Username and password do not match any user in this service");
    }
}