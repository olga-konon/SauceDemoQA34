package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class LoginPage extends BasePage {

    private final By USERNAME_FIELD = By.cssSelector("[data-test='username']");
    private final By PASSWORD_FIELD = By.name("password");
    private final By LOGIN_BUTTON = By.className("submit-button");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open login page")
    public LoginPage open() {
        driver.get("https://www.saucedemo.com/");
        return this;
    }

    @Step("Login page is opened")
    public LoginPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return this;
    }

    @Step("Login with username: '{user}' and password '{password}'")
    public ProductsPage login(String user, String password) {
        log.info("Login with user: {}", user);
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver);
    }

    @Step("Login with username: '{user}' and password '{password}'")
    public LoginPage loginWithNotValidCred(String user, String password) {
        log.info("Try login with invalid user: {}", user);
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return this;
    }

    @Step("Get error message")
    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
